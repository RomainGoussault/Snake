package com.snake.utils;

import java.awt.event.KeyEvent;

public interface Observable
{
   public void NotifyObservers(KeyEvent keyEvent);
}
