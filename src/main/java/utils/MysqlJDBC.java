package utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MysqlJDBC {
	private Context initalContext;

	private static MysqlJDBC mysqljdbc = new MysqlJDBC(); // new出一個靜態 MysqlJDBC 物件

	public static MysqlJDBC getInstance() { //呼叫 MysqlJDBC.getInstance() 靜態方法 取得 靜態物件
		return mysqljdbc;
	}

	private MysqlJDBC() {
		try {
			this.initalContext = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public DataSource getDataSource() {
		DataSource dataSource = null;
		try {
//			Context ctx = (Context) initalContext.lookup("java:comp/env");
//			dataSource = (DataSource) ctx.lookup("jdbc/cga102g4");
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/cga102g4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return dataSource;
	}
	
	public DataSource getDataSource(String dataSourceName) {
		DataSource datasource = null;
		try {
			Context ctx = (Context) initalContext.lookup("java:comp/env");
			datasource = (DataSource) ctx.lookup(dataSourceName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return datasource;
	}
}
