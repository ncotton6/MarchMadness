package model.sim;

import java.util.Random;

import model.Game;
import model.GameSimulator;
import model.Tuple;

/**
 * This implementation of the {@link GameSimulator} will simply select the
 * winning team at random. This can be used to see if performing data mining can
 * actually improve your odds when selecting winners.
 * 
 * @author Nathaniel Cotton
 * 
 */
public class RandomSim implements GameSimulator {

	private Random random = null;

	public RandomSim() {
		random = new Random();
	}

	public RandomSim(long seed) {
		random = new Random(seed);
	}

	@Override
	public Tuple<Double, Double> simulate(Game game, int round) {
		double t1 = 0;
		double t2 = 0;
		while (t1 == t2) {
			t1 = random.nextDouble();
			t2 = random.nextDouble();
		}
		return new Tuple<Double, Double>(t1, t2);
	}

}
