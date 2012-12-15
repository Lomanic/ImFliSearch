package donnees;

import java.util.List;

import javax.swing.JOptionPane;

import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;
import org.geonames.utils.Distance;

import constantes.Constantes;

public class Vol {
	
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

	
	public Vol (Ville parVilleDepart, Ville parVilleArrivee, int parDistance, int parAllerRetour,int parNbAdulte,int parNbEnfant/*, int parNbBebe*/,
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
		
	}
	
	public String toString()
	{
		return "La ville de depart est "+chVilleDepart.getChNom()+" pour le "+Constantes.jours[chJourAller]+" "+Constantes.mois[chMoisAller]+
		" "+Constantes.annees[chAnneeAller]+" et la ville d'arrivee est "+chVilleArrivee.getChNom()+". \n"+"La distance choisie est de "+chDistance+" km."+" Le vol est un aller avec "+
		Constantes.nombres[chNbAdulte]+" adulte(s), "+Constantes.nombres[chNbEnfant]+" enfant(s)  "/*+Constantes.nombres[chNbBebe]+" bebe(s) en "*/+Constantes.classes[chClasse]+".";
	}	

}