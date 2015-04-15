package model.sim;

import java.lang.reflect.Field;
import java.util.List;

import model.Game;
import model.GameSimulator;
import model.Link;
import model.Stat;
import model.Tuple;
import model.data.TeamStat;

/**
 * This implementation of the {@link GameSimulator} will use a naive bayes to
 * determine the winner of a game.
 * 
 * @author Nathaniel Cotton
 * 
 */
public class NaiveBayesSim implements GameSimulator {

	/* private static variables */
	private static List<Stat> losers = null;
	private static List<Stat> winners = null;
	private static Stat averageLoser = null;
	private static Stat averageWinner = null;

	/* Private variables */
	private String season = null;

	public NaiveBayesSim(String season) {
		this.season = season;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.GameSimulator#simulate(model.Game, int)
	 */
	@Override
	public Tuple<Double, Double> simulate(Game game, int round) {
		// what do winning teams have in common
		// what do losing teams have in common
		if (losers == null || winners == null) {
			Tuple<List<Stat>, List<Stat>> winnersLosers = null;
			try {
				winnersLosers = Link.getWinnerLoserData();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (winnersLosers != null) {
				this.winners = winnersLosers.v1;
				this.losers = winnersLosers.v2;
				// calculate average
				try {
					this.averageLoser = average(losers);
					this.averageWinner = average(winners);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (averageLoser != null && averageWinner != null) {
			// classify team a as a loser or a winner
			TeamStat tsa = Link.getTeamStat(game.getA(), season);
			double aClassifyValue = 0;
			// classify team b as a loser or a winner
			TeamStat tsb = Link.getTeamStat(game.getB(), season);
			double bClassifyValue = 0;
			// which team is a bigger winner, or less of a loser
			return new Tuple<Double, Double>(aClassifyValue, bClassifyValue);
		}
		System.err.println("Something went wrong, and the default was returned");
		return new Tuple<Double, Double>(1d, 0d);
	}

	private Stat average(List<Stat> statList) throws IllegalArgumentException,
			IllegalAccessException {
		Stat stat = new Stat();
		Field[] fields = Stat.class.getFields();
		for (int i = 0; i < fields.length; ++i) {
			fields[i].setAccessible(true);
			double value = 0;
			for (Stat s : statList) {
				value += (double) fields[i].get(s);
			}
			value = value / statList.size();
			fields[i].set(stat, value);
		}
		return stat;
	}
}
