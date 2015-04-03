package model;

/**
 * This interface will be implemented by classes that will be used to predict
 * the outcome of march madness.
 * 
 * @author Nathaniel Cotton
 * 
 */
public interface GameSimulator {

	/**
	 * Given a {@link Game}, and the round of the tournament that game takes
	 * place a call to this method on an implementing class will produce a tuple
	 * with a proportion for a win by team A, and win by team B.
	 * 
	 * Win by team A {@link Tuple#v1} Win by team B {@link Tuple#v2}
	 * 
	 * @param game
	 * @param round
	 * @return
	 */
	public Tuple<Double, Double> simulate(Game game, int round);

}
