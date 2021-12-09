package com.troll.algorithm.二分法;

public class 二分法 {


  /**
   * 利用二分法查找相对应的数
   *
   * @param sortedArr
   * @param num
   * @return
   */


  public boolean method1(int[] sortedArr, int num) {

    if (sortedArr == null || sortedArr.length == 0) return false;

    int l = 0;
    int r = sortedArr.length - 1;
    int mid = 0;

    while (l < r) {

      // 这里不使用 （r+l）/2 会溢出
      // 因为
      // 可用 L + （r- l）
      mid = l + ((r - l) >> 1);
      if (sortedArr[mid] == num) {
        return true;

      } else if (sortedArr[mid] > num) {
        r = mid - 1; // 从左边开始
      } else if (sortedArr[mid] < num) {
        l = mid + 1; // 从右边开始
      }
    }
    return sortedArr[l] == num;
  }
}
