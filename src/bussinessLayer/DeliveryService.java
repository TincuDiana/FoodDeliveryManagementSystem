package bussinessLayer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import dataLayer.Serializator;
import dataLayer.WriteFile;
import presentationLayer.AdaugareProdus;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing {
	private HashSet<BaseProduct> baseProducts = new HashSet<>();
	private Collection<MenuItem> menuItems = new HashSet<>();
	private Map<Order, Collection<MenuItem>> orderInfo = new HashMap<>();
	private List<Order> orderList = new ArrayList<>();
	private int currentId = 0;
	private int idClient = 0;
	private int idComanda = 0;
	private int newOrder = 1;
	private List<MenuItem> produseComandate = new ArrayList<MenuItem>();
	private Clientt client = new Clientt();
	private List<Clientt> listaClienti = new ArrayList<Clientt>();
	private List<CompositeProduct> compPr = new ArrayList<>();
	private Observable o = new Observable();
	public void setBaseProducts(HashSet<BaseProduct> baseProducts) {
		this.baseProducts = baseProducts;
	}

	public DeliveryService() {
	}

	public Collection<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(Collection<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public int getClientId() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public HashSet<BaseProduct> getBaseProducts() {
		return baseProducts;
	}

	public Clientt getClient() {
		return client;
	}

	public void setClient(Clientt client) {
		this.client = client;
	}

	public List<CompositeProduct> getCompPr() {
		return compPr;
	}

	public void setCompPr(List<CompositeProduct> compPr) {
		this.compPr = compPr;
	}

	protected boolean isWellFormed()
	{
		 if(baseProducts.isEmpty())
	        	return false;
	         return true;		
	}
	
	@Override
	public void importProducts() throws IOException {
		List<String> lines = Files.lines(Paths.get("products.csv")).skip(1).collect(Collectors.toList());
		HashSet<BaseProduct> pr = new HashSet<>();
		for (String l : lines) {
			String[] v = l.split(",");
			BaseProduct product = new BaseProduct(v[0], Integer.parseInt(v[6]), Float.parseFloat(v[1]),
					Integer.parseInt(v[2]), Integer.parseInt(v[3]), Integer.parseInt(v[4]), Integer.parseInt(v[5]));
			pr.add(product);
		}
		baseProducts = (HashSet<BaseProduct>) pr.stream().distinct().collect(Collectors.toSet());
		for (BaseProduct obj : baseProducts) {
			currentId++;
			obj.setId(currentId);
		}
	}

	@Override
	public void addProduct(BaseProduct baseProduct) {
		assert baseProduct != null;
		baseProducts.add(baseProduct);
	}

	@Override
	public void deleteProduct(String titlu) {
		assert titlu != null;
		for (BaseProduct product : baseProducts)
			if (product.getName().equalsIgnoreCase(titlu) == true) {
				baseProducts.remove(product);
				break;
			}
	}

	@Override
	public void modifyProduct(String oldname, float rating, String name, int calories, int sodium, int fat, int proteins, int price) {
		assert oldname!=null && rating >0 && name!=null && calories >=0 && sodium>=0 && fat >=0 && proteins >=0 && price >=0;
		for (BaseProduct product : baseProducts)
			if (product.getName().equalsIgnoreCase(oldname) == true) {
				product.setName(name);
				product.setRating(rating);
				product.setCalorii(calories);
				product.setProteine(proteins);
				product.setGrasimi(fat);
				product.setSodium(sodium);
				product.setPrice(price);
				break;
			}
	}

	@Override
	public List<Order> Report1(int start, int end) {// comenzile dintr-un interval de timp
		assert start >0 && start <24 && end>0 && end<24 && start <end;
		return orderList.stream()
				.filter(o -> o.getOrderData().getHours() >= start && o.getOrderData().getHours() <= end)
				.collect(Collectors.toList());
	}

	public List<MenuItem> Report2(int max) throws IOException {// produsele comandate de cel putin max ori
		assert produseComandate != null && max>0;
		return produseComandate.stream().filter(o -> o.getNrcom() >= max).collect(Collectors.toList());
	}

	public List<Clientt> Report3(int n, int max) throws IOException {// clientii care au comandat de n ori si valoarea
																		// comenzii este mai mare decat max
		assert max > 0;
		assert n > 0;
		List<Order> orr = orderList.stream().filter(o -> o.getTotalValue() >= max).collect(Collectors.toList());
		List<Clientt> cll = new ArrayList<Clientt>();
		for (Order o : orr) {
			if (o.getClient().getNrOrders() >= n) {
				cll.add(o.getClient());
			}
		}
		return cll;
	}

	public HashMap<MenuItem, Integer> Report4(Date data) throws IOException {// comenzile de la o anumita data
		assert data != null;
		assert orderInfo != null;
		HashMap<MenuItem, Integer> pr = new HashMap<MenuItem, Integer>();
		List<Order> orr = orderList
				.stream().filter(o -> o.getOrderData().getDay() == data.getDay()
						&& o.getOrderData().getMonth() == data.getMonth() && o.getOrderData().getYear() == data.getYear())
				.collect(Collectors.toList());
		assert pr != null;
		for (Order o : orr) {
			int nb = 0;
			if (orderInfo != null) {
				for (MenuItem m : orderInfo.get(o)) {
					if (m != null) {
						if (pr.get(m) != null) {
							nb++;
							pr.remove(m);
						}
						pr.put(m, nb);
					}
				}
			}
		}
		return pr;
	}

	public int computePrice(Order order) {
		assert order != null;
		Collection<MenuItem> itemsList = orderInfo.get(order);
		int price = 0;
		for (MenuItem menuitem : itemsList) {
			price += menuitem.getPrice();
		}
		return price;
	}

	public HashSet<BaseProduct> findProductsByPrice(int price) {
		assert price > 0;
		return (HashSet<BaseProduct>) baseProducts.stream().filter(s -> s.getPrice() == price)
				.collect(Collectors.toSet());
	}

	public MenuItem SearchProductByName(String prName) {
		assert prName!=null;
		MenuItem pr = null;
		for (BaseProduct bp : baseProducts) {
			if (bp.getName().equals(prName))
				pr = bp;
		}
		return pr;
	}

	public List<BaseProduct> extractFromCompositeProduct(String compProduct) {
		assert compProduct!=null;
		String[] pr = compProduct.split(",");
		List<BaseProduct> result = new ArrayList<BaseProduct>();
		for (int i = 0; i < pr.length; i++) {
			MenuItem produs = SearchProductByName(pr[i]);
			if (produs != null) {
				for (BaseProduct p : baseProducts) {
					if (p.compareTo(produs) == 0)
						result.add(p);
				}
			} else {
				BaseProduct base = new BaseProduct(produs.getName(), produs.getPrice());
				result.add(base);
			}
		}
		return result;
	}

	public void addCompProduct(int id) throws IOException {
		assert produseComandate == null;
		for (CompositeProduct product : compPr)
			if (product.getId() == id + 1) {
				produseComandate.remove(product);
				int n = product.getNrcom() + 1;
				product.setNrcom(n);
				menuItems.add(product);
				break;
			}
	}

	public void addMenuItems(int id) {
		assert produseComandate == null;
		for (BaseProduct product : baseProducts)
			if (product.getId() == id + 1) {
				produseComandate.remove(product);
				int n = product.getNrcom() + 1;
				product.setNrcom(n);
				produseComandate.add(product);
				menuItems.add(product);
				break;
			}
	}
	
	public void deserializareDeliveryService() {
		Serializator ser = new Serializator();
		try {
			baseProducts = (HashSet<BaseProduct>) ser.deserialization("file.txt");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public void serializareDeliveryService() {
		Serializator ser = new Serializator();
		ser.serialization(baseProducts,"file.txt");
	}
	
	public String newOrder(Clientt client) throws IOException {
		int totalPrice = 0;
		assert menuItems == null;
		idComanda++;
		Order order = new Order(idComanda, client);
		listaClienti.add(client);
		orderList.add(order);
		orderInfo.put(order, menuItems);
		String str = "Data si ora comenzii nr " + idComanda + " sunt " + order.getOrderData() + "\n "
				+ "\nProdusele comandate sunt:\n";
		for (MenuItem item : menuItems) {
			str = str + "Nume: " + item.getName() + " - Pret: " + item.getPrice() + " \n";
			totalPrice += item.getPrice();
		}
		str = str + "Total: " + totalPrice;
		int no = client.getNrOrders() + 1;
		client.setNrOrders(no);
		order.setTotalValue(totalPrice);
		client.getComenzi().add(order);
		orderInfo.clear();
		menuItems.clear();
		this.setChanged();
		this.notifyObservers(this);
		System.out.println("Notificare angajat!");
		return str;
	}

}
