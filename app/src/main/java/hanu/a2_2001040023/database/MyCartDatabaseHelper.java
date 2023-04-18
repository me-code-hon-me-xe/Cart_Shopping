package hanu.a2_2001040023.database;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyCartDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "my_cart.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "MyCart";
    public static final String COLUMN_PRODUCT_ID = "id";
    public static final String COLUMN_PRODUCT_THUMBNAIL = "product_thumbnail";
    public static final String COLUMN_PRODUCT_NAME = "product_name";
    public static final String COLUMN_PRODUCT_QUANTITY = "product_quantity";
    public static final String COLUMN_PRODUCT_PRICE = "product_price";
    public static final String COLUMN_PRODUCT_SUM_PRICE = "product_sum_price";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_PRODUCT_ID + " INTEGER, " + COLUMN_PRODUCT_NAME + " TEXT, " + COLUMN_PRODUCT_THUMBNAIL + " TEXT, " + COLUMN_PRODUCT_QUANTITY + " INTEGER, " + COLUMN_PRODUCT_PRICE + " REAL, " + COLUMN_PRODUCT_SUM_PRICE + " REAL);";

    public MyCartDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table
        db.execSQL("DROP TABLE IF EXISTS cart");

        // Create the new table
        db.execSQL(CREATE_TABLE);
    }
}


