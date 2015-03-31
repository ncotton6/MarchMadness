package model.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import datareader.CSVReader;

public class Loader {

	public static void Load() throws IOException {
		List<Season> regularSeason = null;
		List<SeasonDetail> seasonDetail = null;
		List<Team> teams = null;
		List<Result> results = null;
		List<TourneySlot> slots = null;
		List<TourneySeed> seeds = null;
		// parse season
		{
			FileInputStream fis = new FileInputStream(new File("data"
					+ File.separator + "regular_season_results.csv"));
			regularSeason = CSVReader.parse(fis, Season.class);
			fis.close();
		}
		{
			// parse season details
			FileInputStream fis = new FileInputStream(new File("data"
					+ File.separator + "seasons.csv"));
			seasonDetail = CSVReader.parse(fis, SeasonDetail.class);
			fis.close();
		}
		{
			// parse teams
			FileInputStream fis = new FileInputStream(new File("data"
					+ File.separator + "teams.csv"));
			teams = CSVReader.parse(fis, Team.class);
			fis.close();
		}
		{
			// parse tournament results
			FileInputStream fis = new FileInputStream(new File("data"
					+ File.separator + "tourney_results.csv"));
			results = CSVReader.parse(fis, Result.class);
			fis.close();
		}
		{
			// parse tournament seeds
			FileInputStream fis = new FileInputStream(new File("data"
					+ File.separator + "tourney_seeds.csv"));
			seeds = CSVReader.parse(fis, TourneySeed.class);
			fis.close();
		}
		{
			// parse tournament slots
			FileInputStream fis = new FileInputStream(new File("data"
					+ File.separator + "tourney_slots.csv"));
			slots = CSVReader.parse(fis, TourneySlot.class);
			fis.close();
		}

	}

}
