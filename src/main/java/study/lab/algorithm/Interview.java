package study.lab.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Interview {

    private static final int[] arr = new int[]{7,9,2,3,5,8,4};

    private static final int target = 8;
    private static List<List<Integer>> result = new ArrayList<List<Integer>>();

    public static void solve(int index, List<Integer> road) {
        for (int i = index; i < arr.length; i++) {
            if (index >= arr.length) {
                return;
            }
            if (arr[index] > target) {
                return;
            }
            int temp = 0;
            for (int j = 0; j < road.size(); j++) {
                temp += arr[j];
            }
            if (temp + arr[index] > target) {
                return;
            }
            road.add(arr[index]);
            if (temp + arr[index] == target) {
                result.add(new ArrayList<Integer>(road));
            }
            solve(index+1, road);
            road.remove(road.size() - 1);
        }

    }

    private static void printResult() {
        for (List<Integer> ll : result) {
            for (Integer l : ll) {
                System.out.print(l + ",");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Arrays.sort(arr);
        List<Integer> road = new ArrayList<Integer>();
        solve(0, road);

        printResult();
    }
}
