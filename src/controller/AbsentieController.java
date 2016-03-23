package controller;

import javax.json.JsonObject;

import model.PrIS;
import server.Conversation;
import server.Handler;

public class AbsentieController implements Handler {
	private PrIS informatieSysteem;
	
	public AbsentieController(PrIS infoSys)	{
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
