package main;

import main.CSVReader;
import main.CSVReader.Game;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class test {

	public test(String inFile, String outFile) {
	     try {
			PrintWriter writer = new PrintWriter(outFile, "UTF-8");
			List<Game> results = LoadIncidentFromCSV(inFile);

            writer.println("\nTop 5 and Low 5");
            for(int i = 0; i < 5; i++){
                writer.println((results.get(i)).toString());
            }
            writer.println("...");
            for(int i = results.size()-5; i<results.size(); i++) {
                writer.println(results.get(i).toString());
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
			try {
				List<Game> results = LoadIncidentFromCSV(args[0]);
				//results = CSVReader.qsort(results,(results.size()/2),results.size()-1,"year");
				//results = CSVReader.sort(results, "sales");
				results = CSVReader.subList(results, "genre", "Platform");
				//Game temp = results.get(0);
				//results.set(0, results.get(results.size()-1));
				//results.set(results.size()-1, temp);
	            for(int i = 0; i < results.size(); i++){
	                System.out.println((results.get(i)).toString());
	            }
	            //System.out.println("...");
	            //for(int i = results.size()-5; i<results.size(); i++) {
	            //	System.out.println(results.get(i).toString());
	            //}
			} catch (IOException e) {
				System.out.println("couldn't read file");
			}
        }

        System.out.println("Complete!");

	}

}