package techgeeks.info.foodorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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


    public void ChangeOrPayment(View view) {

        switch(view.getId())
        {
            case R.id.buttonChange_Order:
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                billManagement.setFastFood_message("");
                billManagement.setMainFood_message(""); //clearing log
                billManagement.setFastfood_bill(0);
                billManagement.setMainfood_bill(0);
                startActivity(i);
                break;

            case R.id.buttonPayment_Order: break;
        }
    }
}
