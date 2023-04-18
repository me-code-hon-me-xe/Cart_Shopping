package hanu.a2_2001040023.database;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import hanu.a2_2001040023.Models.Product;

public class MyCartCRUD {
    Context context;
    private MyCartDatabaseHelper dbHelper;
    public List<Product> products;

    public MyCartCRUD(Context context) {
        this.context = context;
        dbHelper = new MyCartDatabaseHelper(context);
        String dbPath = context.getDatabasePath("my_cart.db").getAbsolutePath();
        File dbFile = new File(dbPath);
        if(dbFile.exists()) {
            // Database exists
            System.out.println("Database exists");
        } else {
            // Database does not exist
            System.out.println("Database does not exist");
        }
    }

    public long insert(Product product) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_PRODUCT_ID, product.getId());
        values.put(dbHelper.COLUMN_PRODUCT_NAME, product.getName());
        values.put(dbHelper.COLUMN_PRODUCT_THUMBNAIL, product.getThumbnail());
        values.put(dbHelper.COLUMN_PRODUCT_QUANTITY, product.getQuantity());
        values.put(dbHelper.COLUMN_PRODUCT_PRICE, product.getUnitPrice());
        values.put(dbHelper.COLUMN_PRODUCT_SUM_PRICE, product.getProduct_sum_price());
        long id = db.insert(dbHelper.TABLE_NAME, null, values);
        if (id == -1) {
            Toast.makeText(context, "Error inserting new product into database", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Product added to cart", Toast.LENGTH_SHORT).show();
        }
        db.close();
        return id;
    }
    public void delete(Product product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(dbHelper.TABLE_NAME, dbHelper.COLUMN_PRODUCT_ID + " = ?",
                new String[]{String.valueOf(product.getId())});
        db.close();
    }
    public int update(Product product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_PRODUCT_ID, product.getId());
        values.put(dbHelper.COLUMN_PRODUCT_NAME, product.getName());
        values.put(dbHelper.COLUMN_PRODUCT_THUMBNAIL, product.getThumbnail());
        values.put(dbHelper.COLUMN_PRODUCT_QUANTITY, product.getQuantity());
        values.put(dbHelper.COLUMN_PRODUCT_PRICE, product.getUnitPrice());
        values.put(dbHelper.COLUMN_PRODUCT_SUM_PRICE, product.getProduct_sum_price());

        int count = db.update(dbHelper.TABLE_NAME, values,
                dbHelper.COLUMN_PRODUCT_ID + " = ?",
                new String[]{String.valueOf(product.getId())});
        if (count == 0) {
            Toast.makeText(context, count + " has been updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, count + " have been updated", Toast.LENGTH_SHORT).show();
        }
        db.close();
        return count;
    }

    public Product getProductById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_NAME +
                " WHERE " + dbHelper.COLUMN_PRODUCT_ID + " = " + id;

        Cursor cursor = db.rawQuery(selectQuery, null);
        Product product = null;
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PRODUCT_NAME));
            @SuppressLint("Range")int unitPrice = cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_PRODUCT_PRICE));
            @SuppressLint("Range")String thumbnail = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PRODUCT_THUMBNAIL));
            @SuppressLint("Range")int quantity = cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_PRODUCT_QUANTITY));
            @SuppressLint("Range")int product_sum_price = cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_PRODUCT_SUM_PRICE));
            product = new Product(id, name, unitPrice, thumbnail, quantity, product_sum_price);
        }
        cursor.close();
        db.close();

        return product;
    }
    public List<Product> getAllProducts() {
        products = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_PRODUCT_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PRODUCT_NAME));
                @SuppressLint("Range")int unitPrice = cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_PRODUCT_PRICE));
                @SuppressLint("Range")String thumbnail = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PRODUCT_THUMBNAIL));
                @SuppressLint("Range")int quantity = cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_PRODUCT_QUANTITY));
                @SuppressLint("Range")int product_sum_price = cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_PRODUCT_SUM_PRICE));
                Product product = new Product(id, name, unitPrice, thumbnail, quantity, product_sum_price);
                product.setId(id);
                products.add(product);


            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return products;
    }
    public boolean isProductExists(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_NAME +
                " WHERE " + dbHelper.COLUMN_PRODUCT_ID + " = " + id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        boolean exists = cursor.moveToFirst();
        cursor.close();
        db.close();
        return exists;
    }
}
