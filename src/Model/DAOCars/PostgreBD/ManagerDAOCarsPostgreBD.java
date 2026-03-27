package Model.DAOCars.PostgreBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import Controll.Controll;
import Model.Car;
import Model.DAOCars.DAOCars;

enum QuerySql{
	GET("SELECT * FROM car WHERE id= ?");
	
	private String sql;
	private QuerySql(String sql) {
		this.sql = sql;
	}
	
	public String getSql() {
		return sql;
	}
	
}

public class ManagerDAOCarsPostgreBD implements DAOCars{
	ConnectBD connection;
	ArrayList<Car> cars;
	Controll controll;
	
	public ManagerDAOCarsPostgreBD(Controll controll) {
		this.controll = controll;
		connection = new ConnectBD("car", controll);
		cars = new ArrayList<Car>();
	}
	
	@Override
	public Car getCar(int id) {
		try {
			PreparedStatement getQuery = connection.getConn().prepareStatement(QuerySql.GET.getSql());
			getQuery.setInt(1, id);
			getQuery.executeUpdate();
			ResultSet result = getQuery.executeQuery();
			if(result.next()) {
				Car car = new Car(result.getInt("id"),result.getString("name"),result.getString("placa"),result.getString("type"),result.getInt("power"));
				return car;
				
			}else {
				controll.recivErrosModel(new Exception("CARRO NÃO ENCONTRADO"));
			}
			getQuery.close();
		} catch (Exception e) {
			System.out.println("CARRO NÃO ENCONTRADO :" + e.getMessage());
			controll.recivErrosModel(new Exception("CARRO NÃO ENCONTRADO :" + e.getMessage()));
		}
		
		return null;
	}

	@Override
	public Collection<Car> getAllCar(int id) {
		
		return null;
	}

	@Override
	public boolean removeAllCars(Predicate<Car> p) {
		
		return false;
	}

	@Override
	public boolean insertCar(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean UpdateCar(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Car(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
