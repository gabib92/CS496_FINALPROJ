package main;

import main.CSVReader;
import main.CSVReader.Game;
import main.Sorts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class test {

	public test(String inFile, String outFile) {
	     try {
			PrintWriter writer = new PrintWriter(outFile, "UTF-8");
			List<Game> results = LoadIncidentFromCSV(inFile);

			writer.println("[Game] [Year] [Platform] [Genre] [Gobal Sales]");
			
            writer.println("\nTop 5 and Low 5 of global sales");
            List<Game> bySales = Sorts.qsort(results, 0, results.size()-1, "sales");
            for(int i = bySales.size()-1; i>bySales.size()-6; i--){
                writer.println((bySales.get(i)).toString());
            }
            writer.println("...");
            for(int i = 5; i>0; i--) {
                writer.println(bySales.get(i).toString());
            }
            
            writer.println("\nTop 5 sales for each year");
            //Changes the year for each sublist
            for(int i = 1985; i < 1990; i++) {
            	String num = Integer.toString(i);
            	List<Game> byYearSub = CSVReader.subList(results, "year", num);
            	writer.println(num);
            	//Displays the top 5 sales for that year
            	for(int j = byYearSub.size()-1; j>byYearSub.size()-6; j--) {
            		List<Game> byYear = Sorts.qsort(byYearSub, 0, byYearSub.size()-1, "year");
            		writer.println(byYear.get(j).toString());
            	}
            }
            writer.close();
		}
		
		catch (IOException e) {
            System.out.println("Could not write to file.");
        }
	}
	 
	private static List<Game> LoadIncidentFromCSV(String filename) throws IOException {
        CSVReader csv = new CSVReader(filename);
        return csv.getList();
    }
	
	public static void main(String[] args) {
		if(args.length != 2){
            System.out.println("Usage: [INFile] [OUTFile]");
        }
        else {
            new test(args[0], args[1]);
        }

        System.out.println("Complete!");

	}

}
