package com.troll.algorithm.leetCode.n455;import java.util.Arrays;/** * author : TangPeng * date : 11/26/22 20:28 * description : */public class 分发饼干 {    public int findContentChildren(int[] g, int[] s) {        Arrays.sort(g);        Arrays.sort(s);        int count = 0;        int start = 0;        for (int i = 0; i < s.length && start < g.length; i++) {            if (s[i] >= g[start]) {                start++;                count++;            }        }        return count;    }}