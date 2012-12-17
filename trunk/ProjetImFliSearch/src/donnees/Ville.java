﻿package donnees;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.lang.Math;

import org.geonames.FeatureClass;
import org.geonames.PostalCodeSearchCriteria;
import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

public class Ville {
	
	private String chNom;
	private double chLatitude;
	private double chLongitude;
	private int chExiste;
	
	
	public Ville(String parNom, double parLatitude, double parLongitude)
	{
		chNom=parNom;
		chLatitude=parLatitude;
		chLongitude=parLongitude;
	}
	
	public Ville(String parVille)
	{
		
		try{
			ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria(); 
			//On se connecte avec un identifiant
			WebService.setUserName("davidjg");   
	     
			/*String maChaine = parVille;
			StringTokenizer tokenizer = new StringTokenizer(maChaine," -,/.");*/

			/*while ( tokenizer.hasMoreTokens() ) {
				String temp =tokenizer.nextToken(); 
				if(temp.length()==2)
				{
					searchCriteria.setCountryCode(temp);
				}
			    System.out.println(temp);
			}
			
			*/
			
			//On cherche la ville de depart
			searchCriteria.setNameEquals(parVille); 
			
	    
			ToponymSearchResult searchResultVille = WebService.search(searchCriteria); 
			
			
			chExiste=searchResultVille.getTotalResultsCount();
			
			//On cree une liste qui va contenir les resultats de la recherche
			List<Toponym>res = searchResultVille.getToponyms(); 
			
			/*for (int j = 0; j < res.size(); j++) { 
				Toponym toponym = res.get(j); 
				System.out.println(res.get(j));
	  		  
				}*/
	  
			if(res.get(0).getFeatureCode().startsWith("PP"))
			{
				
				chNom=res.get(0).getName();
				chLatitude=res.get(0).getLatitude();
				chLongitude=res.get(0).getLongitude();
			}
			else
			{
				chExiste=0; 
			}
			
			
			//On affiche le premier element qui est le plus pertinent
			//System.out.println(res.get(0));
		
			}
		catch(Exception e)
		{
			//e.printStackTrace();
			System.out.println("Erreur: try du constructeur ville.");
		}
		
		
	}//public Ville(String)
	
	public  String[] listAirport(double parDistance)
	{
		
		
		try
		{PostalCodeSearchCriteria searchCriteria = new PostalCodeSearchCriteria(); 
		//On se connecte avec un identifiant
		WebService.setUserName("davidjg");   
     
		//On cherche la ville de depart
		//searchCriteria.setNameEquals(this.chNom); 
		
		String[] tab = new String[1];
		tab[0]="AIRP"; //Pour aéroport
		
	    List<Toponym> listeAeroport = new ArrayList<Toponym>();
	    listeAeroport=WebService.findNearby(this.chLatitude, this.chLongitude,parDistance,FeatureClass.S, tab,"iata",500);
	    
	    int taillecorrect=0; //On récupère une taille qui permettra de construire le tableau corrigé
		for (int j = 0; j < listeAeroport.size(); j++)
		{ 
			Toponym toponym1 = listeAeroport.get(j); 
			 if (!toponym1.getName().isEmpty())
			{
				//System.out.println(toponym1.getName());
				//On incrémente la variable taillecorrect si le code IATA n'est pas vide
				taillecorrect=taillecorrect+1;
			}
  		  
		}//for pour générer la liste d'aéroports
		
		
		//Tableau permettant de récuperer les codes IATA non vide
		System.out.println("Les aéroports à "+parDistance+"km de "+ chNom+" sont:");
		String [] ListeVilleDepartCorrige= new String[taillecorrect];
		int h=0;
		for (int j = 0; j < listeAeroport.size(); j++)
		{ 
			Toponym toponym1 = listeAeroport.get(j); 
			if (!toponym1.getName().isEmpty())
			{
				//Remplit le tableau si le code IATA n'est pas vide
				ListeVilleDepartCorrige[h]=toponym1.getName();
				h++;
			}
  		  
		}//for
		
		//On affiche le tableau corrigé
		for(int e=0;e<ListeVilleDepartCorrige.length;e++)
		{
			System.out.println(ListeVilleDepartCorrige[e]);	
									
		}
		return ListeVilleDepartCorrige;
	    }
		catch(Exception e)
		{
			System.out.println("erreur: listAirPort(double parDistance)");
		}
		
		String[] vide=new String[0];
		return vide;
		
		//---------------------------------------------------------------------------------
		
	}
	
	public int existe()
	{
		return chExiste;
	}//exists()

	public String getChNom() {
		return chNom;
	}

	public void setChNom(String chNom) {
		this.chNom = chNom;
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

	public int getChExiste() {
		return chExiste;
	}

	public void setChExiste(int chExiste) {
		this.chExiste = chExiste;
	}
	
	
	

}//class Ville