package july6th;
/** https://www.evernote.com/l/AGDRqq0HjBxCWJnVE6_C_7lP4hWQ1Fj8lSo/ **/
public class GoldmanToyDistribution {
	public static void main(String[] args) {
		System.out.println("student who got defective toy : "+returnLastWrongToy(5,2,1));
	}
	
	public static int returnLastWrongToy(int numOfStudent, int numOfToys, int randomStart) {
		//if start from 1st index
		int ans = numOfToys % numOfStudent;
		//since start from random index it may be any number between 1 to nmbStudents
		int newStartIndex =  (ans + randomStart - 1);
		//so new ans after newstart index
		int finalAns = newStartIndex % numOfStudent;
		
		if(finalAns == 0) return numOfStudent;
		return finalAns;
	}
	
	public static int returnLastWrongToy_StartFrom1stStudent(int numOfStudent, int numOfToys, int randomStart) {
		//if start from 1st index
		int ans = numOfToys % numOfStudent;
		if(ans == 0) return numOfStudent;
		return ans;
	}
	
}
