package com.troll.algorithm.leetCode.n349;import android.os.Build;import java.util.HashSet;/** * author : TangPeng * date : 11/1/22 21:56 * description : */public class 两个数组的交集 {    public int[] intersection(int[] nums1, int[] nums2) {        HashSet<Integer> set1 = new HashSet<>();        HashSet<Integer> set2 = new HashSet<>();        for (int i = 0; i < nums1.length; i++) {            set1.add(nums1[i]);        }        for (int j = 0; j < nums2.length; j++) {            int k = nums2[j];            if (set1.contains(k)) {                set2.add(k);            }        }        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {            return set2.stream().mapToInt(value -> value.intValue()).toArray();        }        return null;    }}