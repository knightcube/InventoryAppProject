package com.knightcube.samples.inventoryappproject;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.knightcube.samples.inventoryappproject.data.ProductContract;

public class ProductCursorAdapter extends CursorAdapter{

    private MainActivity activity;
    public ProductCursorAdapter(MainActivity context, Cursor c) {
        super(context, c,0);
        this.activity = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_layout, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView productNameText = (TextView) view.findViewById(R.id.product_name_text);
        TextView productPriceText = (TextView) view.findViewById(R.id.product_price_text);
        TextView productQuantityText = (TextView) view.findViewById(R.id.product_quantity_text);
        ImageView saleBtn = (ImageView)view.findViewById(R.id.sale_btn);
        ImageView image = (ImageView)view.findViewById(R.id.product_image);
        String name = cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_NAME));
        String price = cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRICE));
        final String quantity = cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_QUANTITY));
        image.setImageURI(Uri.parse(cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_IMAGE))));

        productNameText.setText(name);
        productPriceText.setText("Rs "+price);
        productQuantityText.setText(quantity+" items left");

        final long id = cursor.getLong(cursor.getColumnIndex(ProductContract.ProductEntry._ID));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.goToDetails(id);
            }
        });

        saleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.sellItem(id,
                        Integer.parseInt(quantity));
            }
        });

    }
}
