package Action;

import DAO.MysqlDataBase;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

public class RegisterPage {

    public boolean addLoginData(int cid, String pwd){

        MysqlDataBase mysqlDataBase = new MysqlDataBase();
        Connection conn = mysqlDataBase.getConn();
        QueryRunner runner = new QueryRunner();
        int row = 0;

        try {
            row = runner.update(conn,"call addLoginData(?,?)",cid,pwd);
            DbUtils.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row != 0;
    }
}
