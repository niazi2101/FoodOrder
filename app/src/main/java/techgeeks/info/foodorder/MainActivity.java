package techgeeks.info.foodorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OrderMainFood(View view) {

        switch(view.getId())
        {
            case R.id.buttonMainFood:
                Intent i = new Intent(getApplicationContext(),MainFood.class);
                startActivity(i);
                break;

            case R.id.buttonFastFood:
                Intent j = new Intent(getApplicationContext(),FastFood.class);
                startActivity(j);
                break;

            case R.id.buttonDrinks:
                Toast.makeText(getApplicationContext(),"Work in progress",Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonDeliveryType:
                /*Intent k = new Intent(getApplicationContext(),OrderDetail.class);
                startActivity(k);*/
                Toast.makeText(getApplicationContext(),"Work in progress",Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonPaymentType:
                Toast.makeText(getApplicationContext(),"Work in progress",Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonAccount:
                Intent account = new Intent(getApplicationContext(),CustomerProfile.class);
                startActivity(account);
                //Toast.makeText(getApplicationContext(),"Work in progress",Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonSettings:
                Toast.makeText(getApplicationContext(),"Work in progress",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
