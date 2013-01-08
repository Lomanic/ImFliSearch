package donnees;




import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;



public class ModelTable extends DefaultTableModel
{
	//-------------------------------------------------------------------------------------------------------------------
	//constructeur pour la class ModelTable
	public ModelTable (ArrayList<Vol> parResultat)
	{
		//colonnes :
		//les entetes de colonne :
		String[] identifierColumn = {"Aeroport depart","Aeroport arrivee","Date-heure/D�part/Aller","Date-heure/Arriv�e/Aller","Dur�e Aller","Compagnie Aller","Date-heure/D�part/Retour","Date-heure/Arriv�e/Retour","Dur�e Retour","Compagnie Retour","Prix","Lien"};
		//le nombre de colonne :
		this.setColumnCount(identifierColumn.length);
		//On ajoute les entetes
		this.setColumnIdentifiers(identifierColumn);
	
		//lignes :
		this.setRowCount(parResultat.size());
		
		System.out.println(parResultat.get(1).getLien());
		
		for(int i=0;i<parResultat.size()-1;i++)
		{
			for(int k=0;k<this.getColumnCount();k++)
			{
				if(k==0)
				{
					this.setValueAt(parResultat.get(i).getAeroportDepart(), i, k);
				}
				else if(k==1)
				{
					this.setValueAt(parResultat.get(i).getAeroportArrivee(), i, k);
				}
				else if(k==2)
				{
					this.setValueAt(parResultat.get(i).getDateAllerDepart(), i, k);
				}
				else if(k==3)
				{
					this.setValueAt(parResultat.get(i).getDateAllerArrivee(), i, k);
				}
				else if(k==4)
				{	
					this.setValueAt("", i, k);
				}
				else if(k==5)
				{
					this.setValueAt(parResultat.get(i).getCompagnieAller(), i, k);
				}
				else if(k==6)
				{
					this.setValueAt(parResultat.get(i).getDateRetourDepart(), i, k);
				}
				else if(k==7)
				{
					this.setValueAt(parResultat.get(i).getDateRetourArrivee(), i, k);
				}
				else if(k==8)
				{
					this.setValueAt("", i, k);
				}
				else if(k==9)
				{
					this.setValueAt(parResultat.get(i).getCompagnieRetour(), i, k);
				}
				else if(k==10)
				{
					this.setValueAt(parResultat.get(i).getPrix(), i, k);
				}
				else if(k==11)
				{
					this.setValueAt(parResultat.get(i).getLien(), i, k);
				}
				
				
			}
		}
		
		/*//Ajout des valeurs dans les cellules
		Iterator <Reservation> it = treesetannee.iterator();
		Reservation res;
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
