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
public class addRowBinaryTree {
    public TreeNode dfs(TreeNode root,int val,int depth,int curr)
    {
        if(root == null) return null;
        if(curr == depth-1)
        {
            TreeNode left_temp = root.left;
            TreeNode right_temp = root.right;

            root.left = new TreeNode(val);
            root.right = new TreeNode(val);

            root.left.left = left_temp;
            root.right.right = right_temp;
        }
        dfs(root.left, val, depth, curr +1);
        dfs(root.right, val, depth, curr + 1);
        return root;
    }
    public TreeNode addOneRow(TreeNode root, int val, int depth)
    {
        if(depth == 1)
        {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return root;
        }
        int curr = 1;
        return dfs(root, val, depth,curr);
    }
}
