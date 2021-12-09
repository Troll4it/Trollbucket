package com.troll.algorithm.比较排序;

import com.troll.algorithm.CollectionUtil;

import java.util.Arrays;

/**
 * 分解待排序的数组成两个各具n/2个元素的子数组，递归调用归并排序的两个子数组，合并两个
 * 已排序的子数组组成一个已排序的数组
 */
public class 归并排序 {

  public static void main(String[] args) {
    mergeSort(new int[]{1,34,234,1234,2,65});
  }
  public static void mergeSort(int[] arr) {
    int[] temp = new int[arr.length];

    internalMergeSort(arr, temp, 0, arr.length - 1);
    CollectionUtil.INSTANCE.printArray(arr);
  }

  private  static void internalMergeSort(int[] arr, int[] temp, int left, int right) {
    if (left < right) {
      int mid = (left + right) / 2;
      internalMergeSort(arr, temp, left, mid);
      CollectionUtil.INSTANCE.printArray(temp);
      internalMergeSort(arr, temp, mid + 1, right);
      CollectionUtil.INSTANCE.printArray(temp);
      mergeSortedArray(arr, temp, left, mid, right);
      CollectionUtil.INSTANCE.printArray(temp);
    }
  }


  /**
   * 合并两个子序列
   *
   * @param arr
   * @param temp
   * @param left
   * @param mid
   * @param right
   */
  private static void mergeSortedArray(int[] arr, int[] temp, int left, int mid, int right) {
    int i = left;
    int j = mid + 1;
    int k = 0;

    while (i <= mid && j <= right) {

      temp[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
      System.out.println( ", temp = " + Arrays.toString(temp) + ", mid = " + mid );

    }
    while (i <= mid) {
      temp[k++] = arr[i++];
    }
    while (j <= right) {
      temp[k++] = arr[j++];
    }
    for (i = 0; i < k; i++) {
      arr[left + i] = temp[i];
    }
  }
}
