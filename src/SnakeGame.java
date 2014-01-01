import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SnakeGame extends JFrame implements Constants{

	final protected GamePannel p;
	protected JPanel container;
	protected Game g;
	protected JPanel infoPanel;

	public SnakeGame()
	{   

		g = new Game();
		p  = new GamePannel(g);
		container = new JPanel();
		infoPanel = new JPanel();
		this.setTitle("Snake");
		//this.setSize(N_COLUMNS*CELL_SIZE, N_LINES*CELL_SIZE);
		this.setSize(1000,1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(container);
		this.setVisible(true);  
				container.setLayout(new BorderLayout());
				
		p.setBackground(Color.white);
		container.add(p,BorderLayout.CENTER);
		
		infoPanel.setBackground(Color.ORANGE);
		container.add(infoPanel,BorderLayout.SOUTH);
		
		
		//On ajoute l'écouteur à notre composant
		p.addKeyListener(new ClavierListener());
		p.setFocusable(true);
		p.requestFocusInWindow();
	

		Thread thread = new Thread(new Runnable() {                  
			@Override
			public void run() {
				while (true) { // boucle infinie
					// à chaque fois que la boucle est exécutée, la
					// méthode de calcul du jeu est appelée.
					// Comme la boucle est infinie, la méthode de calcul
					// sera appelée en cycle perpétuel.
					//  Fenetre.this.modele.calcul();
					// demander à l'EDT de redessiner le conteneur
					g.update();
					p.repaint();
					try {
						Thread.sleep(100-g.getSpeed());
					} catch (InterruptedException e) {
						//
					}
				}                        
			}
		});
		// lancer le thread
		thread.start();
	}      




	class ClavierListener implements KeyListener{

		public void keyPressed(KeyEvent event) {

			if (event.getKeyCode() == 37)
			{
				g.setDir(Direction.LEFT);
			}

			if (event.getKeyCode() == 39)
			{
				g.setDir(Direction.RIGHT);
		
			}
			if (event.getKeyCode() == 38)
			{
				g.setDir(Direction.UP);
				//System.out.println("Up");
			}
			if (event.getKeyCode() == 40)
			{
				g.setDir(Direction.DOWN);
				//System.out.println("Down");
			}
			
			if (event.getKeyCode() == 82)
			{
				g.setRestart(true);
			}
		}

		public void keyReleased(KeyEvent event) {
			//System.out.println("Code touche relâchée : " + event.getKeyCode() + " - caractère touche relâchée : " + event.getKeyChar());         
		}

		public void keyTyped(KeyEvent event) {
			//System.out.println("Code touche tapée : " + event.getKeyCode() + " - caractère touche tapée : " + event.getKeyChar());
		}   	
	}   



	public static void main(String[] args){
		new SnakeGame();
	}
}