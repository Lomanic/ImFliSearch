package donnees;



import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.table.DefaultTableModel;



public class ModelTable extends DefaultTableModel
{
	//-------------------------------------------------------------------------------------------------------------------
	//constructeur pour la class ModelTable
	public ModelTable ()
	{
		//colonnes :
		//les entetes de colonne :
		String[] identifierColumn = {"Aeroport depart","Aeroport arrivee","Date-heure/départ-arrivé/Aller","Durée Aller","Date-heure/départ-arrivé/Retour","Durée Retour","Compagnie","Prix","Lien"};
		//le nombre de colonne :
		this.setColumnCount(identifierColumn.length);
		//On ajoute les entetes
		this.setColumnIdentifiers(identifierColumn);
	
		//lignes :
		this.setRowCount(20);
		
		
		
		/*//Ajout des valeurs dans les cellules
		Iterator <Reservation> it = treesetannee.iterator();
		Reservation res;
		while (it.hasNext())
		{	res=it.next();
			if (res.chmois == parMois && res.chsalle == parIndiceNomSalle)
			{
				int numligne;
				int numcolonne;
				numligne=res.chheure;
				numcolonne=res.chjour;
				this.setValueAt(res,numligne,numcolonne);
		
			}//if

		}//while
		*/
		
	
	}//ModelePourTable

	
	public Class getColumnClass(int parNum)
	{
		
		return String.class;
	}//getColumnClass
	
}
