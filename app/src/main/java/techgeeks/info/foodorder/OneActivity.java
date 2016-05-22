package techgeeks.info.foodorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OneActivity extends AppCompatActivity {

    Button btnOrder,btnDeliver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
    }

    public void OrderFood(View view) {

        Intent i = new Intent(getApplicationContext(),MainActivity.class);

        startActivity(i);
    }
    public void DeliverFood(View view) {

        Intent customer = new Intent(getApplicationContext(),CustomerLogin.class);
//        textViewMessage.setText("");
        startActivity(customer);

    }
}
