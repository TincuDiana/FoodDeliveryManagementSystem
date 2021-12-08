package bussinessLayer;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

public class MenuItem implements Comparable<MenuItem> {

	private String name;
	private int price;
	private int id;
	private int nrcom = 0;
	// private HashSet<BaseProduct> baseProducts = new HashSet<>();//
	// private Collection<MenuItem> menuItems = new HashSet<>();//

	public MenuItem() {
		
	}
	
	public MenuItem(int id, String name, int price) {
		this.name = name;
		this.price = price;
		this.id = id;
	}

	public MenuItem(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNrcom() {
		return nrcom;
	}

	public void setNrcom(int nrcom) {
		this.nrcom = nrcom;
	}

	@Override
	public int compareTo(MenuItem o) {
		return this.getName().compareTo(o.getName());
	}



}
