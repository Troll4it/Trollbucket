package com.troll.leetcode.二分法.n704;

public class 二分查找 {
    public static int search(int[] nums, int target) {
        if (nums == null || target < nums[0] || target > nums[nums.length - 1]) return -1;

        int end = nums.length - 1, start = 0;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            }
        }
        return -1;
    }

}
