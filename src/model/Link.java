package model;

import model.data.Loader;
import model.data.Team;

public class Link {

	private static int id = 100000;
	
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
			teams[i] = team;
		}
		return teams;
	}

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

}
