package Entity;

import DAO.MysqlDataBase;
import Util.Cached;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class Customer {

    private int cid;
    private String cname;
    private String telephone;
    private String pwd;
    private boolean isVip;

    public boolean addCustomer(){

        MysqlDataBase dataBase = new MysqlDataBase();
        Connection conn = dataBase.getConn();
        QueryRunner runner = new QueryRunner();
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
