package model.sim;

import model.Game;
import model.GameSimulator;
import model.Tuple;

/**
 * This {@link GameSimulator} will use the 1R rule to determine which team
 * should win between to given teams.
 * 
 * @author Nathaniel Cotton
 * 
 */
public class OneR implements GameSimulator {

	@Override
	public Tuple<Double, Double> simulate(Game game, int round) {
		return new Tuple<Double, Double>(1d, 0d);
	}

}
