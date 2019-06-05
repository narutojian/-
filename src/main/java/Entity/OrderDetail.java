package Entity;

import DAO.MysqlDataBase;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderDetail {

    private int odid;
    private int oid;
    private int fid;
    private int fnum;

    public void setOid(int oid) {
        this.oid = oid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public void setFnum(int fnum) {
        this.fnum = fnum;
    }

    public boolean addOderDetail(int bid){

        MysqlDataBase dataBase = new MysqlDataBase();
        Connection conn = dataBase.getConn();
        QueryRunner runner = new QueryRunner();

        int row = 0;

        try {
            row = runner.update(conn,"call addOrderDetail(?,?,?,?)",oid,fid,fnum,bid);
            DbUtils.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row != 0;
    }

}
