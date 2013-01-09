package vue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;


import javax.swing.*;

import donnees.ModelTable;
import donnees.Vol;





public class InterfaceResultat extends JFrame implements ActionListener 
{

	
	//champs relatif au panel de la table :
	private JPanel panelTables ;
	private JTable tableDesVols;
	
	// La JFrame sera g�rer par un GridBagLayout, on cr�er donc un GridBagConstraints : 
	//private GridBagConstraints chContraintes = new GridBagConstraints();GridBagConstraints Contraintes = new GridBagConstraints();


	public InterfaceResultat (ArrayList<Vol> parResultat)
	{
		
		super("R�sultats de la recherche");
		//cr�ation du panel principal de la JFrame :
		JPanel contentPane=new JPanel();
		contentPane.setLayout(new BorderLayout());
		
		//-------------------------------------------------------------------------------------------------//
		//Creation de la JTable
		tableDesVols = new JTable();
		tableDesVols.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//Ajout du model a la JTable
		tableDesVols.setModel(new ModelTable(parResultat));
		tableDesVols.setSize(this.getSize());
		//Hauteur des lignes 
		tableDesVols.setRowHeight(20);
		
		//Empeche l'utilisateur de modifier la JTable
		tableDesVols.getTableHeader().setResizingAllowed(true);
		tableDesVols.getTableHeader().setReorderingAllowed(false);
		//Sp�cifie une largeur predefinie a chaque colonne ( attention : en cas de modification du nombre de colonne, il sera necessaire
		// de modifier ces lignes ci dessous )
		/*tableDesVols.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableDesVols.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableDesVols.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableDesVols.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableDesVols.getColumnModel().getColumn(4).setPreferredWidth(200);
		tableDesVols.getColumnModel().getColumn(5).setPreferredWidth(100);
		tableDesVols.getColumnModel().getColumn(6).setPreferredWidth(100);
		tableDesVols.getColumnModel().getColumn(7).setPreferredWidth(100);
		tableDesVols.getColumnModel().getColumn(8).setPreferredWidth(100);*/
		
		
		// Ajout de la JTable dans son panel
	//panelTables	= new JPanel();
		//panelTables.add(new JScrollPane(tableDesVols));	
		
		//-------------------------------------------------------------------------------------------------//
		//Ajout de tout les panels au Panel principal(contentPane) :
		//chContraintes.insets = new Insets(20,20,20,20);
		contentPane.add(new JScrollPane(tableDesVols),BorderLayout.CENTER);
		//ajouteComposant(contentPane,panelTables,0,0,1,1);

		//-------------------------------------------------------------------------------------------------//
		//Ajout du panel principal dans la JFrame
		this.setContentPane(contentPane);

		setVisible(true);
		setSize(950,600);
		
	}
	
	
	/*private void ajouteComposant ( JPanel panel, Component parComposant, int parColonne, int parLigne, int parLargeur, int parHauteur)
	//positionne 4 attribut de l'objet chconstrainte
	{
		chContraintes.gridx = parColonne;
		chContraintes.gridy = parLigne;
		chContraintes.gridwidth = parLargeur;
		chContraintes.gridheight = parHauteur;
		panel.add(parComposant,chContraintes);
		
	}//ajoute composant*/
	
	//pour le tri en fonction des colonnes: http://docs.oracle.com/javase/tutorial/uiswing/components/table.html#sorting
	
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
