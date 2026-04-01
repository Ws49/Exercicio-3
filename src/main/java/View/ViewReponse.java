package View;


import java.util.ArrayList;
import java.util.HashMap;

import Controll.Controll;
import Model.Car;
import spark.ModelAndView;

public class ViewReponse implements View{
	Controll controll;
	HashMap<String, Object> values;
	
	public byte [] toJsonCars(ArrayList<Car> cars){
		String json = new String();
		json += "[\n" ;
		for(int i =0; i < cars.size(); i++) {
			json += "	{\n" ;
			json += "		\"id\" : " + cars.get(i).getId() + ",\n";
			json += "		\"name\" : " + "\"" + cars.get(i).getName() + "\"," + "\n";
			json += "		\"placa\" : " + "\"" + cars.get(i).getPlaca() + "\"," + "\n";
			json += "		\"type\" : " + "\"" + cars.get(i).getType() + "\"," + "\n";
			json += "		\"power\" : "  + cars.get(i).getPower() + "\n";
			json += i != cars.size() - 1 ? "	},\n" : "	}\n";
		}
		json += "]" ;
		return json.getBytes();
	}
	
	public ModelAndView toPageCar(Car car) {
		values = new HashMap<String, Object>();
		values.put("id",car.getId());
		values.put("name",car.getName());
		values.put("placa",car.getPlaca());
		values.put("type",car.getType());
		values.put("power",car.getPower());
		values.put("photo",car.getPhoto());
		return new ModelAndView(values, "car.hbs");
	}
	
	public ModelAndView toPageStatus(String title,String message) {
		values = new HashMap<String, Object>();
		values.put("title",title);
		values.put("message",message);
		return new ModelAndView(values, "status.hbs");		
	}

	
	@Override
	public void setControll(Controll controll) {
		this.controll = controll;
	}

	@Override
	public byte [] showError(Exception e) {
			return e.getMessage().getBytes();
	}



	
}
