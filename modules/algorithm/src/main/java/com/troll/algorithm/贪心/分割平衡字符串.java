package com.troll.algorithm.贪心;

/**
 * 平衡字符串：L和R字符的数量是相同的
 */
public class 分割平衡字符串 {

  public static void main(String[] args) {
    System.out.println("args = " + balancedStringSplit("LLRRLLRRLLRR"));
  }

  public static int balancedStringSplit(String s) {

    int ans = 0;
    int lCount = 0;
    int rCount = 0;

    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == 'L') {
        lCount++;
      } else {
        rCount++;
      }
      if (lCount == rCount) {
        ans++;
      }
    }
    return ans;
  }


}
