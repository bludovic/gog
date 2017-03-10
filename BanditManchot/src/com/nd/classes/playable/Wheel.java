package com.nd.classes.playable;

import com.nd.interfaces.IAction;

public class Wheel implements IAction {

	private boolean	rolling	= false, slowingDown = false;
	private int		result	= 0;
	private double	speed	= 1;

	@Override
	public void run() {
		this.speed = 1;
		this.rolling = true;
		this.slowingDown = false;

		while (speed > 0.125) {
			result = result < 9 ? (result + 1) : 0;
			if (slowingDown) {
				speed /= 2;
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

}
