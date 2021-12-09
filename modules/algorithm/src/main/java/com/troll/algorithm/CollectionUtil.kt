package com.troll.algorithm

import com.troll.algorithm.二叉树.TreeNode
import java.util.*

object CollectionUtil {

  private val list = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")

  fun printArray(arr: IntArray) {
//    println("当前数组${arr.contentToString()}")
  }

  fun binaryTree(): TreeNode {
    val linkedList = LinkedList<String>()
    list.forEach {
      linkedList.add(it)
    }
    return linkedList.createBinaryTree()
  }

  private fun LinkedList<String>.createBinaryTree(): TreeNode {
    val first = this.removeFirst()
    val node = TreeNode(first)
    if (first != null) {
      node.left = this.createBinaryTree()
      node.right = this.createBinaryTree()
    }
    return node
  }
}


