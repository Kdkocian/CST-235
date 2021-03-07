package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Order;
import beans.Orders;

/**
 * Session Bean implementation class OrdersBusinessService
 */
@Stateless
@Local(OrdersBusinessInterFace.class)
@LocalBean
@Alternative
public class OrdersBusinessService implements OrdersBusinessInterFace {
	
	private List<Order> orders;
	
	
    /**
     * Default constructor. 
     */
    public OrdersBusinessService() {
    	ArrayList<Order> dummyData = new ArrayList<Order>();
		dummyData.add(new Order("0000010", "Product 1", 1.00f, 15));
		dummyData.add(new Order("0000020", "Product 2", 2.00f, 16));
		
		this.orders = dummyData;
    }

	
    public void test() {
       
    	System.out.println("Hello from the OrdersBusinessService");
    	
    }

    @Override
	public List<Order> getOrders() {
		
		return this.orders;
	}

	@Override
	public void setOrders(List<Order> orders) {
	
		this.orders = orders;
		
	}
	
	

}
