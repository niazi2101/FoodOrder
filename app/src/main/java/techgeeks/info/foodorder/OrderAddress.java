package techgeeks.info.foodorder;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import techgeeks.info.foodorder.SQLPackage.DBhandler;
import techgeeks.info.foodorder.core.BillManagement;

public class OrderAddress extends AppCompatActivity {

    EditText etHouse,etStreet,etSector,etPhone;
    AutoCompleteTextView etCity;
    Button btnFinalizeOrder;

    BillManagement billManagement;
    DBhandler dBhandler;
    Context context = this;

    private static int orderNum = 2000;

    private static final String[] CITIES = new String[] {
            "Islamabad", "Rawalpindi", "Quetta", "Karachi", "Lahore", "Multan", "Peshawar"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_address);

        etHouse = (EditText) findViewById(R.id.editTextHouseNum);
        etStreet = (EditText) findViewById(R.id.editTextStreet);
        etSector = (EditText) findViewById(R.id.editTextSector);
        //etCity = (EditText) findViewById(R.id.editTextCity);
        etPhone = (EditText) findViewById(R.id.editTextPhone);

        etCity = (AutoCompleteTextView) findViewById(R.id.editTextCity);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, CITIES);
        etCity.setAdapter(adapter);

        btnFinalizeOrder = (Button) findViewById(R.id.buttonFinalOrder);
        dBhandler = new DBhandler(context);

        billManagement = new BillManagement();

        orderNum++;
    }

    public void SubmitOrderAddress(View view) {
        switch(view.getId())
        {
            case R.id.buttonFinalOrder: FinalOrder();
                                        break;
        }
    }

    public void FinalOrder()
    {
        String house = etHouse.getText().toString();
        String street = etStreet.getText().toString();
        String sector = etSector.getText().toString();
        String city = etCity.getText().toString();
        String phone = etPhone.getText().toString();

        try {
            if (house.equals("") || street.equals("") || sector.equals("")
                    || city.equals("") || phone.equals("")) {
                Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                dBhandler.insertAddress(dBhandler,house, street, sector, city, phone, orderNum);
                billManagement.setOrderNum(orderNum);
                Toast.makeText(getApplicationContext(), "Address inserted : "+ orderNum, Toast.LENGTH_SHORT).show();

            }
        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), "Address insert failed: "+ e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
}
