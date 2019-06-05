package Entity;

import DAO.MysqlDataBase;
import Util.Cached;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Order {

    private int oid;
    private int cid;
    private int bid;
    private int wid;
    private double discount;
    private String dateTime;
    private double totalprice;//原价
    private double orderprice;//最终订单价格,除去折扣
    private ArrayList<OrderDetail> orderDetails;//订单详情

    public void setWid(int wid) {
        this.wid = wid;
    }

    public void setDateTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        this.dateTime = sdf.format(date);
    }

    public void setDiscount() {
        Random random = new Random();
        int index = random.nextInt(PayDetail.discount.length);
        this.discount = PayDetail.discount[index];
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public void setOrderprice() {
        this.orderprice = totalprice*discount/10;
    }

    public double getDiscount() {
        return discount;
    }

    public double getOrderprice() {
        return orderprice;
    }

    public boolean addOrder(){
        MysqlDataBase dataBase = new MysqlDataBase();
        Connection conn = dataBase.getConn();
        QueryRunner runner = new QueryRunner();

        int row = -1;

        try {
            row = runner.update(conn,"call addOrder(?,?,?,?,?,?,?)",bid,wid,cid,dateTime,totalprice,discount,orderprice);
            Object object = runner.query(conn,"call getLastId()",dataBase.getHandler());
            this.oid = Integer.parseInt(object.toString());
            Cached.setOid(this.oid);
            DbUtils.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row != -1;
    }
}
