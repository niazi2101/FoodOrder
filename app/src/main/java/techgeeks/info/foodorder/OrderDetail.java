package techgeeks.info.foodorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import techgeeks.info.foodorder.core.BillManagement;

public class OrderDetail extends AppCompatActivity {

    TextView textViewMessage;
    Button btnChange,btnAddress,btnLogin;

    BillManagement billManagement;

    Calendar calendar;

    String message;
    String strDateTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        setTitle("Order Detail");

        getSupportActionBar().setHomeButtonEnabled(true);

        textViewMessage = (TextView) findViewById(R.id.textViewOrderDetail);

        btnChange = (Button) findViewById(R.id.buttonChange_Order);
        btnAddress = (Button) findViewById(R.id.buttonGuestLogin);
        btnLogin = (Button) findViewById(R.id.buttonUserLogin);

        billManagement = new BillManagement();

        calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
        SimpleDateFormat sdf_date = new SimpleDateFormat("dd/MM/yyyy");

        SimpleDateFormat sdf_time = new SimpleDateFormat("hh:mm a");

        strDateTime = sdf.format(calendar.getTime());

        String strDate = sdf_date.format(calendar.getTime());
        String strTime = sdf_time.format(calendar.getTime());
        //String strTime = sdf.format(calendar.get());

        //String date = strDate.substring(0,10);
        //String date = strDateTime.substring(0,10);
        //String time = strDate.substring(10,19);
        //String time = strDateTime.substring(10,19);

        message = billManagement.getFull_message();
        message += "\n\n Date: " + strDate;
        message += "\n Time: " + strTime;
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
                textViewMessage.setText("");
                startActivity(i);
                break;

            case R.id.buttonGuestLogin:

                //inserting date n time to database
                billManagement.setStrDateTime(strDateTime);

                Intent j = new Intent(getApplicationContext(),OrderAddress.class);
                textViewMessage.setText("");
                startActivity(j);

                break;

            case R.id.buttonUserLogin:
                //inserting date n time to database
                /*billManagement.setStrDateTime(strDateTime);
                Intent customer = new Intent(getApplicationContext(),CustomerLogin.class);
                textViewMessage.setText("");
                startActivity(customer);
                */
                Toast.makeText(getApplicationContext(),"Work in progress",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * Let's the user tap the activity icon to go 'home'.
     * Requires setHomeButtonEnabled() in onCreate().
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                // ProjectsActivity is my 'home' activity
                startActivityAfterCleanup(MainActivity.class);
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    private void startActivityAfterCleanup(Class<?> cls) {
        //if (projectsDao != null) projectsDao.close();
        Intent intent = new Intent(getApplicationContext(), cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
