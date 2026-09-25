package corrida;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Professor extends JLabel implements Runnable {
	
	private Thread professor = null;
	private static int pos = 0;
	private String nome;
	private int x;
	private int y;
	private ImageIcon img;

	public Professor(String nome, ImageIcon img, int x, int y) {
		super(img);
		this.nome = nome;
		this.img = img;
		this.x = x;
		this.y = y;
		
		professor = new Thread(this, nome);
		professor.start();
	}
	
	public void run() {
	    
	    while (x < 1000) {
	        x += new Random().nextInt(7) * 10;
	        this.setLocation(x, y);
	        

	        if (x >= 1000) {
	            pos++;
	            JOptionPane.showMessageDialog(null, pos + ") " + nome);
	            break;
	        }
	        
	        try {
	            Thread.sleep(new Random().nextInt(5) * 100);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
}