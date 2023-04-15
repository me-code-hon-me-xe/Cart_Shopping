package hanu.a2_2001040023.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import hanu.a2_2001040023.Models.Product;

public class MyCartCRUD {
    Context context;
    private MyCartDatabaseHelper dbHelper;
    public MyCartCRUD(Context context){
        this.context = context;
        dbHelper = new MyCartDatabaseHelper(context);
    }

    public long insert(Product product){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        int COLUMN_PRODUCT_QUANTITY = product.getQuantity();
        double COLUMN_PRODUCT_PRICE = product.getUnitPrice();
        int COLUMN_PRODUCT_SUM_PRICE = COLUMN_PRODUCT_QUANTITY * (int)COLUMN_PRODUCT_PRICE;
        values.put("COLUMN_PRODUCT_NAME", product.getName());
        values.put("COLUMN_PRODUCT_THUMBNAIL", product.getThumbnail());
        values.put("COLUMN_PRODUCT_QUANTITY", product.getQuantity());
        values.put("COLUMN_PRODUCT_PRICE", product.getUnitPrice());
        values.put("COLUMN_PRODUCT_SUM_PRICE", product.getProduct_sum_price());
        long id = db.insert("my_cart.db", null, values);
        if (id == -1) {
            Toast.makeText(context, "Error inserting new product into database", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Product added to cart", Toast.LENGTH_SHORT).show();
        }
        db.close();
        return id;
    }


}
/*
* package hanu.a2_2001040023.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyCartCRUD {
    private MyCartDatabaseHelper dbHelper;

    public MyCartCRUD(Context context) {
        dbHelper = new MyCartDatabaseHelper(context);
    }

    public long insert(Product product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyCartDatabaseHelper.COLUMN_PRODUCT_NAME, product.getProductName());
        values.put(MyCartDatabaseHelper.COLUMN_QUANTITY, product.getQuantity());
        values.put(MyCartDatabaseHelper.COLUMN_PRICE, product.getPrice());
        values.put(MyCartDatabaseHelper.COLUMN_SUM_PRICE, product.getSumPrice());

        long id = db.insert(MyCartDatabaseHelper.TABLE_NAME, null, values);
        db.close();

        return id;
    }

    public int update(Product product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyCartDatabaseHelper.COLUMN_PRODUCT_NAME, product.getProductName());
        values.put(MyCartDatabaseHelper.COLUMN_QUANTITY, product.getQuantity());
        values.put(MyCartDatabaseHelper.COLUMN_PRICE, product.getPrice());
        values.put(MyCartDatabaseHelper.COLUMN_SUM_PRICE, product.getSumPrice());

        int count = db.update(MyCartDatabaseHelper.TABLE_NAME, values,
                MyCartDatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(product.getId())});

        db.close();
        return count;
    }

    public void delete(Product product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(MyCartDatabaseHelper.TABLE_NAME, MyCartDatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(product.getId())});
        db.close();
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + MyCartDatabaseHelper.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(cursor.getLong(cursor.getColumnIndex(MyCartDatabaseHelper.COLUMN_ID)));
                product.setProductName(cursor.getString(cursor.getColumnIndex(MyCartDatabaseHelper.COLUMN_PRODUCT_NAME)));
                product.setQuantity(cursor.getInt(cursor.getColumnIndex(MyCartDatabaseHelper.COLUMN_QUANTITY)));
                product.setPrice(cursor.getDouble(cursor.getColumnIndex(MyCartDatabaseHelper.COLUMN_PRICE)));
                product.setSumPrice(cursor.getDouble(cursor.getColumnIndex(MyCartDatabaseHelper.COLUMN_SUM_PRICE)));

                products.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return products;
    }

    public Product getProductById(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + MyCartDatabaseHelper.TABLE_NAME +
                " WHERE " + MyCartDatabaseHelper.COLUMN_ID + " = " + id;

        Cursor cursor = db.rawQuery(selectQuery, null);
        Product product = null;
        if (cursor.moveToFirst()) {
            product = new Product();
            product.setId(cursor.getLong(cursor.getColumnIndex(MyCartDatabaseHelper.COLUMN_ID)));
            product.setProductName(cursor.getString(cursor.getColumnIndex(MyCartDatabaseHelper.COLUMN_PRODUCT_NAME)));
            product.setQuantity(cursor.getInt(cursor.getColumnIndex(MyCartDatabaseHelper.COLUMN_QUANTITY)));
            product.setPrice(cursor.getDouble(cursor.getColumnIndex(MyCartDatabaseHelper.COLUMN_PRICE)));
            product.setSumPrice(cursor.getDouble(cursor.getColumnIndex(MyCartDatabaseHelper.COLUMN_SUM_PRICE)));
        }
        cursor.close();
        db.close();

        return product;
    }
}

*
* */