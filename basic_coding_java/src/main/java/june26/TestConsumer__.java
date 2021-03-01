package june26;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;


class TestConsumer implements Consumer<Integer> {

	@Override
	public void accept(Integer t) {
		System.out.println("print - "+t);
	}
	
}

public class TestConsumer__ {
	public static void main(String[] args) {
		List<Integer> list = new LinkedList();
		List<Integer> list2 = new LinkedList();
		list.add(1);
		list.add(10);
		list.add(11);
		list.add(13);
		list.add(99);
		
		//m1
//		list.stream().forEach(new Consumer<Integer>() {
//			public void accept(Integer t) {//for each body
//				System.out.println("print "+t);
//				list2.add(t);
//			}
//		});
//		System.out.println(list2);
		
		//m1 advance
//		list.stream().forEach((Integer t) -> {//for each body
//				System.out.println("print "+t);
//				list2.add(t);
//			});
//		System.out.println(list2);
		
		//m1 advance
//		list.stream().forEach((t) -> {//accept -> do something , don't return anything like Runnable run method
//			System.out.println("print "+t);
//			list2.add(t);
//		});
//		System.out.println(list2);
		
		//m2
		list.stream().forEach(new TestConsumer());
		
	}
}
