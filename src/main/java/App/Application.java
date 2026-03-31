package App;

import static spark.Spark.*;


import Controll.Controll;
import DAOCars.PostgreBD.ManagerDAOCarsPostgreBD;
import View.ViewReponse;



public class Application {

	public static void main(String[] args) {
		Controll controllApp = new Controll(new ManagerDAOCarsPostgreBD(), new ViewReponse());
		setConfigsInit();
		
		get("/cars", (request, response) -> controllApp.getAllCars(request,response));
		get("/car/:id", (request, response) -> controllApp.getCar(request, response), Controll.getHandleEngine());
		post("/cars", (request, response) -> controllApp.addCar(request, response),Controll.getHandleEngine());
		post("/car/update/:id", (request, response) -> controllApp.updateCar(request, response), Controll.getHandleEngine());
		post("/car/delete/:id", (request, response) -> controllApp.deleteCar(request, response), Controll.getHandleEngine());
		
	}
	
	private static void setConfigsInit() {
		staticFiles.location("/public");
		port(4444);	
	}

}
