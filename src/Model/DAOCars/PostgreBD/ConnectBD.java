package Model.DAOCars.PostgreBD;

import java.sql.*;

import Controll.Controll;

public class ConnectBD {
	private Connection conn;
	private String myDataBase;
	private Controll controll;
	public ConnectBD(String nameBD, Controll controll) {
		this.myDataBase = nameBD;
		this.conn = null;
		this.controll = controll;
		connect();
	}
	
	private void connect(){
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		int port = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + port +"/" + myDataBase;
		String username = "ti2cc";
		String password = "ti@cc";

		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);
			if(conn == null) { 
					controll.recivErrosModel(new Exception("Connection is NULL"));
					return;
			}
			controll.recivSucessModel("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.out.println("controll.recivErrosModel(new Exception(\"Conexão NÃO efetuada com o postgres -- Driver não encontrado -- \" "+ e.getMessage());
			controll.recivErrosModel(new Exception("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage()));
		} catch (SQLException e) {
			controll.recivErrosModel(new Exception("Conexão NÃO efetuada com o postgres -- " + e.getMessage()));
		}

	}
	public void sendErrorView(Exception e) {
		controll.recivErrosModel(e);
	}
	public boolean close() {
		try {
			conn.close();
			return conn.isClosed();
		}catch (Exception e) {
			controll.recivErrosModel(new Exception("Connection not closed ERROR"));
			return false;
		}
	}
	
	public Connection getConn() {
		return conn;
	}
}
