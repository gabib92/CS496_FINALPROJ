# CS496_FINALPROJ
#CsvReader

package edu.sdsu.cs.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    List<Incident> contents;

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
    public List<Incident> getList() {

        return contents;
    }

    /**
     *
     * @param filename
     * @return dictionary of inputs classified at
     * @throws IOException
     */
    public List<Incident> loadCSV(String filename) throws IOException {
        String line;
        List<Incident> dictionary = new ArrayList<>();

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
                    Incident input = new Incident(values[3],values[2],values[4],values[10]);
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


    public class Incident {
        public String year;
        public String platform;
        public String genre;
        public int sales;

        /**
         * Default constructor
         */
        public Incident(String year, String platform, String genre, int sales) {
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

