package com.troll.algorithm.快速排序;

public class 快速排序 {


  /**
   * 思想：是从冒泡排序演化而来，采用分治方法
   *
   * 在待排序的数组选取一个元素作为基准，将待排序的元素进行分区，比基准元素大的元素放在一边，比其小的放在一边
   * 递归调用快速排序对两边的元素排序，选取基准元素并分区的过程采用双指针左右交换。
   */
  public void quickSort(int[] arr) {
    quickSort(arr, 0, arr.length - 1);
  }

  private void quickSort(int[] arr, int low, int high) {
    if (low >= high) return;

    int pivot = partition(arr, low, high);  // 将数组分成两部分
    quickSort(arr, low, pivot - 1); // 递归排序左子数组
    quickSort(arr, pivot + 1, high); // 递归排序右子数组
  }

  // 采用双循环的方式查找基准元素
  private int partition(int[] arr, int low, int high) {
    int pivot = arr[low];
    while (low < high) {
      while (low < high && arr[high] >= pivot) {
        high--;
      }
      arr[low] = arr[high];
      while (low < high && arr[low] <= pivot) {
        low++;
      }
      arr[high] = arr[low];
    }
    arr[low] = pivot;
    return low;
  }
}
