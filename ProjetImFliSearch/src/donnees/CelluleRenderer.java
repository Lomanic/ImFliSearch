package donnees;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CelluleRenderer extends JLabel implements TableCellRenderer {

	
	
	public CelluleRenderer()
	{
		super();
		setOpaque(true);
		setHorizontalAlignment(JButton.CENTER);
	
	}//CelluleReservationRenderer()
	
	public Component getTableCellRendererComponent (JTable parTable, Object parValeur, 
			boolean estSelectionne, boolean aLeFocus, int ligne, int colonne)
	{
		
		
		if(colonne==15)
			{
			//System.out.println(parTable.getValueAt(1, 15));
			this.setText("Choisir");
			//this.setBackground(Color.blue);
			return this;
			}
		else
		{	this.setBackground(Color.white);
			this.setText((String) parValeur);
		
			return this;
		}
		//System.out.println(parValeur+" "+" "+ligne+" "+colonne);
		
		
		/* if(parValeur==null)
		 {
			 this.setBackground(Color.red);
			 this.setToolTipText("");
			 return this;
		 }
		 else if(parValeur.getClass()==Reservation.class)
		 {
			 Reservation lareservation=(Reservation)parTable.getValueAt(ligne,colonne);
			 this.setBackground(Color.green);
			 this.setToolTipText(lareservation.toString());
		 }*/
		
		
 
	}//getTableCellRendererComponent(JTable,Object,boolean,boolean,int,int)

	
	
	
}//class CelluleReservationRenderer