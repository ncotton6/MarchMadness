import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import model.Attribute;
import model.Bracket;
import model.Link;
import model.data.Loader;
import model.data.SeasonDetail;
import model.data.Team;
import model.data.TeamStat;
import model.sim.ActualSim;
import model.sim.NaiveBayesSim;
import model.sim.OneR;
import model.sim.RandomSim;
import model.sim.AgglomerationSim;

public class Madness {

	public static void main(String[] args) {
		try {
			Loader.Load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * String[] midwestString = new String[] { "Kentucky", "Kansas",
		 * "Notre Dame", "Maryland", "West Virginia", "Butler", "Wichita St",
		 * "Cincinnati", "Purdue", "Indiana", "Texas", "Buffalo", "Valparaiso",
		 * "Northeastern", "New Mexico St", "Hampton" }; String[] westString =
		 * new String[] { "Wisconsin", "Arizona", "Baylor", "North Carolina",
		 * "Arkansas", "Xavier", "VCU", "Oregon", "Oklahoma St", "Ohio St",
		 * "Ole Miss", "Wofford", "Harvard", "Georgia St", "Texas Southern",
		 * "Coastal Carolina" }; String[] eastString = new String[] {
		 * "Villanova", "Virginia", "Oklahoma", "Louisville", "UNla",
		 * "Providence", "Michigan St", "NC State", "LSU", "Georgia", "Dayton",
		 * "Wyoming", "UC Irvine", "Albany NY", "Belmont", "Lafayette" };
		 * String[] southString = new String[] { "Duke", "Gonzaga", "Iowa St",
		 * "Georgetown", "Utah", "SMU", "Iowa", "San Diego St", "St John's",
		 * "Davidson", "UCLA", "Stephen F. Austin", "E Washington", "UAB",
		 * "North Dakota", "Robert Morris" };
		 */
		/*
		 * int[] midwest = Link.lookupTeams(midwestString); int[] west =
		 * Link.lookupTeams(westString); int[] east =
		 * Link.lookupTeams(eastString); int[] south =
		 * Link.lookupTeams(southString);
		 * 
		 * Bracket currentSeason = new Bracket(midwest, west, east, south);
		 * 
		 * 
		 * System.out.println(); currentSeason.solve(new RandomSim(900));
		 * System.out.println(currentSeason.getWinner().getWinner().getName());
		 */

		// Build random probability
		double value = 0;
		RandomSim randomsim = new RandomSim(100000);
		Bracket actualH = Bracket.season("2003");
		ActualSim as = new ActualSim("2003");
		actualH.solve(as);
		for (int i = 0; i < 10000; ++i) {
			Bracket h = Bracket.season("2003");
			h.solve(randomsim);
			value += actualH.compare(h);
		}
		System.out.println("Average correct predications with random guessing "
				+ (value / 10000));
        
		HashMap<Method, ArrayList<Double>> data = new HashMap<Method, ArrayList<Double>>();
		for (SeasonDetail sd : Loader.seasonDetail) {
			try {
				Bracket actual = Bracket.season(sd.getSeason());
				as = new ActualSim(sd.getSeason());
				actual.solve(as);
				NaiveBayesSim nbs = new NaiveBayesSim(sd.getSeason());
				Bracket test = Bracket.season(sd.getSeason());
				test.solve(nbs);
				System.out.println(sd.getSeason());
				System.out.println("Naive Bayes " + sd.getSeason() + " "  + actual.compare(test));
                
                AgglomerationSim agg = new AgglomerationSim();
                
                int col = 0;
				for (Method m : TeamStat.class.getDeclaredMethods()) {
					if (m.getAnnotation(Attribute.class) != null) {
						OneR r = new OneR(sd.getSeason(), m);
                        // season object is not of type Season
						Bracket season = Bracket.season(sd.getSeason());
						season.solve(r);
						if (!data.containsKey(m))
							data.put(m, new ArrayList<Double>());
						data.get(m).add(actual.compare(season));
                        
                        int row = 0;
                        int[][] seeding = Link.getBracketSeeding(sd.getSeason());
                        Team[] teams = new Team[seeding.length*seeding[0].length];
                        for(int x = 0; x < seeding.length; ++x)
                        {
                            for(int y = 0; y < seeding[0].length; ++y)
                            {
                                teams[x*seeding[0].length + y] = 
                                    Link.lookupTeam(seeding[x][y]);
                            }
                        }
                        for (Team t : teams) {
                            TeamStat ts = Link.getTeamStat(t, sd.getSeason());
                            Object aValue = m.invoke(ts, new Object[] {});
                            double aTvalue = 0.0;
                            if (aValue instanceof Integer) {
                                aTvalue = ((Integer) aValue).doubleValue();
                            } else if (aValue instanceof Double) {
                                aTvalue = ((Double) aValue).doubleValue();
                            }
                            agg.addData(aTvalue, row, col);
                            row++;
                        }
                        col++;
					}
				}
                agg.initProtos();
                agg.agglomerate(2);
                System.out.println("Success");
                //agg.printClusters();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		for (Method m : data.keySet()) {
			System.out.println(m.getName() + " "
					+ Arrays.toString(data.get(m).toArray()));
			System.out.println(m.getName() + " avg " + avg(data.get(m)));
		}

	}

	/**
	 * Finds the average of the numbers in the list
	 * 
	 * @param arrayList
	 * @return
	 */
	private static double avg(ArrayList<Double> arrayList) {
		double value = 0;
		for (double d : arrayList)
			value += d;
		return value / arrayList.size();
	}
}
