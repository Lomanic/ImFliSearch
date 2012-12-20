package donnees;

public class Recherche
{
	
	public String urlRecherche(CritereVol cri, Aeroport aeroDepart,Aeroport aeroArrivee)
	{
		String[] ar_type={"roundTrip","oneWay"};
		
		return "http://www.ebookers.fr/shop/home?type=air"+
				"&ar.type="+ar_type[cri.getChAllerRetour()]+
				"&ar.rt.leaveSlice.orig.key="+aeroDepart.getChCodeIATA()+
				"&ar.rt.leaveSlice.dest.key="+aeroArrivee.getChCodeIATA()+
				"&ar.rt.leaveSlice.date="+cri.getChJourAller()+"%2F"+cri.getChMoisAller()+"%2F"+"12"+
				"&ar.rt.leaveSlice.eturnSlice.time=Anytime" +
				"&_ar.rt.flexAirSearch=0" +
				"&ar.rt.numAdult="+cri.getChNbAdulte()+
				"&ar.rt.numChild="+cri.getChNbEnfant()+
				"&ar.rt.child[0]=3" +
				"&ar.rt.child[1]=3"+
				"&ar.rt.child[2]=3"+
				"&ar.rt.child[3]=3"+
				"&ar.rt.child[4]=3"+
				"&ar.rt.child[5]=3"+
				"&ar.rt.child[6]=3"+
				"&ar.rt.child[7]=3";
		
		
		//http://www.ebookers.fr/shop/home?type=air&ar.type=roundTrip&ar.rt.leaveSlice.orig.key="+listeVilleDepartAeroportCorrige[f]+
			/*"&ar.rt.leaveSlice.dest.key="+listeVilleArriveeAeroportCorrige[z]+"&ar.rt.leaveSlice.date=12%2F12%2F12" +
					"&ar.rt.leaveSlice." +
					"time=Anytime&ar.rt.returnSlice.date=22%2F01%2F13" +
					"&ar.rt.returnSlice.time=Anytime&_ar.rt.flexAirSearch=0" +
					"&ar.rt.numAdult="+
					Constantes.nombres[chNbAdulte.getSelectedIndex()]+"" +
							"&ar.rt.numChild="+Constantes.nombres[chNbEnfants.getSelectedIndex()]+
					"&ar.rt.child[0]=3&ar.rt.child[1]=4&ar.rt.child[2]=4&ar.rt.child[3]=&ar.rt.child[4]=5&ar.rt.child[5]=&ar.rt.child[6]=3" +
					"&ar.rt.child[7]=" +
					"&_ar.rt.nonStop=0" +
					"&_ar.rt.narrowSel=0" +
					"&ar.rt.narrow=airlines" +
					"&ar.rt.carriers[0]=" +
					"&ar.rt.carriers[1]=" +
					"&ar.rt.carriers[2]=" +
					"&ar.rt.cabin=C&search=Rechercher" +
					"&search=Rechercher"*/
	}
}
