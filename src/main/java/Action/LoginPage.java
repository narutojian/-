package Action;

import DAO.MysqlDataBase;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginPage {

    private int cid;
    private String pwd;


    public LoginPage(){}
    public LoginPage(int cid,String pwd) {
        super();

        this.cid = cid;
        this.pwd = pwd;
    }

    public boolean checkId(){

        MysqlDataBase dataBase = new MysqlDataBase();
        Connection conn = dataBase.getConn();
        ResultSetHandler<Object> handler = dataBase.getHandler();

        QueryRunner runner = new QueryRunner();
        Object pwd = null;
        try {
            pwd = runner.query(conn,"call queryPwd(?)",handler,cid);
            DbUtils.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pwd.equals(this.pwd);
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
