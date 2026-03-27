package View;


import java.util.ArrayList;

import Controll.Controll;
import Model.Car;

public class ViewReponse implements View{
	Controll controll;
	
	public byte [] toJson(ArrayList<Car> cars){
		String json = new String();
		for(Car c : cars) {
			json += c.getName();
		}
		return json.getBytes();
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
