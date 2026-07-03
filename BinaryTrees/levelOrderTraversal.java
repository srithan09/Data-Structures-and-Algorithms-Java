import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
public class levelOrderTraversal {
    public void bfs(TreeNode root)
    {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        queue.add(root);
        while(!queue.isEmpty())
        {
            int size = queue.size();
            ArrayList<Integer> level = new ArrayList<>();
            for(int i=0;i<size;i++)
            {
                TreeNode curr = queue.poll();
                level.add(curr.data);
                if(curr.left != null) queue.add(curr.left);
                if(curr.right != null) queue.add(curr.right);
            }
            ans.add(new ArrayList<>(level));
        }
        
    }
}
