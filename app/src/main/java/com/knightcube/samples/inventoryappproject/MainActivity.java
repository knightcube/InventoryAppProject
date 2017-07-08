package com.knightcube.samples.inventoryappproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.knightcube.samples.inventoryappproject.data.ProductContract;
import com.knightcube.samples.inventoryappproject.data.ProductDbHelper;

public class MainActivity extends AppCompatActivity {

    private static ProductDbHelper dbHelper;
    private static ProductCursorAdapter productCursorAdapter;
    private ListView productsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(i);
            }
        });
        displayDatabaseInfo();
     }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Method","onStart");
        displayDatabaseInfo();
    }

    public void displayDatabaseInfo(){

        dbHelper = new ProductDbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                ProductContract.ProductEntry._ID,
                ProductContract.ProductEntry.COLUMN_NAME,
                ProductContract.ProductEntry.COLUMN_PRICE,
                ProductContract.ProductEntry.COLUMN_QUANTITY,
                ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME,
                ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL,
                ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONE,
                ProductContract.ProductEntry.COLUMN_IMAGE
        };
        Cursor cursor = db.query(
                ProductContract.ProductEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        productsListView = (ListView) findViewById(R.id.products_list_view);
        productCursorAdapter = new ProductCursorAdapter(this, cursor);
        productsListView.setAdapter(productCursorAdapter);
        TextView mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        productsListView.setEmptyView(mEmptyStateTextView);


    }
    public static void sellItem(long id, int quantity) {
        dbHelper.sellItem(id, quantity);
        productCursorAdapter.swapCursor(dbHelper.readStock());
    }

    public void goToDetails(long id) {
        Intent i = new Intent(MainActivity.this, DetailsActivity.class);
        i.putExtra("itemId", id);
        startActivity(i);
    }

}
