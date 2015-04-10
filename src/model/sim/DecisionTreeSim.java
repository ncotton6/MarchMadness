package model.sim;

import model.Game;
import model.GameSimulator;
import model.Tuple;

public class DecisionTreeSim implements GameSimulator {

	@Override
	public Tuple<Double, Double> simulate(Game game, int round) {
		return new Tuple<Double, Double>(1d, 0d);
	}

}
