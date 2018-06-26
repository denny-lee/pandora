package study.lab.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Interview {

    private static final int[] arr = new int[]{7,9,2,3,5,8,4};
    private static final List<Integer> source = new ArrayList<Integer>();

    private static final int target = 8;
    private static List<List<Integer>> result = new ArrayList<List<Integer>>();
    private static List<Integer> tempList = new ArrayList<Integer>();

    public static void solve(int index) {
        int tempSum = sumUp();
        if (tempSum == target) {
            result.add(new ArrayList<Integer>(tempList));
        } else if (tempSum < target) {
            int i = index;
            while (i >= 0){
                tempList.add(i);
                solve(i);
                tempList.remove(tempList.size() - 1);
                i--;
            }
        }
    }

    private static int sumUp() {
        int tempSum = 0;
        for (Integer i : tempList) {
            tempSum += arr[i];
        }
        return tempSum;
    }

    private static void printResult() {
        for (List<Integer> ll : result) {
            for (Integer l : ll) {
                System.out.print(arr[l] + ",");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Arrays.sort(arr);
        int i = 0;
        while (i < arr.length) {
            if (arr[i] > target) {
                break;
            }
            source.add(arr[i]);
            i++;
        }
        i--;
        for (int j = i; j > 0; j--) {
            tempList.add(j);
            solve(j);
            tempList.remove(tempList.size() - 1);
        }

        printResult();
    }
}
