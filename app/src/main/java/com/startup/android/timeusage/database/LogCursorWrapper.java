package com.startup.android.timeusage.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.startup.android.timeusage.Log;
import com.startup.android.timeusage.Setting;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Utsah on 10/12/2017.
 */

public class LogCursorWrapper extends CursorWrapper {
    public LogCursorWrapper(Cursor cursor)
    {
        super(cursor);
    }

    public Log getLog() {
        String uuidString = getString(getColumnIndex(LogDbSchema.LogTable.Cols.UUID));
        String title = getString(getColumnIndex(LogDbSchema.LogTable.Cols.TITLE));
        String comment = getString(getColumnIndex(LogDbSchema.LogTable.Cols.COMMENT));
        long date = getLong(getColumnIndex(LogDbSchema.LogTable.Cols.DATE));
        String activitytype = getString(getColumnIndex(LogDbSchema.LogTable.Cols.ACTIVITY_TYPE));
        String duration = getString(getColumnIndex(LogDbSchema.LogTable.Cols.DURATION));
        String place = getString(getColumnIndex(LogDbSchema.LogTable.Cols.PLACE));

        Log log = new Log(UUID.fromString(uuidString));
        log.setTitle(title);
        log.setComment(comment);
        log.setDate(new Date(date));
        log.setActivityType(activitytype);
        log.setDuration(duration);
        log.setPlace(place);

        return log;
    }

    public Setting getSetting() {
        String name = getString(getColumnIndex(LogDbSchema.SettingTable.Cols.NAME));
        String id = getString(getColumnIndex(LogDbSchema.SettingTable.Cols.ID));
        String email = getString(getColumnIndex(LogDbSchema.SettingTable.Cols.EMAIL));
        String gender = getString(getColumnIndex(LogDbSchema.SettingTable.Cols.GENDER));
        String comment = getString(getColumnIndex(LogDbSchema.SettingTable.Cols.COMMENT));

        Setting setting = new Setting();
        setting.setName(name);
        setting.setId(id);
        setting.setEmail(email);
        setting.setGender(gender);
        setting.setComment(comment);

        return setting;
    }
}
