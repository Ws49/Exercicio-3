package Service;

import java.util.ArrayList;
import java.util.Collection;

import DAOCars.DAOCars;
import Model.Car;

public class ServiceCars {
	DAOCars daoCars;
	
	public ServiceCars(DAOCars daoCars) {
		this.daoCars = daoCars;
	}
	
	public ArrayList<Car> getAllCars(){
		Collection<Car> result = daoCars.getAllCar();
		return result != null ? new ArrayList<Car>(result) : null;
	}
	
	public Collection<Car> getAllCarOrderByFilter(String filter){
		return null;
	}
	
	public Car getCar(int id) {
		return daoCars.getCar(id);
	}
	
	public boolean addCar(Car car) {
			if((!car.getName().isEmpty() && !car.getName().equals("null")) && (!car.getType().isEmpty() && !car.getType().equals("null")) && (!car.getPlaca().isEmpty() && !car.getPlaca().equals("null"))) {
				return daoCars.addCar(car);
			}
			return false;
	}
	
	public boolean UpdateCar(Car car) {
		return false;
	}
	
	public boolean deleteCar(Car car) {
		return false;
	}
}
