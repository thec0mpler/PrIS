package controller;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import model.Opleiding;
import server.Conversation;
import server.Handler;

class UserController extends Controller {
	public UserController(Opleiding opleiding) {
		super(opleiding);
	}
	
	public String login(String username, String password) {		
		String rol = this.opleiding.login(username, password);			// inloggen methode aanroepen op domeinmodel...
		
		return rol;									// terugsturen naar de Polymer-GUI!
	}
}