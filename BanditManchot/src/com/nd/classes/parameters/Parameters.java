package com.nd.classes.parameters;

import java.lang.reflect.Constructor;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.nd.interfaces.IAction;

@XmlRootElement
@XmlAccessorType
public class Parameters {

	private int						numberPlayable;
	private double					maximumBet;
	private String					playableName;
	private Constructor<IAction>	constructor	= null;

	public Parameters() {
	}

	public void getParameters() {
		Class<IAction> playableClass;
		try {
			Class<?> classe = Class.forName("com.nd.classes.playable." + playableName);
			playableClass = (Class<IAction>) classe;
			this.constructor = playableClass.getConstructor();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

	}

	public int getNumberPlayable() {
		return numberPlayable;
	}

	public void setNumberPlayable(int numberPlayable) {
		this.numberPlayable = numberPlayable;
	}

	public double getMaximumBet() {
		return maximumBet;
	}

	public void setMaximumBet(double maximumBet) {
		this.maximumBet = maximumBet;
	}

	public String getPlayableName() {
		return playableName;
	}

	public void setPlayableName(String playableName) {
		this.playableName = playableName;
	}

	public Constructor<IAction> getConstructor() {
		return constructor;
	}

	public void setConstructor(Constructor<IAction> constructor) {
		this.constructor = constructor;
	}

	@Override
	public String toString() {
		return "Parameters [numberPlayable=" + numberPlayable + ", maximumBet=" + maximumBet + ", playableName="
				+ playableName + "]";
	}

}
