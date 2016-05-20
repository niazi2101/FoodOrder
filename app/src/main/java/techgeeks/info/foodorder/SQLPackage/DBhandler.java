package techgeeks.info.foodorder.SQLPackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Hamza Khan Niaz on 5/15/2016.
 */
public class DBhandler extends SQLiteOpenHelper {

    //Database name
    public static final String DATABASE_NAME = "FoodInfo2101";
    private static final int DATABASE_VERSION = 1;

    //Address Table Name
    public static final String ADDRESS_TABLE_NAME = "address_table";
    public static final String ADDRESS_COLUMN_ID = "_id";   //customer_id
    public static final String ADDRESS_COLUMN_HOUSE = "house_num";
    public static final String ADDRESS_COLUMN_STREET = "street_num";
    public static final String ADDRESS_COLUMN_SECTOR = "sector_num";
    public static final String ADDRESS_COLUMN_CITY = "city_num";
    public static final String ADDRESS_COLUMN_PHONE = "phone_num";
    public static final String ADDRESS_COLUMN_ORDER_ID = "order_num";   //foriegn key

    //Order Table Name
    public static final String ORDER_TABLE_NAME = "order_table";
    public static final String ORDER_COLUMN_ID = "order_id";  //Primary key
    public static final String ORDER_COLUMN_ORDER_ID = "order_num";  //To identify each order

    public static final String ORDER_COLUMN_DETAIL = "order_detail"; //to store order detail
    public static final String ORDER_COLUMN_PRICE = "order_price";
    public static final String ORDER_COLUMN_PLACING_TIME = "order_Ptime"; // time when order was placed
    public static final String ORDER_COLUMN_DELIVERING_TIME = "order_Dtime";   // time when order delivered
    //public static final String ORDER_COLUMN_TAKEN = "order_taken";  //delivery guy selected order
    public static final String ORDER_COLUMN_DELIVERED = "order_delivered"; //yes or no


    // Address_Table Create Statement
    private static final String CREATE_ADDRESS_TABLE = "CREATE TABLE IF NOT EXISTS "
            + ADDRESS_TABLE_NAME + "(" + ADDRESS_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL," +
            ADDRESS_COLUMN_HOUSE + " TEXT," + ADDRESS_COLUMN_STREET + " TEXT," +
            ADDRESS_COLUMN_SECTOR + " TEXT," + ADDRESS_COLUMN_CITY + " TEXT," +
            ADDRESS_COLUMN_PHONE + " TEXT," + ADDRESS_COLUMN_ORDER_ID + " NUMBER"
            + ")";

    // Order_Table Create Statement
    private static final String CREATE_ORDER_TABLE = "CREATE TABLE IF NOT EXISTS "
            + ORDER_TABLE_NAME + "(" + ORDER_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL,"
            + ORDER_COLUMN_ORDER_ID + " INTEGER,"
            + ORDER_COLUMN_DETAIL + " TEXT," + ORDER_COLUMN_PRICE + " TEXT,"
            + ORDER_COLUMN_PLACING_TIME + " TEXT," + ORDER_COLUMN_DELIVERING_TIME + " TEXT,"
            + ORDER_COLUMN_DELIVERED + " INTEGER" + ")";


    public DBhandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_ADDRESS_TABLE);

        db.execSQL(CREATE_ORDER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ADDRESS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_TABLE_NAME);

        onCreate(db);
    }

    //Function to add a new address to database
    public boolean insertAddress(DBhandler handle,String house, String street,
                                String sector, String city,String phone,int order) {
        SQLiteDatabase db = handle.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ADDRESS_COLUMN_HOUSE, house);
        contentValues.put(ADDRESS_COLUMN_STREET, street);
        contentValues.put(ADDRESS_COLUMN_SECTOR, sector);
        contentValues.put(ADDRESS_COLUMN_CITY, city);
        contentValues.put(ADDRESS_COLUMN_PHONE, phone);
        contentValues.put(ADDRESS_COLUMN_ORDER_ID, order);

        db.insert(ADDRESS_TABLE_NAME, null, contentValues);

        return true;
    }

    //Function for updating Address
    public boolean updateAddress(Integer id,String house, String street,
                                 String sector, String city,String phone,int order) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(ADDRESS_COLUMN_HOUSE, house);
        contentValues.put(ADDRESS_COLUMN_STREET, street);
        contentValues.put(ADDRESS_COLUMN_SECTOR, sector);
        contentValues.put(ADDRESS_COLUMN_CITY, city);
        contentValues.put(ADDRESS_COLUMN_PHONE, phone);
        contentValues.put(ADDRESS_COLUMN_ORDER_ID, order);


        db.update(ADDRESS_TABLE_NAME, contentValues, ADDRESS_COLUMN_ID + " = ? ",
                new String[]{Integer.toString(id)});
        return true;

    }

    //Function to get address of a person on the basis of his ID
    public Cursor getAddress(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + ADDRESS_TABLE_NAME + " WHERE " +
                ADDRESS_COLUMN_ID + "=?", new String[]{Integer.toString(id)});

        return res;
    }

    //Function to get all addresses
    public Cursor getAllAddress() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + ADDRESS_TABLE_NAME, null);
        return res;
    }

    //Function to delete address
    public Integer deleteAddress(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(ADDRESS_TABLE_NAME,
                ADDRESS_COLUMN_ID + " = ? ",
                new String[]{Integer.toString(id)});
    }

    /*
        ALL FUNCTIONS BELOW THIS POINT ARE FOR ORDERS

         */

    //Function to add a new Order to database
    public boolean insertOrder(DBhandler handle,int ordernum,String detail, String price,
                                String placingTime, String deliveryTime,int delivered) {
        long check = -1;
        boolean ch = false;
        try {
            SQLiteDatabase db = handle.getWritableDatabase();

            ContentValues contentValues = new ContentValues();

            contentValues.put(ORDER_COLUMN_ORDER_ID, ordernum);
            contentValues.put(ORDER_COLUMN_DETAIL, detail);
            contentValues.put(ORDER_COLUMN_PRICE, price);
            contentValues.put(ORDER_COLUMN_PLACING_TIME, placingTime);
            contentValues.put(ORDER_COLUMN_DELIVERING_TIME, deliveryTime);
            contentValues.put(ORDER_COLUMN_DELIVERED, delivered);


            check = db.insert(ORDER_TABLE_NAME, null, contentValues);
            if(check == 1)
            {
                ch = true;
            }
            else
            {
                ch = false;
            }
        }
        catch(Exception e)
        {
            Log.e("INSERERROR", e.getMessage());
        }
        return ch;

    }

    //Function for updating Order
    public boolean updateOrder(Integer id,String detail, String price,
                               String placingTime, String deliveryTime,int delivered) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(ORDER_COLUMN_DETAIL, detail);
        contentValues.put(ORDER_COLUMN_PRICE, price);
        contentValues.put(ORDER_COLUMN_PLACING_TIME, placingTime);
        contentValues.put(ORDER_COLUMN_DELIVERING_TIME, deliveryTime);
        contentValues.put(ORDER_COLUMN_DELIVERED, delivered);


        db.update(ORDER_TABLE_NAME, contentValues, ORDER_COLUMN_ID + " = ? ",
                new String[]{Integer.toString(id)});
        return true;

    }

    //Function to get Order of a person on the basis of his ID
    public Cursor getOrder(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + ORDER_TABLE_NAME + " WHERE " +
                ORDER_COLUMN_ID + "=?", new String[]{Integer.toString(id)});

        return res;
    }

    //Function to get all Order
    public Cursor getAllOrders() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + ORDER_TABLE_NAME, null);
        return res;
    }

    //Function to delete Order
    public Integer deleteOrder(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(ADDRESS_TABLE_NAME,
                ADDRESS_COLUMN_ID + " = ? ",
                new String[]{Integer.toString(id)});
    }



}
