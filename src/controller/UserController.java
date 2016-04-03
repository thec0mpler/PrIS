package controller;

import model.Opleiding;

class UserController extends Controller {
	public UserController(Opleiding opleiding) {
		super(opleiding);
	}
	
	public String login(String username, String password) {		
		String rol = this.opleiding.login(username, password);
		
		return rol;
	}
}