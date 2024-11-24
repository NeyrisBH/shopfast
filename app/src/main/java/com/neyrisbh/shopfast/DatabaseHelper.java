package com.neyrisbh.shopfast;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.neyrisbh.shopfast.models.Product;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "ProductsDB";
        private static final int DATABASE_VERSION = 1;

        private static final String TABLE_PRODUCTS = "products";
        private static final String COLUMN_ID = "id";
        private static final String COLUMN_NAME = "name";
        private static final String COLUMN_PRICE = "price";
        private static final String COLUMN_QUANTITY = "quantity";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + " ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NAME + " TEXT, "
                    + COLUMN_PRICE + " REAL, "
                    + COLUMN_QUANTITY + " INTEGER)";
            db.execSQL(CREATE_PRODUCTS_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
            onCreate(db);
        }

        // Métodos CRUD
        public void addProduct(Product product) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, product.getName());
            values.put(COLUMN_PRICE, product.getPrice());
            values.put(COLUMN_QUANTITY, product.getQuantity());

            db.insert(TABLE_PRODUCTS, null, values);
            db.close();
        }

        public List<Product> getAllProducts() {
            List<Product> productList = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_PRODUCTS, null, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Product product = new Product(
                            cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                            cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY))
                    );
                    productList.add(product);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return productList;
        }
}
