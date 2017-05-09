package main;
import main.CSVReader.Game;
import java.util.List;

public class Sorts {
	
	public static List<Game> qsort(List<Game> arr, int p, int r, String type) {
		if(p<r) {
			int q = partition(arr,p,r,type);
			qsort(arr,p,q-1,type);
			qsort(arr,q+1,r,type);
		}
		return arr;
	}
	

public static int partition(List<Game> arr, int p, int r, String type) {
	int median = middle(arr,p,(int)Math.floor(arr.size()/2),r,type);
	Game pivot,temp;
	pivot = arr.get(median);
	int front = p+1;
	int back = r;
	boolean firstCheck = median!=p;
	if(firstCheck) {
		temp = pivot;
		arr.set(median,arr.get(p));
		arr.set(p,temp);
	}
	
	boolean done = false;
	if(type.compareTo("year")==0) {
		while(!done) {
			while(front<=back && arr.get(front).year<=pivot.year) {
				front++;
			}
			while(arr.get(back).year>=pivot.year && back>=front) {
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
	temp = arr.get(p);
	arr.set(p,arr.get(back));
	arr.set(back,temp);
	return back;
}

public static int middle(List<Game> arr,int one, int two, int three,String type) {
	if(type.compareTo("year")==0) {
		if(arr.get(one).year<=arr.get(two).year && arr.get(one).year>arr.get(three).year) {
			return one;
		}
		if(arr.get(one).year>arr.get(two).year && arr.get(one).year<=arr.get(three).year) {
			return one;
		}
		if(arr.get(two).year<=arr.get(one).year && arr.get(two).year>arr.get(three).year) {
			return two;
		}
		if(arr.get(two).year>arr.get(one).year && arr.get(two).year<=arr.get(three).year) {
			return two;
		}
		if(arr.get(three).year<=arr.get(one).year && arr.get(three).year>arr.get(two).year) {
			return three;
		}
		if(arr.get(three).year<arr.get(one).year && arr.get(three).year>=arr.get(two).year) {
			return three;
		}
		return two;
	}
	else if(type.compareTo("platform")==0) {
		if(arr.get(one).platform.compareTo(arr.get(two).platform) >= 0 && arr.get(one).platform.compareTo(arr.get(three).platform) < 0) {
			return one;
		}
		if(arr.get(one).platform.compareTo(arr.get(two).platform) < 0 && arr.get(one).platform.compareTo(arr.get(three).platform) >= 0) {
			return one;
		}
		if(arr.get(two).platform.compareTo(arr.get(one).platform) >= 0 && arr.get(two).platform.compareTo(arr.get(three).platform) < 0) {
			return two;
		}
		if(arr.get(two).platform.compareTo(arr.get(one).platform) < 0 && arr.get(two).platform.compareTo(arr.get(three).platform) >= 0) {
			return two;
		}
		if(arr.get(three).platform.compareTo(arr.get(one).platform) >= 0 && arr.get(three).platform.compareTo(arr.get(two).platform) < 0) {
			return three;
		}
		if(arr.get(three).platform.compareTo(arr.get(one).platform) < 0 && arr.get(three).platform.compareTo(arr.get(two).platform) >= 0) {
			return three;
		}
		return two;
	}
	else if(type.compareTo("genre")==0) {
		if(arr.get(one).genre.compareTo(arr.get(two).genre) >= 0 && arr.get(one).genre.compareTo(arr.get(three).genre) < 0) {
			return one;
		}
		if(arr.get(one).genre.compareTo(arr.get(two).genre) < 0 && arr.get(one).genre.compareTo(arr.get(three).genre) >= 0) {
			return one;
		}
		if(arr.get(two).genre.compareTo(arr.get(one).genre) >= 0 && arr.get(two).genre.compareTo(arr.get(three).genre) < 0) {
			return two;
		}
		if(arr.get(two).genre.compareTo(arr.get(one).genre) < 0 && arr.get(two).genre.compareTo(arr.get(three).genre) >= 0) {
			return two;
		}
		if(arr.get(three).genre.compareTo(arr.get(one).genre) >= 0 && arr.get(three).genre.compareTo(arr.get(two).genre) < 0) {
			return three;
		}
		if(arr.get(three).genre.compareTo(arr.get(one).genre) < 0 && arr.get(three).genre.compareTo(arr.get(two).genre) >= 0) {
			return three;
		}
		return two;
	}
	else if(type.compareTo("name")==0) {
		if(arr.get(one).name.compareTo(arr.get(two).name) >= 0 && arr.get(one).name.compareTo(arr.get(three).name) < 0) {
			return one;
		}
		if(arr.get(one).name.compareTo(arr.get(two).name) < 0 && arr.get(one).name.compareTo(arr.get(three).name) >= 0) {
			return one;
		}
		if(arr.get(two).name.compareTo(arr.get(one).name) >= 0 && arr.get(two).name.compareTo(arr.get(three).name) < 0) {
			return two;
		}
		if(arr.get(two).name.compareTo(arr.get(one).name) < 0 && arr.get(two).genre.compareTo(arr.get(three).name) >= 0) {
			return two;
		}
		if(arr.get(three).name.compareTo(arr.get(one).name) >= 0 && arr.get(three).genre.compareTo(arr.get(two).name) < 0) {
			return three;
		}
		if(arr.get(three).name.compareTo(arr.get(one).name) < 0 && arr.get(three).genre.compareTo(arr.get(two).name) >= 0) {
			return three;
		}
		return two;
	}
	else if(type.compareTo("sales")==0) {
		if(arr.get(one).sales<=arr.get(two).sales && arr.get(one).sales>arr.get(three).sales) {
			return one;
		}
		if(arr.get(one).sales>arr.get(two).sales && arr.get(one).sales<=arr.get(three).sales) {
			return one;
		}
		if(arr.get(two).sales<=arr.get(one).sales && arr.get(two).sales>arr.get(three).sales) {
			return two;
		}
		if(arr.get(two).sales>arr.get(one).sales && arr.get(two).sales<=arr.get(three).sales) {
			return two;
		}
		if(arr.get(three).sales<=arr.get(one).sales && arr.get(three).year>arr.get(two).sales) {
			return three;
		}
		if(arr.get(three).sales<arr.get(one).sales && arr.get(three).sales>=arr.get(two).sales) {
			return three;
		}
		return two;
	}
	else { return -1; }
}
}
