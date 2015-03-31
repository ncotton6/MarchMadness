package model.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import datareader.CSVReader;

public class Loader {

	public static void Load() throws IOException {
		// parse season
		FileInputStream fis = new FileInputStream(new File("data"
				+ File.separator + "regular_season_results.csv"));
		List<Season> regularSeason = CSVReader.parse(fis, Season.class);
		//System.out.println("Num season entries "+regularSeason.size());
		fis.close();
	}

}
