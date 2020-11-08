package com.msk.home;

/**
 * 查找二叉树的最近公共祖先
 */
public class LowestCommonAncestorExample {

    /**
     * 递归大法
     * 二叉树的后续遍历+回溯
     * 时间复杂度 O(N)
     * 空间复杂度 O(N)
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNodes lowestCommonAncestor(TreeNodes root, TreeNodes p, TreeNodes q) {
        //若遇到pq则返回
        if (root == null || root == p || root == q) return root;
        //二叉树后续遍历，遇到节点pq时，从底至顶回溯
        TreeNodes left = lowestCommonAncestor(root.left, p, q);
        TreeNodes right = lowestCommonAncestor(root.right, p, q);
        //若左子树为空，1.pq在右子树，root指向pq的最近公共祖先 2.p在右子树，root指向p
        if (left == null) return right;
        //反之
        if (right == null) return left;
        //若(left && right) != null 则说明pq在左右两侧，此时返回root
        return root;
    }

    //测试案例
    public static void main(String[] args) {
        //3 56 274   108
        TreeNodes root = new TreeNodes(3);
        root.left = new TreeNodes(5);
        root.right = new TreeNodes(1);
        root.left.left = new TreeNodes(6);
        root.left.right = new TreeNodes(2);
        root.left.right.left = new TreeNodes(7);
        root.left.right.right = new TreeNodes(4);
        root.right.left = new TreeNodes(0);
        root.right.right = new TreeNodes(8);
        TreeNodes treeNodes = new LowestCommonAncestorExample().lowestCommonAncestor(root, root.left.right.left, root.left.right.right);
        System.out.println(treeNodes.val);
    }

}

class TreeNodes {

    int val;

    TreeNodes left;

    TreeNodes right;

    TreeNodes() {

    }

    TreeNodes(int val) {
        this.val = val;
    }

}


