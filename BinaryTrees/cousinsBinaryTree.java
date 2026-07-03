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
public class cousinsBinaryTree {
    // LeetCode 993 cousins in binary tree -  boolean function to just say it's  x and y are cousins are not
    public boolean cousinsValidation(TreeNode root,int x,int y)
    {
        if(root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode parentX = null;
            TreeNode parentY = null;
            for(int i=0;i<size;i++)
            {
                TreeNode node = queue.poll();
                if(node.left != null)
                {
                    if(node.left.data == x)
                    {
                        parentX = node;
                    }
                    if(node.left.data == y)
                    {
                        parentY = node;
                    }
                }
                if(node.right != null)
                {
                    if(node.right.data == x)
                    {
                        parentX = node;
                    }
                    if(node.right.data == y)
                    {
                        parentY = node;
                    }
                }
                if(parentX != null && parentY != null)
                {
                    return parentX != parentY;
                }
                if(parentX != null || parentY != null)
                {
                    return false;
                }
            }
        }

        return false;
    }

    // LeetCode cousins in binary tree 2 replacing the value of the tree
   /*
   Approach 1 two pass solution with the help 
   Time complexity - O(n) + O(n)
   Space Complexity - O(n)
    */
    public TreeNode replaceValuNode(TreeNode root)
    {
        if(root == null) return root;
        Queue<TreeNode> levels = new  LinkedList<>();
        ArrayList<Integer> levelSum = new ArrayList<>();
        levels.offer(root);
        // Calculating the level sum for each level
        while(!levels.isEmpty())
        {
            int sum =0;
            int size = levels.size();
            for(int i=0;i<size;i++)
            {
                TreeNode curr = levels.poll();
                sum = sum + curr.data;
                levelSum.add(sum);
                if(curr.left != null)
                {
                    levels.offer(curr.left);
                }
                if(curr.right != null)
                {
                    levels.offer(curr.right);
                }
            }
        }
        // Step -2 of calculating the sibling sum and updating the node value
        levels.offer(root);
        root.data = 0;
        int j = 1; // used to access the value from the arraylist so the root value is of no use
        while (!levels.isEmpty()) {
            int size = levels.size();
            for(int i=0;i<size;i++)
            {
                TreeNode curr = levels.poll();
                int siblingSum =0;
                if(curr.left != null)
                {
                    siblingSum = siblingSum + curr.left.data;
                }
                if(curr.right != null)
                {
                    siblingSum = siblingSum + curr.right.data;
                }
                if(curr.left != null)
                {
                    curr.left.data = levelSum.get(j) - siblingSum;
                    levels.offer(curr.left);
                }
                if(curr.right != null)
                {
                    curr.right.data = levelSum.get(j) - siblingSum;
                    levels.offer(curr.right);
                }
            }
            j++;
        }

        return root;
    }

    /*
    Approach-2 one pass solution
    Calculating the level sum  and sibling sum at the same time 
    */
   public TreeNode replcaeValueNodes(TreeNode root)
   {
    if(root == null) return root;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    int levelSum = root.data;
    while (!queue.isEmpty()) {
        int size = queue.size();
        int nextLevelSum =0;
        for(int i=0;i<size;i++)
        {
            TreeNode node = queue.poll();
            node.data = levelSum - node.data;
            int siblingSum = (node.left != null ? node.left.data : 0 );
            siblingSum += (node.right != null ? node.right.data : 0);

            if(node.left != null)
            {
                nextLevelSum += node.left.data;
                node.left.data = siblingSum;
                queue.offer(node.left);
            }
            if(node.right != null)
            {
                nextLevelSum += node.right.data;
                node.right.data = siblingSum;
                queue.offer(node.right);
            }
        }
        levelSum = nextLevelSum;
    }
    return root;
   }
}
