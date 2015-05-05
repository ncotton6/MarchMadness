package model.sim;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import model.Game;
import model.GameSimulator;
import model.Link;
import model.NaiveBayesAttribute;
import model.Stat;
import model.Tuple;
import model.data.TeamStat;

/**
 * This implementation of the {@link GameSimulator} will use a naive bayes to
 * determine the winner of a game.
 * 
 * http://en.wikipedia.org/wiki/Naive_Bayes_classifier
 * 
 * @author Nathaniel Cotton
 * 
 */
public class NaiveBayesSim implements GameSimulator {

	/* private static variables */
	private static List<Stat> losers = null;
	private static List<Stat> winners = null;
	private static Stat winnerMean = new Stat(), winnerStd = new Stat(),
			loserMean = new Stat(), loserStd = new Stat();

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
				if (winnersLosers != null) {
					this.winners = winnersLosers.v1;
					this.losers = winnersLosers.v2;
					// Crunch the data for the mean, and standard deviation of a
					// value.
					SummaryStatistics ss = new SummaryStatistics();
					Field[] statFields = Stat.class.getDeclaredFields();
					System.out.println("++++++++++++++++++++++++");
					System.out.println("++++++++++++++++++++++++");
					for (int i = 0; i < statFields.length; ++i) {
						statFields[i].setAccessible(true);
						ss.clear();
						for (Stat s : winners) {
							ss.addValue(statFields[i].getDouble(s));
						}
						// set winner values
						statFields[i].set(winnerMean, ss.getMean());
						statFields[i].set(winnerStd, ss.getStandardDeviation());

						System.out.println("winner " + statFields[i].getName()
								+ " " + ss.getMean() + " "
								+ ss.getStandardDeviation());

						ss.clear();
						for (Stat s : losers) {
							ss.addValue(statFields[i].getDouble(s));
						}
						// set loser values
						statFields[i].set(loserMean, ss.getMean());
						statFields[i].set(loserStd, ss.getStandardDeviation());

						System.out.println("loser " + statFields[i].getName()
								+ " " + ss.getMean() + " "
								+ ss.getStandardDeviation());

					}
					System.out.println("++++++++++++++++++++++++");
					System.out.println("++++++++++++++++++++++++");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (winnerMean != null && winnerStd != null && loserMean != null
				&& loserStd != null) {
			try {
				// classify team a as a loser or a winner
				TeamStat tsa = Link.getTeamStat(game.getA(), season);
				double aClassifyValueWinning = classify(winnerMean, winnerStd,
						tsa)
						* (((double) tsa.getNumWins()) / (double) (tsa
								.getNumLoses() + tsa.getNumWins()));
				double aClassifyValueLosing = classify(loserMean, loserStd, tsa)
						* (((double) tsa.getNumLoses()) / (double) (tsa
								.getNumLoses() + tsa.getNumWins()));
				// classify team b as a loser or a winner
				TeamStat tsb = Link.getTeamStat(game.getB(), season);
				double bClassifyValueWinning = classify(winnerMean, winnerStd,
						tsb)
						* (((double) tsb.getNumWins()) / (double) (tsb
								.getNumLoses() + tsb.getNumWins()));
				double bClassifyValueLosing = classify(loserMean, loserStd, tsb)
						* (((double) tsb.getNumLoses()))
						/ (double) (tsb.getNumLoses() + tsb.getNumWins());
				// which team is a bigger winner, or less of a loser
				return new Tuple<Double, Double>(-aClassifyValueLosing,
						-bClassifyValueLosing);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.err
				.println("Something went wrong, and the default was returned");
		return new Tuple<Double, Double>(1d, 0d);
	}

	/**
	 * @param mean
	 * @param std
	 * @param team
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	private double classify(Stat mean, Stat std, TeamStat team)
			throws IllegalArgumentException, IllegalAccessException,
			NoSuchFieldException, SecurityException {
		Field[] statFields = Stat.class.getDeclaredFields();
		double prob = 1;
		for (int i = 0; i < statFields.length; ++i) {
			if (statFields[i].getAnnotation(NaiveBayesAttribute.class) != null) {
				statFields[i].setAccessible(true);
				Field f = TeamStat.class.getDeclaredField(statFields[i]
						.getName());
				f.setAccessible(true);
				double value = (1 / Math.sqrt(2 * Math.PI
						* Math.pow(statFields[i].getDouble(std), 2)))
						* Math.pow(
								Math.E,
								(-Math.pow((f.getDouble(team) - statFields[i]
										.getDouble(mean)), 2) / (2 * statFields[i]
										.getDouble(std))));
				prob *= value;
			}
		}
		return prob;
	}
}
