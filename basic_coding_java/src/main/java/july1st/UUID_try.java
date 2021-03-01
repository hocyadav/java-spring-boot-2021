package july1st;

import java.util.UUID;

public class UUID_try {
	public static void main(String[] args) {
		//UUID uuid = new UUID(mostSigBits, leastSigBits);
		UUID randomUUID = UUID.randomUUID();
		System.out.println(randomUUID);
		System.out.println(randomUUID.toString());
		
		UUID fromString = UUID.fromString(randomUUID.toString());
		//UUID fromString = UUID.fromString(randomUUID.toString()+"m");//error : invalid uuid
		System.out.println(fromString);

		//compare 2 same UUID
		System.out.println(randomUUID.equals(fromString));
	
	}
}
