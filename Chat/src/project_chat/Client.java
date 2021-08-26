package project_chat;

import java.net.*;
import java.io.*;

public class Client {
	public static void main(String[] args) throws IOException
	{
		InetAddress addr = InetAddress.getByName(null);
		Socket serverSocket = null;
		BufferedReader in = null, stdIn = null;
		PrintWriter out = null;
		try {
			serverSocket = new Socket(addr, Server.PORT);
			in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream())), true);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Inserire nome utente:");
			//inserimento nome utente
			String userName = stdIn.readLine();
			out.println(userName);
			//validazione username
			String verifiedUser = in.readLine();
			while(!verifiedUser.substring(0,4).equals("CIAO"))
			{
				System.out.println(verifiedUser);
				userName = stdIn.readLine();
				out.println(userName);
				verifiedUser = in.readLine();
			}
			System.out.println(verifiedUser);
			
			//client disponibile a chattare
			String message = null;
			while(true)
			{
				if(in.ready()) {
					System.out.println(in.readLine());
				}
				if(stdIn.ready()) {
					message = stdIn.readLine();
					if(message.equals("STOP")) {
						System.out.println("[Chat abbandonata]");
						out.println("[" + userName + " è uscito dalla chat]");
						break;
					}
					if(!message.isBlank()) {
						out.println(userName.toUpperCase() + " " + message);
					}
				}
				
			}
			
		
		} catch(UnknownHostException uk) {
			System.err.println("Non conosco l'IP " + addr);
		} catch(IOException e) {}
		out.close();
		in.close();
		stdIn.close();
		serverSocket.close();
		
	}
}
