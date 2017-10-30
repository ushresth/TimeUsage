package com.startup.android.timeusage.database;

/**
 * Created by Utsah on 10/12/2017.
 */

public class LogDbSchema {
    public static final class LogTable {
        public static final String NAME = "logs";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String COMMENT = "comment";
            public static final String DATE = "date";
            public static final String ACTIVITY_TYPE = "activity_type";
            public static final String DURATION = "duration";
            public static final String PLACE = "place";
        }
    }

    public static final class SettingTable {
        public static final String NAME = "setting";

        public static final class Cols {
            public static final String NAME = "name";
            public static final String ID = "id";
            public static final String EMAIL = "email";
            public static final String GENDER = "gender";
            public static final String COMMENT = "comment";
        }
    }
}
