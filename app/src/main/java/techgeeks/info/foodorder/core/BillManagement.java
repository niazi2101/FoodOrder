package techgeeks.info.foodorder.core;

import techgeeks.info.foodorder.SQLPackage.DBhandler;

/**
 * Created by Hamza Khan Niaz on 5/14/2016.
 */
public class BillManagement {
    //DBhandler dbhandler = new DBhandler(this);
    private static int sub_bill = 0;
    private static int mainfood_bill = 0;   //bill from main food activity
    private static int fastfood_bill = 0;   //bill from fast food activity
    private static int total_bill = 0;

    private static int orderNum;
    private static boolean checkMainFood = false,checkFastFood = false;

    private static String mainFood_message ;
    private static String fastFood_message ;
    private static String full_message ;
    private static String strDateTime;

    public static String getStrDateTime() {
        return strDateTime;
    }

    public static void setStrDateTime(String strDateTime) {
        BillManagement.strDateTime = strDateTime;
    }

    public static String getFull_message() {
        /*
        //if main food  null
        if(mainFood_message == null )
        {
            //if fastfood is not null
            if(fastFood_message == null )
            {
                full_message =   fastFood_message;
            }
        }// if main food is not null
        else
        //if(mainFood_message == null && mainFood_message.isEmpty() )
        {
            full_message = mainFood_message ;   //add main food message
            //if fastfood is not null
            if(fastFood_message != null && !fastFood_message.isEmpty())
            {
                full_message += "\n" + fastFood_message;
            }



        }
        */
        if(checkMainFood = true)
        {
            full_message = mainFood_message;
            if(checkFastFood = true)
            {
                full_message += "\n " +fastFood_message;
            }
        }
        else
        {
            full_message = fastFood_message;
        }

        if(full_message != null && !full_message.isEmpty())
        {
            full_message += "\n " + "Total Price: " + getTotal_bill() + " Rs";
        }
        //full_message = mainFood_message + "\n " + fastFood_message;
        //full_message += "\n " + "Total Price: " + getTotal_bill() + " Rs";
        return full_message;
    }

    public static String getFastFood_message() {
        return fastFood_message;
    }

    public static void setFastFood_message(String fastFood_message) {

        checkFastFood = fastFood_message.length() > 1;
        BillManagement.fastFood_message = fastFood_message;
    }

    //setting fast food bill
    public static String getMainFood_message() {
        return mainFood_message;
    }

    public  static void setMainFood_message(String mainFood_message) {
        if(mainFood_message.length() > 1)
        {checkMainFood = true;}
        else
        {
            checkMainFood = false;
        }
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

    /*
    public void insertAddress(DBhandler handle, String house, String street,
                                 String sector, String city, String phone, int orderNum) {
        //dbhandler = new DBhandler();

        handle.insertAddress(handle,house,street,sector,city,phone,orderNum);
        //return true;
    }*/



    public void setOrderNum(int orderNum)
    {
        this.orderNum = orderNum;
    }

    public static int getOrderNum()
    {
        return orderNum;
    }
}