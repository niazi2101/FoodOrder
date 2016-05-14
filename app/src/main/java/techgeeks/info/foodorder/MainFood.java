package techgeeks.info.foodorder;

import android.content.Intent;
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

    Spinner spinOne,spinTwo,spinThree,spinFour;
    CheckBox checkBoxOne, checkBoxTwo, checkBoxThree, checkBoxFour;
    TextView textPrice;

    private static final String mainItem1 = "Chicken Plao - Single - 165 Rs";
    private static final String mainItem2 = "Chicken Plao - Double - 220 Rs";
    private static final String mainItem3 = "Chicken Roast - 475 Rs";
    private static final String mainItem4 = "Chicken Karahi - 600 Rs";

    private static String message ;

    private static final int mainItem1_price = 165;
    private static final int mainItem2_price = 220;
    private static final int mainItem3_price = 475;
    private static final int mainItem4_price = 600;

    public static int main_subtotal = 0;    //sum of all subtotals
    public static int main_subtotal1 = 0;
    public static int main_subtotal2 = 0;
    public static int main_subtotal3 = 0;
    public static int main_subtotal4 = 0;
    //public static int main_total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_food);

        this.setTitle("Main Food");

        spinOne = (Spinner) findViewById(R.id.spinnerOne);
        spinTwo = (Spinner) findViewById(R.id.spinnerTwo);
        spinThree = (Spinner) findViewById(R.id.spinnerThree);
        spinFour = (Spinner) findViewById(R.id.spinnerFour);

        checkBoxOne = (CheckBox) findViewById(R.id.checkBoxOne);
        checkBoxTwo = (CheckBox) findViewById(R.id.checkBoxTwo);
        checkBoxThree = (CheckBox) findViewById(R.id.checkBoxThree);
        checkBoxFour = (CheckBox) findViewById(R.id.checkBoxFour);

        textPrice = (TextView) findViewById(R.id.textViewPrice);

        checkBoxOne.setText(mainItem1);
        checkBoxTwo.setText(mainItem2);
        checkBoxThree.setText(mainItem3);
        checkBoxFour.setText(mainItem4);


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
        spinFour.setAdapter(adapter);


        message = "";
        textPrice.setText(message);

    }

    //Function to calculate price of selected foods
    public void CalculateMainOrder() {
        //main_total = 0;
        main_subtotal=0;
        main_subtotal1=0;
        main_subtotal2=0;
        main_subtotal3=0;

        message += " Selected Items and Total Price: ";

        //If checkboxOne is checked, calculate price of checkboxone items
        if(checkBoxOne.isChecked())
        {
            String selChocie = spinOne.getSelectedItem().toString();

            if(selChocie.equals("1 Item"))
            {
                main_subtotal1 = mainItem1_price;
                message += "\n"+ 1 + " " + mainItem1 + " @: " + main_subtotal1;

            }
            else if(selChocie.equals("2 Items"))
            {
                main_subtotal1 = mainItem1_price * 2;
                message += "\n"+ 2 + " " + mainItem1 + " @ : " + main_subtotal1;
            }
            else if(selChocie.equals("3 Items"))
            {
                main_subtotal1 = mainItem1_price * 3;
                message += "\n"+ 3 + " " + mainItem1 + " @ : " + main_subtotal1;
            }
            else if(selChocie.equals("4 Items"))
            {
                main_subtotal1 = mainItem1_price * 4;
                message += "\n"+ 4 + " " + mainItem1 + " @ : " + main_subtotal1;
            }
            else if(selChocie.equals("5 Items"))
            {
                main_subtotal1 = mainItem1_price * 5;
                message += "\n"+ 5 + " " + mainItem1 + " @ : " + main_subtotal1;
            }
            else if(selChocie.equals("6 Item"))
            {
                main_subtotal1 = mainItem1_price * 6;
                message += "\n"+ 6 + " " + mainItem1 + " @ : " + main_subtotal1;
            }

            //main_total += main_subtotal;
        }else
        {
            main_subtotal1 = 0;
        }

        if(checkBoxTwo.isChecked())
        {
            String selChocie = spinTwo.getSelectedItem().toString();

            if(selChocie.equals("1 Item"))
            {
                main_subtotal2 = mainItem2_price;
                message += "\n"+ 1 + " " + mainItem2 + " @ : " + main_subtotal2;

            }
            else if(selChocie.equals("2 Items"))
            {
                main_subtotal2 = mainItem2_price * 2;
                message += "\n"+ 2 + " " + mainItem2 + " @ : " + main_subtotal2;
            }
            else if(selChocie.equals("3 Items"))
            {
                main_subtotal2 = mainItem2_price * 3;
                message += "\n"+ 3 + " " + mainItem2 + " @ : " + main_subtotal2;
            }
            else if(selChocie.equals("4 Items"))
            {
                main_subtotal2 = mainItem2_price * 4;
                message += "\n"+ 4 + " " + mainItem2 + " @ : " + main_subtotal2;
            }
            else if(selChocie.equals("5 Items"))
            {
                main_subtotal2 = mainItem2_price * 5;
                message += "\n"+ 5 + " " + mainItem2 + " @ : " + main_subtotal2;
            }
            else if(selChocie.equals("6 Item"))
            {
                main_subtotal2 = mainItem2_price * 6;
                message += "\n"+ 6 + " " + mainItem2 + " @ : " + main_subtotal2;
            }

            //main_total += main_subtotal;
        }
        else
        {
            main_subtotal2 = 0;
        }

        if(checkBoxThree.isChecked())
        {
            String selChocie = spinThree.getSelectedItem().toString();

            if(selChocie.equals("1 Item"))
            {
                main_subtotal3 = mainItem3_price;
                message += "\n"+ 1 + " " + mainItem3 + " @ : " + main_subtotal3;
            }
            else if(selChocie.equals("2 Items"))
            {
                main_subtotal3 = mainItem3_price * 2;
                message += "\n"+ 2 + " " + mainItem3 + " @ : " + main_subtotal3;
            }
            else if(selChocie.equals("3 Items"))
            {
                main_subtotal3 = mainItem3_price * 3;
                message += "\n"+ 3 + " " + mainItem3 + " @ : " + main_subtotal3;
            }
            else if(selChocie.equals("4 Items"))
            {
                main_subtotal3 = mainItem3_price * 4;
                message += "\n"+ 4 + " " + mainItem3 + " @ : " + main_subtotal3;
            }
            else if(selChocie.equals("5 Items"))
            {
                main_subtotal3 = mainItem3_price * 5;
                message += "\n"+ 5 + " " + mainItem3 + " @ : " + main_subtotal3;
            }
            else if(selChocie.equals("6 Item"))
            {
                main_subtotal3 = mainItem3_price * 6;
                message += "\n"+ 6 + " " + mainItem3 + " @ : " + main_subtotal3;
            }


        }
        else
        {
            main_subtotal3 = 0;
        }

        if(checkBoxFour.isChecked())
        {
            String selChocie = spinFour.getSelectedItem().toString();

            if(selChocie.equals("1 Item"))
            {
                main_subtotal4 = mainItem4_price;
                message += "\n"+ 1 + " " + mainItem4 + " @ : " + main_subtotal4;
            }
            else if(selChocie.equals("2 Items"))
            {
                main_subtotal4 = mainItem4_price * 2;
                message += "\n"+ 2 + " " + mainItem4 + " @ : " + main_subtotal4;

            }
            else if(selChocie.equals("3 Items"))
            {
                main_subtotal4 = mainItem4_price * 3;
                message += "\n"+ 3 + " " + mainItem4 + " @ : " + main_subtotal4;

            }
            else if(selChocie.equals("4 Items"))
            {
                main_subtotal4 = mainItem4_price * 4;
                message += "\n"+ 4 + " " + mainItem4 + " @ : " + main_subtotal4;

            }
            else if(selChocie.equals("5 Items"))
            {
                main_subtotal4 = mainItem4_price * 5;
                message += "\n"+ 5 + " " + mainItem4 + " @ : " + main_subtotal4;

            }
            else if(selChocie.equals("6 Item"))
            {
                main_subtotal4 = mainItem4_price * 6;
                message += "\n"+ 6 + " " + mainItem4 + " @ : " + main_subtotal4;

            }


        }
        else
        {
            main_subtotal4 = 0;
        }

        //calculating price of selected main foods
        main_subtotal = main_subtotal1 + main_subtotal2 + main_subtotal3 + main_subtotal4;


        message += "\n Total Price: " + main_subtotal + " Rs";
        //updating UI with price of selected items
        textPrice.setText(message);

        Toast.makeText(getApplicationContext(),"Price: " + main_subtotal + " Rs",Toast.LENGTH_SHORT).show();

    }

    public void ButtonListenerMethod(View view) {
        switch(view.getId())
        {
            case R.id.buttonCalculate_mFood:
                CalculateMainOrder();
                break;

            case R.id.buttonOrderNow_main: break;

            case R.id.buttonAddToCart_main:
                CalculateMainOrder();
                Intent intent = new Intent(getApplicationContext(),FastFood.class);
                startActivity(intent);
        }
    }
}
