package test;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author maple
 * @create 2022-06-14 21:17
 */
public class LeetCodeDemo2 {
    @Test
    public void test() {
        int[][] mat = new int[][]{{1,2,3}, {4,5,6}};
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 3; j++) {
//                System.out.println(mat[i][j]);
//            }
//        }
        int[] res = findDiagonalOrder(mat);
        Arrays.stream(res).forEach(e -> System.out.print(e + ","));
    }
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int len = m * n;
        int k = 0;
        int[] res = new int[len];
        for (int sum = 0; sum < m + n - 1; sum++) {
            int x = sum;
            if (sum % 2 == 0) {
                while (x >= m) {
                    x--;
                }
                for (int i = sum - x; i <= sum; i++) {
                    res[k++] = mat[sum - i][i];
                }
            } else {
                while (x >= n) {
                    x--;
                }
                for (int i = sum - x; i <= sum; i++) {
                    res[k++] = mat[i][sum - i];
                    if (k == len) {
                        return res;
                    }
                }
            }
        }
        return res;
    }
}
