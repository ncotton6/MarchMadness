package model.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TeamStat {

	private String season;
	private int team, numWins, numLoses, winStreak, losingStreak;
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
}
