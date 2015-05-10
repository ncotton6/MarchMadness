package model.sim;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import model.Attribute;
import model.Game;
import model.GameSimulator;
import model.KNNAttribute;
import model.Link;
import model.Tuple;
import model.data.Team;
import model.data.TeamStat;

public class KNNSim implements GameSimulator {

	private static final int numSeason = 3, k = 3, distance = 1;
	private String season = null;
	private List<TeamStat> statSpace = null;

	public KNNSim(String season) {
		this.season = season;
	}

	@Override
	public Tuple<Double, Double> simulate(Game game, int round) {
		// get past number of seasons
		if (statSpace == null) {
			statSpace = new ArrayList<TeamStat>();
			int tempSeason = Integer.valueOf(season) - 1;
			for (int i = 0; i < numSeason; ++i) {
				statSpace.addAll(Link.getSeasonStat(String.valueOf(tempSeason
						- i)));
			}
		}
		// find K closest points to team A
		double meanA = mean(points(game.getA()));
		// find K closest points to team B
		double meanB = mean(points(game.getB()));
		// select the greater seed
		return new Tuple<Double, Double>(meanA, meanB);
	}

	private double mean(List<Integer> points) {
		int total = 0;
		for (Integer i : points) {
			total += i;
		}
		return total / points.size();
	}

	private List<Integer> points(Team team) {
		TeamStat teamstat = Link.getTeamStat(team, season);
		List<Integer> progressions = new ArrayList<Integer>();
		PriorityQueue<Tuple<Double, TeamStat>> distances = new PriorityQueue<Tuple<Double, TeamStat>>(
				statSpace.size(), new Comparator<Tuple<Double, TeamStat>>() {

					@Override
					public int compare(Tuple<Double, TeamStat> o1,
							Tuple<Double, TeamStat> o2) {
						return (int) ((o1.v1 * 1000) - (o2.v1 * 1000));
					}
				});
		// fill queue
		for (TeamStat ts : statSpace) {
			distances.add(new Tuple<Double, TeamStat>(Distance(ts, teamstat),
					ts));
		}
		// pop off k
		double twoBack = 0, oneBack = 0;
		while (progressions.size() < k
				|| (twoBack == oneBack && progressions.size() >= k)) {
			Tuple<Double, TeamStat> stat = distances.poll();
			twoBack = oneBack;
			oneBack = stat.v1;
			progressions.add(Link.getRound(stat.v2.getTeam(), season));
		}
		return progressions;
	}

	private Double Distance(TeamStat a, TeamStat b) {
		Method[] methods = TeamStat.class.getDeclaredMethods();
		double dist = 0;
		for (int i = 0; i < methods.length; ++i) {
			if (methods[i].getAnnotation(Attribute.class) != null) {
				try {
					double valueA = (double) methods[i].invoke(a, null);
					double valueB = (double) methods[i].invoke(b, null);
					dist += Math.pow(Math.abs(valueA - valueB), distance);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return Math.pow(dist, (1 / (double) distance));
	}
}
