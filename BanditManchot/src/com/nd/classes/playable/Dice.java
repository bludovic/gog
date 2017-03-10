package com.nd.classes.playable;

import com.nd.interfaces.IAction;

public class Dice implements IAction {

	private boolean	rolling	= false, slowingDown = false;
	private int		result	= 1;
	private double	speed	= 1;

	@Override
	public void run() {
		this.speed = 1;
		this.rolling = true;
		this.slowingDown = false;

		while (speed > 0.125) {
			this.result = (int) (Math.random() * 6 + 1);
			if (slowingDown) {
				this.speed /= 2;
			}
			try {
				Thread.sleep((long) (250 / speed));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.rolling = false;
	}

	@Override
	public void start() {
		this.run();
	}

	@Override
	public void stop() {
		this.slowingDown = true;
	}

	@Override
	public int getResult() {
		return this.result;
	}

	public boolean isSlowingDown() {
		return slowingDown;
	}

	public boolean isRolling() {
		return rolling;
	}

	public void setRolling(boolean rolling) {
		this.rolling = rolling;
	}

	@Override
	public boolean isPlaying() {
		return true;
	}

}
