package techgeeks.info.foodorder;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import techgeeks.info.foodorder.SQLPackage.DBhandler;

public class TestActivity extends AppCompatActivity {

    /*
    Bad programming
     */
    TextView tView1,tView2,tView3,tView4,tView5,tView6,tView7;
    String st1,st2,st3,st4,st5,st6,st7;
    Button btn1;

    int orderNum;
    public static String ORDER = "order";

    DBhandler dBhandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        tView1 = (TextView) findViewById(R.id.textView7);
        tView2 = (TextView) findViewById(R.id.textView8);
        tView3 = (TextView) findViewById(R.id.textView9);
        tView4 = (TextView) findViewById(R.id.textView10);
        tView5 = (TextView) findViewById(R.id.textView11);
        tView6 = (TextView) findViewById(R.id.textView12);
        tView7 = (TextView) findViewById(R.id.textView13);

        Bundle get = getIntent().getExtras();
        orderNum = get.getInt(ORDER,0);
        btn1 = (Button) findViewById(R.id.button);

        Toast.makeText(getApplicationContext(), "Order id: " + orderNum, Toast.LENGTH_SHORT).show();

        dBhandler = new DBhandler(this);
        try {
            Cursor rs = dBhandler.getOrder(orderNum);

            rs.moveToFirst();
            st1 = rs.getString(rs.getColumnIndex(dBhandler.ORDER_COLUMN_ORDER_ID));
            st2 = rs.getString(rs.getColumnIndex(dBhandler.ORDER_COLUMN_DETAIL));
            st3 = rs.getString(rs.getColumnIndex(dBhandler.ORDER_COLUMN_PLACING_TIME));
            st4 = rs.getString(rs.getColumnIndex(dBhandler.ORDER_COLUMN_PRICE));
            //st1 = rs.getString(rs.getColumnIndex(dBhandler.ORDER_COLUMN_ORDER_ID));

            if (!rs.isClosed()) {
                rs.close();
            }
        }catch (Exception e)
        {
            Log.e("cursortest",e.getMessage());
        }

        tView1.setText("Order #: " + st1);
        tView2.setText("Detail #: " + st2);
        tView3.setText("Price #: " + st4);
        tView4.setText("Date #: " + st3);


    }


    public void buttonCick(View view) {
        Intent i = new Intent(getApplicationContext(), OneActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}

