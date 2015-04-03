package model;

import model.data.Team;

public class Game {

	private Team A, B = null;
	private double AScore, BScore;

	/**
	 * @return the a
	 */
	public Team getA() {
		return A;
	}

	/**
	 * @param a
	 *            the a to set
	 */
	public void setA(Team a) {
		A = a;
	}

	/**
	 * @return the b
	 */
	public Team getB() {
		return B;
	}

	/**
	 * @param b
	 *            the b to set
	 */
	public void setB(Team b) {
		B = b;
	}

	/**
	 * @return the aScore
	 */
	public double getAScore() {
		return AScore;
	}

	/**
	 * @param aScore
	 *            the aScore to set
	 */
	public void setAScore(double aScore) {
		AScore = aScore;
	}

	/**
	 * @return the bScore
	 */
	public double getBScore() {
		return BScore;
	}

	/**
	 * @param bScore
	 *            the bScore to set
	 */
	public void setBScore(double bScore) {
		BScore = bScore;
	}
}
