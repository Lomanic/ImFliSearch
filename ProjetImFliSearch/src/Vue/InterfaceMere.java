package Vue;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.geonames.FeatureClass;
import org.geonames.Toponym;
import org.geonames.WebService;

import constantes.Constantes;
import donnees.BareBonesBrowserLaunch;
import donnees.Ville;
import donnees.Vol;

public class InterfaceMere extends JFrame implements ActionListener{
	
	
	//panelVille
	private JTextField chVilleDepart = new JTextField();
	private JTextField chVilleArrive = new JTextField();

	//panelVoyageur
	private JComboBox chNbAdulte = new JComboBox(Constantes.nombres);
	private JComboBox chNbEnfants = new JComboBox(Constantes.nombres);
	//private JComboBox chNbBebe = new JComboBox(Constantes.nombres);
	
	//panelClasse
	private JComboBox chClasse=new JComboBox(Constantes.classes);
	//panelPerimetre
	private JTextField chPerimetre = new JTextField();
	//panelDate
	private JComboBox chDateJour=new JComboBox(Constantes.jours);
	private JComboBox chDateMois=new JComboBox(Constantes.mois);
	private JComboBox chDateAnnee=new JComboBox(Constantes.annees);
	private JComboBox chDateJourRetour=new JComboBox(Constantes.jours);
	private JComboBox chDateMoisRetour=new JComboBox(Constantes.mois);
	private JComboBox chDateAnneeRetour=new JComboBox(Constantes.annees);
	
	//panelAllerRetour
	private JRadioButton[] chButtonAllerRetour= new JRadioButton[2];
	private ButtonGroup chboutonGroupe=new ButtonGroup();
	
	private GridBagConstraints chContraintes = new GridBagConstraints();
	
	//Boutons Validation et Annulation
	private JButton chBoutonValidation = new JButton("Validation");
	private JButton chBoutonAnnulation = new JButton("Annulation");
	
	
	public InterfaceMere(String parTitre)
	{
		super(parTitre);
		
		JPanel contentPane=new JPanel();//conteneur père
		this.setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		contentPane.setLayout(new GridBagLayout());
		
		chContraintes.insets = new Insets (5, 5, 5, 5);
		
		chContraintes.fill = GridBagConstraints.HORIZONTAL;
		
		chContraintes.weightx = 1;
		chContraintes.weighty = 1;	
		
		
		
		//-----------------LABEL DE BIENVENUE--------------------//
		JLabel chBienvenue= new JLabel("Bienvenue sur ImFliSearch");
		chBienvenue.setFont(new Font("Arial",Font.BOLD,25));
		//-----------------PANEL-VILLE----------------------------//
		JPanel panelVille = new JPanel() ;
		panelVille.setBorder(new TitledBorder("Ville")) ;
		panelVille.setLayout(new GridBagLayout());
		
		JLabel labelDepart=new JLabel("Ville depart :");
		JLabel labelArrive=new JLabel("Ville arrivee :");
		
		this.ajoutComposant(labelDepart, panelVille, 0, 0, 1, 1);
		this.ajoutComposant(chVilleDepart, panelVille, 1, 0, 1, 1);
		this.ajoutComposant(labelArrive, panelVille, 0, 1, 1, 1);
		this.ajoutComposant(chVilleArrive, panelVille, 1, 1, 1, 1);
		
		chVilleDepart.addActionListener(this);
		chVilleArrive.addActionListener(this);
		//-----------------PANEL-PERIMETRE----------------------------//
		JPanel panelPerimetre = new JPanel() ;
		panelPerimetre.setBorder(new TitledBorder("Perimetre de recherche")) ;
		panelPerimetre.setLayout(new GridBagLayout());
		JLabel LabelchoixPerimetre=new JLabel("Distance maximale de recherche (en km):");
		 
		this.ajoutComposant(LabelchoixPerimetre, panelPerimetre, 0, 0, 1, 1);
		this.ajoutComposant(chPerimetre, panelPerimetre, 1, 0, 1, 1);
		chPerimetre.setColumns(6);
		
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
		JLabel chLabelEnfant=new JLabel("Enfants ( 0 - 17 ans )");
		//JLabel chLabelBebe=new JLabel("Bebes (-2 ans)");
		
		this.ajoutComposant(chLabelAdulte, panelVoyageur, 0, 0, 1, 1);
		this.ajoutComposant(chNbAdulte, panelVoyageur, 1, 0, 1, 1);
		
		this.ajoutComposant(chLabelEnfant, panelVoyageur, 0, 1, 1, 1);
		this.ajoutComposant(chNbEnfants, panelVoyageur, 1, 1, 1, 1);
		
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
		
		JPanel panelDateDepart = new JPanel();
		panelDateDepart.setBorder(new TitledBorder("Dates du Départ"));
		panelDateDepart.setLayout(new GridBagLayout());
		JLabel chLabelJourDepart=new JLabel("Jour du depart :");		 
		this.ajoutComposant(chLabelJourDepart, panelDateDepart, 0, 0, 1, 1);
		this.ajoutComposant(chDateJour, panelDateDepart, 1, 0, 1, 1);
		this.ajoutComposant(chDateMois, panelDateDepart, 2, 0, 1, 1);
		this.ajoutComposant(chDateAnnee, panelDateDepart, 3, 0, 1, 1);
		
		
		JPanel panelDateArrivee = new JPanel();
		panelDateArrivee.setBorder(new TitledBorder("Date de l'arrivée"));
		panelDateArrivee.setLayout(new GridBagLayout());
		JLabel chLabelJourRetour=new JLabel("Jour du retour :");		 
		this.ajoutComposant(chLabelJourRetour, panelDateArrivee, 0, 0, 1, 1);
		this.ajoutComposant(chDateJourRetour, panelDateArrivee, 1, 0, 1, 1);
		this.ajoutComposant(chDateMoisRetour, panelDateArrivee, 2, 0, 1, 1);
		this.ajoutComposant(chDateAnneeRetour, panelDateArrivee, 3, 0, 1, 1);
		

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
		//Ajout panelDateDepart
		this.ajoutComposant(panelDateDepart,contentPane,0,4,1,1);
		//Ajout panelDateArrivee
		this.ajoutComposant(panelDateArrivee,contentPane,1,4,1,1);
		
		//Ajout boutons		
		chContraintes.fill = GridBagConstraints.CENTER;
		
		this.ajoutComposant(chBoutonValidation,contentPane, 0, 5, 1, 1);
		this.ajoutComposant(chBoutonAnnulation,contentPane, 1, 5, 1, 1);
		
		
		this.setVisible(true);
		this.pack();
		this.setMinimumSize(this.getSize());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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
	
	public void actionPerformed (ActionEvent telEvenement)
	{
		//On récupere les valeurs indiquées par l'utilisateur lorqu'il selectionne le bouton de validation
		if (telEvenement.getSource()==chBoutonValidation)
		{
			//On recupere le type de vol
			int AllerRetour=Integer.parseInt(chboutonGroupe.getSelection().getActionCommand());
			
			//Conversion des JTextField en String
			String VilleDepart1=chVilleDepart.getText();
			String VilleArrivee1=chVilleArrive.getText();
			
			try
			{
				int perimetreEntre=Integer.parseInt(chPerimetre.getText());
				// Creation des objets villes
				Ville VilleDepart= new Ville(VilleDepart1);
				Ville VilleArrivee= new Ville(VilleArrivee1);
								
				if(chVilleDepart.getText().equals("") && chVilleArrive.getText().equals(""))
				{
					JOptionPane.showMessageDialog(this, "Veuillez remplir les champs Ville de départ/arrivée",
					"Erreur : Ville départ/arrivée",JOptionPane.ERROR_MESSAGE);
				}//if les champs ville départ/arrivée n'ont pas été remplie
				else
				{
					//Condition permettant d'indiquer si l'utilisateur a bien entre toutes les valeurs demandees
					if(VilleDepart.existe()!=0 && VilleArrivee.existe()!=0 && perimetreEntre>0 && (chNbAdulte.getSelectedIndex()!=0 || chNbEnfants.getSelectedIndex()!=0/* || chNbBebe.getSelectedIndex()!=0*/) )
					{	
						System.out.println("Les aéroports à "+perimetreEntre+"km de "+ VilleDepart1+" sont:");
						List<Toponym> listAirportDepart = VilleDepart.listAirport((double)perimetreEntre);
						for (int j = 0; j < listAirportDepart.size(); j++)
						{ 
							Toponym toponym = listAirportDepart.get(j); 
							System.out.println(toponym.getName());
							//System.out.println(toponym));
				  		  
						}//for pour générer la liste d'aéroports
						
						System.out.println("Les aéroports à "+perimetreEntre+"km de "+ VilleArrivee1+" sont:");
						List<Toponym> listAirportArrivee = VilleArrivee.listAirport((double)perimetreEntre);
						
						for (int j = 0; j < listAirportArrivee.size(); j++) 
						{ 
							Toponym toponym = listAirportArrivee.get(j);	
							System.out.println(toponym.getName());
				  		  
						}//for affiche la liste des aéroports
						
					    if (listAirportDepart.size()==0)
						{
					    	JOptionPane.showMessageDialog(this, "Aucun aeroport n'a été trouvé pour le perimetre choisi.",
							"Erreur : Perimetre trop faible",JOptionPane.ERROR_MESSAGE);
					    }//if la liste des aéroports est vide pour la ville de départ
						    
					    if (listAirportArrivee.size()==0)
						{
					    	JOptionPane.showMessageDialog(this, "Aucun aeroport n'a été trouvé pour le perimetre choisi.",
							"Erreur : Perimetre trop faible",JOptionPane.ERROR_MESSAGE);
					    }//if la liste des aéroports est vide pour la ville d'arrivée
						    
						if (VilleDepart.existe()!=0 && VilleArrivee.existe()!=0 && listAirportArrivee.size()!=0 && listAirportDepart.size()!=0 )
						{
							//On instancie un objet Vol qui va contenir les informations du vol recherché par l'utilisateur
							Vol unVol= new Vol(VilleDepart,VilleArrivee,perimetreEntre,AllerRetour,chNbAdulte.getSelectedIndex(),chNbEnfants.getSelectedIndex(),
							/*chNbBebe.getSelectedIndex(),*/chClasse.getSelectedIndex(),chDateJour.getSelectedIndex(),chDateMois.getSelectedIndex(),
							chDateAnnee.getSelectedIndex(),chDateJourRetour.getSelectedIndex(),chDateMoisRetour.getSelectedIndex(),chDateAnneeRetour.getSelectedIndex());
						
							
							BareBonesBrowserLaunch hey = new BareBonesBrowserLaunch();
							hey.openURL("http://www.ebookers.fr/shop/home?type=air&ar.type=roundTrip&ar.rt.leaveSlice.orig.key="+listAirportDepart.get(0).getName()+
									"&ar.rt.leaveSlice.dest.key="+listAirportArrivee.get(0).getName()+"&ar.rt.leaveSlice.date=12%2F12%2F12&ar.rt.leaveSlice." +
											"time=Anytime&ar.rt.returnSlice.date=22%2F01%2F13&ar.rt.returnSlice.time=Anytime&_ar.rt.flexAirSearch=0&ar.rt.numAdult="+
											Constantes.nombres[chNbAdulte.getSelectedIndex()]+"&ar.rt.numChild="+Constantes.nombres[chNbEnfants.getSelectedIndex()]+
											"&ar.rt.child[0]=3&ar.rt.child[1]=4&ar.rt.child[2]=&ar.rt.child[3]=&ar.rt.child[4]=&ar.rt.child[5]=&ar.rt.child[6]=" +
											"&ar.rt.child[7]=&_ar.rt.nonStop=0&_ar.rt.narrowSel=0&ar.rt.narrow=airlines&ar.rt.carriers[0]=&ar.rt.carriers[1]=&ar.rt." +
											"carriers[2]=&ar.rt.cabin=C&search=Rechercher&search=Rechercher");
							
							
							
							//On affiche une fenetre indiquant les informations sur le vol pour que l'utilisateur puisse confirmer
							JOptionPane.showConfirmDialog(this, "Voici les informations que vous avez tapees:"+"\n"+unVol.toString()+"\n"
							+"Voulez vous valider ces informations ?","Resume",
							JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
						}//if les villes départ/arrivée existent et la liste des aéroports n'est pas vide
					}//if toutes les info ont été saisies correctement
					else if (perimetreEntre<0)
					{
						JOptionPane.showMessageDialog(this, "Votre périmètre est négatif, ou égal à zéro... ",
						"Erreur : Périmètre",JOptionPane.ERROR_MESSAGE);
					}// le périmètre est null ou négatif
					else if (VilleDepart.existe()==0 && VilleArrivee.existe()!=0)
					{
						JOptionPane.showMessageDialog(this, "La ville de depart n'existe pas. ",
								"Erreur : ville de depart",JOptionPane.ERROR_MESSAGE);
					}// la ville de départ n'existe pas mais la ville d'arrivée existe
					else if (VilleDepart.existe()!=0 && VilleArrivee.existe()==0)
					{
						JOptionPane.showMessageDialog(this, "La ville d'arrivee n'existe pas. ",
								"Erreur : ville d'arrivee",JOptionPane.ERROR_MESSAGE);
					}// la ville de départ existe  mais la ville d'arrivée n'existe pas
					else if (VilleDepart.existe()==0 && VilleArrivee.existe()==0)
					{
						JOptionPane.showMessageDialog(this, "Les villes de depart et d'arrivee n'existent pas. ",
								"Erreur : ville Depart/Arrivee",JOptionPane.ERROR_MESSAGE);
					}// les deux villes n'existent pas 
					
					
					else if(VilleDepart1.length()==0 || perimetreEntre==0 || VilleArrivee1.length()==0 || perimetreEntre==0 || (chNbAdulte.getSelectedIndex()==0 && chNbEnfants.getSelectedIndex()==0 /*&& chNbBebe.getSelectedIndex()==0*/))
					{
						JOptionPane.showMessageDialog(this, "Une information est manquante. Veuillez remplir tous les champs",
								"Erreur lors de l'entrÃ©e des informations",JOptionPane.ERROR_MESSAGE);
					}//au moins une information est manquante
					else if (VilleDepart1.length()==0 && VilleArrivee1.length()==0 && perimetreEntre==0 && (chNbAdulte.getSelectedIndex()==0 && chNbEnfants.getSelectedIndex()==0 /*&& chNbBebe.getSelectedIndex()==0*/))
					{
						JOptionPane.showMessageDialog(this, "Une information est manquante. Veuillez remplir tous les champs",
							"Erreur lors de l'entree des informations",JOptionPane.ERROR_MESSAGE);	
					}//aucune info n'a été saisie
				}//else
			}//try
			catch(NumberFormatException e)
			{
	            System.out.println("Erreur: La valeur entrée pour le périmètre n'est pas un entier.");
	            JOptionPane.showMessageDialog(this, "Erreur: La valeur entrée pour le périmètre n'est pas un entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
			}//catch
			
				
				
		}//if
		
		//Lorsque l'utilisateur selectionne le bouton Annulation, l'interface est réinitialisee
		else if(telEvenement.getSource()==chBoutonAnnulation)
		{
			chVilleDepart.setText("");
			chVilleArrive.setText("");
			chPerimetre.setText("");
			chNbAdulte.setSelectedIndex(0);
			chNbEnfants.setSelectedIndex(0);
			/*chNbBebe.setSelectedIndex(0);*/
			chButtonAllerRetour[0].setSelected(true);
			chClasse.setSelectedIndex(0);
			chDateJour.setSelectedIndex(0);
			chDateMois.setSelectedIndex(0);
			chDateAnnee.setSelectedIndex(0);
			chDateJourRetour.setSelectedIndex(0);
			chDateMoisRetour.setSelectedIndex(0);
			chDateAnneeRetour.setSelectedIndex(0);	
		}//bouton Annulation
		
	}//actionPerformed
	
}//class InterfaceMere
