package model;

import model.data.Loader;
import model.data.Team;

public class Link {

	public static Team lookupTeam(int i) {
		for (Team t : Loader.teams) {
			if (t.getId() == i)
				return t;
		}
		return null;
	}

	public static int[] lookupTeams(String[] teamNames) {
		int[] teams = new int[teamNames.length];
		for (int i = 0; i < teamNames.length; ++i) {
			int team = lookupTeam(teamNames[i]);
			if(team == -999){
				System.out.println(teamNames[i] + " wasn't found");
			}
			teams[i] = team;
		}
		return teams;
	}

	private static int lookupTeam(String string) {
		for (Team t : Loader.teams) {
			if (t.getName().equalsIgnoreCase(string))
				return t.getId();
		}
		return -999;
	}

}
