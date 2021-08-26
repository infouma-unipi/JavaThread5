package project_pasticceria;

public class AiutoCioccolatiere extends Thread 
{
	private Tavolo tavolo;
	private int id;
	
	public AiutoCioccolatiere(Tavolo tavolo, int id) 
	{
		this.tavolo = tavolo;
		this.id = id;
	}
	
	public void run() 
	{	
		while(true)
		{
			try
			{
				int cioccolata = (int) (Math.ceil(Math.random()*700/100.0))*100;
				synchronized (tavolo)
				{
					tavolo.put(cioccolata, id);
				}
				
				sleep(2000);
				
				
			} catch(InterruptedException e) {}
		}
	}
}

