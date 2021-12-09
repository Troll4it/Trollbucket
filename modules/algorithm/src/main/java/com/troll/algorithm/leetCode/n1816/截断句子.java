package com.troll.algorithm.leetCode.n1816;

public class 截断句子 {

    public static String truncateSentence(String s, int k) {
        if (s.equals("") || k == 0) return "";
        StringBuilder out = new StringBuilder();
        String[] split = s.split(" ");
        if (k >= split.length) return s;
        for (int i = 0; i < k; i++) {
            out.append(split[i]).append(" ");
        }


//        if (s.equals("") || k == 0) return "";
//        StringBuilder out = new StringBuilder();
//        int num = 0;
//        for (char c : s.toCharArray()) {
//            if (c ==' '){
//                num++;
//            }
//            if (num == k)break;
//            out.append(c);
//        }
//        return out.toString();
        return out.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println("args = " + truncateSentence("Hello how are you Contestant", 4) + "::::");
    }
}
