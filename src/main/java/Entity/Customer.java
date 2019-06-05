package Entity;

import DAO.MysqlDataBase;
import Util.Cached;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class Customer {

    private int cid;
    private String cname;
    private String telephone;
    private String pwd;
    private boolean isVip;
    private MysqlDataBase dataBase = new MysqlDataBase();
    private Connection conn = null;
    private QueryRunner runner = new QueryRunner();

    public boolean addCustomer(){

        Connection conn = dataBase.getConn();
        int row = 0;
        try {
            row = runner.update(conn,"call addCustomer(?,?)",cname,telephone);

            if (Cached.getCid() != -1)
                this.cid = Cached.getCid()+1;
            else setCid(conn,runner,dataBase.getHandler());
            DbUtils.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row != 0;
    }

    public int getCid() {
        return cid;
    }

    private void setCid(Connection conn, QueryRunner runner, ResultSetHandler<Object> handler) {

        try {
            this.cid = Integer.parseInt(runner.query(conn,"call getLastId()",handler).toString());
            Cached.setCid(this.cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public boolean modifyCustomer(String s1, int choice){

        conn = dataBase.getConn();
        int row = -1;
        switch (choice){
            case 1:
                row = updateCustomer(row,conn,"call modPwd(?,?)",s1);
                break;
            case 2:
                row = updateCustomer(row,conn,"call modTel(?,?)",s1);
                break;
        }

        return row != -1;
    }

    public boolean modifyCustomer(String s1,String s2){
        conn = dataBase.getConn();
        int row = -1;
        try {
            runner.update(conn,"call modPwd(?,?)",cid,s1);
            row = runner.update(conn,"call modTel(?,?)",cid,s2);
            DbUtils.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row != -1;
    }

    public void Display(){

        if (cname == null && telephone == null) {
            conn = dataBase.getConn();
            try {
                Object[] objects = runner.query(conn,"call showCustomer(?)",new ArrayHandler(),cid);
                this.cname = (String) objects[1];
                this.telephone = (String) objects[2];
                this.isVip = (boolean) objects[3];
                DbUtils.close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("account: "+cid);
        System.out.println("name: "+cname);
        System.out.println("telephone: "+telephone);
        System.out.println("Vip: "+isVip);
    }

    private int updateCustomer(int row, Connection conn,String sql,String s1){
        try {
            row = runner.update(conn,sql,cid,s1);
            DbUtils.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }
}
