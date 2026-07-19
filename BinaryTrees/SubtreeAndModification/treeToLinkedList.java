package SubtreeAndModification;

import java.util.ArrayList;

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
public class treeToLinkedList {
    ArrayList<TreeNode> leaves = new ArrayList<>();
    public void preOrder(TreeNode root)
    {
        if(root == null) return;
        leaves.add(root);
        preOrder(root.left);
        preOrder(root.right);
    }
    // approach-1 O(n)
    public TreeNode flatten(TreeNode root)
    {
        preOrder(root);
        for(int i=0;i<leaves.size()-1;i++)
        {
            TreeNode curr = leaves.get(i);
            TreeNode nextNode = leaves.get(i+1);
            curr.left = null;
            curr.right = nextNode;
        }
        if(!leaves.isEmpty())
        {
            TreeNode lastNode = leaves.get(leaves.size()-1);
            lastNode.left = null;
            lastNode.right = null;
        }
        return root;
    }
    //Approach-2 Tree Modification on how its done
    public TreeNode flattenTreeNode(TreeNode root)
    {
        TreeNode curr = root;
        while (curr != null) {
            if(curr.left != null)
            {
                TreeNode predecessor = curr.left;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
            
        }
        return root;
    }
}
