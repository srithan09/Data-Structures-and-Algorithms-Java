package SubtreeAndModification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
public class returnForest {
    public TreeNode delHelper(TreeNode root,HashSet<Integer> toDelete,List<TreeNode> forest)
    {
        if(root == null) return null;
        root.left  = delHelper(root.left, toDelete,forest);
        root.right = delHelper(root.right, toDelete,forest);
        if(toDelete.contains(root.data))
        {
            if(root.left != null)
            {
                forest.add(root.left);
            }
            if(root.right != null)
            {
                forest.add(root.right);
            }
            return null;
        }
        return root;
    }
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> forest = new ArrayList<>();
        HashSet<Integer> toDelete = new HashSet<>();
        for(int i : to_delete)
        {
            toDelete.add(i);
        }
        delHelper(root,toDelete,forest);
        if (!toDelete.contains(root.data)) {
            forest.add(root);
        }
        return forest;
    }
}
