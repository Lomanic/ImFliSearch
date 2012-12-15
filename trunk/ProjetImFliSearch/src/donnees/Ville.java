package donnees;

import java.util.ArrayList;
import java.util.List;
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
	     
			//On cherche la ville de depart
			searchCriteria.setNameEquals(parVille); 
	    
			ToponymSearchResult searchResultVille = WebService.search(searchCriteria); 
			
			
			chExiste=searchResultVille.getTotalResultsCount();
			
			
			//On cree une liste qui va contenir les resultats de la recherche
			List<Toponym>res = searchResultVille.getToponyms(); 
			
			for (int j = 0; j < res.size(); j++) { 
				Toponym toponym = res.get(j); 
	  		  
				}//for
	  
	  
			if(res.get(0).getFeatureCode().startsWith("PPL"))
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
			chExiste=0;
		}
		
		
	}//public Ville(String)
	
	public  List<Toponym> listAirport(double parDistance)
	{
		/*double r = parDistance/6371; 
		System.out.println(parDistance);
		double latmin = chLatitude - r;
		double latmax = chLatitude + r;
		
		
		double latT = Math.asin(Math.sin(chLatitude)/Math.cos(r));
		double Δlon = Math.asin(Math.sin(r)/Math.cos(chLatitude)) ;
		double lonmin = chLongitude - Δlon ;
		double lonmax = chLongitude + Δlon;
		System.out.println(r);*/
		//--------------------------------------------------------------------------
		
		
		try
		{PostalCodeSearchCriteria searchCriteria = new PostalCodeSearchCriteria(); 
		//On se connecte avec un identifiant
		WebService.setUserName("davidjg");   
     
		//On cherche la ville de depart
		//searchCriteria.setNameEquals(this.chNom); 
		
		String[] tab = new String[1];
		tab[0]="AIRP"; //Pour aéroport
		//WebService
		
	    return WebService.findNearby(this.chLatitude, this.chLongitude,parDistance,FeatureClass.S, tab,"iata",500);
	    }
		catch(Exception e)
		{
			System.out.println("erreur: listAirPort(double parDistance)");
		}
		List<Toponym> vide = new ArrayList<Toponym>();
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
