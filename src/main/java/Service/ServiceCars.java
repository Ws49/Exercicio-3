package Service;

import java.util.ArrayList;
import java.util.Collection;

import DAOCars.DAOCars;
import Model.Car;
import Model.CarException;

public class ServiceCars {
	private DAOCars daoCars;
	
	public ServiceCars(DAOCars daoCars) {
		this.daoCars = daoCars;
	}
	
	public ArrayList<Car> getAllCars(){
		Collection<Car> result = daoCars.getAllCar();
		return result != null ? new ArrayList<Car>(result) : null;
	}
	
	public ArrayList<Car> getAllCarOrderByFilter(String filter){
		Collection<Car> result = daoCars.getAllCarOrderByFilter(filter);
		return result != null ? new ArrayList<Car>(result) : null;
	}
	
	public Car getCar(int id) {
		return daoCars.getCar(id);
	}
	
	private boolean checkParamsCar(Car car) throws CarException{
		if(car.getName().isEmpty() || car.getType().isEmpty() || car.getPlaca().isEmpty()) {
			throw new CarException("Erro: Dados nulos!");
		}
		
		if(!car.getPlaca().chars().anyMatch(c->Character.isLetter(c)) || !car.getPlaca().chars().anyMatch(Character::isDigit)) {
			throw new CarException("Erro: placa inválida!");
		}
		
		if(car.getPower() > 5000) {
			throw new CarException("Erro: Cilindrada inválida!");
		}
		
		return true;
	}
	
	public boolean addCar(Car car) throws CarException{
			if(checkParamsCar(car)) {
				return daoCars.addCar(car);
			}
			return false;
	}
	
	public boolean updateCar(Car car) throws CarException{
		if(checkParamsCar(car)) {
			return daoCars.updateCar(car);
		}
		return false;
	}
	
	public boolean deleteCar(int id) {
		return daoCars.deleteCar(id);
	}
}
