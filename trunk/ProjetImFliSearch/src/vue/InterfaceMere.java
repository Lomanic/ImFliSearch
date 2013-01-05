
package vue;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
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
		JLabel chLabelEnfant=new JLabel("Enfant(s) ( 0 - 17 ans )");
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
		
		
		this.setVisible(true);
		this.pack();
		this.setMinimumSize(this.getSize());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);

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
				
				int numJourDepart= Integer.parseInt(Constantes.joursDepart[chDateJour.getSelectedIndex()]);
				int numMoisDepart=chDateMois.getSelectedIndex();
				int numAnneeDepart= Integer.parseInt(Constantes.annees[chDateAnnee.getSelectedIndex()]);

				calendarChangeDepart.set(numAnneeDepart, numMoisDepart, numJourDepart);

				int numDeJourMoisDepart=calendarChangeDepart.getActualMaximum(Calendar.DAY_OF_MONTH);
				System.out.println("Nombre Jours du Mois Depart:" + numDeJourMoisDepart);
				
				remove(panelDateDepart);
				constructionPanelDateDepart(numDeJourMoisDepart);
			}//if
			
			if(e.getSource()==chDateMoisRetour || e.getSource()==chDateAnneeRetour)
			{
				Calendar calendarChangeArrivee = Calendar.getInstance();
				
				int numJourArrivee= Integer.parseInt(Constantes.joursRetour[chDateJourRetour.getSelectedIndex()]);
				int numMoisArrivee=chDateMoisRetour.getSelectedIndex();
				int numAnneeArrivee= Integer.parseInt(Constantes.annees[chDateAnneeRetour.getSelectedIndex()]);
				
				calendarChangeArrivee.set(numAnneeArrivee, numMoisArrivee, numJourArrivee);

				int numDeJourMoisArrivee=calendarChangeArrivee.getActualMaximum(Calendar.DAY_OF_MONTH);
				System.out.println("Nombre Jours du Mois Arrivee:" + numDeJourMoisArrivee);
				
				remove(panelDateRetour);
				constructionPanelDateRetour(numDeJourMoisArrivee);
			}//if
			
			
			contentPane.updateUI();
			
		}//if
		
	}//itemStateChanged(ItemEvent e)
	
	
	
	

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
					if(VilleDepart.existe()!=0 && VilleArrivee.existe()!=0 && perimetreEntre>0 && (chNbAdulte.getSelectedIndex()!=0 ||
							chNbEnfants.getSelectedIndex()!=0/* || chNbBebe.getSelectedIndex()!=0*/) )
					{	
						
						//------------------Création de la liste d'aéroports proches de la ville de départ--------//
						
						Aeroport [] listeVilleDepartAeroportCorrige = VilleDepart.listAirport((double)perimetreEntre);
					
			  		  
						//------------------Création de la liste d'aéroports proches de la ville d'arrivée--------//
						
						
						Aeroport [] listeVilleArriveeAeroportCorrige = VilleArrivee.listAirport((double)perimetreEntre);
						
						//------------------------------------------------------------------------//
						
					    if (listeVilleDepartAeroportCorrige.length==0 && listeVilleArriveeAeroportCorrige.length==0)
						{
					    	JOptionPane.showMessageDialog(this, "Aucun aéroport n'a été trouvé pour le périmètre choisi.",
							"Erreur : Perimetre trop faible",JOptionPane.ERROR_MESSAGE);
					    }//if la liste des aéroports est vide pour la ville de départ
						    
					    else if (listeVilleArriveeAeroportCorrige.length==0 || listeVilleDepartAeroportCorrige.length==0)
						{
					    	JOptionPane.showMessageDialog(this, "Aucun aéroport n'a été trouvé pour le périmètre choisi.",
							"Erreur : Perimetre trop faible",JOptionPane.ERROR_MESSAGE);
					    }//if la liste des aéroports est vide pour la ville d'arrivée
						    
						if (VilleDepart.existe()!=0 && VilleArrivee.existe()!=0 && listeVilleArriveeAeroportCorrige.length!=0 && listeVilleDepartAeroportCorrige.length!=0 )
						{
							//On instancie un objet CritereVol qui va contenir les critères du vol recherché par l'utilisateur
							CritereVol lesCriteres= new CritereVol(VilleDepart,VilleArrivee,perimetreEntre,AllerRetour,chNbAdulte.getSelectedIndex(),chNbEnfants.getSelectedIndex(),
							/*chNbBebe.getSelectedIndex(),*/0,0,0,0,0,0,0,0,chClasse.getSelectedIndex(),chDateJour.getSelectedIndex(),chDateMois.getSelectedIndex(),
							chDateAnnee.getSelectedIndex(),chDateJourRetour.getSelectedIndex(),chDateMoisRetour.getSelectedIndex(),chDateAnneeRetour.getSelectedIndex());
							
						
							
							
							
							
							//Il faudrait créer une classe pour récuperer les valeurs de la page web comme les prix, les aéroports, les dates de départ, etc.
							//Il faudrait aussi créer une interface pour afficher les résultats à l'utilisateur.
						
						
							//On affiche une fenetre indiquant les informations sur le vol pour que l'utilisateur puisse confirmer
							int confirmation=JOptionPane.showConfirmDialog(this, "Voici les informations que vous avez tapées:"+"\n"+lesCriteres.toString()+"\n"
							+"Voulez vous valider ces informations ?","Resume",JOptionPane.OK_CANCEL_OPTION);
							
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
										//Lance le navigateur avec autant d'onglets que de combinaisons
										//System.out.println(ListeVilleDepartCorrige[f]+" avec "+ListeVilleDepartCorrige[z]);
										/*hey.openURL("http://www.ebookers.fr/shop/home?type=air&ar.type=roundTrip&ar.rt.leaveSlice.orig.key="+listeVilleDepartAeroportCorrige[f]+
												"&ar.rt.leaveSlice.dest.key="+listeVilleArriveeAeroportCorrige[z]+"&ar.rt.leaveSlice.date=12%2F12%2F12&ar.rt.leaveSlice." +
														"time=Anytime&ar.rt.returnSlice.date=22%2F01%2F13&ar.rt.returnSlice.time=Anytime&_ar.rt.flexAirSearch=0&ar.rt.numAdult="+
														Constantes.nombres[chNbAdulte.getSelectedIndex()]+"&ar.rt.numChild="+Constantes.nombres[chNbEnfants.getSelectedIndex()]+
														"&ar.rt.child[0]=3&ar.rt.child[1]=4&ar.rt.child[2]=4&ar.rt.child[3]=&ar.rt.child[4]=5&ar.rt.child[5]=&ar.rt.child[6]=3" +
														"&ar.rt.child[7]=&_ar.rt.nonStop=0&_ar.rt.narrowSel=0&ar.rt.narrow=airlines&ar.rt.carriers[0]=&ar.rt.carriers[1]=&ar.rt." +
														"carriers[2]=&ar.rt.cabin=C&search=Rechercher&search=Rechercher");*/
										System.out.println(lesCriteres.urlRecherche(listeVilleDepartAeroportCorrige[f], listeVilleArriveeAeroportCorrige[z]));
										
										
										Document doc = Jsoup.connect(lesCriteres.urlRecherche(listeVilleDepartAeroportCorrige[f], listeVilleArriveeAeroportCorrige[z])).timeout(50000).get();
										
										if(doc.select(".container").isEmpty())
										{
											JOptionPane.showMessageDialog(this, "Pas  de vols trouvés entre "+listeVilleDepartAeroportCorrige[f].getChNom()+" et "+listeVilleArriveeAeroportCorrige[z].getChNom());
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
												resultatsRecherche.add(new Vol(ligneJsoupSearchBarContent.get(0),ligneJsoupContainer.get(i),ligneJsoupButtonLink.get(i)));
											}
											
											//remise à zéro des ligneJsoup pour éviter les duplicatas
											ligneJsoupSearchBarContent=null;
											ligneJsoupContainer=null;
											ligneJsoupButtonLink=null;
										}//else du test du nombre d'aéroports trouvés
										
									}//for
								}//for
								
								//affichage de la liste des vols :
								InterfaceResultat interfaceR = new InterfaceResultat();
								interfaceR.setLocationRelativeTo(this);
								
								//normalement...
								//InterfaceResultat interfaceR =new InterfaceResultat(resultatsRecherche);
							}//recherche confirmée
							
							
							
							
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
								"Erreur : ville d'arrivée",JOptionPane.ERROR_MESSAGE);
					}// la ville de départ existe  mais la ville d'arrivée n'existe pas
					else if (VilleDepart.existe()==0 && VilleArrivee.existe()==0)
					{
						JOptionPane.showMessageDialog(this, "Les villes de depart et d'arrivee n'existent pas. ",
								"Erreur : ville Depart/Arrivée",JOptionPane.ERROR_MESSAGE);
					}// les deux villes n'existent pas 
					
					
					else if(VilleDepart1.length()==0 || perimetreEntre==0 || VilleArrivee1.length()==0 || perimetreEntre==0 || (chNbAdulte.getSelectedIndex()==0 &&
							chNbEnfants.getSelectedIndex()==0 /*&& chNbBebe.getSelectedIndex()==0*/))
					{
						JOptionPane.showMessageDialog(this, "Une information est manquante. Veuillez remplir tous les champs",
								"Erreur lors de l'entrée des informations",JOptionPane.ERROR_MESSAGE);
					}//au moins une information est manquante
					else if (VilleDepart1.length()==0 && VilleArrivee1.length()==0 && perimetreEntre==0 && (chNbAdulte.getSelectedIndex()==0 && chNbEnfants.getSelectedIndex()==0
							/*&& chNbBebe.getSelectedIndex()==0*/))
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
 catch (IOException e) {
	 			System.out.println("JSOUP");
				e.printStackTrace();
			}
			
				
				
		}//if
		
		//Lorsque l'utilisateur selectionne le bouton Annulation, l'interface est réinitialisee
		else if(telEvenement.getSource()==chBoutonAnnulation)
		{
			chVilleDepart.setText("");
			chVilleArrive.setText("");
			chPerimetre.setText("");
			chNbAdulte.setSelectedIndex(0);
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