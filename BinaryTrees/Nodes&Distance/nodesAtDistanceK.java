import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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

public class nodesAtDistanceK {
    HashMap<TreeNode,TreeNode> map = new HashMap<>();
    public void parentMap(TreeNode root)
    {
        if(root == null) return;
        Queue<TreeNode> queue  = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
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

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k)
    {
        int distance =0;
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        HashSet<TreeNode> visted = new HashSet<>();
        parentMap(root);
        queue.offer(target);
        visted.add(target);
        while (!queue.isEmpty()) {
            if(distance == k)
            {
                while (!queue.isEmpty()) {
                    ans.add(queue.poll().data);
                }
            }
            int size = queue.size();
            for(int i=0;i<size;i++)
            {
                TreeNode node = queue.poll();
                if(node.left != null && !visted.contains(node.left))
                {
                    visted.add(node.left);
                    queue.offer(node.left);
                }
                if(node.right != null && !visted.contains(node.right))
                {
                    visted.add(node.right);
                    queue.offer(node.right);
                }
                TreeNode parent = map.get(node);
                if(parent != null && !visted.contains(parent))
                {
                    visted.add(parent);
                    queue.offer(parent);
                }
            }
            distance++;
            
        }
        return ans;
    }
}
