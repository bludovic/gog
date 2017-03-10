package com.nd.interfaces;

public interface IAction extends Runnable {


	public void start();

	public void stop();

	public int getResult();

	public boolean isSlowingDown();

	public boolean isRolling();

	public boolean isPlaying();
}
