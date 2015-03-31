import java.io.IOException;

import model.data.Loader;


public class Madness {

	public static void main(String[] args){
		try {
			Loader.Load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
