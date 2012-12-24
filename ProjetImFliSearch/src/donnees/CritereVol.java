package donnees;

import java.util.List;

import javax.swing.JOptionPane;

import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;
import org.geonames.utils.Distance;

import constantes.Constantes;

public class CritereVol {
	
	private Ville chVilleDepart;
	private Ville chVilleArrivee;
	private int chDistance;
	private int chAllerRetour;
	private int chNbAdulte;
	private int chNbEnfant;
	//private int chNbBebe;
	private int chClasse;
	private int chJourAller;
	private int chMoisAller;
	private int chAnneeAller;
	private int chJourRetour;
	private int chMoisRetour;
	private int chAnneeRetour;

	
	public CritereVol (Ville parVilleDepart, Ville parVilleArrivee, int parDistance, int parAllerRetour,int parNbAdulte,int parNbEnfant/*, int parNbBebe*/,
			int parClasse, int parJourAller, int parMoisAller, int parAnneeAller,int parJourRetour,int parMoisRetour, int parAnneeRetour)
	{
			chVilleDepart=parVilleDepart;
			chVilleArrivee=parVilleArrivee;
			chDistance=parDistance;
			chAllerRetour=parAllerRetour;
			chNbAdulte=parNbAdulte;
			chNbEnfant=parNbEnfant;
			//chNbBebe=parNbBebe;
			chClasse=parClasse;
			chJourAller=parJourAller;
			chMoisAller=parMoisAller;
			chAnneeAller=parAnneeAller;
			chJourRetour=parJourRetour;
			chMoisRetour=parMoisRetour;
			chAnneeRetour=parAnneeRetour;
		
	}//constructeur
	
	public String toString()
	{
		return "La ville de depart est "+chVilleDepart.getChNom()+" pour le "+Constantes.joursDepart[chJourAller]+" "+Constantes.mois[chMoisAller]+
		" "+Constantes.annees[chAnneeAller]+" et la ville d'arrivee est "+chVilleArrivee.getChNom()+". \n"+"La distance choisie est de "+chDistance+" km."+" Le vol est un aller avec "+
		Constantes.nombres[chNbAdulte]+" adulte(s), "+Constantes.nombres[chNbEnfant]+" enfant(s)  "/*+Constantes.nombres[chNbBebe]+" bebe(s) en "*/+Constantes.classes[chClasse]+".";
	}

	public Ville getChVilleDepart() {
		return chVilleDepart;
	}

	public void setChVilleDepart(Ville chVilleDepart) {
		this.chVilleDepart = chVilleDepart;
	}

	public Ville getChVilleArrivee() {
		return chVilleArrivee;
	}

	public void setChVilleArrivee(Ville chVilleArrivee) {
		this.chVilleArrivee = chVilleArrivee;
	}

	public int getChDistance() {
		return chDistance;
	}

	public void setChDistance(int chDistance) {
		this.chDistance = chDistance;
	}

	public int getChAllerRetour() {
		return chAllerRetour;
	}

	public void setChAllerRetour(int chAllerRetour) {
		this.chAllerRetour = chAllerRetour;
	}

	public int getChNbAdulte() {
		return chNbAdulte;
	}

	public void setChNbAdulte(int chNbAdulte) {
		this.chNbAdulte = chNbAdulte;
	}

	public int getChNbEnfant() {
		return chNbEnfant;
	}

	public void setChNbEnfant(int chNbEnfant) {
		this.chNbEnfant = chNbEnfant;
	}

	public int getChClasse() {
		return chClasse;
	}

	public void setChClasse(int chClasse) {
		this.chClasse = chClasse;
	}

	public int getChJourAller() {
		return chJourAller;
	}

	public void setChJourAller(int chJourAller) {
		this.chJourAller = chJourAller;
	}

	public int getChMoisAller() {
		return chMoisAller;
	}

	public void setChMoisAller(int chMoisAller) {
		this.chMoisAller = chMoisAller;
	}

	public int getChAnneeAller() {
		return chAnneeAller;
	}

	public void setChAnneeAller(int chAnneeAller) {
		this.chAnneeAller = chAnneeAller;
	}

	public int getChJourRetour() {
		return chJourRetour;
	}

	public void setChJourRetour(int chJourRetour) {
		this.chJourRetour = chJourRetour;
	}

	public int getChMoisRetour() {
		return chMoisRetour;
	}

	public void setChMoisRetour(int chMoisRetour) {
		this.chMoisRetour = chMoisRetour;
	}

	public int getChAnneeRetour() {
		return chAnneeRetour;
	}

	public void setChAnneeRetour(int chAnneeRetour) {
		this.chAnneeRetour = chAnneeRetour;
	}	
}