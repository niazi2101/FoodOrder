package techgeeks.info.foodorder.core;

/**
 * Created by Hamza Khan Niaz on 5/14/2016.
 */
public class BillManagement {
    private static int sub_bill = 0;
    private static int mainfood_bill = 0;   //bill from main food activity
    private static int fastfood_bill = 0;   //bill from fast food activity
    private static int total_bill = 0;

    //setting fast food bill
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


    public static void setSub_bill(int sub_bill) {
        BillManagement.sub_bill = sub_bill;
    }


    public static int getSub_bill() {
        return sub_bill;
    }

    public static int getTotal_bill() {
        return total_bill;
    }

    public static int getMainfood_bill() {
        return mainfood_bill;
    }

    public static int getFastfood_bill() {
        return fastfood_bill;
    }


}
