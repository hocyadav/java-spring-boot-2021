import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListRemove {
	public static void main(String[] args) {
		List<Integer> list = new LinkedList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()) {
			int i = it.next();
			if(i == 2)
				it.remove();
			System.out.print(i+" ");
		}
	}
}
