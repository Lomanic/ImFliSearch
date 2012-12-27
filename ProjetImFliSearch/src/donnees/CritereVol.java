package donnees;

import constantes.Constantes;

public class CritereVol {
	
	private Ville chVilleDepart;
	private Ville chVilleArrivee;
	private int chDistance;
	private int chAllerRetour;
	private int chNbAdulte;
	private int chNbEnfant;
	//private int chNbBebe;
	private int chAgeEnfant0;
	private int chAgeEnfant1;
	private int chAgeEnfant2;
	private int chAgeEnfant3;
	private int chAgeEnfant4;
	private int chAgeEnfant5;
	private int chAgeEnfant6;
	private int chAgeEnfant7;
	private int chClasse;
	private int chJourAller;
	private int chMoisAller;
	private int chAnneeAller;
	private int chJourRetour;
	private int chMoisRetour;
	private int chAnneeRetour;

	
	public CritereVol (Ville parVilleDepart, Ville parVilleArrivee, int parDistance, int parAllerRetour,int parNbAdulte,int parNbEnfant/*, int parNbBebe*/,
			int parAgeEnfant0, int parAgeEnfant1, int parAgeEnfant2, int parAgeEnfant3, int parAgeEnfant4, int parAgeEnfant5, int parAgeEnfant6, int parAgeEnfant7,
			int parClasse, int parJourAller, int parMoisAller, int parAnneeAller,int parJourRetour,int parMoisRetour, int parAnneeRetour)
	{
			chVilleDepart=parVilleDepart;
			chVilleArrivee=parVilleArrivee;
			chDistance=parDistance;
			chAllerRetour=parAllerRetour;
			chNbAdulte=parNbAdulte;
			chNbEnfant=parNbEnfant;
			//chNbBebe=parNbBebe;
			
			chAgeEnfant0=parAgeEnfant0;
			chAgeEnfant1=parAgeEnfant1;
			chAgeEnfant2=parAgeEnfant2;
			chAgeEnfant3=parAgeEnfant3;
			chAgeEnfant4=parAgeEnfant4;
			chAgeEnfant5=parAgeEnfant5;
			chAgeEnfant6=parAgeEnfant6;
			chAgeEnfant7=parAgeEnfant7;
			
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
	
	public String urlRecherche(Aeroport aeroDepart,Aeroport aeroArrivee)
	{
		String[] ar_type={"roundTrip","oneWay"};
		String[] ar_rt_cabin={"C","E","B","F"};
		
		return "http://www.ebookers.fr/shop/home?type=air"+
				"&ar.type="+ar_type[this.getChAllerRetour()]+
				"&ar.rt.leaveSlice.orig.key="+aeroDepart.getChCodeIATA()+
				"&ar.rt.leaveSlice.dest.key="+aeroArrivee.getChCodeIATA()+
				"&ar.rt.leaveSlice.date="+Constantes.joursDepart[this.getChJourAller()]+"%2F"+(this.getChMoisAller()+1)+"%2F"+Integer.parseInt(Constantes.annees[this.getChAnneeAller()])%100+ //pour avoir les deux derniers chiffres d'un int : http://yhoo.it/UZHfbS
				"&ar.rt.leaveSlice.time=Anytime" +
				
				"&ar.rt.returnSlice.date="+Constantes.joursDepart[this.getChJourRetour()]+"%2F"+(this.getChMoisRetour()+1)+"%2F"+Integer.parseInt(Constantes.annees[this.getChAnneeRetour()])%100+

				"&ar.rt.returnSlice.time=Anytime"+
				"&_ar.rt.flexAirSearch=0"+
				"&ar.rt.numAdult="+this.getChNbAdulte()+
				"&ar.rt.numChild="+this.getChNbEnfant()+
				"&ar.rt.child[0]="+this.getChAgeEnfant0()+
				"&ar.rt.child[1]="+this.getChAgeEnfant1()+
				"&ar.rt.child[2]="+this.getChAgeEnfant2()+
				"&ar.rt.child[3]="+this.getChAgeEnfant3()+
				"&ar.rt.child[4]="+this.getChAgeEnfant4()+
				"&ar.rt.child[5]="+this.getChAgeEnfant5()+
				"&ar.rt.child[6]="+this.getChAgeEnfant6()+
				"&ar.rt.child[7]="+this.getChAgeEnfant7()+
				"&_ar.rt.nonStop=0" +
				"&_ar.rt.narrowSel=0" +
				"&ar.rt.narrow=airlines" +
				"&ar.rt.carriers[0]=" +
				"&ar.rt.carriers[1]=" +
				"&ar.rt.carriers[2]=" +
				"&ar.rt.cabin=" +ar_rt_cabin[this.getChClasse()]+
				"&search=Rechercher" +
				"&search=Rechercher";
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

	public int getChAgeEnfant0() {
		return chAgeEnfant0;
	}

	public void setChAgeEnfant0(int chAgeEnfant0) {
		this.chAgeEnfant0 = chAgeEnfant0;
	}

	public int getChAgeEnfant1() {
		return chAgeEnfant1;
	}

	public void setChAgeEnfant1(int chAgeEnfant1) {
		this.chAgeEnfant1 = chAgeEnfant1;
	}

	public int getChAgeEnfant2() {
		return chAgeEnfant2;
	}

	public void setChAgeEnfant2(int chAgeEnfant2) {
		this.chAgeEnfant2 = chAgeEnfant2;
	}

	public int getChAgeEnfant3() {
		return chAgeEnfant3;
	}

	public void setChAgeEnfant3(int chAgeEnfant3) {
		this.chAgeEnfant3 = chAgeEnfant3;
	}

	public int getChAgeEnfant4() {
		return chAgeEnfant4;
	}

	public void setChAgeEnfant4(int chAgeEnfant4) {
		this.chAgeEnfant4 = chAgeEnfant4;
	}

	public int getChAgeEnfant5() {
		return chAgeEnfant5;
	}

	public void setChAgeEnfant5(int chAgeEnfant5) {
		this.chAgeEnfant5 = chAgeEnfant5;
	}

	public int getChAgeEnfant6() {
		return chAgeEnfant6;
	}

	public void setChAgeEnfant6(int chAgeEnfant6) {
		this.chAgeEnfant6 = chAgeEnfant6;
	}

	public int getChAgeEnfant7() {
		return chAgeEnfant7;
	}

	public void setChAgeEnfant7(int chAgeEnfant7) {
		this.chAgeEnfant7 = chAgeEnfant7;
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