package com.troll.algorithm.二叉树;

public class TreeNode {
  TreeNode left;
  TreeNode right;
  String value;

  public TreeNode(TreeNode left, TreeNode right, String value) {
    this.left = left;
    this.right = right;
    this.value = value;
  }

  public TreeNode(String value) {
    this.value = value;
  }

  public TreeNode getLeft() {
    return left;
  }

  public void setLeft(TreeNode left) {
    this.left = left;
  }

  public TreeNode getRight() {
    return right;
  }

  public void setRight(TreeNode right) {
    this.right = right;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
