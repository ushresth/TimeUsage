package com.startup.android.timeusage.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.startup.android.timeusage.database.LogDbSchema.LogTable;
import com.startup.android.timeusage.database.LogDbSchema.SettingTable;

/**
 * Created by Utsah on 10/12/2017.
 */

public class LogBaseHelper extends SQLiteOpenHelper {
        private static final int VERSION = 1;
        private static final String DATABASE_NAME = "logBase.db";

        public LogBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + LogTable.NAME + "(" +
                    " _id integer primary key autoincrement, " +
                    LogTable.Cols.UUID + ", " +
                    LogTable.Cols.TITLE + ", " +
                    LogTable.Cols.COMMENT + "," +
                    LogTable.Cols.DATE + "," +
                    LogTable.Cols.ACTIVITY_TYPE + "," +
                    LogTable.Cols.DURATION + "," +
                    LogTable.Cols.PLACE +
                    ")"
            );

            db.execSQL("create table " + SettingTable.NAME + "(" +
                    SettingTable.Cols.NAME + ", " +
                    SettingTable.Cols.ID + ", " +
                    SettingTable.Cols.EMAIL + ", " +
                    SettingTable.Cols.GENDER + ", " +
                    SettingTable.Cols.COMMENT +
                    ")"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
}
