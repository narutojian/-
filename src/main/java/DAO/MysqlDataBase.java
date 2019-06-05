package DAO;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.*;

public class MysqlDataBase {

//    private Connection conn = null;
    private ResultSetHandler<Object> handler = null;
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/canteen?serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&useSSL=false";
    private final String user = "root";
    private final String pwd = "o19980822235";

    public Connection getConn(){
        Connection conn = null;
        try {
            DbUtils.loadDriver(driver);
            conn = DriverManager.getConnection(url,user,pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public ResultSetHandler<Object> getHandler() {

        handler = new ResultSetHandler<Object>() {
            public Object handle(ResultSet rs) throws SQLException {

                if (!rs.next())
                    return null;

//                ResultSetMetaData meta = rs.getMetaData();
//                int cols = meta.getColumnCount();
//                Object[] result = new Object[cols];
//                for (int i = 0;i<cols;i++)
//                    result[i] = rs.getObject(i+1);

                Object result = rs.getObject(1);
                return result;
            }
        };
        return handler;
    }


}
