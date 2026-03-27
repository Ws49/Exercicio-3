package Controll;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.jetty.util.ajax.JSON;

import DAOCars.DAOCars;
import Model.Car;
import Service.ServiceCars;
import View.ViewReponse;
import spark.Request;
import spark.Response;


public class Controll {
	private DAOCars sourceCars;
	private ViewReponse view;
	private ServiceCars service;
	
	public Controll(DAOCars origimDataCars, ViewReponse view){
		
		this.sourceCars = origimDataCars;
		this.view = view;
		this.sourceCars.setControll(this);
		this.view.setControll(this);
		this.service = new ServiceCars(sourceCars);
	}
	
	
	public Object getAllCars(Request request, Response response) {
		response.type("text/html");
		ArrayList<Car> cars = service.getAllCars();
		return cars == null ? "<h1>Erro ao encontrar os carros</h1>" : view.toJson(cars);
	}
	
	public Object getCar(Request request, Response response) {
		response.type("text/html");
		Car car = service.getCar(Integer.valueOf(request.params(":id")));
		return car == null ? "<h1>Erro carro não cadastrado</h1>" : car.getName();
	}
	
	@SuppressWarnings("unchecked")
	public Object addCar(Request request, Response response) {
		response.type("text/html");
		HashMap<String,Object> values = (HashMap<String, Object>)JSON.parse(request.body());
		int power = 0;
		if(!String.valueOf(values.get("power")).equals("null")) {
			power = Integer.valueOf(String.valueOf(values.get("power")));
		}
		
		boolean result = false;
		
		if(power != 0) {
			
			result = service.addCar(
				new Car(0,
						(String.valueOf(values.get("name"))),
						String.valueOf(values.get("placa")),
						String.valueOf(values.get("type")),
						power));
		}
		
		if(result) {
			response.status(201);
			return "{\"message\" : \"Adicionado com sucesso!\"}";
		}else {
			response.status(400);
			return "{\"message\" : \"Falha ao adiconar carro\"}";
		}
	}
	
	
	public void recivErrosDAO(Exception e) {
		System.out.println(new String(view.showError(e)));
		e.printStackTrace();
	}
	
}