package com.snake.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import com.snake.model.Direction;
import com.snake.model.Game;
import com.snake.utils.Observable;
import com.snake.utils.Observer;

public class Controller implements KeyListener, Observable
{
	private ArrayList<Observer> obsList;

	public Controller() {
		 obsList = new ArrayList<Observer>();
	}

	public void keyPressed(KeyEvent e) 
	{
		NotifyObservers(e);
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}

	public void NotifyObservers(KeyEvent keyEvent)
	{
		for(Observer obs : obsList)
		{
			obs.update(keyEvent);
		}
	}

	public void AddObserver(Observer obs)
	{
		if (obs != null)
			obsList.add(obs);
	}


	public void DelObserver(Observer obs)
	{
		if (obs != null)
			obsList.remove(obs);
	}
}