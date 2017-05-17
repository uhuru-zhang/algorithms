package cn.zhangsiqi.algorithms.part_3;

import org.junit.Test;

import java.io.Console;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by zhixuan on 2017/5/15.
 */
public class P_12_SearchTree {
    public static void main(String[] args){
        SearchTree searchTree = new SearchTree();
        searchTree.buildTree();
        System.out.println();
    }
}

class SearchTree{
    private class Node{
        private int value;
        private Node leftChild;
        private Node rightChild;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

    }
    private Scanner scanner = new Scanner(System.in);
    private Node root = new Node();

    private void buildTree(Node root){
        int value = scanner.nextInt();
        Node node = new Node();
        if (value < 0){
            root.setLeftChild(null);
        }else {
            node.setValue(value);
            root.setLeftChild(node);
            buildTree(root.getLeftChild());
        }


        value = scanner.nextInt();
        node = new Node();
        if (value < 0){
            root.setRightChild(null);
        }else {
            node.setValue(value);
            root.setRightChild(node);
            buildTree(root.getRightChild());
        }


    }

    public void buildTree(){
        int value = scanner.nextInt();
        if (value > 0){
            root.setValue(value);
            this.buildTree(this.root);
        }
    }

    public void inOrderPrintTree(){
        this.inOrderPrintTree(this.root);
    }

    private void inOrderPrintTree(Node root) {
        if (root != null){
            inOrderPrintTree(root.getLeftChild());
            System.out.print(root.getValue() + " ");
            inOrderPrintTree(root.getRightChild());
        }
    }

    public void insert(int value){
        Node node = root;
        if (node == null){
            node.setValue(value);
            node.setLeftChild(null);
            node.setRightChild(null);
        }

        while (!(node.getLeftChild() == null && node.getRightChild() == null)){
            if (value < node.getValue()){
                node = node.getLeftChild();
                if (node == null)
                    break;
            }else if (value > node.getValue()){
                node = node.getRightChild();
                if (node == null)
                    break;
            }else {
                return;
            }
        }

        Node newNode = new Node();
        newNode.setValue(value);

        if (value < node.getValue()){
            node.setLeftChild(newNode);
        }else {
            node.setRightChild(newNode);
        }
    }

    public void delete(int value){
        Node deleteNode = root;
        Node changeNode;

        while (deleteNode != null){
            if (value < deleteNode.getValue())
                deleteNode = deleteNode.getLeftChild();
            else if (value > deleteNode.getValue())
                deleteNode = deleteNode.getRightChild();
            else
                break;
        }

        if (deleteNode.leftChild == null)
            deleteNode = deleteNode.getRightChild();
        if (deleteNode.rightChild == null)
            deleteNode = deleteNode.getLeftChild();

        if (deleteNode.getLeftChild() != null && deleteNode.getRightChild() != null){
            changeNode = deleteNode.getRightChild();
            while (changeNode.getRightChild() != null){
                changeNode = changeNode.getLeftChild();
            }

            deleteNode.setValue(changeNode.getValue());
            changeNode = changeNode.getRightChild();
        }
    }
}
//1 2 -1 -1 3 -1 4 5 -1 -1 -1