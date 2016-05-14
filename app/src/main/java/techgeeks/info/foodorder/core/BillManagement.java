package techgeeks.info.foodorder.core;

/**
 * Created by Hamza Khan Niaz on 5/14/2016.
 */
public class BillManagement {
    private static int sub_bill = 0;
    private static int mainfood_bill = 0;   //bill from main food activity
    private static int fastfood_bill = 0;   //bill from fast food activity
    private static int total_bill = 0;

    private static String mainFood_message;
    private static String fastFood_message;
    private static String full_message;


    public static String getFull_message() {
        full_message += mainFood_message + "\n " + fastFood_message;
        full_message += "\n " +"Total Price: "+getTotal_bill();
        return full_message;
    }

    public static String getFastFood_message() {
        return fastFood_message;
    }

    public static void setFastFood_message(String fastFood_message) {
        BillManagement.fastFood_message = fastFood_message;
    }

    //setting fast food bill
    public static String getMainFood_message() {
        return mainFood_message;
    }

    public static void setMainFood_message(String mainFood_message) {
        BillManagement.mainFood_message = mainFood_message;
    }

    public static void setFastfood_bill(int fastfood_bill) {
        BillManagement.fastfood_bill = fastfood_bill;
    }

    //setting main food bill
    public static void setMainfood_bill(int mainfood_bill) {
        BillManagement.mainfood_bill = mainfood_bill;
    }

    //setting total bill
    public static void setTotal_bill() {
        BillManagement.total_bill = fastfood_bill + mainfood_bill;
    }


    /*
    public static void setSub_bill(int sub_bill) {
        BillManagement.sub_bill = sub_bill;
    }


    public static int getSub_bill() {
        return sub_bill;
    }
*/
    public static int getTotal_bill() {
        total_bill = mainfood_bill + fastfood_bill;
        return total_bill;
    }

    public static int getMainfood_bill() {
        return mainfood_bill;
    }

    public static int getFastfood_bill() {
        return fastfood_bill;
    }


}
