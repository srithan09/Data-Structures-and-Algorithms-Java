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
   public TreeNode pruneTree(TreeNode root)
   {
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
    if(root.left == null && root.right == null && root.data ==0) return null;
    return root;
   }
   public boolean isOnePresent(TreeNode root)
   {
    if(root == null) return false;
    if(root.data == 1) return true;
    return isOnePresent(root.left) || isOnePresent(root.right);
   }

   // Approach - O(N) with the help of postOrder Traversal
   public TreeNode treePrune(TreeNode root)
   {
    if(root == null) return root;
    //post Order left -> right -> root
    root.left = treePrune(root.left);
    root.right = treePrune(root.right);
    if(root.right == null && root.left == null && root.data ==0) return null; 
    return root;
   }
}
