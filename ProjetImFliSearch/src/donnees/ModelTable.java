package donnees;




import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.table.DefaultTableModel;



public class ModelTable extends DefaultTableModel
{
	//-------------------------------------------------------------------------------------------------------------------
	//constructeur pour la class ModelTable
	public ModelTable (ArrayList<Vol> parResultat)
	{
		//colonnes :
		//les entetes de colonne :
		
		//Si le resultat comporte des vols aller retour
		if(parResultat.get(12).getNumVolRetour()!=null)
		{
			
		String[] identifierColumn = {"N° Vol Aller","Aeroport depart","Distance (km)","Aeroport arrivee","Distance (km)","Date-heure/Départ/Aller","Date-heure/Arrivée/Aller","Durée Aller","Compagnie Aller","N° Vol Retour","Date-heure/Départ/Retour","Date-heure/Arrivée/Retour","Durée Retour","Compagnie Retour","Tarif total","Lien"};
		//le nombre de colonne 


		//le nombre de colonne :

		this.setColumnCount(identifierColumn.length);
		//On ajoute les entetes
		this.setColumnIdentifiers(identifierColumn);
	
		//lignes :
		this.setRowCount(parResultat.size());
		
		
		for(int i=0;i<parResultat.size()-1;i++)
		{
			for(int k=0;k<this.getColumnCount();k++)
			{
				if(k==0)
				{
					this.setValueAt(parResultat.get(i).getNumVolAller(), i, k);
				}
				else if(k==1)
				{
					this.setValueAt(parResultat.get(i).getAeroportDepart(), i, k);
				}
				else if(k==2)
				{
					this.setValueAt(parResultat.get(i).getAeroportDepart().recupererDistance(),i,k);	
				}
				else if(k==3)
				{
					this.setValueAt(parResultat.get(i).getAeroportArrivee(), i, k);
				}
				else if(k==4)
				{
					this.setValueAt(parResultat.get(i).getAeroportArrivee().recupererDistance(),i,k);	
				}
				else if(k==5)
				{
					this.setValueAt(parResultat.get(i).getDateAllerDepart(), i, k);
				}
				else if(k==6)
				{
					this.setValueAt(parResultat.get(i).getDateAllerArrivee(), i, k);
				}
				else if(k==7)
				{	
					long millis=parResultat.get(i).getDateAllerArrivee().getTime()-parResultat.get(i).getDateAllerDepart().getTime();
					
					String dureeAller= TimeUnit.MILLISECONDS.toHours(millis)+"h"+TimeUnit.MILLISECONDS.toMinutes(millis)%60;
					
					this.setValueAt(dureeAller, i, k);
				}
				else if(k==8)
				{
					this.setValueAt(parResultat.get(i).getCompagnieAller(), i, k);
				}
				else if(k==9)
				{
					this.setValueAt(parResultat.get(i).getNumVolRetour(), i, k);
				}
				else if(k==10)
				{
					this.setValueAt(parResultat.get(i).getDateRetourDepart(), i, k);
				}
				else if(k==11)
				{
					this.setValueAt(parResultat.get(i).getDateRetourArrivee(), i, k);
				}
				else if(k==12)
				{

					try{
						long millis=parResultat.get(i).getDateRetourArrivee().getTime()-parResultat.get(i).getDateRetourDepart().getTime();
						
						String dureeRetour=TimeUnit.MILLISECONDS.toHours(millis)+"h"+TimeUnit.MILLISECONDS.toMinutes(millis)%60;
						
						this.setValueAt(dureeRetour, i, k);
					}catch(NullPointerException e){/*en cas d'aller simple*/}
				}
				else if(k==13)
				{
					this.setValueAt(parResultat.get(i).getCompagnieRetour(), i, k);
				}
				else if(k==14)
				{
					this.setValueAt(parResultat.get(i).getPrix(), i, k);
				}
				else if(k==15)
				{
					this.setValueAt(parResultat.get(i).getLien(), i, k);
				}
				
				
			}//for
			}//for
		}//if
		else //Si le tableau ne comporte que des vols aller
		{
			//On adapte le tableau pour permettre d'afficher les vols aller
			String[] identifierColumn = {"N° Vol Aller","Aeroport depart","Distance (km)","Aeroport arrivee","Date-heure/Départ/Aller","Date-heure/Arrivée/Aller","Durée Aller","Compagnie Aller","Tarif total","Lien"};
			//le nombre de colonne 

			this.setColumnCount(identifierColumn.length);
			//On ajoute les entetes
			this.setColumnIdentifiers(identifierColumn);
		
			//lignes :
			this.setRowCount(parResultat.size());
			
			
			for(int i=0;i<parResultat.size()-1;i++)
			{
				for(int k=0;k<this.getColumnCount();k++)
				{
					if(k==0)
					{
						this.setValueAt(parResultat.get(i).getNumVolAller(), i, k);
					}
					else if(k==1)
					{
						this.setValueAt(parResultat.get(i).getAeroportDepart(), i, k);
					}
					else if(k==2)
					{
						this.setValueAt(parResultat.get(i).getAeroportDepart().recupererDistance(),i,k);	
					}
					else if(k==3)
					{
						this.setValueAt(parResultat.get(i).getAeroportArrivee(), i, k);
					}
					else if(k==4)
					{
						this.setValueAt(parResultat.get(i).getDateAllerDepart(), i, k);
					}
					else if(k==5)
					{
						this.setValueAt(parResultat.get(i).getDateAllerArrivee(), i, k);
					}
					else if(k==6)
					{	
						long millis=parResultat.get(i).getDateAllerArrivee().getTime()-parResultat.get(i).getDateAllerDepart().getTime();
						
						String dureeAller= TimeUnit.MILLISECONDS.toHours(millis)+"h"+TimeUnit.MILLISECONDS.toMinutes(millis)%60;
						
						this.setValueAt(dureeAller, i, k);
					}
					else if(k==7)
					{
						this.setValueAt(parResultat.get(i).getCompagnieAller(), i, k);
					}
					else if(k==8)
					{
						this.setValueAt(parResultat.get(i).getPrix(), i, k);
					}
					else if(k==9)
					{
						this.setValueAt(parResultat.get(i).getLien(), i, k);
					}
					
					
				}//for
				}//for
			}//if
			
		
	}//ModelePourTable

	
	public Class getColumnClass(int parNum)
	{
		return String.class;
	}//getColumnClass
	
}
