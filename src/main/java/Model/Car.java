package Model;

public class Car {
	private int id;
	private String name;
	private String placa;
	private String type;
	private int power;
	
	
	public Car(int id, String name, String placa, String type, int power) {
		this.id = id;
		this.name = name;
		this.placa = placa;
		this.power = power;
		this.type = type;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getPlaca(){
		return placa;
	}
	
	public void setPlaca(String placa){
		this.placa = placa;
	}
	
	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public int getPower(){
		return power;
	}
	
	public void setPower(int power){
		this.power = power;
	}
	
	@Override
	public String toString() {
		return id + ";" + name + ";" + placa + ";" + type + ";" + power;
	}
}
