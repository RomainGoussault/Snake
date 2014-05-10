package com.snake.ui;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.snake.model.Direction;
import com.snake.model.Game;
import com.snake.utils.Constants;

@SuppressWarnings("serial")
public class Main extends JFrame implements Constants{

	final protected GamePannel p;
	final protected Game g;

	public Main()
	{   
		g = new Game();
		p  = new GamePannel(g);
		this.setTitle("Snake");
		this.setSize(N_COLUMNS*CELL_SIZE, (N_LINES+2)*CELL_SIZE);
		//this.setSize(1000,1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(p);
		this.pack();
		this.setVisible(true);  

		p.setBackground(Color.white);

		p.addKeyListener(new KeyboardsListener());
		p.setFocusable(true);
		p.requestFocusInWindow();

		while (true)
		{ 
			g.update();
			p.repaint();
			try
			{
				Thread.sleep( 100-g.getSpeed());
			}
			catch (InterruptedException e) 
			{
			}
		}      
	}

	class KeyboardsListener implements KeyListener{

		public void keyPressed(KeyEvent event) {

			//System.out.println(event.getKeyCode());
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
			}
			if (event.getKeyCode() == 40)
			{
				g.setDir(Direction.DOWN);
			}

			if (event.getKeyCode() == 82) //R
			{
				g.setRestart(true);
			}
			if (event.getKeyCode() == 88) //X
			{
				Main.this.dispose();
			}
		}

		public void keyReleased(KeyEvent event) {
		}

		public void keyTyped(KeyEvent event) {
		}   	
	}   

	public static void main(String[] args){
		new Main();
	}
}