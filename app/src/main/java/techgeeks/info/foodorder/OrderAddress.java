package techgeeks.info.foodorder;

import android.content.Context;
import android.content.Intent;
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
    AutoCompleteTextView etCity,etOrdNum;
    Button btnFinalizeOrder;

    BillManagement billManagement;
    DBhandler dBhandler;
    Context context = this;

    public static String ORDER = "order";

    boolean addressCheck = false, orderCheck =false, whilecheck = false;
    private static int orderNum ;

    int i=0;
    //array of order numbers
    private static final String[] orders = {"2000","2001","2002","2003",
            "2004","2005","2006","2007","2008","2009","2010"} ;
    //private static final int[] orders = {2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010} ;

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
        etOrdNum = (AutoCompleteTextView) findViewById(R.id.editTextOrdNum);

        ArrayAdapter<String> Cityadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_selectable_list_item, CITIES);
        ArrayAdapter<String> Orderadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_selectable_list_item, orders);


        etCity.setAdapter(Cityadapter);
        etOrdNum.setAdapter(Orderadapter);

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

        //String orderNumString = etOrdNum.getText().toString();

        //int orderNumInt = Integer.parseInt(etOrdNum.getText().toString());

        String orderDetail = billManagement.getFull_message();
        int price = billManagement.getTotal_bill();
        String SPrice = ""+ price;

        //int orderID = billManagement.getOrderNum();
        String date = billManagement.getStrDateTime();

        try {
            /*
            if (house.equals("") || street.equals("") || sector.equals("")
                    || city.equals("") || phone.equals("")) {
                Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
            */
            do{
                int orderNumInt = Integer.parseInt(etOrdNum.getText().toString());

                orderCheck = dBhandler.insertOrder(dBhandler, orderNumInt,
                        orderDetail, SPrice, date, null, 0);

                //addressCheck = dBhandler.insertAddress(dBhandler,"house",
                        //"street", "sector", "city", "phone", orderNum);
                if (orderCheck == true) {

                    //i++;
                   //orderNum = array[i];
                addressCheck = dBhandler.insertAddress(dBhandler,house, street, sector, city, phone, orderNumInt);

                   //orderCheck = dBhandler.insertOrder(dBhandler, orderNum, "orderDetail", "SPrice", "date", null, 0);
                if(addressCheck = true) {

                       billManagement.setOrderNum(orderNumInt);

                       Toast.makeText(getApplicationContext(), "Data inserted : " + orderNumInt, Toast.LENGTH_SHORT).show();
                       Intent i = new Intent(getApplicationContext(), TestActivity.class);
                       //i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                       i.putExtra(ORDER, orderNumInt);
                       startActivity(i);
                       whilecheck = true;
                   } else {
                       Toast.makeText(getApplicationContext(), "Address insert failed :  " + orderNumInt, Toast.LENGTH_SHORT).show();
                       etOrdNum.setText("");
                       whilecheck = false;
                   }
                }
            else {
                    Toast.makeText(getApplicationContext(), "Order insert failed : " + orderNum, Toast.LENGTH_SHORT).show();
                    etOrdNum.setText("");
                    whilecheck = false;
                }
            }while(whilecheck != true);



        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), "Address insert failed: "+ e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
}
