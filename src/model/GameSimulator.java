package model;

public interface GameSimulator {

	public Tuple<Double,Double> simulate(Game game, int round);
	
}
