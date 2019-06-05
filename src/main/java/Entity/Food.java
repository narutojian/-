package Entity;

import DAO.MysqlDataBase;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class Food {

    private int fid;
    private int bid;
    private String fname;
    private double fprice;
    private int fstorage;
    private String foodcategory;//对应FoodCategory中的索引号
    private String supprotTime;

    public void setFood(){
        Object[] data;
        String[] time;
        MysqlDataBase dataBase = new MysqlDataBase();
        Connection conn = dataBase.getConn();
        QueryRunner runner = new QueryRunner();

        try {
            data = runner.query(conn,"call showBusFood(?,?)",new ArrayHandler(),bid,fid);
            List<Object[]> objects = runner.query(conn,"call getSName(?,?)",new ArrayListHandler(),bid,fid);
            time = new String[objects.size()];

            for (int i = 0;i<objects.size();i++)
                time[i] = objects.get(i)[0].toString();

            this.fname = (String) data[0];
            this.fprice = (double) data[1];
            this.fstorage = (int) data[2];
            this.foodcategory = (String) data[3];

            HashMap<String,Integer> map = new HashMap<>();
            map.put("Breakfast",1);
            map.put("Lunch",2);
            map.put("Dinner",3);

            this.supprotTime = "";
            for (int i = 0;i<time.length-1;i++)
                this.supprotTime += SupportTime.supprotTime[map.get(time[i])-1]+",";
            this.supprotTime += SupportTime.supprotTime[map.get(time[time.length-1])-1];

            DbUtils.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

//    public String  showInfo(){
//        return fid+"        "+fname+"       "+fprice+"      "+fstorage+"        "+supprotTime;
//    }

    public String getFoodcategory() {
        return foodcategory;
    }

    public int getBid() {
        return bid;
    }

    public double getFprice() {
        return fprice;
    }

    public int getFstorage() {
        return fstorage;
    }

    public String getFname() {
        return fname;
    }

    public String getSupprotTime() {
        return supprotTime;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getFid() {
        return fid;
    }
}
