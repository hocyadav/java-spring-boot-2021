package feb_12th_night;
/**
 * 
 * @author Hariom Yadav | 12-Feb-2020
 *
 */
public class Prime_erothes_thenes {
	public static void main(String[] args) {
		primeNumber(20);
	}

	private static void primeNumber(int n) {
		boolean[] prime = new boolean[n];
		for(int i = 0; i < n; i++) {
			prime[i] = true;
		}
		
		for(int p = 2; p * p < n; p++) {
			if(prime[p] == true) {
				for(int j = p * p; j < n; j += p) {
					System.out.println(j + " - "+prime[j]);
					prime[j] = false;
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			if(prime[i] == true)
				System.out.println(i);
		}
		
	}
}
/**
4 - true
6 - true
8 - true
10 - true
12 - true
14 - true
16 - true
18 - true
9 - true
12 - false
15 - true
18 - false
0
1
2
3
5
7
11
13
17
19


*/