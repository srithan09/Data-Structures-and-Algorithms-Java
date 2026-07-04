import java.util.HashMap;
import java.util.HashSet;
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
public class binaryTreeInfection {
     HashMap<TreeNode,TreeNode> map = new HashMap<>();
    public void buildParentMap(TreeNode root)
    {
        if(root == null) return;
        Queue<TreeNode>queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty())
        {
            int size = queue.size();
            for(int i=0;i<size;i++)
            {
                TreeNode node = queue.poll();
                if(node.left != null)
                {
                    map.put(node.left, node);
                    queue.offer(node.left);
                }
                if(node.right != null)
                {
                    map.put(node.right, node);
                    queue.offer(node.right);
                }
            }
        }
    }
     private TreeNode findNode(TreeNode root, int val) {
        if (root == null) return null;
        if (root.data == val) return root;
        TreeNode left = findNode(root.left, val);
        return left != null ? left : findNode(root.right, val);
    }
    public int amountOfTime(TreeNode root, int start) {
        if(root == null) return 0;
        int time =0;
        buildParentMap(root);
        Queue<TreeNode> queue = new LinkedList<>();
        HashSet<TreeNode>visited = new HashSet<>();
        TreeNode begin = findNode(root,start);
        queue.offer(begin);
        visited.add(begin);
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean infected = false;
            for(int i=0;i<size;i++)
            {
                TreeNode node = queue.poll();
                // left node
                if(node.left != null && !visited.contains(node.left))
                {
                    queue.offer(node.left);
                    visited.add(node.left);
                    infected = true;
                }
                if(node.right != null && !visited.contains(node.right))
                {
                    queue.offer(node.right);
                    visited.add(node.right);
                    infected = true;
                }
                TreeNode parent  = map.get(node);
                if(parent != null && !visited.contains(parent))
                {
                    queue.offer(parent);
                    visited.add(parent);
                    infected = true;
                }
            }
            if(infected)
            {
                time++;
            }
        }
        return time;
    }
}
