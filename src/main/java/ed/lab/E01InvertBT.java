package ed.lab;
//
public class E01InvertBT {
    public TreeNode<Integer> invertTree(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }

        // Se intercambian los nodos hijos
        TreeNode<Integer> temp = root.left;
        root.left = root.right;
        root.right = temp;

        // Se llama recursivmente a los subarboles
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
