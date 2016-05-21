package techgeeks.info.foodorder;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


/**
 * Created by Hamza Khan Niaz on 4/7/2016.
 */
public class TodoCursorAdaptor extends CursorAdapter {

    public TodoCursorAdaptor(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.listitem_rect, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template

        TextView TextOrd = (TextView) view.findViewById(R.id.ordNum);
        TextView TextDate = (TextView) view.findViewById(R.id.firstLine);
        TextView TextPrice = (TextView) view.findViewById(R.id.secondLine);

        // Extract properties from cursor
        int order = cursor.getInt(cursor.getColumnIndexOrThrow("order_num"));
        String price = cursor.getString(cursor.getColumnIndexOrThrow("order_price"));
        String date = cursor.getString(cursor.getColumnIndexOrThrow("order_Ptime"));

        // Populate fields with extracted properties
        TextOrd.setText(String.valueOf(order));
        TextDate.setText(date);
        TextPrice.setText(price + " Rs");



    }
}