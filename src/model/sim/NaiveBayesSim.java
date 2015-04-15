package model.sim;

import model.Game;
import model.GameSimulator;
import model.Tuple;

/**
 * This implementation of the {@link GameSimulator} will use a naive bayes to
 * determine the winner of a game.
 * 
 * @author Nathaniel Cotton
 * 
 */
public class NaiveBayesSim implements GameSimulator {

	/* (non-Javadoc)
	 * @see model.GameSimulator#simulate(model.Game, int)
	 */
	@Override
	public Tuple<Double, Double> simulate(Game game, int round) {
		// what do winning teams have in common
		
		// what do losing teams have in common
		
		// classify team a as a loser or a winner
		
		// classify team b as a loser or a winner
		
		// which team is a bigger winner, or less of a loser
		
		return new Tuple<Double, Double>(1d, 0d);
	}

}
