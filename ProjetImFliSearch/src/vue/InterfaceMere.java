﻿
package vue;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import java.net.UnknownHostException;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import constantes.Constantes;
import donnees.Aeroport;
import donnees.Ville;
import donnees.CritereVol;
import donnees.Vol;

public class InterfaceMere extends JFrame implements ActionListener, ItemListener{
	
	JPanel contentPane;
	
	//panelVille
	private JTextField chVilleDepart = new JTextField(1);
	private JTextField chVilleArrive = new JTextField(1);

	//panelVoyageur
	private JComboBox chNbAdulte = new JComboBox(Constantes.nombres);
	private JComboBox chNbEnfants = new JComboBox(Constantes.nombres);
	//private JComboBox chNbBebe = new JComboBox(Constantes.nombres);
	private int [] ageEnfant=new int[8];
	
	//panelClasse
	private JComboBox chClasse=new JComboBox(Constantes.classes);
	//panelPerimetre
	private JTextField chPerimetre = new JTextField(5);
	//panelDate

	

	private JPanel panelDateDepart = new JPanel();
	private JPanel panelDateRetour = new JPanel();
	
	JLabel chLabelJourDepart=new JLabel("Jour du depart :");
	JLabel chLabelJourRetour=new JLabel("Jour du retour :");
	
	
	private JComboBox chDateJour;

	private JComboBox chDateMois=new JComboBox(Constantes.mois);
	private JComboBox chDateAnnee;
	private JComboBox chDateJourRetour;
	private JComboBox chDateMoisRetour=new JComboBox(Constantes.mois);
	private JComboBox chDateAnneeRetour;
	
	
	
	
	//panelAllerRetour
	private JRadioButton[] chButtonAllerRetour= new JRadioButton[2];
	private ButtonGroup chboutonGroupe=new ButtonGroup();
	
	private GridBagConstraints chContraintes = new GridBagConstraints();
	
	//Boutons Validation et Annulation
	private JButton chBoutonValidation = new JButton("Validation");
	private JButton chBoutonAnnulation = new JButton("Annulation");
	
	//Données pour l'affichage de la date courante
	private Calendar calendarMaintenant = Calendar.getInstance();
	private int numJour=calendarMaintenant.get(Calendar.DAY_OF_MONTH);
	private int numMois=calendarMaintenant.get(Calendar.MONTH);
	private int numAnnee=calendarMaintenant.get(Calendar.YEAR);
	private int numDeJourMois=calendarMaintenant.getActualMaximum(Calendar.DAY_OF_MONTH);
	
	private JButton unBouton = new JButton("Valider");
	
	private JDialog choixEnfants;

	
	public InterfaceMere(String parTitre)
	{
		super(parTitre);
		
		
		//-----------------Instanciation de Constantes.annees--------------------//
		
		int nbAnnee=5;
		Constantes.annees= new String[5];
		for(int i=0; i<nbAnnee; i++)
		{
			Constantes.annees[i]= String.valueOf(numAnnee+i);
		}
		chDateAnnee=new JComboBox(Constantes.annees);
		chDateAnneeRetour=new JComboBox(Constantes.annees);
		
		//-----------------FIN Instanciation de Constantes.annees--------------------//
				
		
		
		contentPane=new JPanel();
		this.setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		contentPane.setLayout(new GridBagLayout());
		
		chContraintes.insets = new Insets (5, 5, 5, 5);
		
		chContraintes.fill = GridBagConstraints.HORIZONTAL;
		chContraintes.anchor = GridBagConstraints.CENTER;
		
		chContraintes.weightx = 1;
		chContraintes.weighty = 1;	
		
		
		
		//-----------------LABEL DE BIENVENUE--------------------//
		JLabel chBienvenue= new JLabel("Bienvenue sur ImFliSearch");
		chBienvenue.setFont(new Font("Arial",Font.BOLD,25));
		//-----------------PANEL-VILLE----------------------------//
		JPanel panelVille = new JPanel() ;
		panelVille.setBorder(new TitledBorder("Ville")) ;
		panelVille.setLayout(new GridBagLayout());
		
		JLabel labelDepart=new JLabel("Ville départ :");
		JLabel labelArrive=new JLabel("Ville arrivée :");
		
		this.ajoutComposant(labelDepart, panelVille, 0, 0, 1, 1);
		this.ajoutComposant(chVilleDepart, panelVille, 1, 0, 1, 1);
		this.ajoutComposant(labelArrive, panelVille, 0, 1, 1, 1);
		this.ajoutComposant(chVilleArrive, panelVille, 1, 1, 1, 1);
		
		chVilleDepart.addActionListener(this);
		chVilleArrive.addActionListener(this);
		
		
		//-----------------PANEL-PERIMETRE----------------------------//
		JPanel panelPerimetre = new JPanel() ;
		panelPerimetre.setBorder(new TitledBorder("Périmètre de recherche")) ;
		panelPerimetre.setLayout(new GridBagLayout());
		JLabel LabelchoixPerimetre=new JLabel("Distance maximale de recherche (en km):");
		 
		this.ajoutComposant(LabelchoixPerimetre, panelPerimetre, 0, 0, 1, 1);
		this.ajoutComposant(chPerimetre, panelPerimetre, 1, 0, 1, 1);
		
		chPerimetre.addActionListener(this);
		
		//-----------------PANEL-ALLER-RETOUR--------------------------//
		JPanel panelAllerRetour = new JPanel() ;
		chButtonAllerRetour[0] = new JRadioButton("Oui");
		chButtonAllerRetour[1] = new JRadioButton("Non");
		chButtonAllerRetour[0].addActionListener(this);
		chButtonAllerRetour[1].addActionListener(this);
		
		panelAllerRetour.setBorder(new TitledBorder("Aller-Retour")) ;
		panelAllerRetour.setLayout(new GridBagLayout());
		chButtonAllerRetour[0].setActionCommand(Integer.toString(0));
		chButtonAllerRetour[1].setActionCommand(Integer.toString(1));
		chboutonGroupe.add(chButtonAllerRetour[0]);
		chboutonGroupe.add(chButtonAllerRetour[1]);
		
		this.ajoutComposant(chButtonAllerRetour[0], panelAllerRetour, 0, 0, 1, 1);
		this.ajoutComposant(chButtonAllerRetour[1], panelAllerRetour, 1, 0, 1, 1);
		
		chButtonAllerRetour[0].setSelected(true);
		//-----------------PANEL-LE TYPE DE VOYAGEUR----------------------------//
		JPanel panelVoyageur = new JPanel() ;
		panelVoyageur.setBorder(new TitledBorder("Type de voyageur")) ;
		panelVoyageur.setLayout(new GridBagLayout());
		JLabel chLabelAdulte=new JLabel("Adulte(s)");
		JLabel chLabelEnfant=new JLabel("Enfant(s) ( 0 - 17 ans )");
		//JLabel chLabelBebe=new JLabel("Bebes (-2 ans)");
		
		this.ajoutComposant(chLabelAdulte, panelVoyageur, 0, 0, 1, 1);
		this.ajoutComposant(chNbAdulte, panelVoyageur, 1, 0, 1, 1);
		chNbAdulte.setSelectedIndex(1);
		
		this.ajoutComposant(chLabelEnfant, panelVoyageur, 0, 1, 1, 1);
		this.ajoutComposant(chNbEnfants, panelVoyageur, 1, 1, 1, 1);
		chNbEnfants.addActionListener(this);
		
		//this.ajoutComposant(chLabelBebe, panelVoyageur, 0, 2, 1, 1);
		//this.ajoutComposant(chNbBebe, panelVoyageur, 1, 2, 1, 1);
		

		//-----------------PANEL-LA-CLASSE-DU-VOL----------------------------//
		JPanel panelClasse = new JPanel() ;
		panelClasse.setBorder(new TitledBorder("Classe")) ;
		panelClasse.setLayout(new GridBagLayout());
		JLabel chChoixClasse=new JLabel("Choix de la classe :");
		
		this.ajoutComposant(chChoixClasse, panelClasse, 0, 0, 1, 1);
		this.ajoutComposant(chClasse, panelClasse, 0, 1, 1, 1);
		
		
		//-------------------PANEL-DATE----------------------------------------//
		
		constructionPanelDateDepart(numDeJourMois);
		constructionPanelDateRetour(numDeJourMois);
		
		chDateJour.setSelectedIndex(numJour-1);
		chDateMois.setSelectedIndex(numMois);
		
		chDateJour.addItemListener(this);
		chDateMois.addItemListener(this);
		chDateAnnee.addItemListener(this);
		
		
		chDateJourRetour.setSelectedIndex(numJour-1);
		chDateMoisRetour.setSelectedIndex(numMois);
		
		chDateJourRetour.addItemListener(this);
		chDateMoisRetour.addItemListener(this);
		chDateAnneeRetour.addItemListener(this);
			
		
		

		//---------------------BOUTONS-VALIDATION-ANNULATION----------------------//
		chBoutonValidation.addActionListener(this);
		chBoutonAnnulation.addActionListener(this);
		
		//-----------------AJOUT DANS LA JFRAME--------------------//
		
		chContraintes.fill = GridBagConstraints.CENTER;
		// Ajout labelbienvenue
		this.ajoutComposant(chBienvenue,contentPane,0,0,2,1);
		
		chContraintes.anchor = GridBagConstraints.NORTH;
		chContraintes.fill = GridBagConstraints.BOTH;
		
		// Ajout panelville
		this.ajoutComposant(panelVille,contentPane,0,1,1,2);
		// Ajout panelperimetre
		this.ajoutComposant(panelPerimetre,contentPane,1,1,1,1);
		// Ajout panelAllerRetour
		this.ajoutComposant(panelAllerRetour,contentPane,1,2,1,1);
		// Ajout panelType de Voyageur
		this.ajoutComposant(panelVoyageur,contentPane,0,3,1,1);
		// Ajout panelType de Voyageur
		this.ajoutComposant(panelClasse,contentPane,1,3,1,1);
		
		
		//Ajout boutons		
		chContraintes.fill = GridBagConstraints.CENTER;
		
		this.ajoutComposant(chBoutonValidation,contentPane, 0, 5, 1, 1);
		this.ajoutComposant(chBoutonAnnulation,contentPane, 1, 5, 1, 1);
		
		
		this.pack();
		this.setMinimumSize(this.getSize());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setIconImage(new ImageIcon("img/icone.png").getImage());
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		

	}//InterfaceMere(String parTitre)
	
	
	//Fonction Ajoute un composant a la JFrame
	public void ajoutComposant(Component parComponent,JPanel parConteneur,
			int parColonne, int parLigne, int parLargeur, int parHauteur)
	{
		chContraintes.gridx=parColonne;
		chContraintes.gridy=parLigne;
		chContraintes.gridwidth=parLargeur;
		chContraintes.gridheight=parHauteur;
		parConteneur.add(parComponent, chContraintes);
	}//setContraintes
	
	public void constructionPanelDateDepart(int parNbJoursDuMois)
	{
		Constantes.joursDepart= new String[parNbJoursDuMois];
		for(int i=0; i<parNbJoursDuMois; i++)
		{
			Constantes.joursDepart[i]= String.valueOf(i+1);
		}
		chDateJour=new JComboBox(Constantes.joursDepart);
		
		panelDateDepart = new JPanel();
		panelDateDepart.setBorder(new TitledBorder("Dates du Départ"));
		panelDateDepart.setLayout(new GridBagLayout());
		
		chContraintes.anchor = GridBagConstraints.CENTER;
		chContraintes.fill = GridBagConstraints.HORIZONTAL;
		
		this.ajoutComposant(chLabelJourDepart, panelDateDepart, 0, 0, 1, 1);
		this.ajoutComposant(chDateJour, panelDateDepart, 1, 0, 1, 1);
		this.ajoutComposant(chDateMois, panelDateDepart, 2, 0, 1, 1);
		this.ajoutComposant(chDateAnnee, panelDateDepart, 3, 0, 1, 1);
		
		chContraintes.anchor = GridBagConstraints.NORTH;
		chContraintes.fill = GridBagConstraints.BOTH;
		//Ajout panelDateDepart
		this.ajoutComposant(panelDateDepart,contentPane,0,4,1,1);
		
	}//constructionPanelDateDepart(int parNbJoursDuMois)
	
	public void constructionPanelDateRetour(int parNbJoursDuMois)
	{
		Constantes.joursRetour= new String[parNbJoursDuMois];
		for(int i=0; i<parNbJoursDuMois; i++)
		{
			Constantes.joursRetour[i]= String.valueOf(i+1);
		}
		chDateJourRetour=new JComboBox(Constantes.joursRetour);
		
		panelDateRetour = new JPanel();
		panelDateRetour.setBorder(new TitledBorder("Date du Retour"));
		panelDateRetour.setLayout(new GridBagLayout());
		
		chContraintes.anchor = GridBagConstraints.CENTER;
		chContraintes.fill = GridBagConstraints.HORIZONTAL;
		
		this.ajoutComposant(chLabelJourRetour, panelDateRetour, 0, 0, 1, 1);
		this.ajoutComposant(chDateJourRetour, panelDateRetour, 1, 0, 1, 1);
		this.ajoutComposant(chDateMoisRetour, panelDateRetour, 2, 0, 1, 1);
		this.ajoutComposant(chDateAnneeRetour, panelDateRetour, 3, 0, 1, 1);
		
		chContraintes.anchor = GridBagConstraints.NORTH;
		chContraintes.fill = GridBagConstraints.BOTH;
		//Ajout panelDateretour
		this.ajoutComposant(panelDateRetour,contentPane,1,4,1,1);
		
	}//constructionPanelDateRetour(int parNbJoursDuMois)
			


	public void itemStateChanged(ItemEvent e)
	{
		if (e.getStateChange() == 1) 
		{
			if(e.getSource()==chDateMois || e.getSource()==chDateAnnee)
			{
				Calendar calendarChangeDepart = Calendar.getInstance();
				
				int numMoisDepart=chDateMois.getSelectedIndex();
				int numAnneeDepart= Integer.parseInt(Constantes.annees[chDateAnnee.getSelectedIndex()]);

				calendarChangeDepart.set(numAnneeDepart, numMoisDepart, 1);

				int numDeJourMoisDepart=calendarChangeDepart.getActualMaximum(Calendar.DAY_OF_MONTH);
				
				int jourDateJour = chDateJour.getSelectedIndex();
				remove(panelDateDepart);
				constructionPanelDateDepart(numDeJourMoisDepart);
				if(jourDateJour<numDeJourMoisDepart)
					chDateJour.setSelectedIndex(jourDateJour);
				else
					chDateJour.setSelectedIndex(chDateJour.getItemCount()-1);
			}//if
			
			if(e.getSource()==chDateMoisRetour || e.getSource()==chDateAnneeRetour)
			{
				Calendar calendarChangeArrivee = Calendar.getInstance();
				
				int numMoisArrivee=chDateMoisRetour.getSelectedIndex();
				int numAnneeArrivee= Integer.parseInt(Constantes.annees[chDateAnneeRetour.getSelectedIndex()]);	
				
				calendarChangeArrivee.set(numAnneeArrivee, numMoisArrivee, 1);

				int numDeJourMoisArrivee=calendarChangeArrivee.getActualMaximum(Calendar.DAY_OF_MONTH);
				//System.out.println("Nombre Jours du Mois Arrivee:" + numDeJourMoisArrivee);
				
				int jourDateJourRetour = chDateJourRetour.getSelectedIndex();
				
				remove(panelDateRetour);
				constructionPanelDateRetour(numDeJourMoisArrivee);
			//	System.out.println(jourDateJourRetour+" < " +(numDeJourMoisArrivee-1));
				if(jourDateJourRetour<numDeJourMoisArrivee)
					chDateJourRetour.setSelectedIndex(jourDateJourRetour);
				else
					chDateJourRetour.setSelectedIndex(chDateJourRetour.getItemCount()-1);
			}//if
			
			
			contentPane.updateUI();
			
		}//if
		
	}//itemStateChanged(ItemEvent e)
	
	
	
	

	public void actionPerformed (ActionEvent telEvenement)
	{
		//**************** Traitement de l'Interface pour Choix Aller-Retour ou Aller Simple**********
				if (telEvenement.getSource()==chButtonAllerRetour[1])
				{
					this.chDateAnneeRetour.setEnabled(false);
					this.chDateMoisRetour.setEnabled(false);
					this.chDateJourRetour.setEnabled(false);
				}
				
				else
					if(telEvenement.getSource()==chButtonAllerRetour[0])
					{
						this.chDateAnneeRetour.setEnabled(true);
						this.chDateMoisRetour.setEnabled(true);
						this.chDateJourRetour.setEnabled(true);
					}
					
		//**************** Fin Traitement de l'Interface pour Choix Aller-Retour ou Aller Simple**********
		
		
		if(telEvenement.getSource()==chNbEnfants)
		{
		//System.out.println(chNbEnfants.getSelectedIndex());
			if(chNbEnfants.getSelectedIndex()!=0)
			{
				choixEnfants= new JDialog(new JFrame(),"Indiquer l'âge des enfants",true);
				Container contentPaneDuDialogue = choixEnfants.getContentPane();
				JPanel uneFenetre= new JPanel();
				JComboBox [] tab = new JComboBox[Constantes.agesEnfants.length];
				
				for(int h=0;h<tab.length;h++)
				{
					tab[h]=new JComboBox(Constantes.agesEnfants);
				}
				
				choixEnfants.setLocation(300, 300);
				
				for(int g=0;g<chNbEnfants.getSelectedIndex();g++)
				{
					GridLayout unLayout = new GridLayout(6,1);
					uneFenetre.setLayout(unLayout);
					JLabel unJlabel=new JLabel("Enfant "+(g+1)+" : ");
					unJlabel.setPreferredSize(new Dimension(10,5));
					tab[g].addActionListener(this);
					uneFenetre.add(unJlabel);
					uneFenetre.add(tab[g]);
				}
				
				JPanel uneFenetre1 = new JPanel();
				
				
				//uneFenetre1.add(unBouton,"CENTER");
				uneFenetre1.add(new JLabel("Pour valider, fermez la fenêtre."));
				BorderLayout unLayout1= new BorderLayout();
				contentPaneDuDialogue.setLayout(unLayout1);
				contentPaneDuDialogue.add(uneFenetre,"North");
				contentPaneDuDialogue.add(uneFenetre1,"South");
				
				
				
				choixEnfants.setMinimumSize(new Dimension(300,200));
				choixEnfants.pack();
				choixEnfants.setVisible(true);

				for(int l=0;l<chNbEnfants.getSelectedIndex();l++)
					{
						ageEnfant[l]=tab[l].getSelectedIndex()+1;
						System.out.println(ageEnfant[l]);
						
					}		//for	
				}//if
			
			
			
			/*if(telEvenement.getSource()==unBouton)
			{
				choixEnfants.setVisible (false);
				choixEnfants.dispatchEvent (new WindowEvent (choixEnfants, WindowEvent.WINDOW_CLOSING));
				System.out.println("OKFFNJH");
			}*/
			//si nb enfants sélectionné = 0
			/*else if( chNbEnfants.getSelectedIndex()==0){
					for (int f=0;f<8;f++)
					{
						ageEnfant[f]=0;
						System.out.println(ageEnfant[f]);
					}
				}*/
					
			
			}//if
		
		
		//On récupere les valeurs indiquées par l'utilisateur lorqu'il selectionne le bouton de validation
		else if (telEvenement.getSource()==chBoutonValidation)
		{
			//On créé un ArrayList contenant les erreurs saisies par l'utilisateur
			ArrayList <String> tabErreurs = new ArrayList <String> ();
			 
			//On recupere le type de vol
			int AllerRetour=Integer.parseInt(chboutonGroupe.getSelection().getActionCommand());
			
			//Conversion des JTextField en String
			String VilleDepart1=chVilleDepart.getText();
			String VilleArrivee1=chVilleArrive.getText();
			
			try
			{
				int perimetreEntre=0;
				// Creation des objets villes
				Ville VilleDepart= new Ville(VilleDepart1);
				Ville VilleArrivee= new Ville(VilleArrivee1);
				try
				{
					perimetreEntre=Integer.parseInt(chPerimetre.getText());
					if (perimetreEntre<=0)
					{
						tabErreurs.add("Votre périmètre est négatif, ou égal à zéro...\n ");
						
					}// le périmètre est null ou négatif
					else if (perimetreEntre>300)
					{
						tabErreurs.add("Votre périmètre ne peut pas être supérieur à 300 km.\n");
						
					}// le périmètre est null ou négatif
				}
				catch(NumberFormatException e)
				{
					tabErreurs.add("La valeur entrée pour le périmètre n'est pas un entier.");
				}//catch
				
				
				double temps=(chDateMoisRetour.getSelectedIndex()+1)*chDateJourRetour.getSelectedIndex()*24*3600+
						Integer.parseInt(Constantes.annees[chDateAnneeRetour.getSelectedIndex()])*365.25*24*3600;
				
				double temps1=(chDateMois.getSelectedIndex()+1)*chDateJour.getSelectedIndex()*24*3600+
						Integer.parseInt(Constantes.annees[chDateAnnee.getSelectedIndex()])*365.25*24*3600;
				
				/*if(temps-temps1>0)
				{
					System.out.println("La date est bonne");
				}
				else*/ if ((temps-temps1)<0 && chButtonAllerRetour[0].isSelected())
				{
					tabErreurs.add("La date de départ doit être antérieure à celle de retour.\n");
				}
				
			
				
				if(chVilleDepart.getText().equals("") && chVilleArrive.getText().equals(""))
				{
					tabErreurs.add("Veuillez remplir les champs Ville de départ et d'arrivée.\n");
					
				}//if les champs ville départ/arrivée n'ont pas été remplie
				else if(chVilleDepart.getText().equals(""))
				{
					tabErreurs.add("Veuillez remplir le champ Ville de départ.\n");
				}
				else if(chVilleArrive.getText().equals(""))
				{
					tabErreurs.add("Veuillez remplir le champ Ville d'arrivée.\n");
				}
				
				
				else if (VilleDepart.existe()==0 && VilleArrivee.existe()!=0)
				{
					tabErreurs.add("La ville de depart n'existe pas.\n");
			
				}// la ville de départ n'existe pas mais la ville d'arrivée existe
				
				else if (VilleDepart.existe()!=0 && VilleArrivee.existe()==0)
				{
					tabErreurs.add("La ville d'arrivee n'existe pas.\n");
					
				}// la ville de départ existe  mais la ville d'arrivée n'existe pas
				
				else if (VilleDepart.existe()==0 && VilleArrivee.existe()==0)
				{
					tabErreurs.add("Les villes de depart et d'arrivee n'existent pas.\n");
					
				}// les deux villes n'existent pas 
				
	
				//Condition permettant d'indiquer si l'utilisateur a bien entre toutes les valeurs demandees
				if(tabErreurs.size()==0)
				{	
						
						//------------------Création de la liste d'aéroports proches de la ville de départ--------//
						
						Aeroport [] listeVilleDepartAeroportCorrige = VilleDepart.listAirport((double)perimetreEntre);
					
			  		  
						//------------------Création de la liste d'aéroports proches de la ville d'arrivée--------//
						
						
						Aeroport [] listeVilleArriveeAeroportCorrige = VilleArrivee.listAirport((double)perimetreEntre);
						
						//------------------------------------------------------------------------//
						
					    if (listeVilleDepartAeroportCorrige.length==0 && listeVilleArriveeAeroportCorrige.length==0)
						{
					    	JOptionPane.showMessageDialog(this, "Aucun aéroport n'a été trouvé pour le périmètre choisi.",
							"Erreur : Périmètre trop faible.",JOptionPane.ERROR_MESSAGE);
					    }//if la liste des aéroports est vide pour la ville de départ
						    
					    
						if (VilleDepart.existe()!=0 && VilleArrivee.existe()!=0 && listeVilleArriveeAeroportCorrige.length!=0 && listeVilleDepartAeroportCorrige.length!=0 )
						{
							
							//On instancie un objet CritereVol qui va contenir les critères du vol recherché par l'utilisateur
							CritereVol lesCriteres= new CritereVol(VilleDepart,VilleArrivee,perimetreEntre,AllerRetour,chNbAdulte.getSelectedIndex(),chNbEnfants.getSelectedIndex(),
							/*chNbBebe.getSelectedIndex(),*/ageEnfant[0],ageEnfant[1],ageEnfant[2],ageEnfant[3],ageEnfant[4],ageEnfant[5],ageEnfant[6],ageEnfant[7],chClasse.getSelectedIndex(),chDateJour.getSelectedIndex(),chDateMois.getSelectedIndex(),
							chDateAnnee.getSelectedIndex(),chDateJourRetour.getSelectedIndex(),chDateMoisRetour.getSelectedIndex(),chDateAnneeRetour.getSelectedIndex());
						
						
							//On affiche une fenetre indiquant les informations sur le vol pour que l'utilisateur puisse confirmer
							int confirmation=JOptionPane.showConfirmDialog(this, "Voici les informations que vous avez tapées:"+"\n"+lesCriteres.toString()+"\n",
									"Voulez vous valider ces informations ?",JOptionPane.OK_CANCEL_OPTION);
							
							if (confirmation==JOptionPane.YES_OPTION)
							{
								ArrayList<Vol> resultatsRecherche=new ArrayList<Vol>();
								ArrayList<String> ligneJsoupSearchBarContent= new ArrayList<String>();
								ArrayList<String> ligneJsoupContainer= new ArrayList<String>();
								ArrayList<String> ligneJsoupButtonLink= new ArrayList<String>();
								
								//Boucle permettant de génerer les différentes combinaisons entre les aéroports trouvés
								for(int f=0;f<listeVilleDepartAeroportCorrige.length;f++)
								{
									for(int z=0;z<listeVilleArriveeAeroportCorrige.length;z++)
									{
										System.out.println(lesCriteres.urlRecherche(listeVilleDepartAeroportCorrige[f], listeVilleArriveeAeroportCorrige[z]));
										
										
										Document doc = Jsoup.connect(lesCriteres.urlRecherche(listeVilleDepartAeroportCorrige[f], listeVilleArriveeAeroportCorrige[z])).timeout(50000).get();
										
										if(doc.select(".container").isEmpty())
										{
											//JOptionPane.showMessageDialog(this, "Pas  de vols trouvés entre "+listeVilleDepartAeroportCorrige[f].getChNom()+" et "+listeVilleArriveeAeroportCorrige[z].getChNom());
											System.out.println("Pas  de vols trouvés entre "+listeVilleDepartAeroportCorrige[f].getChCodeIATA()+" et "+listeVilleArriveeAeroportCorrige[z].getChCodeIATA());
										}
										else
										{

											//paramètres de recherche
											java.util.Iterator<Element> it1 = doc.select(".searchBarContent").iterator();
											while (it1.hasNext())
											{
												String temp =it1.next().text();
												ligneJsoupSearchBarContent.add(temp);
											}
											
											
											//vols
											java.util.Iterator<Element> it2 = doc.select(".container").iterator();
											while (it2.hasNext())
											{
												String temp =it2.next().text();
												ligneJsoupContainer.add(temp);
											}
											
											
											
											//liens
											java.util.Iterator<Element> it3 = doc.select(".container>.secondary>.content>.linkAsButton>.buttonLink.link").iterator();
											while (it3.hasNext())
											{
												String temp =it3.next().attr("href");
												ligneJsoupButtonLink.add(temp);
											}
											
											
											for (int i=0;i<ligneJsoupContainer.size();i++)//peuplement de la ArrayList de Vol 
											{
												resultatsRecherche.add(new Vol(ligneJsoupSearchBarContent.get(0),ligneJsoupContainer.get(i),ligneJsoupButtonLink.get(i),listeVilleDepartAeroportCorrige,listeVilleArriveeAeroportCorrige));
											}
											
											//remise à zéro des ligneJsoup pour éviter les duplicatas
											ligneJsoupSearchBarContent.clear();
											ligneJsoupContainer.clear();
											ligneJsoupButtonLink.clear();
										}//else du test du nombre d'aéroports trouvés
										
									}//for
								}//for
								
								//affichage de la liste des vols :

								if (!resultatsRecherche.isEmpty())
								{
									InterfaceResultat interfaceR = new InterfaceResultat(resultatsRecherche);
									interfaceR.setLocationRelativeTo(this);
								}
								else
									JOptionPane.showMessageDialog(this, "Aucun vols trouvés selon vos critères");
	
							
						}//if les villes départ/arrivée existent et la liste des aéroports n'est pas vide
					}//if toutes les info ont été saisies correctement
						
					
				}//else
				
			}//try
			
			catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Ebookers met trop de temps à répondre.\nVérifiez votre connexion et réessayez.","Erreur",JOptionPane.ERROR_MESSAGE);
				//e.printStackTrace();
			}
			
			//Si le tableau des erreurs n'est pas vide
			if(tabErreurs.size()!=0)
			{		
				//On affiche les erreurs du tableau 
				JOptionPane.showMessageDialog(this, tabErreurs.toArray(),"Erreur dans les informations entrées",JOptionPane.ERROR_MESSAGE);
			}
			
		}//if
		
		//Lorsque l'utilisateur selectionne le bouton Annulation, l'interface est réinitialisee
		else if(telEvenement.getSource()==chBoutonAnnulation)
		{
			this.chDateAnneeRetour.setEnabled(true);
			this.chDateMoisRetour.setEnabled(true);
			this.chDateJourRetour.setEnabled(true);
			
			chVilleDepart.setText("");
			chVilleArrive.setText("");
			chPerimetre.setText("");
			chNbAdulte.setSelectedIndex(1);
			chNbEnfants.setSelectedIndex(0);
			//chNbBebe.setSelectedIndex(0);
			chButtonAllerRetour[0].setSelected(true);
			chClasse.setSelectedIndex(0);
			//chDateJour.setSelectedIndex(0);
			//chDateMois.setSelectedIndex(0);
			chDateAnnee.setSelectedIndex(0);
			//chDateJourRetour.setSelectedIndex(0);
			//chDateMoisRetour.setSelectedIndex(0);
			chDateAnneeRetour.setSelectedIndex(0);
			
			calendarMaintenant = Calendar.getInstance();
			int numJour=calendarMaintenant.get(Calendar.DAY_OF_MONTH);
			int numMois=calendarMaintenant.get(Calendar.MONTH);
			
			chDateJour.setSelectedIndex(numJour-1);
			chDateMois.setSelectedIndex(numMois);
			
			chDateJourRetour.setSelectedIndex(numJour-1);
			chDateMoisRetour.setSelectedIndex(numMois);
			
			
		}//bouton Annulation
		
	}//actionPerformed
	
}//class InterfaceMere