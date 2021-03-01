package june26;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
/**
 * Predicate in expand way and simple way
 * @author hy665678
 *
 */
class TestPredicateObj implements Predicate<Integer> {
	@Override
	public boolean test(Integer t) {
		return t % 2 == 0 ? true : false;//even check
	}
}

public class TestPredicate {
	public static void main(String[] args) {
		//old style
//		TestPredicateObj obj = new TestPredicateObj();
//		System.out.println(obj.test(10));
//		System.out.println(obj.test(11));
		
		//new style
		Predicate<Integer> p = (t) -> {
			return  (t % 2 == 0) ? true : false;
		};
		System.out.println(p.test(10));
		System.out.println(p.test(12));
		
		//modern way
		Predicate<Integer> p2 = t ->  t % 2 == 0;
		System.out.println(p2.test(10));
		System.out.println(p2.test(12));
		
		
		List<Integer> l1 = Arrays.asList(1,2,3,4,5,6);
		l1.stream()
			.filter( t -> {
				return t % 2 == 0;
			})
			.forEach(t -> {
				System.out.println(t);
			});
		
		//stream in expand way
		l1.stream()
		.filter(new Predicate<Integer>() {
			public boolean test(Integer t) {
				return t == 2;
			}
		})
		.forEach(new Consumer<Integer>() {
			public void accept(Integer t) {
				System.out.println("accept :"+t);
			}
		});
	}
}
