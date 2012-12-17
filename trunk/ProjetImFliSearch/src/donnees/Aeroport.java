package donnees;

public class Aeroport {
	
	private String chNom;
	private String chCodeIATA;
	private double chLatitude;
	private double chLongitude;
	
	
	public Aeroport(String parCodeIATA, double parLatitude, double parLongitude)
	{
		chCodeIATA=parCodeIATA;
		chLatitude=parLatitude;
		chLongitude=parLongitude;
		
	}
	
	public void recupererNomAeroport(int parCodeIATA)
	{
		//Cette classe permettra de récuperer le nom complet de l'aéroport
	}

	public String toString()
	{
		return "L'aeroport a pour code IATA : "+chCodeIATA+"."+"\nLatitude : "+chLatitude+"\nLongitude : "+chLongitude+"\n" +
				"---------------------------------------------------";
	}

	public String getChNom() {
		return chNom;
	}


	public void setChNom(String chNom) {
		this.chNom = chNom;
	}


	public String getChCodeIATA() {
		return chCodeIATA;
	}


	public void setChCodeIATA(String chCodeIATA) {
		this.chCodeIATA = chCodeIATA;
	}


	public double getChLatitude() {
		return chLatitude;
	}


	public void setChLatitude(double chLatitude) {
		this.chLatitude = chLatitude;
	}


	public double getChLongitude() {
		return chLongitude;
	}


	public void setChLongitude(double chLongitude) {
		this.chLongitude = chLongitude;
	}

}
