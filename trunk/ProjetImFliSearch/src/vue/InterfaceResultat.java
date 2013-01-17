package vue;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


import javax.swing.*;

import donnees.CelluleRenderer;
import donnees.ModelTable;
import donnees.MouseActionEcouteur;
import donnees.TableColumnAdjuster;
import donnees.Vol;





public class InterfaceResultat extends JFrame
{

	
	//champs relatif au panel de la table :
	private JPanel panelTables ;
	private JTable tableDesVols;
	

	public InterfaceResultat (ArrayList<Vol> parResultat)
	{
		
		super("Résultats de la recherche");
		//création du panel principal de la JFrame :
		JPanel contentPane=new JPanel();
		contentPane.setLayout(new BorderLayout());
		
		//tri de la ArrayList en fonction du prix (voir compareTo de Vol)
		Collections.sort(parResultat);
		
		//Pour voir si le tableau comporte des vols aller retour
		boolean allerretour=false;
		if(parResultat.get(0).getCompagnieRetour()!=null)
		{
			allerretour=true;
		}
		
		
		//-------------------------------------------------------------------------------------------------//
		//Creation de la JTable
		tableDesVols = new JTable();
		//tableDesVols.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableDesVols.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnAdjuster tca = new TableColumnAdjuster(tableDesVols);
		
		//Ajout du model a la JTable
		tableDesVols.setModel(new ModelTable(parResultat));
		tableDesVols.setSize(this.getSize());
		//Hauteur des lignes 
		tableDesVols.setRowHeight(20);
		
		//Empeche l'utilisateur de modifier la JTable
		tableDesVols.getTableHeader().setResizingAllowed(true);
		tableDesVols.getTableHeader().setReorderingAllowed(false);
		
		
		tableDesVols.getTableHeader().setReorderingAllowed(false);
		//tableDesVols.setDefaultRenderer(String.class, new CelluleRenderer());
		tableDesVols.addMouseListener(new MouseActionEcouteur(allerretour));
		//tableDesVols.setAutoCreateRowSorter(true);
		
		tca.adjustColumns();

		//-------------------------------------------------------------------------------------------------//
		//Ajout de tout les panels au Panel principal(contentPane) :
		contentPane.add(new JScrollPane(tableDesVols),BorderLayout.CENTER);

		//-------------------------------------------------------------------------------------------------//
		//Ajout du panel principal dans la JFrame
		this.setContentPane(contentPane);
		this.setIconImage(new ImageIcon("img/icone.png").getImage());
		setSize(950,600);
		setVisible(true);
	}	
	//pour le tri en fonction des colonnes: http://docs.oracle.com/javase/tutorial/uiswing/components/table.html#sorting

}
