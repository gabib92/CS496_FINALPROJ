package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.CSVReader.Game;

public class CSVReader {
	List<Game> contents;

	/**
	 * Uses the filename user gave to load all its contents
	 * @param filename
	 * @throws IOException
	 */
	public CSVReader(String filename) throws IOException {
		contents = loadCSV(filename);
	}

	/**
	 * Return the contents from the list made by reading the .csv file the user gave.
	 * @return the contents from the list made by reading the .csv file.
	 */
	public List<Game> getList() {
		return contents;
	}

	/**
	 *
	 * @param filename
	 * @return dictionary of inputs classified at
	 * @throws IOException
	 */
	public List<Game> loadCSV(String filename) throws IOException {
		String line;
		List<Game> dictionary = new ArrayList<>();

		try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
			line = br.readLine();

			while((line = br.readLine()) != null) {
				String values[] = line.split(",");

				/**
				 * Finds the year and changes the string to an int
				 */
				int year = 0;
				if(values.length != 0) {

					try {
						year = Integer.parseInt(values[3]);
					}
					catch(NumberFormatException e) {
						year = 0;
					}
				}

				/**
				 * Finds the sales and changes the string to a double
				 * Sales are in millions
				 */
				double sales = 0;
				if(values.length != 0) {

					try {
						sales = Double.parseDouble(values[10]);
					}
					catch(NumberFormatException e) {
						sales = 0;
					}


				}   
				/**
				 * Creates new Games
				 * values[2] is the platform (String)
				 * values[4] is the genre (String)
				 */
				Game input = new Game(year,values[2],values[4],sales,values[1]);
				dictionary.add(input);




			}
		}

		catch(IOException ioe) {
			ioe.printStackTrace();
		}

		return dictionary;
	}

public static List<Game> subList(List<Game> arr, String splitOn, String type) {
	List<Game> temp = new ArrayList<Game>();
	for(int i=0;i<arr.size();i++) {
		switch(splitOn) {
			case "year":
				if(arr.get(i).year == Integer.parseInt(type))
					temp.add(arr.get(i));
				break;
			case "platform":
				if(arr.get(i).platform.compareTo(type)==0)
					temp.add(arr.get(i));
				break;
			case "genre":
				if(arr.get(i).genre.compareTo(type)==0)
					temp.add(arr.get(i));
				break;
		}
	}
	return temp;
}

	public class Game {
		public String name;
		public int year;
		public String platform;
		public String genre;
		public double sales;



		/**
		 * Default constructor
		 */
		public Game(int year, String platform, String genre, double sales, String name) {
			this.name = name;
			this.year = year;
			this.platform = platform;
			this.genre = genre;
			this.sales = sales;
		}


	/**
	 * 
	 * @return 
	 */
	@Override
	public String toString() {
		return name+", "+year+", "+platform+", "+genre+", "+sales;
	}
}
}