package vue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;


import javax.swing.*;

import donnees.ModelTable;





public class InterfaceResultat extends JFrame implements ActionListener 
{
	//champs relatif au panelBouton :
	private JPanel chpanelBouton = new JPanel (new GridLayout(1,2,8,8));
	private JButton boutonSuivant = new JButton("Page suivante");
	private JButton boutonPrecedent = new JButton("Page precedente");
	
	//champs relatif au panel de la table :
	private JPanel panelTables ;
	private JTable tableDesVols;
	
	// La JFrame sera gérer par un GridBagLayout, on créer donc un GridBagConstraints : 
	private GridBagConstraints chContraintes = new GridBagConstraints();GridBagConstraints Contraintes = new GridBagConstraints();


	public InterfaceResultat ()
	{
		
		super("Liste des vols trouvés");
		//création du panel principal de la JFrame :
		JPanel contentPane=new JPanel();
		contentPane.setLayout(new GridBagLayout());
		
		//-------------------------------------------------------------------------------------------------//
		// Ajout du panelBouton et de ces boutons : 	
		boutonSuivant.addActionListener(this);
		boutonPrecedent.addActionListener(this);
		chpanelBouton.add(boutonPrecedent);
		chpanelBouton.add(boutonSuivant);
		//-------------------------------------------------------------------------------------------------//
		//Creation de la JTable
		tableDesVols = new JTable();
		tableDesVols.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//Ajout du model a la JTable
		tableDesVols.setModel(new ModelTable());
		//Hauteur des lignes 
		tableDesVols.setRowHeight(20);
		
		//Empeche l'utilisateur de modifier la JTable
		tableDesVols.getTableHeader().setResizingAllowed(false);
		tableDesVols.getTableHeader().setReorderingAllowed(false);
		//Spécifie une largeur predefinie a chaque colonne ( attention : en cas de modification du nombre de colonne, il sera necessaire
		// de modifier ces lignes ci dessous )
		tableDesVols.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableDesVols.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableDesVols.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableDesVols.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableDesVols.getColumnModel().getColumn(4).setPreferredWidth(200);
		tableDesVols.getColumnModel().getColumn(5).setPreferredWidth(100);
		tableDesVols.getColumnModel().getColumn(6).setPreferredWidth(100);
		tableDesVols.getColumnModel().getColumn(7).setPreferredWidth(100);
		tableDesVols.getColumnModel().getColumn(8).setPreferredWidth(100);
		// Ajout de la JTable dans son panel
		panelTables	= new JPanel(new FlowLayout());
		panelTables.add(new JScrollPane(tableDesVols));
		
		//-------------------------------------------------------------------------------------------------//
		//Ajout de tout les panels au Panel principal(contentPane) :
		chContraintes.insets = new Insets(20,20,20,20);
		ajouteComposant(contentPane,panelTables,0,0,1,1);
		ajouteComposant(contentPane,chpanelBouton,0,1,10,1);
		//-------------------------------------------------------------------------------------------------//
		//Ajout du panel principal dans la JFrame
		this.setContentPane(contentPane);

		setVisible(true);
		setSize(950,600);
		
	}
	
	
	private void ajouteComposant ( JPanel panel, Component parComposant, int parColonne, int parLigne, int parLargeur, int parHauteur)
	//positionne 4 attribut de l'objet chconstrainte
	{
		chContraintes.gridx = parColonne;
		chContraintes.gridy = parLigne;
		chContraintes.gridwidth = parLargeur;
		chContraintes.gridheight = parHauteur;
		panel.add(parComposant,chContraintes);
		
	}//ajoute composant
	
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
