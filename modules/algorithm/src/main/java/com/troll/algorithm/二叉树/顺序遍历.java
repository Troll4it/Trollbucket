package com.troll.algorithm.二叉树;

import com.troll.algorithm.CollectionUtil;

import java.util.Stack;

public class 顺序遍历 {

  /**
   * 递归版本
   */
  public void preTraverse(TreeNode root) {
    if (root == null) return;
    System.out.println(root.value);
    preTraverse(root.left);
    preTraverse(root.right);
  }

  public void inTraverse(TreeNode root) {
    if (root == null) return;
    inTraverse(root.left);
    System.out.println(root.value);
    inTraverse(root.right);
  }

  public void postTraverse(TreeNode root) {
    if (root == null) return;
    postTraverse(root.left);
    postTraverse(root.right);
    System.out.println(root.value);
  }

  /**
   * 非递归版本
   */

  public static void preOrderTraverse(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode treeNode = root;

    while (treeNode != null || !stack.isEmpty()) {
      // 方法节点的左孩子入栈
      while (treeNode != null) {
        System.out.println(treeNode.value);
        stack.push(treeNode);
        treeNode = treeNode.left;
      }
      // 如果节点没有左孩子则弹出栈顶节点，访问节点右孩子
      if (!stack.isEmpty()) {
        treeNode = stack.pop();
        treeNode = treeNode.right;
      }
    }
  }

  public static void main(String[] args) {
    TreeNode treeNode = CollectionUtil.INSTANCE.binaryTree();
    preOrderTraverse(treeNode);
  }

}
