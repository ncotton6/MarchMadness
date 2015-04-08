package model.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Attribute;

/**
 * The {@link TeamStat} class will take the mass of data provided by kraggle,
 * and create some cumulative statistics that will be easier to determine what
 * team would win in a match up.
 * 
 * @author Nathaniel Cotton
 * 
 */
public class TeamStat {

	/* Private variables */
	private String season;
	private int team, numWins, numLoses, winStreak, losingStreak, seed;
	private double averagePoints;
	private List<TeamVersusStat> versusStat = new ArrayList<TeamVersusStat>();

	public class TeamVersusStat implements Comparable<TeamVersusStat>,
			Comparator<TeamVersusStat> {

		private int team, wins, loses;
		private double avgScore, otherTeamsAvgScore;

		@Override
		public int compare(TeamVersusStat arg0, TeamVersusStat arg1) {
			if (arg0.wins == arg1.wins) {
				return (int) ((arg1.avgScore * 100) - (arg0.avgScore * 100));
			}
			return arg1.wins - arg0.wins;
		}

		@Override
		public int compareTo(TeamVersusStat arg0) {
			if (arg0.wins == wins) {
				return (int) ((arg0.avgScore * 100) - (avgScore * 100));
			}
			return arg0.wins - wins;
		}

		/**
		 * @return the team
		 */
		public int getTeam() {
			return team;
		}

		/**
		 * @param team
		 *            the team to set
		 */
		public void setTeam(int team) {
			this.team = team;
		}

		/**
		 * @return the wins
		 */
		public int getWins() {
			return wins;
		}

		/**
		 * @param wins
		 *            the wins to set
		 */
		public void setWins(int wins) {
			this.wins = wins;
		}

		/**
		 * @return the loses
		 */
		public int getLoses() {
			return loses;
		}

		/**
		 * @param loses
		 *            the loses to set
		 */
		public void setLoses(int loses) {
			this.loses = loses;
		}

		/**
		 * @return the avgScore
		 */
		public double getAvgScore() {
			return avgScore;
		}

		/**
		 * @param avgScore
		 *            the avgScore to set
		 */
		public void setAvgScore(double avgScore) {
			this.avgScore = avgScore;
		}

		/**
		 * @return the otherTeamsAvgScore
		 */
		public double getOtherTeamsAvgScore() {
			return otherTeamsAvgScore;
		}

		/**
		 * @param otherTeamsAvgScore
		 *            the otherTeamsAvgScore to set
		 */
		public void setOtherTeamsAvgScore(double otherTeamsAvgScore) {
			this.otherTeamsAvgScore = otherTeamsAvgScore;
		}

	}

	/**
	 * @return the season
	 */
	public String getSeason() {
		return season;
	}

	/**
	 * @param season
	 *            the season to set
	 */
	public void setSeason(String season) {
		this.season = season;
	}

	/**
	 * @return the team
	 */
	public int getTeam() {
		return team;
	}

	/**
	 * @param team
	 *            the team to set
	 */
	public void setTeam(int team) {
		this.team = team;
	}

	/**
	 * @return the numWins
	 */
	@Attribute
	public int getNumWins() {
		return numWins;
	}

	/**
	 * @param numWins
	 *            the numWins to set
	 */
	public void setNumWins(int numWins) {
		this.numWins = numWins;
	}

	/**
	 * @return the numLoses
	 */
	@Attribute
	public int getNumLoses() {
		return numLoses;
	}

	/**
	 * @param numLoses
	 *            the numLoses to set
	 */
	public void setNumLoses(int numLoses) {
		this.numLoses = numLoses;
	}

	/**
	 * @return the winStreak
	 */
	@Attribute
	public int getWinStreak() {
		return winStreak;
	}

	/**
	 * @param winStreak
	 *            the winStreak to set
	 */
	public void setWinStreak(int winStreak) {
		this.winStreak = winStreak;
	}

	/**
	 * @return the losingStreak
	 */
	@Attribute
	public int getLosingStreak() {
		return losingStreak;
	}

	/**
	 * @param losingStreak
	 *            the losingStreak to set
	 */
	public void setLosingStreak(int losingStreak) {
		this.losingStreak = losingStreak;
	}

	/**
	 * @return the averagePoints
	 */
	@Attribute
	public double getAveragePoints() {
		return averagePoints;
	}

	/**
	 * @param averagePoints
	 *            the averagePoints to set
	 */
	public void setAveragePoints(double averagePoints) {
		this.averagePoints = averagePoints;
	}

	/**
	 * @return the versusStat
	 */
	public List<TeamVersusStat> getVersusStat() {
		Collections.sort(versusStat);
		return versusStat;
	}

	/**
	 * @param versusStat
	 *            the versusStat to set
	 */
	public void setVersusStat(List<TeamVersusStat> versusStat) {
		this.versusStat = versusStat;
	}

	@Attribute
	public int getSeed() {
		return seed;
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}
}
