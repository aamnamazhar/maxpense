/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication140;
class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}
 class BST {
    Node root;

    public BST() {
        root = null;
    }

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
 return root;
        }

        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }
        return root;
    }
    public void deleteLeafNodes() {
        root = deleteLeafNodesRec(root);
    }
    private Node deleteLeafNodesRec(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return null;
        }
        root.left = deleteLeafNodesRec(root.left);
        root.right = deleteLeafNodesRec(root.right);

        return root;
    }
    public void inorderTraversal(Node root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.key + " ");
            inorderTraversal(root.right);
        }
    } }

public class JavaApplication140 {
    public static void main(String[] args) {
          BST bst = new BST();
        bst.insert(20);
        bst.insert(10);
        bst.insert(30);
        bst.insert(5);
        bst.insert(15);
        bst.insert(25);
        bst.insert(35);

        System.out.println("Original BST:");
        bst.inorderTraversal(bst.root);

        bst.deleteLeafNodes();

        System.out.println("\nBST after deleting leaf nodes:");
        bst.inorderTraversal(bst.root);

    }
}

