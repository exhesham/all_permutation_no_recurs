import java.util.Arrays;

public class AllPermsNoRec {

	final private char[] arrConst;
	
	
	char[] tempArr;
	long fc;
	
	int fixed = 0;
	long fcCount=0;
	int swapCount = 1;
	
	public AllPermsNoRec(char[] arr) {
		this.arrConst = arr;
		tempArr = arrConst.clone();
		this.fc = factorial(arrConst.length-1);
	}

	public void swap(char[] arr, int i, int j) {
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public char[] next(){
		if(arrConst.length < 2 && fixed == 0){
			fixed++;
			return arrConst;
			
		}
		if(arrConst.length == 2 && fixed == 0){
			fixed++;
			return arrConst;
		}
		if(arrConst.length == 2 && fixed == 1){
			tempArr = arrConst.clone();
			swap(tempArr, 0, fixed);
			fixed++;
			return tempArr;
		}
		for (; fixed < arrConst.length; ) {
			
			
			for (;fcCount < fc; ) {
				
				for (;swapCount < tempArr.length - 1; ) {
					swap(tempArr, swapCount, swapCount + 1);
					//System.out.println(Arrays.toString(arr));
					swapCount++;
					return tempArr;
				}
				swapCount = 1;
				fcCount++;
			}
			fcCount = 0;
			fixed++;
			if(fixed == arrConst.length){
				return null;
			}
			tempArr = arrConst.clone();
			swap(tempArr, 0, fixed);
			
		}

		return null;
	}
	private long factorial(int n) {
		long res = 1;
		for (int i = 1; i <= n; i++) {
			res *= i;
		}
		return res;
	}

	public void printAllPerms() {
//		int fc = factorial(arrConst.length-1);
//		for (int i = 0; i < arrConst.length; i++) {
//			char[] arr = arrConst.clone();
//			swap(arr, 0, i);
//			for (int j = 0; j < fc; j++) {
//				for (int k = 1; k < arr.length - 1; k++) {
//					swap(arr, k, k + 1);
//					System.out.println(Arrays.toString(arr));
//				}
//			}
//		}
	}

	public static void main(String[] args) {
		AllPermsNoRec iter = new AllPermsNoRec("abcd".toCharArray());
		char[] arr;
		while(( arr = iter.next()) != null){
			System.out.println(Arrays.toString(arr));
		}
	}

}
