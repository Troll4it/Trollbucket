package com.troll.leetcode.滑动窗口.n209;

public class 长度最小的子数组 {

    public static int minSubArrayLen(int target, int[] nums) {
        int sum = 0, length = Integer.MAX_VALUE, start = 0;
        for (int end = 0; end < nums.length; end++) {
            sum = sum + nums[end];// 移动右边窗口
            while (sum >= target) {
                length = Math.min(length, end - start + 1);
                System.out.println("sum = " + sum + ", start = " + start + ", end = " + end + ", length = " + length);
                sum = sum - nums[start]; //移动左边窗口
                start++;
            }
        }
        return length == Integer.MAX_VALUE ? 0 : length;
    }

    public static void main(String[] args) {
        minSubArrayLen(8, new int[]{1, 1, 1, 1, 5, 6, 7, 8});
    }
}
