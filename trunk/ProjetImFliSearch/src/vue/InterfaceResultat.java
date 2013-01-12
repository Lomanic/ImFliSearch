package vue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;


import javax.swing.*;

import donnees.CelluleRenderer;
import donnees.ModelTable;
import donnees.MouseActionEcouteur;
import donnees.TableColumnAdjuster;
import donnees.Vol;





public class InterfaceResultat extends JFrame implements ActionListener 
{

	
	//champs relatif au panel de la table :
	private JPanel panelTables ;
	private JTable tableDesVols;
	
	// La JFrame sera gérer par un GridBagLayout, on créer donc un GridBagConstraints : 
	//private GridBagConstraints chContraintes = new GridBagConstraints();GridBagConstraints Contraintes = new GridBagConstraints();


	public InterfaceResultat (ArrayList<Vol> parResultat)
	{
		
		super("Résultats de la recherche");
		//création du panel principal de la JFrame :
		JPanel contentPane=new JPanel();
		contentPane.setLayout(new BorderLayout());
		
		//Pour voir si le tableau comporte des vols aller retour
		boolean allerretour=false;
		if(parResultat.size()>12)
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
		tableDesVols.setAutoCreateRowSorter(true);
		
		tca.adjustColumns();
		//Spécifie une largeur predefinie a chaque colonne ( attention : en cas de modification du nombre de colonne, il sera necessaire
		// de modifier ces lignes ci dessous )
		//tableDesVols.getColumnModel().getColumn(0).setPreferredWidth(150);
		//tableDesVols.getColumnModel().getColumn(1).setPreferredWidth(150);
		//tableDesVols.getColumnModel().getColumn(2).setWidth(10);
		/*tableDesVols.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableDesVols.getColumnModel().getColumn(4).setPreferredWidth(200);
		tableDesVols.getColumnModel().getColumn(5).setPreferredWidth(100);
		tableDesVols.getColumnModel().getColumn(6).setPreferredWidth(100);
		tableDesVols.getColumnModel().getColumn(7).setPreferredWidth(100);
		tableDesVols.getColumnModel().getColumn(8).setPreferredWidth(100);
		tableDesVols.getColumnModel().getColumn(9).setPreferredWidth(100);
		tableDesVols.getColumnModel().getColumn(10).setPreferredWidth(100);
		tableDesVols.getColumnModel().getColumn(11).setPreferredWidth(100);
		tableDesVols.getColumnModel().getColumn(12).setPreferredWidth(100);
		tableDesVols.getColumnModel().getColumn(13).setPreferredWidth(100);
		tableDesVols.getColumnModel().getColumn(14).setPreferredWidth(100);
		tableDesVols.getColumnModel().getColumn(15).setPreferredWidth(100);*/
		
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
		this.setIconImage(new ImageIcon("img/icone.png").getImage());
		setSize(950,600);
		setVisible(true);
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
