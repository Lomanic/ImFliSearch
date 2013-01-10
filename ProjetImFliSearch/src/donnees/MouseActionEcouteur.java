package donnees;



import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;


public class MouseActionEcouteur extends MouseAdapter implements ActionListener{
	

	
	//ajouter champs
	
	public MouseActionEcouteur()
	{
		
		
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
	            	if ( column == 15 ) 
	            	{
	            		//System.out.println(table.getValueAt(1,15).toString());
	            		
	            		BareBonesBrowserLaunch lanceur = new BareBonesBrowserLaunch();
	            		
	            		lanceur.openURL(table.getValueAt(row,15).toString());
	            	}
	            }
		
	}	 //mouseClicked(MouseEvent)
	
		public void actionPerformed(ActionEvent telEvt) {
				    
	
			 }
									
	
}//class MouseActionEcouteur
