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
	/* The following are all averages of a season performance */
	private double points, fgm, fga, fgm3, fga3, ftm, fta, or, dr, ast, to,
			stl, blk, pf;
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
	public int getNumLoses() {
		return numLoses;
	}

	@Attribute
	public int getReverseNumLoses() {
		return -numLoses;
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
	public int getLosingStreak() {
		return losingStreak;
	}

	@Attribute
	public int getReverseLosingStreak() {
		return -losingStreak;
	}

	/**
	 * @param losingStreak
	 *            the losingStreak to set
	 */
	public void setLosingStreak(int losingStreak) {
		this.losingStreak = losingStreak;
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

	public int getSeed() {
		return seed;
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}

	@Attribute
	public int getReverseSeed() {
		return -seed;
	}

	/**
	 * @return the points
	 */
	@Attribute
	public double getPoints() {
		return points;
	}

	/**
	 * @param points
	 *            the points to set
	 */
	public void setPoints(double points) {
		this.points = points;
	}

	/**
	 * @return the fgm
	 */
	@Attribute
	public double getFgm() {
		return fgm;
	}

	/**
	 * @param fgm
	 *            the fgm to set
	 */
	public void setFgm(double fgm) {
		this.fgm = fgm;
	}

	/**
	 * @return the fga
	 */
	@Attribute
	public double getFga() {
		return fga;
	}

	/**
	 * @param fga
	 *            the fga to set
	 */
	public void setFga(double fga) {
		this.fga = fga;
	}

	/**
	 * @return the fgm3
	 */
	@Attribute
	public double getFgm3() {
		return fgm3;
	}

	/**
	 * @param fgm3
	 *            the fgm3 to set
	 */
	public void setFgm3(double fgm3) {
		this.fgm3 = fgm3;
	}

	/**
	 * @return the fga3
	 */
	@Attribute
	public double getFga3() {
		return fga3;
	}

	/**
	 * @param fga3
	 *            the fga3 to set
	 */
	public void setFga3(double fga3) {
		this.fga3 = fga3;
	}

	/**
	 * @return the ftm
	 */
	@Attribute
	public double getFtm() {
		return ftm;
	}

	/**
	 * @param ftm
	 *            the ftm to set
	 */
	public void setFtm(double ftm) {
		this.ftm = ftm;
	}

	/**
	 * @return the fta
	 */
	@Attribute
	public double getFta() {
		return fta;
	}

	/**
	 * @param fta
	 *            the fta to set
	 */
	public void setFta(double fta) {
		this.fta = fta;
	}

	/**
	 * @return the or
	 */
	@Attribute
	public double getOr() {
		return or;
	}

	/**
	 * @param or
	 *            the or to set
	 */
	public void setOr(double or) {
		this.or = or;
	}

	/**
	 * @return the dr
	 */
	@Attribute
	public double getDr() {
		return dr;
	}

	/**
	 * @param dr
	 *            the dr to set
	 */
	public void setDr(double dr) {
		this.dr = dr;
	}

	/**
	 * @return the ast
	 */
	@Attribute
	public double getAst() {
		return ast;
	}

	/**
	 * @param ast
	 *            the ast to set
	 */
	public void setAst(double ast) {
		this.ast = ast;
	}

	/**
	 * @return the to
	 */
	public double getTo() {
		return to;
	}
	
	@Attribute
	public double getReverseTo(){
		return -to;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(double to) {
		this.to = to;
	}

	/**
	 * @return the stl
	 */
	@Attribute
	public double getstl() {
		return stl;
	}

	/**
	 * @param wstl
	 *            the stl to set
	 */
	public void setstl(double stl) {
		this.stl = stl;
	}

	/**
	 * @return the blk
	 */
	@Attribute
	public double getblk() {
		return blk;
	}

	/**
	 * @param wblk
	 *            the blk to set
	 */
	public void setblk(double blk) {
		this.blk = blk;
	}

	/**
	 * @return the pf
	 */
	public double getPf() {
		return pf;
	}
	
	@Attribute
	public double getReversePf(){
		return -pf;
	}

	/**
	 * @param pf
	 *            the pf to set
	 */
	public void setPf(double pf) {
		this.pf = pf;
	}
}
