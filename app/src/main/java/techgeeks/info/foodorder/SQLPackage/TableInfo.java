package techgeeks.info.foodorder.SQLPackage;

/**
 * Created by Hamza Khan Niaz on 5/15/2016.
 */
public class TableInfo {

    public static final String ADDRESS_TABLE_NAME = "address_table";
    public static final String ADDRESS_COLUMN_ID = "_id";   //customer_id
    public static final String ADDRESS_COLUMN_HOUSE = "house_num";
    public static final String ADDRESS_COLUMN_STREET = "street_num";
    public static final String ADDRESS_COLUMN_SECTOR = "sector_num";
    public static final String ADDRESS_COLUMN_CITY = "city_num";
    public static final String ADDRESS_COLUMN_PHONE = "phone_num";
    public static final String ADDRESS_COLUMN_ORDER_ID = "order_num";   //foriegn key

    public static final String ORDER_TABLE_NAME = "order_table";
    public static final String ORDER_COLUMN_ID = "order_id";  //Primary key
    public static final String ORDER_COLUMN_DETAIL = "order_detail"; //to store order detail
    public static final String ORDER_COLUMN_PRICE = "order_price";
    public static final String ORDER_COLUMN_DATE = "order_date";
    public static final String ORDER_COLUMN_TIME = "order_time";
    public static final String ORDER_COLUMN_TAKEN = "order_taken";  //delivery guy selected order
    public static final String ORDER_COLUMN_DELIVERED = "order_delivered"; //yes or no

}
