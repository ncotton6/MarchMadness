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

}
