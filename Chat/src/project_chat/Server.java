package project_chat;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Server {
	static final int PORT = 8080;
	public static ArrayList<ServerThread> clients = new ArrayList<>();
	public static void main(String[] args) throws IOException
	{
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("Server running.");
		try {
			while(true)
			{
				Socket clientSocket = s.accept();
				try
				{
					ServerThread client = new ServerThread(clientSocket);
					clients.add(client);
				} catch(IOException e) {clientSocket.close();}
			}
		} catch(IOException e) {System.err.println("Accept failed");}
		
		s.close();
	}
}
