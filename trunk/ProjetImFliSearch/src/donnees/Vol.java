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
	
	public Vol(String ligneJsoupSearchBarContent,String ligneJsoupContainer, String ligneJsoupButtonLink, Aeroport [] AeroportsDepart,Aeroport [] AeroportsArrivee)
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
		}else
		{//aller simple
			Pattern o1=Pattern.compile("Aller:.+([a-zA-Z]{3}\\.)\\s+([0-9]{1,2})\\s+([a-zA-ZÀ-ÿ]{4}\\.)\\s+([0-9]{4})");
			Matcher n1 = o1.matcher(ligneJsoupSearchBarContent);
			
			if(n1.find())
			{
				this.dateRechercheDepartAnnee=n1.group(4);
			}
			
		}
		
		
		
		
		//Pattern p2 commun à aller simple/aller-retour
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

		//Aller mar. 8 janv. 13:55 Paris ORY 21:55 Berlin TXL 1 escale 8h 0 Air Berlin 5083 British Airways 988 Changement de compagnie aérienne Vol 5083 Opéré par British Airways Cliquez pour choisir ce vol retour Retour sam. 12 janv. 06:45 Berlin TXL 08:40 Paris ORY Direct 1h 55 Air Berlin 8154
		//Aller ven. 1 févr. 19:55 Paris CDG 21:30 *Berlin *TXL Direct 1h 35 Lufthansa 3245 Cliquez pour choisir ce vol retour Retour dim. 17 févr. 15:10 Berlin TXL 22:45 Paris CDG 1 escale 7h 35 Lufthansa 2039 / 2240                         m4.group(10)
		Pattern p4=Pattern.compile("Aller.+\\s([a-zA-ZÀ-ÿ -]{2,})\\s+([A-Z]{3})\\s+(\\d{2}:\\d{2})\\s+([a-zA-ZÀ-ÿ -]{2,})\\s+([A-Z]{3}).+\\D(\\d{1,3})h\\s+(\\d{1,2})\\s+([a-zA-ZÀ-ÿ -]{2,})\\s+([0-9 /]{2,}+).+Retour.+\\s([a-zA-ZÀ-ÿ]{2,})\\s+([A-Z]{3})\\s+(\\d{2}:\\d{2})\\s+([a-zA-ZÀ-ÿ -]{2,})\\s+([A-Z]{3}).+\\D(\\d{1,3})h\\s+(\\d{1,2})\\s+([a-zA-ZÀ-ÿ -]{2,})\\s+?([0-9 /]{2,}+).*");//attention aux compagnies/escales multiples + les récupérer.
		//											(1)					(2)				(3)						(4)				(5)			_(5+1)		(5+2)_			(6+2)						(7+2)						(8+2)			(9+2)			(10+2)			(11+2)					(12+2)		_(12+3)			(12+4)_			(13+4)					(14+4)
		Matcher m4 = p4.matcher(ligneJsoupContainer);
		
		if (m4.find())
		{
			for(Aeroport j:AeroportsDepart)
			{
				if(m4.group(2).equals(j.getChCodeIATA()));
					{
						aeroportDepart=j;
					}
			}
			
			for(Aeroport j:AeroportsArrivee)
			{
				if(m4.group(5).equals(j.getChCodeIATA()));
					{
						aeroportArrivee=j;
					}
			}
			Long l1=dateAllerDepart.getTime()+Long.parseLong(m4.group(5+1))* 3600000L+Long.parseLong(m4.group(5+2))*60000L;
			dateAllerArrivee=new Date(l1);
			
			this.compagnieAller=m4.group(6+2);
			this.numVolAller=m4.group(7+2);
			
			//********Retour m.group(8)
			Long l2=dateRetourDepart.getTime()+Long.parseLong(m4.group(12+3))* 3600000L+Long.parseLong(m4.group(12+4))*60000L;
			dateRetourArrivee=new Date(l2);
			
			this.compagnieRetour=m4.group(13+4);
			this.numVolRetour=m4.group(14+4);
		}

		
		
		//Lien et prix communs aux aller/retour et aux allers simples.
		//lien
		lien=ligneJsoupButtonLink;
		
		//prix
		p3 = Pattern.compile("[0-9]{0,2}\\D*\\d{1,3}\\s+[$€£]");
		m3 = p3.matcher(ligneJsoupContainer);

		if (m3.find())
			prix=m3.group();
		
		
		
		
		
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		//ALLER SIMPLE
		
		//Modifier la recherche Votre voyage (De / A) Date Aller: Paris (ORY) à Berlin (TXL) jeu. 10 janv. 2013
		
		
		
		
		
		
		//1 751 € Tarif total Choisir Détails du vol Aller jeu. 10 janv. 19:25 Paris ORY 11:55 Berlin TXL 1 escale 16h 30 Air Berlin 5085 Lufthansa 3373 Vol de nuit, arrivée le lendemain. Changement de compagnie aérienne Vol 5085 Opéré par British Airways
		Pattern o2=Pattern.compile("Aller\\s+([a-zA-Z]{3}\\.)\\s+([0-9]{1,2})\\s+([a-zA-ZÀ-ÿ -]{4}\\.)\\s+(\\d{2})\\:(\\d{2})\\s+([a-zA-ZÀ-ÿ -]{2,})\\s+([A-Z]{3}).+\\s+([a-zA-ZÀ-ÿ -]{2,})\\s+([A-Z]{3}).+\\D(\\d{1,2})h\\s+(\\d{1,2})\\s+([a-zA-ZÀ-ÿ -]{2,})\\s+([0-9 /]{2,})");
		//										(1)					(2)					(3)					(4)			(5)				(6)aero			(7)iata			(8)aero				(9)iata			(10)h				(11)		(12)cmpnie			(13)n°vol
		Matcher n1 = o2.matcher(ligneJsoupContainer);
		
		if (n1.find())
		{
			for(Aeroport j:AeroportsDepart)
			{
				if(n1.group(7).equals(j.getChCodeIATA()));
					{
						aeroportDepart=j;
					}
			}
			
			for(Aeroport j:AeroportsArrivee)
			{
				if(n1.group(9).equals(j.getChCodeIATA()));
					{
						aeroportArrivee=j;
					}
			}
			
			String stringDateAllerDepart=n1.group(1)+" "+n1.group(2)+" "+n1.group(3)+" "+this.dateRechercheDepartAnnee+" "+n1.group(4)+":"+n1.group(5)+":00";
			
			SimpleDateFormat parseur = new SimpleDateFormat("EEE dd MMM yyyy HH:mm:ss", Locale.FRENCH);
			try{
				dateAllerDepart=parseur.parse(stringDateAllerDepart);
			}catch(ParseException e){System.out.println(stringDateAllerDepart);}
			
	/**/	//dateAllerArrivee=		//finir l'implémentation de l'aller simple
			Long l=dateAllerDepart.getTime()+Long.parseLong(n1.group(10))* 3600000L+Long.parseLong(n1.group(11))*60000L;
			dateAllerArrivee=new Date(l);
			
			System.out.println(n1.group(10)+"h"+n1.group(11));
			
			
			this.compagnieAller=n1.group(12);
			this.numVolAller=n1.group(13);
			
			System.out.println(ligneJsoupContainer);
		}
		else
			System.out.println("erreur->"+ligneJsoupContainer);
		
		
		
		
		
		
		
		
		
		
		
		
		
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
