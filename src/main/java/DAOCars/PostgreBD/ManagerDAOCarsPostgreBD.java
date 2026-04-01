package DAOCars.PostgreBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;


import Controll.Controll;
import DAOCars.DAOCars;
import Model.Car;
import Model.CarException;

enum QuerySql{
	GET("SELECT * FROM car WHERE id = ?"),
	GET_ORDERBY("SELECT * FROM car ORDER BY "),
	GETALL("SELECT * FROM car"),
	INSERT("INSERT INTO car(name,placa,type,power,photo) VALUES (?,?,?,?,?)"),
	UPDATE("UPDATE car SET name=?, placa=?, type=?, power=?, photo=? WHERE id = ?"),
	DELETE("DELETE FROM car WHERE id = ?"),
	DELETEALL("DELETE FROM car");
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
	
	public ManagerDAOCarsPostgreBD() {
		cars = new ArrayList<Car>();
	}
	
	public void setControll(Controll controll) {
		this.controll = controll;
		connection = new ConnectBD("teste", controll);
	}
	
	@Override
	public Car getCar(int id) {
		try {
			PreparedStatement getQuery = connection.getConn().prepareStatement(QuerySql.GET.getSql());
			getQuery.setInt(1, id);
			ResultSet result = getQuery.executeQuery();
			if(result.next()) {
				Car car = new Car(result.getInt("id"),result.getString("name"),result.getString("placa"),result.getString("type"),result.getString("photo"),result.getInt("power"));
				return car;
				
			}else {
				controll.recivErrosDAO(new Exception("CARRO NÃO ENCONTRADO"));
			}
			getQuery.close();
		} catch (Exception e) {
			controll.recivErrosDAO(new Exception("CARRO NÃO ENCONTRADO :" + e.getMessage()));
		}
		
		return null;
	}

	public Collection<Car> findCarsBD(QuerySql sql, String params) {
		try {

			PreparedStatement query;
			
			if(!params.isEmpty()) {
				query = connection.getConn().prepareStatement(sql.getSql() + params);
			}else {
				query = connection.getConn().prepareStatement(sql.getSql());
			}
			
			
			ResultSet result = query.executeQuery();
			cars = new ArrayList<Car>();
	
			while(result.next()) {				
				Car newCar = new Car(result.getInt("id"),result.getString("name"),result.getString("placa"),result.getString("type"),result.getString("photo"),result.getInt("power"));
				cars.add(newCar);
			}
			
			query.close();
			return cars;
			
		} catch (Exception e) {
			controll.recivErrosDAO(new Exception("CARROS NÃO ENCONTRADOS :" + e.getMessage()));
		}
		return null;
	}
	
	@Override
	public Collection<Car> getAllCar() {
		return findCarsBD(QuerySql.GETALL,"");
	}
	
	@Override
	public Collection<Car> getAllCarOrderByFilter(String filter) {
		return findCarsBD(QuerySql.GET_ORDERBY,filter);
	}


	@Override
	public boolean addCar(Car car) throws CarException{
		try {

			PreparedStatement query = connection.getConn().prepareStatement(QuerySql.INSERT.getSql());
			query.setString(1, car.getName());
			query.setString(2, car.getPlaca());
			query.setString(3, car.getType());
			query.setInt(4, car.getPower());
			query.setString(5, car.getPhoto());
			int result =  query.executeUpdate();
			query.close();
			return result == 1 ?  true :  false;
			
		} catch (Exception e) {
			if(e.getMessage().contains("duplicate key value")) {
				throw new CarException("Erro ao adicionar carro: está placa já se encontra cadastrada!");
			}else {
				controll.recivErrosDAO(new Exception("ERRO AO ADICIONAR CARRO :" + e.getMessage()));
				throw new CarException("Erro ao adicionar carro");
			}
		}
	}

	@Override
	public boolean updateCar(Car car) throws CarException{
		try {

			PreparedStatement query = connection.getConn().prepareStatement(QuerySql.UPDATE.getSql());
			query.setString(1, car.getName());
			query.setString(2, car.getPlaca());
			query.setString(3, car.getType());
			query.setInt(4, car.getPower());
			query.setString(5, car.getPhoto());
			query.setInt(6, car.getId());
			
			int result = query.executeUpdate();
			query.close();
			return result == 1 ?  true :  false;
			
		} catch (Exception e) {
			if(e.getMessage().contains("duplicate key value")) {
				throw new CarException("Erro ao atualizar carro: está placa já se encontra cadastrada!");
			}else {
				controll.recivErrosDAO(new Exception("ERRO AO ATUALIZAR CARRO :" + e.getMessage()));
				throw new CarException("Erro ao atualizar carro");
			}
		}
	}

	@Override
	public boolean deleteCar(int id) {
		try {

			PreparedStatement query = connection.getConn().prepareStatement(QuerySql.DELETE.getSql());
			query.setInt(1, id);
			int result = query.executeUpdate();
			query.close();
			return result == 1 ?  true :  false;
			
		} catch (Exception e) {
			controll.recivErrosDAO(new Exception("ERRO AO REMOVER O CARRO :" + e.getMessage()));
		}
		return false;
	}

	
	@Override
	public boolean removeAllCars() {
		try {

			PreparedStatement query = connection.getConn().prepareStatement(QuerySql.DELETEALL.getSql());
			int result = query.executeUpdate();
			query.close();
			return result == 1 ?  true :  false;
			
		} catch (Exception e) {
			controll.recivErrosDAO(new Exception("ERRO AO REMOVER TODOS OS CARROS :" + e.getMessage()));
		}
		return false;
	}

}
