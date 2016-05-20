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
        return LayoutInflater.from(context).inflate(R.layout.list_items_three, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template

        TextView Textreg = (TextView) view.findViewById(R.id.student_reg);
        TextView Textname = (TextView) view.findViewById(R.id.Text_name);
        //TextView Textdegree = (TextView) view.findViewById(R.id.Text_degree);

        // Extract properties from cursor
        int reg = cursor.getInt(cursor.getColumnIndexOrThrow("order_num"));
        String name = cursor.getString(cursor.getColumnIndexOrThrow("order_price"));
        //String degree = cursor.getString(cursor.getColumnIndexOrThrow("order_Ptime"));

        // Populate fields with extracted properties
        Textname.setText(name);
        Textreg.setText(String.valueOf(reg));
//        Textdegree.setText(degree);


    }
}