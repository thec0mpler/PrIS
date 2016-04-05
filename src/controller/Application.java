package controller;

import java.io.File;
import java.io.IOException;

import model.Opleiding;
import server.JSONFileServer;

public class Application {
	public static void main(String[] args) throws IOException {
		JSONFileServer server = new JSONFileServer(new File("webapp/app"), 80);
		Opleiding opleiding = new Opleiding("ICT");

		server.registerHandler("/api", new Controller(opleiding));
		server.start();
	}
}
