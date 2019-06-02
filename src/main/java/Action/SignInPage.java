package Action;

import DAO.MysqlDataBase;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

public class SignIn {

    private MysqlDataBase mysqlDataBase = new MysqlDataBase();
    private Connection conn = mysqlDataBase.getConn();
    private QueryRunner runner = new QueryRunner();

    public int getLastId(){

        Object id = -1;
        try {
            id = runner.query(conn,"call getLastId()",mysqlDataBase.getHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(id.toString());
    }

    public boolean addLoginData(int cid, String pwd){

        int row = 0;

        try {
            row = runner.update(conn,"call addaddLoginData(?,?)",cid,pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row != 0;
    }

    public void closeConnection(){
        try {
            DbUtils.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
