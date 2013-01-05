package donnees;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import donnees.Aeroport;

public class Vol {

	private Aeroport aeroportDepart;
	private Aeroport aeroportArrivee;
	
	private String lien;
	
	private String prix;
	
	private Date dateAllerDepart;
	private Date dateAllerArrivee;
	private Date dateRetourDepart;
	private Date dateRetourArrivee;
	
	private String compagnieAller;
	private String compagnieRetour;
	private String numVolAller;
	private String numVolRetour;
	
	//variables temporaires
	private String dateRechercheDepartAnnee;
	private String dateRechercheArriveeAnnee;
	
	public Vol(String ligneJsoupSearchBarContent,String ligneJsoupContainer, String ligneJsoupButtonLink)
	{
		System.out.println("**********************************************************************");
		//recherche
		Pattern p1=Pattern.compile("Aller:.+([a-zA-Z]{3}\\.)\\s+([0-9]{1,2})\\s+([a-zA-ZÀ-ÿ]{4}\\.)\\s+([0-9]{4})\\s+Retour:.+([a-zA-Z]{3}\\.)\\s+([0-9]{1,2})\\s+([a-zA-ZÀ-ÿ]{4}\\.)\\s+([0-9]{4})\\s+");
		Matcher m1 = p1.matcher(ligneJsoupSearchBarContent);
		
		if (m1.find())//la plupart des String ci-dessous ne servent pas. Ne les supprimez pas quand même
		{
			String dateRechercheDepartJourSemaine=m1.group(1);
			if (m1.group(2).length()==1)
			{
				String dateRechercheDepartJourNumero="0"+m1.group(2);
			}else
			{
				String dateRechercheDepartJourNumero=m1.group(2);
			}
			String dateRechercheDepartMois=m1.group(3);
			this.dateRechercheDepartAnnee=m1.group(4);
			//--------------------------------------------
			String dateRechercheArriveeJourSemaine=m1.group(5);
			if (m1.group(6).length()==1)
			{
				String dateRechercheArriveeJourNumero="0"+m1.group(6);
			}else
			{
				String dateRechercheArriveeJourNumero=m1.group(6);
			}
			String dateRechercheArriveeMois=m1.group(7);
			this.dateRechercheArriveeAnnee=m1.group(8);
		}
		
		
		
		
		
		Pattern p2=Pattern.compile("Aller\\s+([a-zA-Z]{3}\\.)\\s+([0-9]{1,2})\\s+([a-zA-ZÀ-ÿ]{4}\\.)\\s+(\\d{2})\\:(\\d{2})");//Aller ven. 1 févr. 19:55
		Matcher m2 = p2.matcher(ligneJsoupContainer);
		
		if (m2.find())
		{
			String stringDateAllerDepart=m2.group(1)+" "+m2.group(2)+" "+m2.group(3)+" "+this.dateRechercheDepartAnnee+" "+m2.group(4)+":"+m2.group(5)+":00";
			
			SimpleDateFormat parseur = new SimpleDateFormat("EEE dd MMM yyyy HH:mm:ss", Locale.FRENCH);
			try{
				dateAllerDepart=parseur.parse(stringDateAllerDepart);
			}catch(ParseException e){System.out.println("erreur->"+stringDateAllerDepart);}
		}
			
			
		Pattern p3=Pattern.compile("Retour\\s+([a-zA-Z]{3}\\.)\\s+([0-9]{1,2})\\s+([a-zA-ZÀ-ÿ]{4}\\.)\\s+(\\d{2})\\:(\\d{2})");//Aller ven. 1 févr. 19:55
		Matcher m3 = p3.matcher(ligneJsoupContainer);
		
		if (m3.find())
		{
			String stringDateRetourDepart=m3.group(1)+" "+m3.group(2)+" "+m3.group(3)+" "+this.dateRechercheArriveeAnnee+" "+m3.group(4)+":"+m3.group(5)+":00";
			
			SimpleDateFormat parseur = new SimpleDateFormat("EEE dd MMM yyyy HH:mm:ss", Locale.FRENCH);
			try{
				dateRetourDepart=parseur.parse(stringDateRetourDepart);
			}catch(ParseException e){System.out.println("erreur->"+stringDateRetourDepart);}
		}
		//Aller ven. 1 févr. 19:55 Paris CDG 21:30 *Berlin *TXL Direct 1h 35 Lufthansa 3245 Cliquez pour choisir ce vol retour Retour dim. 17 févr. 15:10 Berlin TXL 22:45 Paris CDG 1 escale 7h 35 Lufthansa 2039 / 2240                         m4.group(10)
		Pattern p4=Pattern.compile("Aller.+\\s([a-zA-ZÀ-ÿ]{2,})\\s+([A-Z]{3})\\s+(\\d{2}:\\d{2})\\s+([a-zA-ZÀ-ÿ]{2,})\\s+([A-Z]{3}).+\\d{1,3}h\\s+\\d{1,2}\\s+([a-zA-ZÀ-ÿ ]{2,})\\s+([0-9 /]{2,}+).+Retour.+\\s([a-zA-ZÀ-ÿ]{2,})\\s+([A-Z]{3})\\s+(\\d{2}:\\d{2})\\s+([a-zA-ZÀ-ÿ]{2,})\\s+([A-Z]{3}).+\\d{1,3}h\\s+\\d{1,2}\\s+([a-zA-ZÀ-ÿ ]{2,})\\s+?([0-9 /]{2,}+).*");//attention aux compagnies/escales multiples + les récupérer.
		Matcher m4 = p4.matcher(ligneJsoupContainer);
		
		if (m4.find())
		{
			aeroportDepart=new Aeroport(m4.group(1),m4.group(2),0,0,new Ville(m4.group(1),0,0));
			aeroportArrivee=new Aeroport(m4.group(4),m4.group(5),0,0,new Ville(m4.group(4),0,0));
			
			String stringDateAllerArrivee=m2.group(1)+" "+m2.group(2)+" "+m2.group(3)+" "+this.dateRechercheDepartAnnee+" "+m4.group(3)+":00";
		
			SimpleDateFormat parseur = new SimpleDateFormat("EEE dd MMM yyyy HH:mm:ss", Locale.FRENCH);
			try{
				dateAllerArrivee=parseur.parse(stringDateAllerArrivee);
			}catch(ParseException e){System.out.println("erreur->"+stringDateAllerArrivee);}
			
			this.compagnieAller=m4.group(6);
			this.numVolAller=m4.group(7);
			
			//********Retour m.group(8)
			
			String stringDateRetourArrivee=m3.group(1)+" "+m3.group(2)+" "+m3.group(3)+" "+this.dateRechercheArriveeAnnee+" "+m4.group(10)+":00";
			try{
				dateRetourArrivee=parseur.parse(stringDateRetourArrivee);
			}catch(ParseException e){System.out.println("erreur->"+stringDateRetourArrivee);}
			
			this.compagnieRetour=m4.group(13);
			this.numVolRetour=m4.group(14);
		}
		else
			System.out.println("erreur de find\n"+ligneJsoupContainer);

		//Aller mar. 8 janv. 13:55 Paris ORY 21:55 Berlin TXL 1 escale 8h 0 Air Berlin 5083 British Airways 988 Changement de compagnie aérienne Vol 5083 Opéré par British Airways Cliquez pour choisir ce vol retour Retour sam. 12 janv. 06:45 Berlin TXL 08:40 Paris ORY Direct 1h 55 Air Berlin 8154

		
		
		
		//lien
		lien=ligneJsoupButtonLink;
		
		//prix
		p3 = Pattern.compile("\\s*+(\\d+)\\s*+[$€£]");
		m3 = p3.matcher(ligneJsoupContainer);

		if (m3.find())
			prix=m3.group();
		
		System.out.println(prix+"AllerD:"+dateAllerDepart+"/AllerA:"+dateAllerArrivee+"\nRetourD:"+dateRetourDepart+"/RetourA:"+dateRetourArrivee);
		System.out.println(aeroportDepart);
		System.out.println("Aller : vol n°"+numVolAller+" "+compagnieAller+"\nRetour : vol n°"+numVolRetour+" "+compagnieRetour);
		System.out.println(lien);
	}

	
	
	//ACCESSEURS
	public Aeroport getAeroportDepart() {
		return aeroportDepart;
	}

	public void setAeroportDepart(Aeroport aeroportDepart) {
		this.aeroportDepart = aeroportDepart;
	}

	public Aeroport getAeroportArrivee() {
		return aeroportArrivee;
	}

	public void setAeroportArrivee(Aeroport aeroportArrivee) {
		this.aeroportArrivee = aeroportArrivee;
	}

	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

	public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}

	public Date getDateAllerDepart() {
		return dateAllerDepart;
	}

	public void setDateAllerDepart(Date dateAllerDepart) {
		this.dateAllerDepart = dateAllerDepart;
	}

	public Date getDateAllerArrivee() {
		return dateAllerArrivee;
	}

	public void setDateAllerArrivee(Date dateAllerArrivee) {
		this.dateAllerArrivee = dateAllerArrivee;
	}

	public Date getDateRetourDepart() {
		return dateRetourDepart;
	}

	public void setDateRetourDepart(Date dateRetourDepart) {
		this.dateRetourDepart = dateRetourDepart;
	}

	public Date getDateRetourArrivee() {
		return dateRetourArrivee;
	}

	public void setDateRetourArrivee(Date dateRetourArrivee) {
		this.dateRetourArrivee = dateRetourArrivee;
	}



	public String getCompagnieAller() {
		return compagnieAller;
	}



	public void setCompagnieAller(String compagnieAller) {
		this.compagnieAller = compagnieAller;
	}



	public String getCompagnieRetour() {
		return compagnieRetour;
	}



	public void setCompagnieRetour(String compagnieRetour) {
		this.compagnieRetour = compagnieRetour;
	}



	public String getNumVolAller() {
		return numVolAller;
	}

	public void setNumVolAller(String numVol) {
		this.numVolAller = numVol;
	}



	public String getNumVolRetour() {
		return numVolRetour;
	}



	public void setNumVolRetour(String numVolRetour) {
		this.numVolRetour = numVolRetour;
	}
	
	
}
