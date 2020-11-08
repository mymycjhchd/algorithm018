package com.msk.home;


public class BuildTreeExample {

    /**
     * 从前序与中序遍历数组结果构造二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNodes1 buildTree(int[] preorder, int[] inorder) {
        return _buildTree(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNodes1 _buildTree(int[] preorder, int p_start, int p_end, int[] inorder, int i_start, int i_end) {
        //preorder或者inorder任意一个递归完就返回Null
        if (p_start == p_end) return null;
        //前序遍历的第0个元素就是树的根节点
        int root_val = preorder[p_start];
        //初始化根节点
        TreeNodes1 root = new TreeNodes1(root_val);
        //保存中序遍历根节点的数组下标
        int i_root_index = 0;
        //找到中序遍历数组中的根节点元素的下标，就能分出左子树节点和右子树节点
        for (int i = i_start; i < i_end; i++) {
            if (inorder[i] == root_val) {
                i_root_index = i;
                break;
            }
        }
        //左子树元素个数
        int leftNum = i_root_index - i_start;
        //递归构造左子树
        root.left = _buildTree(preorder, p_start + 1, leftNum + 1 + p_start, inorder, i_start, i_start + leftNum);
        //递归构造右子树
        root.right = _buildTree(preorder, 1 + leftNum + p_start, p_end, inorder, i_start + 1 + leftNum, i_end);
        return root;
    }


    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNodes1 treeNodes = new BuildTreeExample().buildTree(preorder, inorder);
        System.out.println(treeNodes);
    }


}

class TreeNodes1 {
    int val;
    TreeNodes1 left;
    TreeNodes1 right;

    TreeNodes1() {

    }

    TreeNodes1(int x) {
        val = x;
    }
}