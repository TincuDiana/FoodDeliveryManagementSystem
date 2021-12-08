package bussinessLayer;

import java.util.ArrayList;
import java.util.List;

public class Clientt extends User{
	private int nrOrders=0;
	private List<Order> comenzi=new ArrayList<Order>();
	
	public Clientt() {
		super();
	}
	
	public int getNrOrders() {
		return nrOrders;
	}

	public void setNrOrders(int nrOrders) {
		this.nrOrders = nrOrders;
	}

	public List<Order> getComenzi() {
		return comenzi;
	}

	public void setComenzi(List<Order> comenzi) {
		this.comenzi = comenzi;
	}
	
	
}
