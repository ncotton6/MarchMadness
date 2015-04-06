import java.io.IOException;

import model.Bracket;
import model.Link;
import model.data.Loader;

public class Madness {

	public static void main(String[] args) {
		try {
			Loader.Load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] midwestString = new String[] { "Kentucky", "Kansas",
				"Notre Dame", "Maryland", "West Virginia", "Butler",
				"Wichita St", "Cincinnati", "Purdue", "Indiana", "Texas",
				"Buffalo", "Valparaiso", "Northeastern", "New Mexico St",
				"Hampton" };
		String[] westString = new String[] { "Wisconsin", "Arizona", "Baylor",
				"North Carolina", "Arkansas", "Xavier", "VCU", "Oregon",
				"Oklahoma St", "Ohio St", "Ole Miss", "Wofford", "Harvard",
				"Georgia St", "Texas Southern", "Coastal Carolina" };
		String[] eastString = new String[] { "Villanova", "Virginia",
				"Oklahoma", "Louisville", "UNla", "Providence", "Michigan St",
				"NC State", "LSU", "Georgia", "Dayton", "Wyoming", "UC Irvine",
				"Albany NY", "Belmont", "Lafayette" };
		String[] southString = new String[] { "Duke", "Gonzaga", "Iowa St",
				"Georgetown", "Utah", "SMU", "Iowa", "San Diego St",
				"St John's", "Davidson", "UCLA", "Stephen F. Austin",
				"E Washington", "UAB", "North Dakota", "Robert Morris" };

		int[] midwest = Link.lookupTeams(midwestString);
		int[] west = Link.lookupTeams(westString);
		int[] east = Link.lookupTeams(eastString);
		int[] south = Link.lookupTeams(southString);

		Bracket currentSeason = new Bracket(midwest, west, east, south);
	}

}
