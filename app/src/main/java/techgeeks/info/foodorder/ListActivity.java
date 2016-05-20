package techgeeks.info.foodorder;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import techgeeks.info.foodorder.SQLPackage.DBhandler;

public class ListActivity extends AppCompatActivity {

    public final static String KEY_EXTRA_CONTACT_ID = "KEY_EXTRA_CONTACT_ID";

    DBhandler handler;
    Cursor cursor;
    AlertDialog dialog;

    TodoCursorAdaptor todoAdaptor;
    ListView listView;

    Context context = this;

    private static final int DIALOG_ALERT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        try {
            handler = new DBhandler(this);

            cursor = handler.getAllOrders();

            if(cursor != null) {
                listView = (ListView) findViewById(R.id.listView);

                Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(cursor));
                todoAdaptor = new TodoCursorAdaptor(context, cursor, 0);
                listView.setAdapter(todoAdaptor);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView listView, View view,
                                            int position, long id) {
                        Cursor itemCursor = (Cursor) ListActivity.this.listView.getItemAtPosition(position);
                        int studentID = itemCursor.getInt(itemCursor.getColumnIndex(DBhandler.ORDER_COLUMN_ORDER_ID));



                        Toast.makeText(getApplicationContext(),"Roll Num clicked: " + studentID,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ShowDetail.class);
                        intent.putExtra(KEY_EXTRA_CONTACT_ID, studentID);
                        startActivity(intent);

                    }
                });
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Cursor Empty",Toast.LENGTH_SHORT).show();

            }

        }catch(Exception e)
        {
            Log.e("ERROR","UNABLE TO DISPLAY STUDENT LIST: "+e);
            Log.e("ERROR","UNABLE TO DISPLAY STUDENT LIST: "+cursor.getColumnNames().toString());
        }

    }


}
