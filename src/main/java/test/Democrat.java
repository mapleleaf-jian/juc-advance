package test;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author maple
 * @create 2022-06-15 20:14
 */
public class Democrat {
    @Test
    public void test() {
        int[] nums = new int[]{1,3,1};
        int k = 1;
        System.out.println(smallestDistancePair(nums, k));
    }
    public int smallestDistancePair(int[] nums, int k) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        boolean flag = true;//true表示直接添加
        Optional<Integer> max = Optional.of(0);
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int val = Math.abs(nums[i] - nums[j]);
                if (flag) {
                    res.add(val);
                    if (res.size() == k) {
                        flag = false; //需要删除元素
                        max = res.stream().max((i1, i2) -> Integer.compare(i1, i2));
                    }
                } else {
                    if (val < max.get()) {
                        max = insert(res, val);
                    }
                }
            }
        }
        // System.out.println(Arrays.toString(res));
        return res.stream().max((i1, i2) -> Integer.compare(i1, i2)).get();
    }

    // 插入数组，并返回最大值
    public Optional<Integer> insert(List<Integer> nums, int val) {
        Optional<Integer> max = nums.stream().max((i1, i2) -> Integer.compare(i1, i2));
        nums.remove(nums.indexOf(max));
        nums.add(val);
        return nums.stream().max((i1, i2) -> Integer.compare(i1, i2));
    }
}
