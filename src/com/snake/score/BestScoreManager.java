package com.snake.score;

public interface BestScoreManager {
	public void update(int score);
	public int getBestScore();
	public void setBestScore(int best);
}
