学习笔记
/**
 * 寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方
 *
 * @param nums 半有序数组
 * @return 无序元素在半有序数组中的下标区间
 */
 
 
    public String disorderSearch(int[] nums) {
        int n = nums.length;
        //处理数组长度为0、1、2的情况
        if (n == 0 || n == 1) return "无";
        if (n == 2) return nums[0] < nums[1] ? "0-1" : "无";
        //输出的左右区间
        String l = "", r = "";
        //定义左中右边界属性
        int left = 0, right = n - 1, mid;
        //旋转后的数组左右两边必有一边为有序的
        while (left <= right) {
            mid = (left + right) / 2;
            //因为原数组本身是递增的，故只需判断中位数是否小于它的后一位数，大于则为无序区间
            if (nums[mid] > nums[mid + 1]) {
                l += mid;
                r += mid + 1;
            }
            //若左边有序，则无序区间在右边，左边界+1
            if (nums[0] <= nums[mid]) {
                left = mid + 1;
            } else {
            //若右边有序，则无序区间在左边，右边界-1
                right = mid - 1;
            }
        }
        return l + "-" + r;
    }

二分查找代码模板

    public int binarySearch(int[] array, int target) {
        int left = 0, right = array.length - 1, mid;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

DFS代码模板

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> allResults = new ArrayList<>();
        if(root==null){
            return allResults;
        }
        travel(root,0,allResults);
        return allResults;
    }


    private void travel(TreeNode root,int level,List<List<Integer>> results){
        if(results.size()==level){
            results.add(new ArrayList<>());
        }
        results.get(level).add(root.val);
        if(root.left!=null){
            travel(root.left,level+1,results);
        }
        if(root.right!=null){
            travel(root.right,level+1,results);
        }
    }
    
BFS代码模板

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    
        TreeNode(int x) {
            val = x;
        }
    }
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> allResults = new ArrayList<>();
        if (root == null) {
            return allResults;
        }
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            List<Integer> results = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodes.poll();
                results.add(node.val);
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
            }
            allResults.add(results);
        }
        return allResults;
    }