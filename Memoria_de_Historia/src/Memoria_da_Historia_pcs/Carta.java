
package Memoria_da_Historia_pcs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;


/**
 *
 * @author Carol
 */
public class Carta extends JLabel  {

    //Atributos
    private int anofato;
    public static boolean viradaParaCima;// peca esta virada ou nao
    private Icon imagemdorsoCarta;
    public int id;
    public static Tabuleiro tabuleiro;
    public String nometexto;
    public final Carta carta = null;
    


    //Construtor
    public Carta (int id, Tabuleiro tabuleiro,String nometexto) {  
        super();
        this.viradaParaCima = false;
        this.id = id;
	imagemdorsoCarta = new ImageIcon(getClass().getResource("imagemDorso.jpeg"));
        setIcon(imagemdorsoCarta);
        this.nometexto = nometexto;
        //addMouseListener(new MouseClique()) ;
          
}
    public void desvirar() {
        viradaParaCima = !viradaParaCima;
    }
    
    public static void setViradaParaCima(Carta carta){
        carta.viradaParaCima = true;
    }

    public int getid(){
        return this.id;
    }
    
   public String getnometexto(Carta carta){
       return carta.nometexto;
   }

    
    public boolean isViradaParaCima() {
        return this.viradaParaCima;
    }
    
    public void mudarPosicao(){
	if(isViradaParaCima()){
		viradaParaCima = false;
		setIcon(imagemdorsoCarta);
		}else{
			viradaParaCima = true;		
			
		}		
	}

    
    /*	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carta other = (Carta) obj;
		if (id != other.id)
			return false;
		return true;
	}
	//'MouseListener' que tratar· o evento do clique na carta
	private class MouseClique implements MouseListener{
		
		@Override
		public void mouseReleased(MouseEvent e) {
			//obtÈm a carta que foi clicada e passa-a para o tabuleiro
			Carta c = (Carta) e.getSource();
			c.tabuleiro.acao(c);			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			//n„o precisa implementar
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			//n„o precisa implementar
		}

		@Override
		public void mouseExited(MouseEvent e) {
			//n„o precisa implementar
		}

		@Override
		public void mousePressed(MouseEvent e) {
			//n„o precisa implementar
		}		
	}*/
}
