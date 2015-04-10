package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.data.Loader;
import model.data.Result;
import model.data.Season;
import model.data.Team;
import model.data.TeamStat;
import model.data.TourneySeed;

/**
 * {@link Link} is class the when provided primary key like attributes from a
 * CSV file it will create a link to the other data.
 * 
 * @author Nathaniel Cotton
 * 
 */
public class Link {

	/*
	 * Not all team data is in the teams.csv file, so there are a few teams in
	 * this years tournament that we won't have data for. This id will allow
	 * link to create temporary teams with an incrementing id, but it won't fill
	 * out data for them.
	 */
	private static int id = 100000;

	/**
	 * Given a team id the related team object will be returned. Null will be
	 * returned if the team doesn't exist.
	 * 
	 * @param i
	 * @return
	 */
	public static Team lookupTeam(int i) {
		for (Team t : Loader.teams) {
			if (t.getId() == i)
				return t;
		}
		System.out.println("Couldn't find team " + i);
		throw new RuntimeException();
		// return null;
	}

	/**
	 * Given an array of team names the respective ids for the teams will be
	 * looked up. They will then fill an array that will be returned.
	 * 
	 * @param teamNames
	 * @return
	 */
	public static int[] lookupTeams(String[] teamNames) {
		int[] teams = new int[teamNames.length];
		for (int i = 0; i < teamNames.length; ++i) {
			int team = lookupTeam(teamNames[i]);
			teams[i] = team;
		}
		return teams;
	}

	/**
	 * This method will look up a team based off its team name. If the team
	 * cannot be found than a new team with that name, and assigned id is
	 * created. Note no data will be associated with this team.
	 * 
	 * @param string
	 * @return
	 */
	private static int lookupTeam(String string) {
		for (Team t : Loader.teams) {
			if (t.getName().equalsIgnoreCase(string))
				return t.getId();
		}
		System.out.println(string + " wasn't found team, team being created");
		Team t = new Team();
		t.setId(id++);
		t.setName(string);
		Loader.teams.add(t);
		return t.getId();
	}

	/**
	 * Gets all the tournament results for a season.
	 * 
	 * @param season
	 * @return
	 */
	public static List<Result> results(String season) {
		List<Result> tourneyResults = new ArrayList<Result>();
		for (Result r : Loader.results) {
			if (season.equalsIgnoreCase(r.getSeason()))
				tourneyResults.add(r);
		}
		return tourneyResults;
	}

	/**
	 * Gets all the games in a season.
	 * 
	 * @param season
	 * @return
	 */
	public static List<Season> season(String season) {
		List<Season> seasonlst = new ArrayList<Season>();
		for (Season s : Loader.regularSeason) {
			if (season.equalsIgnoreCase(s.getSeason()))
				seasonlst.add(s);
		}
		return seasonlst;
	}

	/**
	 * Gets all the games by one team in a particular season.
	 * 
	 * @param season
	 * @param team
	 * @return
	 */
	public static List<Season> seasonByTeam(String season, int team) {
		List<Season> seasonlst = new ArrayList<Season>();
		for (Season s : Loader.regularSeason) {
			if (season.equalsIgnoreCase(s.getSeason())
					&& (s.getLteam() == team || s.getWteam() == team))
				seasonlst.add(s);
		}
		return seasonlst;
	}

	/**
	 * Gets the bracket seeding for a particular season.
	 * 
	 * @param season
	 * @return
	 */
	public static int[][] getBracketSeeding(String season) {
		int[][] seeding = new int[4][16];
		for (TourneySeed ts : Loader.seeds) {
			if (ts.getSeason().equalsIgnoreCase(season))
				seeding[ts.getRegion()][ts.getSeed() - 1] = ts.getTeam();
		}
		return seeding;
	}

	/**
	 * Fills out a {@link TeamStat} object for a particular team, for a
	 * particular season.
	 * 
	 * @param team
	 * @param season
	 * @return
	 */
	public static TeamStat getTeamStat(Team team, String season) {
		TeamStat ts = new TeamStat();
		List<Season> s = Link.seasonByTeam(season, team.getId());
		Collections.sort(s, new Comparator<Season>() {
			// order by event time
			@Override
			public int compare(Season o1, Season o2) {
				return o1.getDaynum() - o2.getDaynum();
			}
		});
		int wins = 0;
		int loses = 0;
		int longestWinStreak = 0;
		int longestLosingStreak = 0;
		int tempWinStreak = 0;
		int tempLosingStreak = 0;
		double totalscore = 0;
		for (Season sea : s) {
			if (sea.getLteam() == team.getId()) {
				++loses;
				tempWinStreak = 0;
				++tempLosingStreak;
				if (tempLosingStreak > longestLosingStreak)
					longestLosingStreak = tempLosingStreak;
			} else if (sea.getWteam() == team.getId()) {
				++wins;
				tempLosingStreak = 0;
				++tempWinStreak;
				if (tempWinStreak > longestWinStreak)
					longestWinStreak = tempWinStreak;
			} else {
				// ERROR
			}
		}
		ts.setNumWins(wins);
		ts.setNumLoses(loses);
		ts.setWinStreak(longestWinStreak);
		ts.setLosingStreak(longestLosingStreak);
		ts.setAveragePoints(totalscore / s.size());
		ts.setSeed(Link.getSeed(season, team.getId()));
		ts.setTeam(team.getId());
		return ts;
	}

	/**
	 * Gets the seed of a team for a particular season returns the maximum
	 * integer value if the team isn't in the tournament that year.
	 * 
	 * @param season
	 * @param teamId
	 * @return
	 */
	private static int getSeed(String season, int teamId) {
		int[][] seeding = getBracketSeeding(season);
		for (int i = 0; i < seeding.length; ++i) {
			for (int z = 0; z < seeding[i].length; ++z) {
				if (teamId == seeding[i][z])
					return z + 1;
			}
		}
		return Integer.MAX_VALUE;
	}
}
