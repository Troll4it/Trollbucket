package com.troll.algorithm.桶排序;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * 找出待排序数组中最大值max、最小值、数组ArrayList作为桶，桶里放元素用ArrayList存储
 * 计算每个元素arr[i]放的桶，每个桶各自排序，遍历桶数组，把排好序的元素放进输出数组中
 * <p>
 * 1. 求数列最大值、最小值
 * 2. 创建空桶
 * 3. 把原始数列的元素分配到各个桶中
 * 4. 在每个桶内部做排序，在元素分布相对均匀的情况下
 * 5. 输出排列数组
 *
 * 平均时间复杂度：0（n） \ 空间复杂度： 0（n）
 */
public class 桶排序 {

  public static void bucketSort(int[] arr) {
    int min = arr[0];
    int max = arr[0];

    for (int i = 0; i < arr.length; i++) {
      int arryI = arr[i];
      if (arryI < min) {
        min = arryI;
      }
      if (arryI > max) {
        max = arryI;
      }
    }

    // 桶数
    int bucketNum = (max - min) / arr.length - 1;
    ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);

    for (int i = 0; i < bucketNum; i++) {
      bucketArr.add(new ArrayList<>());
    }

    // 将每个元素放入桶内
    for (int i = 0; i < arr.length; i++) {
      int num = (arr[i] - min) / arr.length;
      bucketArr.get(num).add(arr[i]);
    }

    // 对桶进行排序
    for (int i = 0; i < bucketArr.size(); i++) {
      Collections.sort(bucketArr.get(i));
      for (int j = 0; j < bucketArr.get(i).size(); j++) {
        arr[j] = bucketArr.get(i).get(j);
      }
    }
  }
}
