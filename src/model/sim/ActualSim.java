package model.sim;

import java.util.List;

import model.Game;
import model.GameSimulator;
import model.Link;
import model.Tuple;
import model.data.Result;

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
	private List<Result> tourney_result;

	public ActualSim(String season) {
		this.season = season;
		this.tourney_result = Link.results(season);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.GameSimulator#simulate(model.Game, int)
	 */
	@Override
	public Tuple<Double, Double> simulate(Game game, int round) {
		for (Result r : tourney_result) {
			System.out.println(game == null);
			System.out.println(game.getA() == null);
			System.out.println(game.getB() == null);
			System.out.println(r == null);
			System.out.println(r.getLteam());
			if (game.getA().getId() == r.getLteam()
					&& game.getB().getId() == r.getWteam()) {
				return new Tuple<Double, Double>(0d, 1d);
			}
			if (game.getA().getId() == r.getWteam()
					&& game.getB().getId() == r.getLteam()) {
				return new Tuple<Double, Double>(1d, 0d);
			}
		}
		throw new RuntimeException("Couldn't find game");
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

}
