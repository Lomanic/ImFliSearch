package donnees;



import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;


public class MouseActionEcouteur extends MouseAdapter implements ActionListener{
	
	private boolean chAllerRetour;
	
	//ajouter champs
	
	public MouseActionEcouteur(boolean parAllerRetour)
	{
		chAllerRetour=parAllerRetour;
		
	}//MouseActionEcouteur()

	public void mouseClicked(MouseEvent parEvt)
	{
		
		JTable table=(JTable)parEvt.getSource();
		/*ModelTable model = (ModelTable)table.getModel();
		Point p=parEvt.getPoint();

		int rowIndex = table.rowAtPoint(p);
		int colIndex=table.columnAtPoint(p);*/
		
		 if (parEvt.getClickCount() == 1) {
	            Point p = parEvt.getPoint();

	            int row = table.rowAtPoint(p);
	            int column =table.columnAtPoint(p);
	            	try{
		            	//Si le resultat comporte des vols aller-retour
		            	if ( column == 15 && chAllerRetour==true ) 
		            	{
		            		//On lance le navigateur avec l'adresse URL correspondant au vol
		            		BareBonesBrowserLaunch.openURL(table.getValueAt(row,15).toString());
		            	}
		            	else if (column==9)//si le resultat ne comporte que des vols aller
		            	{
		            		BareBonesBrowserLaunch.openURL(table.getValueAt(row,9).toString());
		            	}
	            	}catch(NullPointerException e){}
	            }
		
	}	 //mouseClicked(MouseEvent)

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
								
	
}//class MouseActionEcouteur
