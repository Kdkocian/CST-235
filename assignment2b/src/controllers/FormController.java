package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import beans.User;

@ManagedBean
@ViewScoped

public class FormController {
	String firstName;
	String lastName;
	
	String onSubmit(User user) {
		firstName = user.getFirstName();
		lastName = user.getLastName();
		return "TestResponse.xhtml";
	}
	
	/*String onFlash(User user) {
		user = theFlash;
		return "TestResponse.xhtml?faces-redirect=true";*/
	}
}
