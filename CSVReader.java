package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	
	public static int partition(List<Game> arr, int p, int r, String type) {
		int i = p-1;
		Game temp;
		//checks to sort by year
		if(type.compareTo("year")==0) {
			int x = arr.get(p).year;
			for(int j = p;j<=r;j++) {
				int toSwitch = arr.get(j).year;
				if(toSwitch<=x) {
					i = i+1;
					temp = arr.get(i);
					arr.set(i, arr.get(j));
					arr.set(j, temp);
				}
			}
			temp = arr.get(i);
			arr.set(i+1, arr.get(r));
			arr.set(r, temp);
			return i+1;
		} else if (type.compareTo("platform")==0 || type.compareTo("genre")==0 || type.compareTo("name")==0) {
			String x = arr.get(r).platform;
			//checks if genre then sorts
			if(type.compareTo("genre")==0) {
				x = arr.get(r).genre;
				for(int j = p;j<=r;j++) {
					if(arr.get(j).genre.compareTo(x) <=0) {
						i = i+1;
						temp = arr.get(i);
						arr.set(i, arr.get(j));
						arr.set(j, temp);
					}
				}
				temp = arr.get(i);
				arr.set(++i, arr.get(r));
				arr.set(r, temp);
				return i;
			}
			//if not sorting by genre checks to sort by name
			else if(type.compareTo("name")==0) {

				x = arr.get(r).name;
				for(int j = p;j<=r;j++) {
					if(arr.get(j).name.compareTo(x) <=0) {
						i = i+1;
						temp = arr.get(i);
						arr.set(i, arr.get(j));
						arr.set(j, temp);
					}
				}
				temp = arr.get(i);
				arr.set(++i, arr.get(r));
				arr.set(r, temp);
				return i; 
			}	
			//if not sorting by name/genre, sorts by platform
			else {
				for(int j = p;j<=r;j++) {
					if(arr.get(j).platform.compareTo(x) <=0) {
						i = i+1;
						temp = arr.get(i);
						arr.set(i, arr.get(j));
						arr.set(j, temp);
					}
				}
				temp = arr.get(i);
				arr.set(++i, arr.get(r));
				arr.set(r, temp);
				return i;
			}	
	//sorts by sales
	} else if(type.compareTo("sales")==0) {
		double x = arr.get(r).sales;
		for(int j = p;j<=r-1;j++) {
			if(arr.get(j).sales<=x) {
				i = i+1;
				temp = arr.get(i);
				arr.set(i, arr.get(j));
				arr.set(j, temp);
			}
		}
		temp = arr.get(i);
		arr.set(++i, arr.get(r));
		arr.set(r, temp);
		return i;
	} else return -1;
}

public static List<Game> qsort(List<Game> arr, int p, int r, String type) {
	if(p<r) {
		int q = partition(arr,p,r,type);
		qsort(arr,p,q-1,type);
		qsort(arr,q+1,r,type);
	}
	return arr;
}

public static List<Game> sort(List<Game> arr, String type) {
	int size = arr.size();
	List<Game> temp = new ArrayList<Game>();
	return mergesort(arr,temp,0,size-1,type);
}

private static List<Game> mergesort(List<Game> arr, List<Game> temp,int low,int high, String type) {
	temp = new ArrayList<Game>();
	for(int i=0;i<=high;i++)
		temp.add(arr.get(i));
	if(low<high) {
		int middle = low + (high-low)/2;
		mergesort(arr,temp,low,middle,type);
		mergesort(arr,temp,middle+1,high,type);
		List<Game> ret = merge(arr,temp,low, middle,high,type);
		return ret;
	} else return arr;
}

public static List<Game> merge(List<Game> arr, List<Game> temp, int low, int middle, int high, String type) {
	
	int i,j,k,tempType;
	i = low;
	j = middle+1;
	k = low;
	switch(type) {
	case "name": tempType = 1;
	break;
	case "year": tempType = 2;
	break;
	case "platform": tempType = 3;
	break;
	case "genre": tempType = 4;
	break;
	case "sales": tempType = 5;
	break;
	default: tempType = -1;
	}
	
	while(i<=middle && j<=high) {
		if(tempType == 1) {
			if(temp.get(i).name.compareTo(temp.get(j).name)<=0) {
				arr.set(k, temp.get(j));
				i++;
			} else {
				arr.set(k, temp.get(j));
				j++;
			}
			k++;
		} else if(tempType == 2) {
			if(temp.get(i).year<=temp.get(j).year) {
				arr.set(k, temp.get(j));
				i++;
			} else {
				arr.set(k, temp.get(j));
				j++;
			}
			k++;
		} else if(tempType == 3) {
			if(temp.get(i).platform.compareTo(temp.get(j).platform)<=0) {
				arr.set(k, temp.get(j));
				i++;
			} else {
				arr.set(k, temp.get(j));
				j++;
			}
			k++;
		} else if(tempType == 4) {
			if(temp.get(i).genre.compareTo(temp.get(j).genre)<=0) {
				arr.set(k, temp.get(j));
				i++;
			} else {
				arr.set(k, temp.get(j));
				j++;
			}
			k++;
		} else if(tempType == 5) {
			if(temp.get(i).sales<=temp.get(j).sales) {
				arr.set(k, temp.get(j));
				i++;
			} else {
				arr.set(k, temp.get(j));
				j++;
			}
			k++;
		} else {
			return null;
		}
	}
	
	while(i<=middle){
		arr.set(k, temp.get(i));
		k++;
		i++;
	} 
	return arr;
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