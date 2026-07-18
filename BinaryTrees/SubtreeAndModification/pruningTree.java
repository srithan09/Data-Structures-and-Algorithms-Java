package SubtreeAndModification;
class TreeNode
{
    int data;
    TreeNode left;
    TreeNode right;
    TreeNode(int data)
    {
        this.data = data;
    }
}
public class pruningTree {
    // Approach - 1 O(N ^ 2)
     public TreeNode pruneTree(TreeNode root) {
        if(root == null) return null;

        if(!isOnePresent(root.left))
        {
            root.left = null;
        }
        if(!isOnePresent(root.right))
        {
            root.right = null;
        }
        pruneTree(root.left);
        pruneTree(root.right);

        if(root.left == null && root.right == null && root.data == 0)
        {
            return null;
        }
        return root;

    }
    public boolean isOnePresent(TreeNode node)
    {
        if(node == null) return false;
        if(node.data == 1) return true;
        return isOnePresent(node.left) || isOnePresent(node.right);
    }
}
