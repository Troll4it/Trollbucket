package com.troll.algorithm.leetCode.n1;import java.util.HashMap;import java.util.Map;/** * author : TangPeng * date : 11/1/22 22:21 * description : */public class 两数之和 {    public int[] twoSum(int[] nums, int target) {        int[] result = new int[2];        if (nums == null || nums.length <= 0) return result;        Map<Integer, Integer> map = new HashMap<>();        for (int j = 0; j < nums.length; j++) {            int plus = target - nums[j];            if (map.containsKey(plus)) {                result[0] = j;                result[1] = map.get(plus);            }            map.put(nums[j], j);        }        return result;    }}