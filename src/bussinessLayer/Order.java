package bussinessLayer;

import java.util.Date;

public class Order {
    private int orderId;
    private Date orderData;
    private Clientt client;
    private int totalValue;
   
    public Order(int orderId, Clientt client) {
        this.orderData=new Date();
        this.orderId = orderId;
        this.client = client;
    }

    public Order() {
    }
    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public Date getOrderData() {
        return orderData;
    }

    public void setOrderData(Date orderData) {
        this.orderData = orderData;
    }

    public int hashCode(){
        return 0;
    }

	public Clientt getClient() {
		return client;
	}

	public void setClient(Clientt client) {
		this.client = client;
	}

	public int getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}
    
    
}
