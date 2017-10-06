# Purpose

yield next permutation in java.

Create an instance of the class `AllPermsNoRec` and call the method `next()` to get the next permutation.

If all the permutations were generated, a null will be returned. in order to restart the generation, call the method `reset()`.

**This code is thread safe using a semaphore**

# Algorithm
1. calculate (n-1)!
2. Clone the original string and use that string to do the swaps.
3. Fix the first position(character), and swap all the k’th and (k+1)’th characters till (n-1)!.
4. At the end of first (n-1)! all the (n-1)th characters will be permuted.
5. Now again store the original string to the auxiliary string and swap i’th and (i+1)’th characters and repeat the 3rd and 4th process.

# Benefits
Recursion is precious in space allocation. Space Complexity in the given algorithm is O(n).

# Usage
```
		AllPermsNoRec<Integer> iter = new AllPermsNoRec<Integer>(new Integer[]{1,2,3});
		Integer[] arr;
		while(( arr = iter.next()) != null){
			System.out.println(Arrays.toString(arr));
		}
```
		
