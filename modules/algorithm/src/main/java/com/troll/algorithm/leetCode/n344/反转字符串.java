package com.troll.algorithm.leetCode.n344;/** * author : TangPeng * date : 11/2/22 23:37 * description : */public class 反转字符串 {    public static void main(String[] args) {        char[] strings = {'h', 'e', 'l', 'l', 'o'};        reverseString(strings);    }    public static void reverseString(char[] s) {        int i = 0, j = s.length - 1;        while (j >= i) {            System.out.println("j = " + j + " i = " + i);            char temp = s[i];            s[i] = s[j];            s[j] = temp;            i++;            j--;        }    }}