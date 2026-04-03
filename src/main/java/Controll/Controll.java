package Controll;

import java.util.ArrayList;

import DAOCars.DAOCars;
import Model.Car;
import Service.ServiceCars;
import View.ViewReponse;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class Controll {
	private DAOCars sourceCars;
	private ViewReponse view;
	private ServiceCars service;
	
	public static HandlebarsTemplateEngine getHandleEngine() {
		return new HandlebarsTemplateEngine();
	}
	
	public Controll(DAOCars origimDataCars, ViewReponse view){
		this.sourceCars = origimDataCars;
		this.view = view;
		this.sourceCars.setControll(this);
		this.view.setControll(this);
		this.service = new ServiceCars(sourceCars);
	}
	
	public Object getAllCars(Request request, Response response) {
		response.type("application/json");
		
		String params = request.queryParams("sort");
		ArrayList<Car> cars;
		
		if(params == null) {
			cars = service.getAllCars();
		}else {
			 cars = service.getAllCarOrderByFilter(request.queryParams("sort"));
		}
		
		if(cars == null) {
			response.status(400);
			return  "{\"message\" : \"Falha ao ler os carros\"}";
		}else {
			response.status(200);
			return view.toJsonCars(cars);
		}
	}
	

	
	public ModelAndView getCar(Request request, Response response) {
		response.type("text/html");
		Car car = service.getCar(Integer.valueOf(request.params(":id")));
		if(car == null) {
			response.status(400);
			return  view.toPageStatus("Erro","Carro não encontrado");
		}else {
			response.status(200);
			return view.toPageCar(car); 
		}
	}
	

	public ModelAndView addCar(Request request, Response response) {
		response.type("text/html");
		try {
			int power = Integer.valueOf(request.queryParams("power"));
			service.addCar(new Car(0, request.queryParams("name"), request.queryParams("placa"), request.queryParams("type"), request.queryParams("photo"),power));
		}catch (Exception e) {
			return  view.toPageStatus("Erro",e.getMessage());
		}
		return view.toPageStatus("Cadastrado","Carro cadastrado com sucesso!");	
	}
	
	public ModelAndView deleteCar(Request request, Response response) {
		response.type("text/html");
		boolean result = service.deleteCar(Integer.valueOf(request.params(":id")));
		if(!result) {
			response.status(400);
			return  view.toPageStatus("Erro","Ocorreu um erro ao deletar o carro");
		}else {
			response.status(200);
			return  view.toPageStatus("Deletado","Carro deletado!"); 
		}
	}
	
	public ModelAndView updateCar(Request request, Response response) {
		response.type("text/html");
	
		try {
			service.updateCar(new Car(Integer.valueOf(request.params(":id")),request.queryParams("name"),request.queryParams("placa"),request.queryParams("type"),request.queryParams("photo"),Integer.valueOf(request.queryParams("power"))));
		}catch (Exception e) {
			response.status(400);
			return  view.toPageStatus("Erro",e.getMessage());
		}

		response.status(200);
		return  view.toPageStatus("Atualizado","carro atualizado com sucesso"); 
		
	}
	
	public void recivErrosDAO(Exception e) {
		System.out.println(new String(view.showError(e)));
	}
	
}