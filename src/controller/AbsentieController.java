package controller;

import javax.json.JsonObject;

import model.Opleiding;
import server.Conversation;
import server.Handler;

public class AbsentieController implements Handler {
	private Opleiding informatieSysteem;
	
	public AbsentieController(Opleiding infoSys)	{
		informatieSysteem = infoSys;
	}
	
	public void handle(Conversation conversation) {
		if (conversation.getRequestedURI().startsWith("/student/mijnabsenties")) {
			mijnAbsenties(conversation);
		}
	}
	
	private void mijnAbsenties(Conversation conversation)	{
		JsonObject jsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
	}
}
