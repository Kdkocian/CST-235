package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.Order;
import beans.User;
import business.OrdersBusinessService;

@ManagedBean(name = "formController", eager = true)
@ViewScoped

public class FormController {
	private OrdersBusinessService service;
	@ManagedProperty(value = "#{user}")
	public User user;
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public String onSubmit(User user) {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		getAllOrders();
		insertOrder();
		getAllOrders();
		return "TestResponse.xhtml";
	}
	
	public String onFlash(User user) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("user", user);
		return "TestResponse.xhtml?faces-redirect=true";
	}
	
	private void getAllOrders() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testapp", "kyle", "bubbles");
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM testapp.Orders");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(String.format("ID: %d   Product: %s    Price: %f", rs.getInt("ID"), rs.getString("PRODUCT_NAME"), rs.getFloat("PRICE")));
			} 
			rs.close();
			System.out.println("Success!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Failure!!");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch(Exception ex) {
					
				}
			}
		}
	}
	private void insertOrder() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testapp", "kyle", "bubbles");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO testapp.Orders(ORDER_NO, PRODUCT_NAME, PRICE, QUANTITY) VALUES('00000000011', 'This is Product 11', 11.00, 11);");
			System.out.println("Success!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Failure!!");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch(Exception ex) {
					
				}
			}
		}
	}
	
	public String onSendOrder(Order order) {
		System.out.println("Send the Order");
		service.sendOrder(order);
		
		return "OrderResponse.xhtml";
	}
}