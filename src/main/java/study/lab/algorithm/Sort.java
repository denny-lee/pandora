package study.lab.algorithm;

public class Sort {

    private static int[] arr = new int[]{8,4,6,12,80,1,3};

    public static void main(String[] args) {

        print(arr);
//        bubble(arr);
//        quick(arr, 0, arr.length-1);
//        print(arr);
        System.out.println();
        print(sort(arr, 0, arr.length - 1));

    }

    private static int[] sort(int[] arr, int l, int r) {
        if (l == r) {
            return new int[]{arr[l]};
        }
        int m = (l + r)/2;
        int[] lr = sort(arr, l, m);
        int[] rr = sort(arr, m+1, r);
        return merge(lr, rr);
    }

    private static int[] merge(int[] l, int[] r) {
        int[] arr = new int[l.length + r.length];
        int p = 0;
        int lp = 0;
        int rp = 0;
        while (lp < l.length && rp < r.length) {
            arr[p++] = l[lp] < r[rp] ? l[lp++] : r[rp++];
        }
        while (lp < l.length) {
            arr[p++] = l[lp++];
        }
        while (rp < r.length) {
            arr[p++] = r[rp++];
        }
        return arr;
    }

    private static void quick(int[] arr, int l, int r) {
        if (l < r) {
            int i,j;
            i = l;
            j = r;
            int x = arr[i];
            while (i < j) {
                while (i < j && arr[j] > x) {
                    j--;
                }
                if (i < j) {
                    arr[i++] = arr[j];
                }
                while (i < j && arr[i] < x) {
                    i++;
                }
                if (i < j) {
                    arr[j--] = arr[i];
                }
            }
            arr[i] = x;
            quick(arr, l, i-1);
            quick(arr, i+1, r);
        }


    }

    private static void bubble(int[] arr) {
        int i, j;
        int n = arr.length;
        boolean flag = false;

        for(i = n-1; i>0; i--) {
            for(j = 0; j <i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j+1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    private static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i);
            System.out.print(",");
        }
    }
}
