import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class AllPermsNoRec<TYPE> {

	private final Semaphore processing = new Semaphore(1, true); /* Use binary semaphore and FIFO the acquires*/

	final private TYPE[] arrConst; // the original array as received

	TYPE[] tempArr; // save the state
	long fc; // factorial on n-1

	int fixed = 0; // fixed element in the array - for the algorithm calculation
	long fcCount = 0; // factorial counter - for the algorithm calculation
	int swapCount = 1; // swap counter - for the algorithm calculation

	public AllPermsNoRec(TYPE[] arr) {
		this.arrConst = arr;
		tempArr = arrConst.clone();
		this.fc = factorial(arrConst.length - 1);
	}

	public void swap(TYPE[] arr, int i, int j) {
		TYPE temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	/***
	 * reset all the counters to start generating from the beginning. acquires the semaphore in order not to interrupt an executing permutation generation
	 * @throws InterruptedException 
	 */
	public void reset() throws InterruptedException {
		processing.acquire(); // acquire the semaphore in order not to generate unexpected permutation
		fixed = 0;
		fcCount = 0;
		swapCount = 1;
		processing.release();
	}
	/***
	 * Generate the next permutation. null will be returned if EOF is reached
	 * @throws InterruptedException 
	 */
	public TYPE[] next() throws InterruptedException {
		processing.acquire();
		// handle single element
		try {
			if (arrConst.length < 2 && fixed == 0) {
				fixed++;

				return arrConst;

			}
			// handle first swap of 2 elements array
			if (arrConst.length == 2 && fixed == 0) {
				fixed++;
				return arrConst;
			}
			// handle second swap of 2 elements array
			if (arrConst.length == 2 && fixed == 1) {
				tempArr = arrConst.clone();
				swap(tempArr, 0, fixed);
				fixed++;
				return tempArr;
			}

			// run the algorithm:
			// 1- calculate n-1!.
			// 2- Store the original string in an auxiliary string and use that
			// string to do the swaps.
			// 3- Fix the first position(character), and swap all the k’th and
			// (k+1)’th characters till (n-1)!.
			// 4- At the end of first (n-1)! all the (n-1)th characters will be
			// permuted.
			// 5- Now again store the original string to the auxiliary string
			// and swap i’th and (i+1)’th characters and repeat the 3rd and 4th
			// process.

			for (; fixed < arrConst.length;) {

				for (; fcCount < fc;) {

					for (; swapCount < tempArr.length - 1;) {
						swap(tempArr, swapCount, swapCount + 1);
						// System.out.println(Arrays.toString(arr));
						swapCount++;
						return tempArr;
					}
					swapCount = 1;
					fcCount++;
				}
				fcCount = 0;
				fixed++;
				if (fixed == arrConst.length) {
					return null;
				}
				tempArr = arrConst.clone();
				swap(tempArr, 0, fixed);

			}

			return null;
		} finally {
			processing.release();
		}
	}

	private long factorial(int n) {
		long res = 1;
		for (int i = 1; i <= n; i++) {
			res *= i;
		}
		return res;
	}

}
