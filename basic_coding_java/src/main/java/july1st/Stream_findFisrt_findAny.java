package july1st;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Stream_findFisrt_findAny {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1,2,3,5,66);
		System.out.println(list);
		Optional<Integer> option = list.stream().findAny();//option object contain any object from stream, usally 1st element
		if (option.isPresent()) {
			Integer val = option.get();
			System.out.println(val);
		}
		
		Optional<Integer> op = list.stream().findFirst();//option object contain 1st element
		if (op.isPresent()) {
			System.out.println(op.get());
		}
		
	}

}
