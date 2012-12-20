package donnees;

import org.geonames.utils.Distance;

public class Aeroport {
	
	private String chNom; //Il reste à récupérer le nom de l'aéroport
	private String chCodeIATA;
	private double chLatitude;
	private double chLongitude;
	private Ville chVille;
	
	public Aeroport(String parCodeIATA, double parLatitude, double parLongitude,Ville parVille)
	{
		chCodeIATA=parCodeIATA;
		chLatitude=parLatitude;
		chLongitude=parLongitude;
		chVille=parVille;
		
	}
	
	public void recupererNomAeroport(int parCodeIATA)
	{
		//Cette classe permettra de récuperer le nom complet de l'aÃ©roport
	}

	public String toString()
	{
		return "L'aeroport a pour code IATA : "+chCodeIATA+"."+"\nLatitude : "+chLatitude+"\nLongitude : "+chLongitude+"\n" +
				"La distance est de "+recupererDistance()+" km"+
				"\n---------------------------------------------------";
				
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

	public void setVille(Ville parVille)
	{
		chVille=parVille;
	}
	
	public double recupererDistance()
	{
		Distance uneDistance=new Distance();
		return uneDistance.distanceKM(chLatitude, chLongitude, chVille.getChLatitude(),
				chVille.getChLongitude());
		
	}
}
