package controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.User;
import business.MyTimerService;
import business.OrdersBusinessInterFace;

@ManagedBean(name = "formController", eager = true)
@ViewScoped

public class FormController {
	@ManagedProperty(value = "#{user}")
	
	public User user;
	@Inject
	OrdersBusinessInterFace service;
	@EJB
	MyTimerService timer;
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public String onSubmit(User user) {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		service.test();
		timer.setTimer(10000);
		return "TestResponse.xhtml";
	}
	
	public String onFlash(User user) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("user", user);
		return "TestResponse.xhtml?faces-redirect=true";
	}
	
	public OrdersBusinessInterFace getService() {
		return service;
	}
}