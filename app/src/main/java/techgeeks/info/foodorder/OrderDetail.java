package techgeeks.info.foodorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import techgeeks.info.foodorder.core.BillManagement;

public class OrderDetail extends AppCompatActivity {

    TextView textViewMessage;
    Button btnDelivery,btnPickup;

    BillManagement billManagement;

    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        textViewMessage = (TextView) findViewById(R.id.textViewOrderDetail);

        btnDelivery = (Button) findViewById(R.id.buttonDeliver_orderAct);
        btnPickup = (Button) findViewById(R.id.buttonPickUp_orderAct);

        billManagement = new BillManagement();

        message = billManagement.getFull_message();

        textViewMessage.setText(message);

    }


}
