package com.troll.algorithm.leetCode.二分法;

/**
 * 二分法：有序数据， 无重复项
 */
public class 二分法 {


    public int search(int[] nums, int target) {

        // fixme 优化 开始判断条件
//        if (nums.length <= 0) {
//            return -1;
//        }
        if (nums == null || target < nums[0] || target > nums[nums.length - 1]) return -1;

        int end = nums.length - 1;
        int start = 0;
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
