package com.troll.algorithm.leetCode.n106;import com.troll.algorithm.二叉树.TreeNode;import java.util.HashMap;import java.util.Map;/** * author : TangPeng * date : 11/12/22 21:35 * description : */public class 从中序和后序遍历序列构造二叉树 {    //必须要有中序遍历，有了中序可以将二叉树分为左右树    /**     * 第一步：如果数组大小为零，说明是空节点     * 第二步：如果不为空，取数组的最后一个元素为节点元素     * 第三步：找到后序数组最后一个元素在中序数组的位置，作为切割点     * 第四步：切割中序数组，切成中序左数组和中序右数组     * 第五步：切割后序数组，切成后序左数组和后序右数组     * 第六步：递归处理左区间和右区间     */    Map<Integer, Integer> map;    public TreeNode buildTree(int[] inOrder, int[] postOrder) {        map = new HashMap<>();        for (int i = 0; i < inOrder.length; i++) {            map.put(inOrder[i], i);        }        return findNode(inOrder, 0, inOrder.length, postOrder, 0, postOrder.length);    }    public TreeNode findNode(int[] inOrder, int inBegin, int inEnd, int[] postOrder, int postBegin, int postEnd) {        if (inBegin >= inEnd || postBegin >= postEnd) {            return null;        }        // 第三步：找到后序数组最后一个元素在中序数组的位置，作为切割点        int rootIndex = map.get(postOrder[postEnd - 1]);        // 构造结点        TreeNode root = new TreeNode(inOrder[rootIndex]);        // 第五步：切割后序数组，切成后序左数组和后序右数组        int lengthLeft = rootIndex - inBegin;        root.left = findNode(inOrder, inBegin, rootIndex,                postOrder, postBegin, postBegin + lengthLeft);        root.right = findNode(inOrder, rootIndex + 1, inEnd,                postOrder, postBegin + lengthLeft, postEnd - 1);        return root;    }}