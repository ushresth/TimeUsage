package com.startup.android.timeusage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.startup.android.timeusage.database.LogBaseHelper;
import com.startup.android.timeusage.database.LogCursorWrapper;
import com.startup.android.timeusage.database.LogDbSchema;
import com.startup.android.timeusage.database.LogDbSchema.LogTable;
import com.startup.android.timeusage.database.LogDbSchema.SettingTable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Utsah on 10/7/2017.
 */

public class LogLab {
    private static LogLab sLogLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static LogLab get(Context context) {
        if (sLogLab == null) {
            sLogLab = new LogLab(context);
        }
        return sLogLab;
    }

    private LogLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new LogBaseHelper(mContext)
                .getWritableDatabase();
    }

    public void addLog(Log l) {
        ContentValues values = getContentValues(l);

        mDatabase.insert(LogTable.NAME, null, values);
    }

    public List<Log> getLogs() {
        List<Log> logs = new ArrayList<>();

        LogCursorWrapper cursor = queryLogs(null, null);
       // try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                logs.add(cursor.getLog());
                cursor.moveToNext();
            }
       // } finally {
            cursor.close();
       // }

        return logs;
    }

    public Log getLog(UUID id) {
        LogCursorWrapper cursor = queryLogs(
                LogTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getLog();
        } finally {
            cursor.close();
        }
    }

    public File getPhotoFile(Log log) {
        File externalFilesDir = mContext
                .getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        if (externalFilesDir == null) {
            return null;
        }

        return new File(externalFilesDir, log.getPhotoFilename());
    }

    public void updateLog(Log log) {
        String uuidString = log.getId().toString();
        ContentValues values = getContentValues(log);

        mDatabase.update(LogTable.NAME, values,
                LogTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    public void deleteLog(Log log) {
        String uuidString = log.getId().toString();
        ContentValues values = getContentValues(log);

        mDatabase.delete(LogTable.NAME, LogTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private static ContentValues getContentValues(Log log) {
        ContentValues values = new ContentValues();
        values.put(LogTable.Cols.UUID, log.getId().toString());
        values.put(LogTable.Cols.TITLE, log.getTitle());
        values.put(LogTable.Cols.COMMENT, log.getComment());
        values.put(LogTable.Cols.DATE, log.getDate().getTime());
        values.put(LogTable.Cols.ACTIVITY_TYPE, log.getActivityType());
        values.put(LogTable.Cols.DURATION, log.getDuration());
        values.put(LogTable.Cols.PLACE, log.getPlace());

        return values;
    }

    private LogCursorWrapper queryLogs(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                LogTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new LogCursorWrapper(cursor);
    }

    private ContentValues getSettingValues(Setting setting) {
        ContentValues values = new ContentValues();

        values.put(SettingTable.Cols.NAME, setting.getName());
        values.put(SettingTable.Cols.ID, setting.getId());
        values.put(SettingTable.Cols.EMAIL, setting.getEmail());
        values.put(SettingTable.Cols.GENDER, setting.getGender());
        values.put(SettingTable.Cols.COMMENT, setting.getComment());

        return values;
    }

    public void updateSetting(Setting setting) {
        ContentValues values = getSettingValues(setting);
        String id = setting.getId();
        mDatabase.update(SettingTable.NAME, values,
                SettingTable.Cols.ID + " = ?",
                new String[]{id});
    }

    private LogCursorWrapper querySetting() {
        Cursor cursor = mDatabase.query(
                SettingTable.NAME,
                null, // Columns - null selects all columns
                null,
                null,
                null, // groupBy
                null, // having
                null  // orderBy
        );

        return new LogCursorWrapper(cursor);
    }

    public Setting getSetting() {
        LogCursorWrapper cursor = querySetting();

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getSetting();
        }
        finally {
            cursor.close();
        }
    }
}
