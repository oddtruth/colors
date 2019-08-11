package colors;

import java.awt.Point;
import java.util.ArrayList;

public class ArrayListHolder {
	ArrayList<Point> list1;
	ArrayList<Point> list2;
	
	public ArrayListHolder(ArrayList<Point> l1, ArrayList<Point> l2) {
		list1 = l1;
		list2 = l2;
	}

	public ArrayList<Point> getList1() {
		return list1;
	}

	public ArrayList<Point> getList2() {
		return list2;
	}

	
}
