package project_chat;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServerThread extends Thread {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private static ArrayList<String> listaUtenti = new ArrayList<>();
	protected boolean loggedIn = false;
	public ServerThread(Socket s) throws IOException
	{
		socket = s;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		start();
	}
	
	public void run()
	{
		try
		{
			//inserimento nome utente
			String nuovoUtente = in.readLine();
			while(listaUtenti.contains(nuovoUtente)) {
				out.println("Username già in uso. Inserisci un nuovo username:");
				nuovoUtente = in.readLine();
			}
			String utente = "UTENTE " + nuovoUtente;
			System.out.println(utente);
			listaUtenti.add(nuovoUtente);
			String utenti = listaUtenti.toString().replace("[", " ");
			//benvenuto
			out.println("CIAO " + nuovoUtente.toUpperCase() + "\n[Utenti connessi:" + utenti);
			
			//controllo termine login
			loggedIn = true;
			//inizio comunicazione chat
			String message;
			while(!socket.isClosed())
			{
				message = in.readLine();
				if(message != null) {
					echoToClients(message);
					//rimozione utente dalla lista utenti
					if (message.contains("è uscito dalla chat]")) {
						String utenteToRemove = message.substring(1, message.indexOf('è') - 1);
						listaUtenti.remove(utenteToRemove);
					}
				}
					
			}
				
		} catch(IOException e) {}
		try 
		{
			socket.close();
		} catch (IOException e) {}
	}
	
	//metodo per l'invio dei messaggi a tutti i client
	private void echoToClients(String message) {
		for (ServerThread client: Server.clients) {
			if(client.loggedIn) {
				client.out.println(message);
			}
		}
	}
	
}
