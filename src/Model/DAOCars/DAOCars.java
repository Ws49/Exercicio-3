package Model.DAOCars;

import java.util.Collection;
import java.util.function.Predicate;

import Model.Car;

public interface DAOCars {
	public Car getCar(int id);
	public boolean insertCar(int id);
	public boolean UpdateCar(int id);
	public Collection<Car> getAllCar(int id);
	public boolean 	Car(int id);
	public boolean removeAllCars(Predicate<Car> p);
}
