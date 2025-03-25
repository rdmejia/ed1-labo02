package ed.lab;

public class E02KthSmallest {
    public int count = 0;
    public int result = 0;

    public int kthSmallest(TreeNode<Integer> root, int k) {
        count=k;
        dfs(root);
        return result;
    }

    public void dfs(TreeNode<Integer> root){
        if(root==null){
            return;
        }
        dfs(root.left);
        if(count==0){
            return;
        }
        else{
            result = root.value;
            count--;
        }
        dfs(root.right);
    }

}