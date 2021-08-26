package project_pasticceria;

public class Tavolo {
	private int n_consegna = 1;
	private int cioccolata = 0;
	private final int maxCioccolata = 3000;
	private int sorprese = 0;
	private final int maxSorprese = 15;
	private boolean disponibile = false;
	
	public synchronized void put(int cioccolata, int id) {
		while (disponibile ==  true || (this.cioccolata == maxCioccolata && this.sorprese == maxSorprese))
		{
			try {
				wait();
			} catch(InterruptedException e) {}
		}
		disponibile = true;
		if(cioccolata + this.cioccolata > maxCioccolata)
		{
			this.cioccolata = maxCioccolata;
		} 
		else 
		{
			this.cioccolata += cioccolata;
		}
		if(sorprese+1 <= maxSorprese)
		{
			sorprese++;
		} 
		System.out.format("Aiuto cioccolatiere " + id + " mette " + cioccolata + " grammi di cioccolata e 1 sorpresa.");
		System.out.println();
		System.out.println("Disponibili: " + this.cioccolata + " grammi di cioccolata e " + this.sorprese + " sorprese." );
		notifyAll();
	}
	
	public void get(int minCioccolata, int minSorprese) {
		try 
		{
			while (disponibile == false || this.cioccolata < minCioccolata || this.sorprese < minSorprese)
			{
				disponibile = false; //correzione disponibilità in caso le condizioni non rispettate siano sui valori minimi degli ingredienti
				wait();
				
			}
			
		disponibile = false;
		System.out.format("Cioccolatiere preleva: %d di cioccolata", minCioccolata);
		System.out.println();
		System.out.format("Cioccolatiere preleva: %d sorprese", minSorprese);
		System.out.println();
		this.cioccolata-=minCioccolata;
		this.sorprese-=minSorprese;
		System.out.format("Consegna numero %d effettuata.", n_consegna);
		n_consegna++;
		System.out.println();
		
		} catch(InterruptedException e) {}
		
	}
	
}

