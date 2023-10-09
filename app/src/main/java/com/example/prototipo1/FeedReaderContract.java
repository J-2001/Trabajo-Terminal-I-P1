package com.example.prototipo1;

import android.provider.BaseColumns;

public final class FeedReaderContract {

    private FeedReaderContract() {}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "prototype";
        public static final String COLUMN_CHARGE_COUNTER = "chargeCounter";
        public static final String COLUMN_CURRENT_NOW = "currentNow";
        public static final String COLUMN_CAPACITY = "capacity";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_TIMESTAMP = "timeStamp";
    }

}
