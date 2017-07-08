package com.knightcube.samples.inventoryappproject.data;

import android.provider.BaseColumns;

public class ProductContract {

    public ProductContract() {
    }

    public static final class ProductEntry implements BaseColumns {

        public static final String TABLE_NAME = "products";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_SUPPLIER_EMAIL = "supplier_email";
        public static final String COLUMN_SUPPLIER_PHONE = "supplier_phone";
        public static final String COLUMN_IMAGE = "image";

        public static final String CREATE_TABLE_PRODUCT = "CREATE TABLE " +
                ProductEntry.TABLE_NAME + "(" +
                ProductEntry._ID + " INTEGER PRIMARY KEY," +
                ProductEntry.COLUMN_NAME + " TEXT NOT NULL," +
                ProductEntry.COLUMN_PRICE + " TEXT NOT NULL," +
                ProductEntry.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0," +
                ProductEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL," +
                ProductEntry.COLUMN_SUPPLIER_EMAIL + " TEXT NOT NULL,"+
                ProductEntry.COLUMN_SUPPLIER_PHONE + " TEXT NOT NULL," +
                ProductEntry.COLUMN_IMAGE + " BLOB NOT NULL" + ");";
    }
}
