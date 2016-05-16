package techgeeks.info.foodorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class OrderAddress extends AppCompatActivity {

    EditText etHouse,etStreet,etSector,etPhone;
    AutoCompleteTextView etCity;
    Button btnFinalizeOrder;

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
    }
}
