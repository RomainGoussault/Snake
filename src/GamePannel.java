import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePannel extends JPanel  implements Constants{ 

	private int posX = -50;
	private int posY = -50;

	Game game;

	GamePannel(Game g)
	{
		super();
		this.setVisible(true);
		this.setLayout(new GridLayout(N_COLUMNS,N_LINES));
		game = g;


	}






	public void paintComponent(Graphics gg){
		//Print background
		gg.setColor(Color.WHITE);
		gg.fillRect(0, 0, N_COLUMNS*CELL_SIZE*2, N_LINES*CELL_SIZE*2);

		gg.setColor(Color.BLACK);
		gg.fillRect(0, 0, N_COLUMNS*CELL_SIZE, N_LINES*CELL_SIZE);

		//Print the snake
		gg.setColor(Color.GREEN);
		for (Cell c : game.getS().getBody())
		{
			gg.fillRect(c.getX(), c.getY(), CELL_SIZE, CELL_SIZE);
		}
		gg.setColor(new Color(0, 100, 0));
		gg.fillRect(game.getS().getHead().getX(), game.getS().getHead().getY(),(int) (CELL_SIZE),(int) (CELL_SIZE));

		//Print the meat
		gg.setColor(Color.RED);
		gg.fillRect(game.getM().getX(),game.getM().getY() ,  CELL_SIZE, CELL_SIZE);

		//Print the wall
		gg.setColor(new Color(139,69,19));	
		gg.fillRect(0,0,N_COLUMNS* CELL_SIZE,CELL_SIZE);
		gg.fillRect(0,(N_COLUMNS-1)* CELL_SIZE,N_COLUMNS* CELL_SIZE,CELL_SIZE);
		gg.fillRect((N_COLUMNS-1)* CELL_SIZE,0, CELL_SIZE,N_COLUMNS*CELL_SIZE);
		gg.fillRect(0,0, CELL_SIZE,N_COLUMNS*CELL_SIZE);



		//Print the score
		gg.setColor(Color.BLUE);
		gg.drawString("Score: " + game.score, 10, (N_LINES+1)*CELL_SIZE);

		if( game.isGameOver())
		{
			gameover(gg);
		}



	}



	public void gameover(Graphics g)
	{
		String str = "GAME OVER";
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
		g.setColor(Color.RED);
		FontMetrics fm = g.getFontMetrics();
		int x = (int) (this.getWidth() *0.1);
		int y = (int) (this.getHeight()   *0.1);  //(this.getHeight() / 2) + fm.getMaxDescent();
		g.drawString(str, x, y);

		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
		g.setColor(Color.orange);
		g.drawString("Score : " + game.getScore(), x, 3*y);

		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		g.setColor(Color.gray);
		g.drawString("Press 'r' to restart", x, 6*y);
	}







	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}   

	public static void main(String[] args){
		new SnakeGame();
	}
}