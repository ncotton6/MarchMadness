package model.sim;

import model.Game;
import model.GameSimulator;
import model.Tuple;

/**
 * This implementation of the {@link GameSimulator} will use a decision tree to determine the winner. 
 * 
 * @author Nathaniel Cotton
 *
 */
public class DecisionTreeSim implements GameSimulator {

	@Override
	public Tuple<Double, Double> simulate(Game game, int round) {
		return new Tuple<Double, Double>(1d, 0d);
	}

}
