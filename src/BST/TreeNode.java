package BST;

public class TreeNode {
    public Comparable key;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(Comparable newKey) {
        this.key = newKey;
        left = right = null;
    }
    public TreeNode(Comparable newKey, TreeNode left, TreeNode right) {
        this.key = newKey;
        this.left = left;
        this.right = right;
    }
}
