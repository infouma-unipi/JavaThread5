package project_pasticceria;

public class Pasticceria {
	public static void main(String[] args)
	{
		Tavolo tavolo = new Tavolo();
		Cioccolatiere cioccolatiere = new Cioccolatiere(tavolo, 1000, 4);
		cioccolatiere.start();
		for (int i=0;i<5;i++) {
			AiutoCioccolatiere a = new AiutoCioccolatiere(tavolo, i);
			a.start();
		}
		
	}
}
