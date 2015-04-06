package model.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import datareader.CSVReader;

/**
 * This class will load the data from the CSV files into memory, and give the
 * application access to the data.
 * 
 * @author Nathaniel Cotton
 * 
 */
public class Loader {

	public static List<Season> regularSeason = null;
	public static List<SeasonDetail> seasonDetail = null;
	public static List<Team> teams = null;
	public static List<Result> results = null;
	public static List<TourneySlot> slots = null;
	public static List<TourneySeed> seeds = null;
	public static List<TeamStat> teamStat = null;

	public static void Load() throws IOException {
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
