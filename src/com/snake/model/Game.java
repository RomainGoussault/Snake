package com.snake.model;

import java.awt.event.KeyEvent;

import com.snake.score.BestScoreManager;
import com.snake.score.FileBestScoreManager;
import com.snake.utils.Constants;
import com.snake.utils.Observer;

public class Game implements Constants, Observer{

	private Snake snake;
	private Direction direction;
	private Direction lastDir;
	private Meat meat;
	private boolean meatCollision;
	private boolean wallCollision;
	private boolean snakeCollision;
	private int score;
	private boolean gameOver;
	private boolean restart;
	private int speed; //speed from 0 -100
	private BestScoreManager bestScoreManager;

	public Game()
	{	       
		snake = new Snake(INITIAL_SNAKE_SIZE);
		meat = new Meat();
		meat.generateNewPosition();

		direction = Direction.UP;
		lastDir = direction;
		meatCollision = false;
		gameOver = false;
		score = 0;
		restart = false;
		speed = INITIAL_SPEED;
		wallCollision = false; 
		snakeCollision = false;
		bestScoreManager = new FileBestScoreManager();
	}

	public void updateModel()
	{
		//We don't allow reverse moves for the snake
		boolean lateral = (lastDir == Direction.RIGHT && direction == Direction.LEFT)
				|| (direction == Direction.RIGHT && lastDir == Direction.LEFT) ;
		boolean vertical = (lastDir == Direction.UP && direction == Direction.DOWN)
				|| (direction == Direction.UP && lastDir == Direction.DOWN) ;

		if	(!gameOver)
		{
			moveSnake(lateral, vertical);	
			handleCollision();
		}
		else if(restart)
		{
			restart();
		}
		else
		{
			bestScoreManager.update(score);	
		}
	}

	private void handleCollision()
	{
		//Snake - Meat meatCollisions
		meatCollision = snake.getHead().getI() == meat.getI() && snake.getHead().getJ() == meat.getJ();
		if(meatCollision)
		{
			meat.generateNewPosition(); 
			score ++;
			snake.grow(CELL_GROWTH_PER_MEAT_EATEN, direction);
			increaseSpeed();
		}

		//Snake - wallCollision
		wallCollision = (snake.getHead().getI() == 0 || snake.getHead().getJ() == 0
				|| snake.getHead().getI() == (N_COLUMNS-1) || snake.getHead().getJ() == (N_COLUMNS-1));
		
		gameOver = wallCollision || snakeCollision;
	}

	private void moveSnake(boolean lateral, boolean vertical) {
		if (!lateral && !vertical )
		{
			snakeCollision = snake.getBody().contains(snake.getNextCell(direction));
			snake.move(direction);
			lastDir = direction;
		}
		else
		{
			direction=lastDir;
			snake.move(direction);
		}
	}

	public void increaseSpeed()
	{
		//speed = 100 - 1/speed ;
		speed= (int) (speed + (float) (100/speed)) ;
	}

	public void restart()
	{
		snake = new Snake(INITIAL_SNAKE_SIZE);
		meat = new Meat();
		meat.generateNewPosition();

		direction = Direction.UP;
		lastDir = direction;
		meatCollision = false;
		gameOver = false;
		score = 0;
		restart = false;
		speed = INITIAL_SPEED;
	}

	@Override
	public void update(KeyEvent keyEvent) {
		//System.out.println(event.getKeyCode());
		if (keyEvent.getKeyCode() == 37)
		{
			setDir(Direction.LEFT);
		}

		if (keyEvent.getKeyCode() == 39)
		{
			setDir(Direction.RIGHT);

		}
		if (keyEvent.getKeyCode() == 38)
		{
			setDir(Direction.UP);
		}
		if (keyEvent.getKeyCode() == 40)
		{
			setDir(Direction.DOWN);
		}

		if (keyEvent.getKeyCode() == 82) //R
		{
			setRestart(true);
		}
	}

	public Snake getS() {
		return snake;
	}

	public void setS(Snake s) {
		this.snake = s;
	}

	public Direction getDir() {
		return direction;
	}

	public void setDir(Direction dir) {
		this.direction = dir;
	}

	public Meat getM() {
		return meat;
	}

	public void setM(Meat m) {
		this.meat = m;
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

	public BestScoreManager getBestScoreManager() {
		return bestScoreManager;
	}
}
