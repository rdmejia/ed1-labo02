package ed.lab;

public class E01InvertBT {
    public TreeNode<Integer> invertTree(TreeNode<Integer> root) {
        if (root == null)
            return null;
        //Guardando los nodos en su lado opuesto
        TreeNode left= invertTree(root.left);
        TreeNode right = invertTree(root.right);

        //Invirtiendo los nodos
        root.left = right;
        root.right = left;
        return root;
    }
}
