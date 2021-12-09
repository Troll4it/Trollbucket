package com.troll.algorithm.比较排序;

import java.util.Arrays;

public class 冒泡排序 {
  public static void bulletSort(int[] arr) {
    int temp = 0;
    boolean swap = false;

    for (int i = arr.length - 1; i > 0; i--) {
      for (int j = 0; j < i; j++) {
        if (arr[j] > arr[j + 1]) {
          temp = arr[j + 1];
          arr[j + 1] = arr[j];
          arr[j] = temp;
          swap = true;
        }
      }
      if (!swap) {
        break;
      }
    }
  }

  public static void main(String[] args) {
    int[] ints = {1, 2, 5, 24, 32, 42, 432, 45};
    bulletSort(ints);
    for (int i = 0; i < ints.length; i++) {
      System.out.println("args = " + ints[i]);

    }
  }
}

