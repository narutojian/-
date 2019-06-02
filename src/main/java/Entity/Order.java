package Entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Order {

    private int oid;
    private int cid;
    private int bid;
    private int wayofpay;
    private double discount;
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    Date date;
    private String dateTime;
    private double totalprice;//原价
    private double orderprice;//最终订单价格,除去折扣
    private ArrayList<OrderDetail> orderDetails;//订单详情

}
