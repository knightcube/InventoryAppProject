package com.knightcube.samples.inventoryappproject.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.knightcube.samples.inventoryappproject.data.ProductContract.ProductEntry.CREATE_TABLE_PRODUCT;

public class ProductDbHelper extends SQLiteOpenHelper {

    public final static String DB_NAME = "products.db";
    public final static int DB_VERSION = 1;
    private String[] projection = {
            ProductContract.ProductEntry._ID,
            ProductContract.ProductEntry.COLUMN_NAME,
            ProductContract.ProductEntry.COLUMN_PRICE,
            ProductContract.ProductEntry.COLUMN_QUANTITY,
            ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME,
            ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL,
            ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONE,
            ProductContract.ProductEntry.COLUMN_IMAGE
    };
    public ProductDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_PRODUCT);
        onCreate(db);
    }

    public void insertItem(ProductItem item) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ProductContract.ProductEntry.COLUMN_NAME, item.getProductName());
        values.put(ProductContract.ProductEntry.COLUMN_PRICE, item.getProductPrice());
        values.put(ProductContract.ProductEntry.COLUMN_QUANTITY, item.getProductQuantity());
        values.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME,item.getProductSupplierName());
        values.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL,item.getProductSupplierEmail());
        values.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONE,item.getProductSupplierPhone());
        values.put(ProductContract.ProductEntry.COLUMN_IMAGE, item.getProductImage());
        long id = db.insert(ProductContract.ProductEntry.TABLE_NAME, null, values);
    }

    public Cursor readStock() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                ProductContract.ProductEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }

    public Cursor readItem(long itemId) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = ProductContract.ProductEntry._ID + "=?";
        String[] selectionArgs = new String[] {String.valueOf(itemId)};
        Cursor cursor = db.query(
                ProductContract.ProductEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        return cursor;
    }

    public void updateItem(long currentItemId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ProductContract.ProductEntry.COLUMN_QUANTITY, quantity);
        String selection = ProductContract.ProductEntry._ID + "=?";
        String[] selectionArgs = new String[] {String.valueOf(currentItemId)};
        db.update(ProductContract.ProductEntry.TABLE_NAME, values, selection, selectionArgs);
    }

    public void sellItem(long itemId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        if (quantity > 0) {
            values.put(ProductContract.ProductEntry.COLUMN_QUANTITY, (quantity-1));
        }else
            values.put(ProductContract.ProductEntry.COLUMN_QUANTITY, 0);
        String selection = ProductContract.ProductEntry._ID + "=?";
        String[] selectionArgs = new String[] {String.valueOf(itemId)};
        db.update(ProductContract.ProductEntry.TABLE_NAME,values, selection, selectionArgs);
    }
}
