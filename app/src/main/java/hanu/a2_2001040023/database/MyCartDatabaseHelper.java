package hanu.a2_2001040023.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyCartDatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "my_cart.db";
        private static final int DATABASE_VERSION = 1;

        private static final String TABLE_NAME = "cart";
        private static final String COLUMN_ID = "id";
        private static final String COLUMN_PRODUCT_THUMBNAIL ="product_thumbnail";
        private static final String COLUMN_PRODUCT_NAME = "product_name";
        private static final String COLUMN_PRODUCT_QUANTITY = "product_quantity";
        private static final String COLUMN_PRODUCT_PRICE = "product_price";
        private static final String COLUMN_PRODUCT_SUM_PRICE = "product_sum_price";

        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCT_NAME + " TEXT, " +
                COLUMN_PRODUCT_THUMBNAIL + " TEXT, " +
                COLUMN_PRODUCT_QUANTITY + " INTEGER, " +
                COLUMN_PRODUCT_PRICE + " REAL, " +
                COLUMN_PRODUCT_SUM_PRICE + " REAL);";

        public MyCartDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Upgrade logic here
        }
    }


