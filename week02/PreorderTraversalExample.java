package com.msk.home;

import java.util.ArrayList;
import java.util.List;

public class PreorderTraversalExample {

    /**
     * 二叉树前、中、后序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    /**
     * 前序遍历
     *
     * @param root
     * @param res
     */
    public void preorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        //根
        res.add(root.val);
        //左
        preorder(root.left, res);
        //右
        preorder(root.right, res);
    }

    /**
     * 中序遍历
     *
     * @param root
     * @param res
     */
    public void inorder(TreeNode root, List res) {
        if (root == null) return;
        //左
        inorder(root.left, res);
        //根
        res.add(root.val);
        //右
        inorder(root.right, res);
    }

    /**
     * 后序遍历
     *
     * @param root
     * @param res
     */
    public void postorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        //左
        postorder(root.left, res);
        //右
        postorder(root.right, res);
        //根
        res.add(root.val);
    }


    public static void main(String[] args) {
        //1423
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> list = new PreorderTraversalExample().preorderTraversal(root);
        System.out.println(list);
    }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {

    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
