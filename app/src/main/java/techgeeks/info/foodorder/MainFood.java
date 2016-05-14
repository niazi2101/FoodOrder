package techgeeks.info.foodorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainFood extends AppCompatActivity {

    Spinner spinOne,spinTwo,spinThree;
    CheckBox checkBoxOne, checkBoxTwo, checkBoxThree;
    TextView textPrice;

    private static final String mainItem1 = "Chicken Plao - Single - 165 Rs";
    private static final String mainItem2 = "Chicken Plao - Double - 220 Rs";
    private static final String mainItem3 = "Chicken Roast - 475 Rs";
    private static final String mainItem4 = "Savor Food - Single";

    private static final int mainItem1_price = 165;
    private static final int mainItem2_price = 220;
    private static final int mainItem3_price = 475;

    public static int main_subtotal = 0;
    public static int main_total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_food);

        spinOne = (Spinner) findViewById(R.id.spinnerOne);
        spinTwo = (Spinner) findViewById(R.id.spinnerTwo);
        spinThree = (Spinner) findViewById(R.id.spinnerThree);

        checkBoxOne = (CheckBox) findViewById(R.id.checkBoxOne);
        checkBoxTwo = (CheckBox) findViewById(R.id.checkBoxTwo);
        checkBoxThree = (CheckBox) findViewById(R.id.checkBoxThree);

        textPrice = (TextView) findViewById(R.id.textViewPrice);

        checkBoxOne.setText(mainItem1);
        checkBoxTwo.setText(mainItem2);
        checkBoxThree.setText(mainItem3);

        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("1 Item");
        spinnerArray.add("2 Items");
        spinnerArray.add("3 Items");
        spinnerArray.add("4 Items");
        spinnerArray.add("5 Items");
        spinnerArray.add("6 Items");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinOne.setAdapter(adapter);
        spinTwo.setAdapter(adapter);
        spinThree.setAdapter(adapter);
    }

    public void CalculateMainOrder(View view) {

        //If checkboxOne is checked, calculate price of checkboxone items
        if(checkBoxOne.isChecked())
        {
            String selChocie = spinOne.getSelectedItem().toString();

            if(selChocie.equals("1 Item"))
            {
                main_subtotal = mainItem1_price;
            }
            else if(selChocie.equals("2 Items"))
            {
                main_subtotal = mainItem1_price * 2;
            }
            else if(selChocie.equals("3 Items"))
            {
                main_subtotal = mainItem1_price * 3;
            }
            else if(selChocie.equals("4 Items"))
            {
                main_subtotal = mainItem1_price * 4;
            }
            else if(selChocie.equals("5 Items"))
            {
                main_subtotal = mainItem1_price * 5;
            }
            else if(selChocie.equals("6 Item"))
            {
                main_subtotal = mainItem1_price * 6;
            }

            main_total += main_subtotal;
        }

        if(checkBoxTwo.isChecked())
        {
            String selChocie = spinOne.getSelectedItem().toString();

            if(selChocie.equals("1 Item"))
            {
                main_subtotal = mainItem2_price;
            }
            else if(selChocie.equals("2 Items"))
            {
                main_subtotal = mainItem2_price * 2;
            }
            else if(selChocie.equals("3 Items"))
            {
                main_subtotal = mainItem2_price * 3;
            }
            else if(selChocie.equals("4 Items"))
            {
                main_subtotal = mainItem2_price * 4;
            }
            else if(selChocie.equals("5 Items"))
            {
                main_subtotal = mainItem2_price * 5;
            }
            else if(selChocie.equals("6 Item"))
            {
                main_subtotal = mainItem2_price * 6;
            }

            main_total += main_subtotal;
        }

        if(checkBoxThree.isChecked())
        {
            String selChocie = spinOne.getSelectedItem().toString();

            if(selChocie.equals("1 Item"))
            {
                main_subtotal = mainItem3_price;
            }
            else if(selChocie.equals("2 Items"))
            {
                main_subtotal = mainItem3_price * 2;
            }
            else if(selChocie.equals("3 Items"))
            {
                main_subtotal = mainItem3_price * 3;
            }
            else if(selChocie.equals("4 Items"))
            {
                main_subtotal = mainItem3_price * 4;
            }
            else if(selChocie.equals("5 Items"))
            {
                main_subtotal = mainItem3_price * 5;
            }
            else if(selChocie.equals("6 Item"))
            {
                main_subtotal = mainItem3_price * 6;
            }

            main_total += main_subtotal;
        }

        //updating UI with price of selected items
        textPrice.setText("Price: " + main_total + " Rs");

        Toast.makeText(getApplicationContext(),"Price: " + main_total + " Rs",Toast.LENGTH_SHORT).show();

    }
}
