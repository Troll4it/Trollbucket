package com.troll.algorithm.leetCode.n1047;import java.util.Stack;/** * author : TangPeng * date : 11/7/22 22:47 * description : */public class 删除字符串中所有的相邻重复项 {    public String removeDuplicates(String s) {        Stack<Character> stack = new Stack<>();        for (int i = 0; i < s.length(); i++) {            char item = s.charAt(i);            if (stack.isEmpty()) {                stack.push(item);            } else {                Character peek = stack.peek();                if (peek == item) {                    stack.pop();                } else {                    stack.push(item);                }            }        }        StringBuilder result = new StringBuilder();        while (!stack.isEmpty()) {            result.insert(0, stack.pop());        }        return result.toString();    }}