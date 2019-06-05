package Entity;

import DAO.MysqlDataBase;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Business {

    private int bid;
    private String bname;
    private String baddress;
    private Integer[] fid;
    private MysqlDataBase dataBase = new MysqlDataBase();
    private Connection conn;
    private QueryRunner runner = new QueryRunner();

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public void setBid() {
        conn = dataBase.getConn();
        try {
            this.bid = Integer.parseInt(runner.query(conn,"call getBid(?)",dataBase.getHandler(),bname).toString());
            DbUtils.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getBid() {
        return bid;
    }

    public void setFid() {
        conn = dataBase.getConn();
        try {
            List<Object[]> objects = runner.query(conn,"call getFid(?)",new ArrayListHandler(),bid);
            fid = new Integer[objects.size()];

            for (int i = 0;i<objects.size();i++)
                fid[i] = (Integer) objects.get(i)[0];
            DbUtils.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer[] getFid() {
        return fid;
    }

}
