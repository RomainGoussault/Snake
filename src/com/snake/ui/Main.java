package com.snake.ui;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JFrame;

import com.snake.controller.Controller;
import com.snake.model.Direction;
import com.snake.model.Game;
import com.snake.utils.Constants;
import com.snake.utils.Observer;

@SuppressWarnings("serial")
public class Main extends JFrame implements Constants, Observer{

	final protected GamePannel pannel;
	final protected Game game;

	public Main()
	{   
		game = new Game();
		pannel  = new GamePannel(game);
		this.setTitle("Snake");
		this.setSize(N_COLUMNS*CELL_SIZE, (N_LINES+2)*CELL_SIZE);
		//this.setSize(1000,1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(pannel);
		this.pack();
		this.setVisible(true);  

		pannel.setBackground(Color.white);
		Controller controller = new Controller();
		controller.AddObserver(this);
		controller.AddObserver(game);
		pannel.addKeyListener(controller);
		pannel.setFocusable(true);
		pannel.requestFocusInWindow();

		while (true)
		{ 
			game.updateModel();
			pannel.repaint();
			try
			{
				Thread.sleep( 100-game.getSpeed());
			}
			catch (InterruptedException e) 
			{
			}
		}      
	}

	public static void main(String[] args){
		new Main();
	}

	@Override
	public void update(KeyEvent keyEvent)
	{
		if (keyEvent.getKeyCode() == 88) //X
		{
			this.dispose();
		}
	}
}