package DAOCars;

import java.util.Collection;

import Controll.Controll;
import Model.Car;

public interface DAOCars {
	public Car getCar(int id);
	public boolean addCar(Car car);
	public boolean UpdateCar(Car car);
	public boolean removeCar(Car car);
	public Collection<Car> getAllCar();
	public Collection<Car> getAllCarOrderByFilter(String filter);
	public boolean removeAllCars();
	public void setControll(Controll controll);
}
