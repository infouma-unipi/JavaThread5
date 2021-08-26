package project_pasticceria;

public class Cioccolatiere extends Thread 
{
	private Tavolo tavolo;
	private int minCioccolata;
	private int minSorprese;
	
	
	
	public Cioccolatiere(Tavolo tavolo, int minCioccolata, int minSorprese) 
	{
		this.tavolo = tavolo;
		this.minCioccolata = minCioccolata;
		this.minSorprese = minSorprese;
	}
	
	public void run() 
	{
		while(true)
		{
			synchronized(tavolo)
			{
				tavolo.get(this.minCioccolata, this.minSorprese);
			}
			
		}
	}
	
}
