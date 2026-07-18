import java.util.ArrayList;
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
        this.left = null;
        this.right = null;
    }
}
class Solution1
{
    // Breadth First Approach
    HashMap<TreeNode,TreeNode> parentMap = new HashMap<>();
    ArrayList<TreeNode> leaves = new ArrayList<>();
    public void buildParentMap(TreeNode root)
    {
        if(root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++)
            {
                TreeNode node = queue.poll();
                if(node.left != null)
                {
                    parentMap.put(node.left,node);
                    queue.add(node.left);
                }
                if(node.right != null)
                {
                    parentMap.put(node.right, node);
                    queue.add(node.right);
                }
            }
        }
    }
    public void findLeaves(TreeNode root)
    {
        if(root == null) return;
        if(root.left == null && root.right == null)
        {
            leaves.add(root);
        }
        findLeaves(root.left);
        findLeaves(root.right);
    }
    public int countPairs(TreeNode root, int distance)
    {
        int level =0;
        int count =0;
        Queue<TreeNode> queue = new LinkedList<>();
        HashSet<TreeNode> visited = new HashSet<>();
        for(TreeNode leaf : leaves)
        {
            queue.add(leaf);
            visited.add(leaf);
            while (!queue.isEmpty() && level <= distance) {
                int size = queue.size();
                for(int i=0;i<size;i++)
                {
                    TreeNode node = queue.poll();
                    if(node != leaf && node.left == null && node.right == null)
                    {
                        count++;
                    }
                    if(node.left != null && !visited.contains(node.left))
                    {
                        queue.add(node.left);
                        visited.add(node.left);
                    }
                    if(node.right != null && !visited.contains(node.right))
                    {
                        queue.add(node.right);
                        visited.add(node.right);
                    }
                    TreeNode parent = parentMap.get(node);
                    if(parent !=null && !visited.contains(parent))
                    {
                        queue.add(parent);
                        visited.add(parent);
                    }
                }
                level++;                
            }
        }
        return count/2;
    }
    public int solve(TreeNode root,int distance)
    {
        buildParentMap(root);
        findLeaves(root);
        return countPairs(root, distance);
    }
}
class Solution2
{
    // Approach-2 DFS
    public int goodPair;
    public int goodPairCount(TreeNode root, int distance)
    {
        goodPair =0;
        dfs(root,distance);
        return goodPair;
    }
    public ArrayList<Integer> dfs(TreeNode root,int distance)
    {
        if(root == null) return new ArrayList<>();
        if(root.left == null && root.right == null)
        {
            ArrayList<Integer> ans = new ArrayList<>();
            ans.add(1);
            return ans;
        }
        ArrayList<Integer> left = dfs(root.left, distance);
        ArrayList<Integer> right = dfs(root.right,distance);

        //distance calculation
        for(Integer l : left)
        {
            for(Integer r : right)
            {
                if(l + r <= distance)
                {
                    goodPair ++;
                }
            }
        }
        // new array creation
        ArrayList<Integer> result = new ArrayList<>();
        for(Integer l : left)
        {
            int newDistance = l + 1;
            if(newDistance < distance)
            {
                result.add(newDistance);
            }
        }
        for(Integer r: right)
        {
            int newDistance = r + 1;
            if(newDistance < distance)
            {
                result.add(newDistance);
            }
        }

        return result;
    }


}
public class goodLeafPair {
    
}
