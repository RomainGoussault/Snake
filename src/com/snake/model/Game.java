package com.snake.model;

import com.snake.utils.BestScoreManager;
import com.snake.utils.Constants;

public class Game implements Constants{

	private Snake s;
	private Direction dir;
	private Direction lastDir;
	private Meat m;
	private boolean meatCollision;
	private boolean wallCollision;
	private boolean snakeCollision;
	private int score;
	private boolean gameOver;
	private boolean restart;
	private int speed; //speed from 0 -100
	private BestScoreManager bestScore;

	public Game()
	{
		s = new Snake(INITIAL_SNAKE_SIZE);
		m = new Meat();
		m.generateNewPosition();

		dir = Direction.UP;
		lastDir = dir;
		meatCollision = false;
		gameOver = false;
		score = 0;
		restart = false;
		speed = INITIAL_SPEED;
		wallCollision = false; 
		snakeCollision = false;
		bestScore = new BestScoreManager();
	}

	public void update()
	{
		//We don't allow reverse moves for the snake
		boolean lateral = (lastDir == Direction.RIGHT && dir == Direction.LEFT) || (dir == Direction.RIGHT && lastDir == Direction.LEFT) ;
		boolean vertical = (lastDir == Direction.UP && dir == Direction.DOWN) || (dir == Direction.UP && lastDir == Direction.DOWN) ;

		if	(!gameOver)
		{
			if (!lateral && !vertical )
			{
				snakeCollision =   s.getBody().contains(s.getNextCell(dir));
				s.move(dir);
				lastDir = dir;
			}
			else
			{
				dir=lastDir;
				s.move(dir);
			}	

			//Snake - Meat meatCollisions
			meatCollision = s.getHead().getI() == m.getI() && s.getHead().getJ() == m.getJ();
			if(meatCollision)
			{
				m.generateNewPosition(); 
				score ++;
				s.increaseSize();
				s.increaseSize();
				s.increaseSize();
				s.getBody().addFirst(s.getNextCell(dir));
				s.getBody().addFirst(s.getNextCell(dir));
				increaseSpeed();
			}

			//Snake - wallCollision
			wallCollision = (s.getHead().getI() == 0 ||s.getHead().getJ() == 0 || s.getHead().getI() == (N_COLUMNS-1) || s.getHead().getJ() == (N_COLUMNS-1));
			gameOver = wallCollision || snakeCollision;
		}
		else if(restart)
		{
			this.restart();
		}
		else
		{
			bestScore.update(score);	
		}
	}

	public void increaseSpeed()
	{
		//speed = 100 - 1/speed ;
		speed= (int) (speed + (float) (100/speed)) ;

	}

	public void restart()
	{
		s = new Snake(INITIAL_SNAKE_SIZE);
		m = new Meat();
		m.generateNewPosition();

		dir = Direction.UP;
		lastDir = dir;
		meatCollision = false;
		gameOver = false;
		score = 0;
		restart = false;
		speed = INITIAL_SPEED;
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

	public boolean ismeatCollision() {
		return meatCollision;
	}

	public void setmeatCollision(boolean meatCollision) {
		this.meatCollision = meatCollision;
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

	public boolean isMeatCollision() {
		return meatCollision;
	}

	public void setMeatCollision(boolean meatCollision) {
		this.meatCollision = meatCollision;
	}

	public boolean isWallCollision() {
		return wallCollision;
	}

	public void setWallCollision(boolean wallCollision) {
		this.wallCollision = wallCollision;
	}

	public boolean isSnakeCollision() {
		return snakeCollision;
	}

	public void setSnakeCollision(boolean snakeCollision) {
		this.snakeCollision = snakeCollision;
	}

	public BestScoreManager getBestScore() {
		return bestScore;
	}

	public void setBestScore(BestScoreManager bestScore) {
		this.bestScore = bestScore;
	}
}
