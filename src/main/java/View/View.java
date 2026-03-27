package View;

import Controll.Controll;

public interface View{

	public void setControll(Controll controll);
	public byte [] showError(Exception e);
}
