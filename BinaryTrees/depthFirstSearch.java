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
public class depthFirstSearch {
    public void preOrder(TreeNode root)
    {
        if(root == null) return;
        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }
    public void inOrder(TreeNode root)
    {
        if(root == null) return;
        inOrder(root.left);
        System.out.println(root.data);
        inOrder(root.right);
    }
    public void postOrder(TreeNode root)
    {
        if(root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data);
    }
}
