# CS496_FINALPROJ
#CsvReader

package edu.sdsu.cs.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    List<Game> contents;

    /**
     * Uses the filename user gave to load all its contents
     * @param filename
     * @throws IOException
     */
    public CsvReader(String filename) throws IOException {
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

                int year;
                if(values.length != 0) {

                    try {
                        year = Integer.parseInt(values[3]);
                    }
                    catch(NumberFormatException e) {
                        year = 0;
                    }

                    /**
                     * Creates new Incidents
                     */
                    Game input = new Game(year,values[2],values[4],sales);
                    dictionary.add(input);
                }

                else {
                    throw new IOException("Your file is not formatted right.");
                }

            }
        }

        catch(IOException ioe) {
            ioe.printStackTrace();
        }

        return dictionary;
    }


    public class Game {
        public int year;
        public String platform;
        public String genre;
        public double sales;

        /**
         * Default constructor
         */
        public Incident(int year, String platform, String genre, double sales) {
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
            return " ";
        }
    }
}

