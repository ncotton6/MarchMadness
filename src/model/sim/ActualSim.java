package model.sim;

import model.Game;
import model.GameSimulator;
import model.Tuple;

/**
 * This implementation of {@link GameSimulator} will use data from past to
 * accurately complete a the bracket for that year. The simulate method will
 * return the team that actually won.
 * 
 * @author Nathaniel Cotton
 * 
 */
public class ActualSim implements GameSimulator {

	private String season;

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.GameSimulator#simulate(model.Game, int)
	 */
	@Override
	public Tuple<Double, Double> simulate(Game game, int round) {
		return new Tuple<Double, Double>(1d, 0d);
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

}
