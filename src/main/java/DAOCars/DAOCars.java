package DAOCars;

import java.util.Collection;

import Controll.Controll;
import Model.Car;
import Model.CarException;

public interface DAOCars {
	public Car getCar(int id);
	public boolean addCar(Car car) throws CarException;
	public boolean updateCar(Car car) throws CarException;
	public boolean deleteCar(int id);
	public Collection<Car> getAllCar();
	public Collection<Car> getAllCarOrderByFilter(String filter);
	public boolean removeAllCars();
	public void setControll(Controll controll);
}
