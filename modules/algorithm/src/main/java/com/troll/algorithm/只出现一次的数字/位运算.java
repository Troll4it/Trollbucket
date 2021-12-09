package com.troll.algorithm.只出现一次的数字;


import java.util.Arrays;

/**
 * 给定一个整数数组nums,其中恰好有两个元素值出现一次，其余所有元素均出现两次。找出只出现一次的那两个元素
 * <p>
 * 位运算规则：
 * 1、 x ^ x == 0  自己跟自己异或的结果为0
 * 2. x ^ 0 == x  自己跟0 的异或还是自己
 * 3. 0 ^ 1 == 1  0 ^ 0 == 0  1 ^ 1 = 0
 */
public class 位运算 {

  public static void main(String[] args) {
     final int CLICKABLE = 0x00004000;
    System.out.println("CLICKABLE = " + Integer.toBinaryString(CLICKABLE));
    System.out.println("0x00008000 = " + Integer.toBinaryString(0x00008000));
    int  mViewFlags = (0 & ~CLICKABLE) | (CLICKABLE & CLICKABLE);

    System.out.println("mViewFlags = " + mViewFlags);

  }

  public static int[] singleNum(int[] num) {
    int xor = 0;
    for (int i : num) {
      xor ^= i;
      System.out.println("xor = " + xor);

    }
    System.out.println("xor = " + Integer.toBinaryString(xor));
    System.out.println("-xor = " + Integer.toBinaryString(-xor));
    // 取出不同的那个位。这里取最右边的那个1
    int bit = xor & (-xor);
    System.out.println("bit = " + Integer.toBinaryString(bit));

    // 按这个位将所有数分成两半分别异或的结果就是需要找的数
    int x = 0, y = 0;
    for (int i : num) {
      if ((i & bit) == 0) {
        x ^= i;
      } else {
        y ^= i;
      }
    }
    System.out.println("x = " + x  + "y = "+y);
    return new int[]{x, y};
  }

}
