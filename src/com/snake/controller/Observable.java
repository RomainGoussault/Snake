package com.snake.controller;

import java.awt.event.KeyEvent;

public interface Observable
{
   public void NotifyObservers(KeyEvent keyEvent);
}
