package main;
import main.CSVReader.Game;
import java.util.List;
/**
 * 
 * @author Andrew Galkowski
 * in the sorts class, we just did one sort, quick sort.  we didn't think it was necessary 
 * to do multiple different sorts for the same data.
 *
 */
public class Sorts {
	/**
	 * 
	 * @param arr the list to be sorted
	 * @param p the start point of the list
	 * @param r the end point of the list
	 * @param type what of the 5 variables we are sorting by
	 * @return returns the sorted list
	 */
	public static List<Game> qsort(List<Game> arr, int p, int r, String type) {
		if(p<r) {
			int q = partition(arr,p,r,type);
			qsort(arr,p,q-1,type);
			qsort(arr,q+1,r,type);
		}
		return arr;
	}
	
/**
 * 
 * @param arr the array to be sorted
 * @param p the start point of the partition to be semi-sorted
 * @param r the end point of the partition to be semi-sorted
 * @param type which of the 5 variables we are sorting by
 * @return returns the point at which the back pointer ends at 
 *  	   (where the list should be partitioned by next)
 */
private static int partition(List<Game> arr, int p, int r, String type) {
	int median = middle(arr,p,(int)Math.floor(arr.size()/2),r,type);
	Game pivot,temp;
	pivot = arr.get(median);
	int front = p+1;
	int back = r;
	//checks to see if the first element in the list is the median value
	if(median!=p) {
		temp = pivot;
		arr.set(median,arr.get(p));
		arr.set(p,temp);
	}
	
	boolean done = false;
	//the if statements check to see what we are supposed to be sorting by 
	if(type.compareTo("year")==0) {
		while(!done) {
			//move front pointer to value on the wrong side
			while(front<=back && arr.get(front).year<=pivot.year) {
				front++;
			}
			//move back pointer to value that is on the wrong side
			while(arr.get(back).year>=pivot.year && back>=front) {
				back--;
			}
			//if overlapped exit, otherwise switch the two elements on the wrong side of the pivot
			if(back<front)
				done = true;
			else {
				temp = arr.get(front);
				arr.set(front,arr.get(back));
				arr.set(back,temp);
			}
		}
	} else if(type.compareTo("platform")==0) {
		while(!done) {
			while(front<=back && arr.get(front).platform.compareTo(pivot.platform) <= 0) {
				front++;
			}
			while(arr.get(back).platform.compareTo(pivot.platform)>=0 && back>=front) {
				back--;
			}
			if(back<front)
				done = true;
			else {
				temp = arr.get(front);
				arr.set(front,arr.get(back));
				arr.set(back,temp);
			}
		}
	} else if(type.compareTo("genre")==0) {
		while(!done) {
			while(front<=back && arr.get(front).genre.compareTo(pivot.genre) <= 0) {
				front++;
			}
			while(arr.get(back).genre.compareTo(pivot.genre)>=0 && back>=front) {
				back--;
			}
			if(back<front)
				done = true;
			else {
				temp = arr.get(front);
				arr.set(front,arr.get(back));
				arr.set(back,temp);
			}
		}
	} else if(type.compareTo("name")==0) {
		while(!done) {
			while(front<=back && arr.get(front).name.compareTo(pivot.name) <= 0) {
				front++;
			}
			while(arr.get(back).name.compareTo(pivot.name)>=0 && back>=front) {
				back--;
			}
			if(back<front)
				done = true;
			else {
				temp = arr.get(front);
				arr.set(front,arr.get(back));
				arr.set(back,temp);
			}
		}
	} else if(type.compareTo("sales")==0) {
		while(!done) {
			while(front<=back && arr.get(front).sales<=pivot.sales) {
				front++;
			}
			while(arr.get(back).sales>=pivot.sales && back>=front) {
				back--;
			}
			if(back<front)
				done = true;
			else {
				temp = arr.get(front);
				arr.set(front,arr.get(back));
				arr.set(back,temp);
			}
		}
	} else {
		System.out.println("invalid sorting type, sorting by sales.");
		while(!done) {
			while(front<=back && arr.get(front).sales<=pivot.sales) {
				front++;
			}
			while(arr.get(back).sales>=pivot.sales && back>=front) {
				back--;
			}
			if(back<front)
				done = true;
			else {
				temp = arr.get(front);
				arr.set(front,arr.get(back));
				arr.set(back,temp);
			}
		}
	}
	//swap the pivot element with the midpoint of front and back
	temp = arr.get(p);
	arr.set(p,arr.get(back));
	arr.set(back,temp);
	return back;
}
/**
 * @param arr list to find elements to compare in
 * @param one location of first comparable element in list
 * @param two location of second comparable element in list
 * @param three location of third comparable element in list
 * @param type what we are comparing by (year,genre,platform,sales,ect.)
 * @return either one two or three based on which of them was the middle element
 */
private static int middle(List<Game> arr,int one, int two, int three,String type) {
	Game first = arr.get(one);
	Game second = arr.get(two);
	Game third = arr.get(three);
	//the if statements check to see what we are supposed to be sorting by 
	if(type.compareTo("year")==0) {
		//these if statements determine which value is the median value of the three given
		//these are repeated for every different sorting type
		if(first.year<=second.year && first.year>third.year) {
			return one;
		}
		if(first.year>second.year && first.year<=third.year) {
			return one;
		}
		if(second.year<=first.year && second.year>third.year) {
			return two;
		}
		if(second.year>first.year && second.year<=third.year) {
			return two;
		}
		if(third.year<=first.year && third.year>second.year) {
			return three;
		}
		if(third.year<first.year && third.year>=second.year) {
			return three;
		}
		return two;
	}
	else if(type.compareTo("platform")==0) {
		if(first.platform.compareTo(second.platform) >= 0 && first.platform.compareTo(third.platform) < 0) {
			return one;
		}
		if(first.platform.compareTo(second.platform) < 0 && first.platform.compareTo(third.platform) >= 0) {
			return one;
		}
		if(second.platform.compareTo(first.platform) >= 0 && second.platform.compareTo(third.platform) < 0) {
			return two;
		}
		if(second.platform.compareTo(first.platform) < 0 && second.platform.compareTo(third.platform) >= 0) {
			return two;
		}
		if(third.platform.compareTo(first.platform) >= 0 && third.platform.compareTo(second.platform) < 0) {
			return three;
		}
		if(third.platform.compareTo(first.platform) < 0 && third.platform.compareTo(second.platform) >= 0) {
			return three;
		}
		return two;
	}
	else if(type.compareTo("genre")==0) {
		if(first.genre.compareTo(second.genre) >= 0 && first.genre.compareTo(third.genre) < 0) {
			return one;
		}
		if(first.genre.compareTo(second.genre) < 0 && first.genre.compareTo(third.genre) >= 0) {
			return one;
		}
		if(second.genre.compareTo(first.genre) >= 0 && second.genre.compareTo(third.genre) < 0) {
			return two;
		}
		if(second.genre.compareTo(first.genre) < 0 && second.genre.compareTo(third.genre) >= 0) {
			return two;
		}
		if(third.genre.compareTo(first.genre) >= 0 && third.genre.compareTo(second.genre) < 0) {
			return three;
		}
		if(third.genre.compareTo(first.genre) < 0 && third.genre.compareTo(second.genre) >= 0) {
			return three;
		}
		return two;
	}
	else if(type.compareTo("name")==0) {
		if(first.name.compareTo(second.name) >= 0 && first.name.compareTo(third.name) < 0) {
			return one;
		}
		if(first.name.compareTo(second.name) < 0 && first.name.compareTo(third.name) >= 0) {
			return one;
		}
		if(second.name.compareTo(first.name) >= 0 && second.name.compareTo(third.name) < 0) {
			return two;
		}
		if(second.name.compareTo(first.name) < 0 && second.genre.compareTo(third.name) >= 0) {
			return two;
		}
		if(third.name.compareTo(first.name) >= 0 && third.genre.compareTo(second.name) < 0) {
			return three;
		}
		if(third.name.compareTo(first.name) < 0 && third.genre.compareTo(second.name) >= 0) {
			return three;
		}
		return two;
	}
	else if(type.compareTo("sales")==0) {
		if(first.sales<=second.sales && first.sales>third.sales) {
			return one;
		}
		if(first.sales>second.sales && first.sales<=third.sales) {
			return one;
		}
		if(second.sales<=first.sales && second.sales>third.sales) {
			return two;
		}
		if(second.sales>first.sales && second.sales<=third.sales) {
			return two;
		}
		if(third.sales<=first.sales && third.year>second.sales) {
			return three;
		}
		if(third.sales<first.sales && third.sales>=second.sales) {
			return three;
		}
		return two;
	}
	else { return -1; }
}
}
