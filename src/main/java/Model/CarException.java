package Model;



public class CarException extends Exception{
	String message;
	public CarException(String message) {
		this.message = message;
	}
	
	private static final long serialVersionUID = 1L;
	@Override
	public String getMessage() {
		return message;
	}
}
