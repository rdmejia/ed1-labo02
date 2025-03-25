package ed.lab;

import java.util.Comparator;
import java.util.Comparator;

public class E03AVLTree<T> {

    private final Comparator<T> comparator;
    private TreeNode<T> root;

    public E03AVLTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void insert(T value, TreeNode<Integer> ignored) {
        root = insertarRecursivo(root, value);
    }

    public void delete(T value) {
        root = deleteRecursivo(root, value);
    }

    public T search(T value) {
        TreeNode<T> node = searchRecursive(root, value);
        return node != null ? node.value : null;
    }

    public int height() {
        return calcularAltura(root);
    }

    public int size() {
        return calculateSize(root);
    }

    private TreeNode<T> insertarRecursivo(TreeNode<T> node, T value) {
        if (node == null) return new TreeNode<>(value);

        if (comparator.compare(value, node.value) < 0) {
            node.left = insertarRecursivo(node.left, value);
        } else if (comparator.compare(value, node.value) > 0) {
            node.right = insertarRecursivo(node.right, value);
        } else {
            return node;
        }

        return balance(node);
    }

    private TreeNode<T> deleteRecursivo(TreeNode<T> node, T value) {
        if (node == null) return null;

        if (comparator.compare(value, node.value) < 0) {
            node.left = deleteRecursivo(node.left, value);
        } else if (comparator.compare(value, node.value) > 0) {
            node.right = deleteRecursivo(node.right, value);
        } else {

            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            TreeNode<T> min = getMin(node.right);
            node.value = min.value;
            node.right = deleteRecursivo(node.right, min.value);
        }

        return balance(node);
    }

    private TreeNode<T> searchRecursive(TreeNode<T> node, T value) {
        if (node == null) return null;

        int cmp = comparator.compare(value, node.value);
        if (cmp == 0) return node;
        else if (cmp < 0) return searchRecursive(node.left, value);
        else return searchRecursive(node.right, value);
    }

    private int calcularAltura(TreeNode<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(calcularAltura(node.left), calcularAltura(node.right));
    }

    private int getBalance(TreeNode<T> node) {
        if (node == null) return 0;
        return calcularAltura(node.left) - calcularAltura(node.right);
    }

    private TreeNode<T> balance(TreeNode<T> node) {
        int balance = getBalance(node);

        // Left heavy
        if (balance > 1) {
            if (getBalance(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }

        // Right heavy
        if (balance < -1) {
            if (getBalance(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        return node;
    }

    private TreeNode<T> rotateRight(TreeNode<T> y) {
        TreeNode<T> x = y.left;
        TreeNode<T> temp = x.right;

        x.right = y;
        y.left = temp;

        return x;
    }

    private TreeNode<T> rotateLeft(TreeNode<T> x) {
        TreeNode<T> y = x.right;
        TreeNode<T> temp = y.left;

        y.left = x;
        x.right = temp;

        return y;
    }

    private TreeNode<T> getMin(TreeNode<T> node) {
        TreeNode<T> current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private int calculateSize(TreeNode<T> node) {
        if (node == null) return 0;
        return 1 + calculateSize(node.left) + calculateSize(node.right);
    }
}
