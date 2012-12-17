package lanceur;

import org.geonames.*;
import org.geonames.utils.Distance;

import vue.InterfaceMere;


public class mainlanceur {

	
	public static void main(String[] args) {
		
	
		InterfaceMere interfacemere = new InterfaceMere("ImFliSearch");
		
		/*String searchName = "tunis";
		ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria(); 
	
		    WebService.setUserName("davidjg");   
		      
		    searchCriteria.setQ(searchName);*/ 
		
		            try {
		              /* ToponymSearchResult searchResult = WebService.search(searchCriteria); 
		 
		                		for (Toponym toponym : searchResult.getToponyms()) {
		                			System.out.println(toponym.getName() + " " + toponym.getCountryName()
		                            + " " + toponym.getLongitude() + " "
		                            + toponym.getLatitude());
		                			*/
		                			
		                		//	double lat1=toponym.getLatitude();
		                			//double long1=toponym.getLongitude();
		                			//double lat2=toponym.getLatitude();
		                			//double long2=toponym.getLongitude();
		                			
		                			
		                        //}
		                		
		            	
		            			/*Distance p= new Distance();
		            			double dist=p.distance(-9.14843,38.72635,10.16579,36.81897,'K',0);
		                		System.out.println(dist);
		            			 	*/
		                        } catch (Exception e)
		                        {
		                            // TODO Auto-generated catch block
		                           e.printStackTrace();
		                         }   
		}
	}


	


