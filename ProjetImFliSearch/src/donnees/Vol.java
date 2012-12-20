package donnees;
import donnees.Ville;
import donnees.Aeroport;

public class Vol {

	private Aeroport AeroportDepart;
	private Aeroport AeroportArrivee;
	//Ajouter les 4 dates
	private String chLien;
	
	
	
	
	
	
	
	public Aeroport getAeroportDepart() {
		return AeroportDepart;
	}
	public void setAeroportDepart(Aeroport aeroportDepart) {
		AeroportDepart = aeroportDepart;
	}
	public Aeroport getAeroportArrivee() {
		return AeroportArrivee;
	}
	public void setAeroportArrivee(Aeroport aeroportArrivee) {
		AeroportArrivee = aeroportArrivee;
	}
	public String getChLien() {
		return chLien;
	}
	public void setChLien(String chLien) {
		this.chLien = chLien;
	}
	public float getChPrix() {
		return chPrix;
	}
	public void setChPrix(float chPrix) {
		this.chPrix = chPrix;
	}
	private float chPrix;
}
