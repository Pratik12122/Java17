package basics;

import java.util.ArrayList;
import java.util.List;

public class ArraySplitter {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3 };
		int k = 5;

		List<Integer> result = splitArraySizesOptimized(nums, k);
		System.out.println(result);
	}

	public static List<Integer> splitArraySizesOptimized(int[] nums, int k) {
		int n = nums.length;
		List<Integer> result = new ArrayList<>();

		int baseSize = n / k;
		int extraElements = n % k;

		for (int i = 0; i < k; i++) {
			int groupSize = baseSize + (i < extraElements ? 1 : 0);
			result.add(groupSize);
		}

		return result;
	}
}
