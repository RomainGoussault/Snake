import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;




public class Game implements Constants{

	protected Snake s;
	protected Direction dir;
	protected Direction lastDir;
	protected Meat m;
	protected boolean MeatCollision;
	protected boolean WallCollision;
	protected boolean SnakeCollision;
	protected int score;
	protected boolean gameOver;
	protected boolean restart;
	protected int speed; //speed from 0 -100

	Game()
	{
		s = new Snake(INITIAL_SNAKE_SIZE);
		m = new Meat();
		m.generateNewPosition();

		dir = Direction.UP;
		lastDir = dir;
		MeatCollision = false;
		gameOver = false;
		score = 0;
		restart = false;
		speed = INITIAL_SPEED;
		WallCollision = false;
		SnakeCollision = false;
	}


	void update() {
		//Snake Moves	
		//We don't allow reverse moves for the snake
		boolean lateral = (lastDir == Direction.RIGHT && dir == Direction.LEFT) || (dir == Direction.RIGHT && lastDir == Direction.LEFT) ;
		boolean vertical = (lastDir == Direction.UP && dir == Direction.DOWN) || (dir == Direction.UP && lastDir == Direction.DOWN) ;
		
		if	(!gameOver)
		{
			
			if (!lateral && !vertical )
			{
				SnakeCollision =   s.getBody().contains(s.getNextCell(dir));
				s.move(dir);
				lastDir = dir;
			}
			else
			{
				dir=lastDir;
				s.move(dir);
			}	


			//Snake - Meat MeatCollisions
			MeatCollision = s.getHead().getI() == m.getI() && s.getHead().getJ() == m.getJ();
			if(MeatCollision)
			{
				m.generateNewPosition(); 
				score ++;
				s.size ++;
				s.size ++;
				s.getBody().addFirst(s.getNextCell(dir));
				s.getBody().addFirst(s.getNextCell(dir));
			}
			
			
			WallCollision = (s.getHead().getI() == 0 ||s.getHead().getJ() == 0 || s.getHead().getI() == (N_COLUMNS-1) || s.getHead().getJ() == (N_COLUMNS-1));
	
			gameOver = WallCollision || SnakeCollision;
			
		}
		else if(restart)
		{
			 this.restart();
		}
	}



	public void restart()
	{
		s = new Snake(INITIAL_SNAKE_SIZE);
		m = new Meat();
		m.generateNewPosition();

		dir = Direction.UP;
		lastDir = dir;
		MeatCollision = false;
		gameOver = false;
		score = 0;
		restart = false;
	}

	public Snake getS() {
		return s;
	}


	public void setS(Snake s) {
		this.s = s;
	}


	public Direction getDir() {
		return dir;
	}


	public void setDir(Direction dir) {
		this.dir = dir;
	}


	public Meat getM() {
		return m;
	}


	public void setM(Meat m) {
		this.m = m;
	}



	public boolean isMeatCollision() {
		return MeatCollision;
	}



	public void setMeatCollision(boolean MeatCollision) {
		this.MeatCollision = MeatCollision;
	}


	public int getScore() {
		return score;
	}



	public void setScore(int score) {
		this.score = score;
	}



	public boolean isGameOver() {
		return gameOver;
	}



	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}




	public boolean isRestart() {
		return restart;
	}


	public void setRestart(boolean restart) {
		this.restart = restart;
	}


	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}

	


}
