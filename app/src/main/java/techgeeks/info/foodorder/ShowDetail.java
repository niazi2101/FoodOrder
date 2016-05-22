package techgeeks.info.foodorder;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import techgeeks.info.foodorder.SQLPackage.DBhandler;

public class ShowDetail extends AppCompatActivity {

    TextView tvOrderNum,tvOrderDetail,tvAddress,tvTime,tvPhone;
    Button btnTakeOrder;

    int orderNum;
    public final static String KEY_EXTRA_ORDER_ID = "KEY_EXTRA_ORDER_ID";

    String stOrder,stOrderDetail,stOrderTime,stOrderPrice;
    String stHome,stStreet,stSector,stCity,stPhone;
    String address;

    private DBhandler dBhandler;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        setTitle("Order Detail");

        tvOrderNum = (TextView) findViewById(R.id.textView15);
        tvOrderDetail = (TextView) findViewById(R.id.textViewOrderDetail);
        tvAddress = (TextView) findViewById(R.id.textViewAddressDetail);
        tvTime = (TextView) findViewById(R.id.textViewTime);
        tvPhone = (TextView) findViewById(R.id.textViewPhone);


        btnTakeOrder = (Button) findViewById(R.id.buttonTakeOrder);



        Bundle get = getIntent().getExtras();
        orderNum = get.getInt(KEY_EXTRA_ORDER_ID,0);

        dBhandler = new DBhandler(this);
        //Reading records from OrderTable
        try {
            Cursor rs = dBhandler.getOrder(orderNum);

            rs.moveToFirst();
            stOrder = rs.getString(rs.getColumnIndex(dBhandler.ORDER_COLUMN_ORDER_ID));
            stOrderDetail = rs.getString(rs.getColumnIndex(dBhandler.ORDER_COLUMN_DETAIL));
            stOrderTime = rs.getString(rs.getColumnIndex(dBhandler.ORDER_COLUMN_PLACING_TIME));
            stOrderPrice = rs.getString(rs.getColumnIndex(dBhandler.ORDER_COLUMN_PRICE));

            if (!rs.isClosed()) {
                rs.close();
            }
        }catch (Exception e)
        {
            Log.e("cursortest",e.getMessage());
        }

        //Reading records from Address Table
        try {
            Cursor rs = dBhandler.getAddress(orderNum);

            rs.moveToFirst();
            stHome = rs.getString(rs.getColumnIndex(dBhandler.ADDRESS_COLUMN_HOUSE));
            stStreet = rs.getString(rs.getColumnIndex(dBhandler.ADDRESS_COLUMN_STREET));
            stSector = rs.getString(rs.getColumnIndex(dBhandler.ADDRESS_COLUMN_SECTOR));
            stCity = rs.getString(rs.getColumnIndex(dBhandler.ADDRESS_COLUMN_CITY));
            stPhone = rs.getString(rs.getColumnIndex(dBhandler.ADDRESS_COLUMN_PHONE));

            if (!rs.isClosed()) {
                rs.close();
            }
        }catch (Exception e)
        {
            Log.e("cursortest",e.getMessage());
        }

        address = "House# "+ stHome + ", Street: " + stStreet + ", Sector: " + stSector + ", City "
                + stCity +". ";

        tvOrderNum.setText("Order #: " + stOrder);
        tvOrderDetail.setText("" + stOrderDetail);
        tvAddress.setText(address);
        tvTime.setText(stOrderTime);
        tvPhone.setText(stPhone);

    }
}
