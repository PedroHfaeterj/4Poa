package corrida;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Tela extends JFrame implements ActionListener {
	private JButton botaoComeca;
	private JButton botaoPara;
	private JLabel pista;
	private ImageIcon professor1;
	private ImageIcon professor2;
	private ImageIcon professor3;
	
	public Tela() {
		this.setLayout(null);
		this.setSize(1000, 650);
		this.setLocation(50, 50);
		this.setResizable(false);
		getContentPane().setBackground(Color.white);
		
		this.setTitle("Corrida");
		
		this.pista = new JLabel();
		this.pista.setBounds(0, 0, 1000, 650);
		
		ImageIcon imagemFundo = new ImageIcon("src/imgs/Clarimundo.png");
		this.pista.setIcon(imagemFundo);
		
		this.add(pista);
		
		this.botaoComeca = new JButton("Comeca");
		this.botaoPara = new JButton("Para");
		
		this.botaoComeca.setBounds(400, 530, 120, 50);
		this.botaoPara.setBounds(540, 530, 120, 50);
		
		professor1 = new ImageIcon("src/imgs/Zanco.png");
		professor2 = new ImageIcon("src/imgs/Bispo.png");
		professor3 = new ImageIcon("src/imgs/Marciano.png");
		
		this.pista.add(botaoComeca);
		this.pista.add(botaoPara);
		
		this.setVisible(true);
		
		botaoComeca.addActionListener(this);
		botaoPara.addActionListener(this);
	}
		
	public JLabel JLabelCarros(String nome, ImageIcon img, int posX, int posY) {
		Professor professor = new Professor(nome, img, posX, posY);
		professor.setSize(300, 150);
		professor.setVisible(true);
		this.add(professor);
		return professor;
	}
	
	public void actionPerformed(ActionEvent dispara) {
		if (dispara.getSource() == this.botaoComeca) {
			this.pista.add(JLabelCarros("Zanco", professor1, 0, 50));
			this.pista.add(JLabelCarros("Bispo", professor2, 0, 200));
			this.pista.add(JLabelCarros("Marciano", professor3, 0, 350));
		}
		if (dispara.getSource() == this.botaoPara) {
			System.exit(0);
		}
	}
}