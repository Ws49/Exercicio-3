package App;

import static spark.Spark.*;


import Controll.Controll;
import DAOCars.PostgreBD.ManagerDAOCarsPostgreBD;
import View.ViewReponse;


public class Aplicattion {

	public static void main(String[] args) {
		Controll controllApp = new Controll(new ManagerDAOCarsPostgreBD(), new ViewReponse());
		setConfigsInit();
		
		get("/cars", (request, response) -> controllApp.getAllCars(request,response));
		get("/cars/:id", (request, response) -> controllApp.getCar(request, response));
		post("/cars", (request, response) -> controllApp.addCar(request, response));

	}
	
	private static void setConfigsInit() {
		staticFiles.location("/public");
		port(4444);	
	}

}
