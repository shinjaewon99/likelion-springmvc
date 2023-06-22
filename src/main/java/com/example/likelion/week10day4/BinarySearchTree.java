package com.example.likelion.week10day4;

public class BinarySearchTree {
    private static class Node {
        private int key;
        private Node left; // 왼쪽 자식을 가르키는 노드
        private Node right; // 오른쪽 자식을 가르키는 노드

        public Node(int key) {
            this.key = key;
            left = null;
            right = null;
        }
    }

    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    // 삽입 메소드
    public void insert(int key) {
        root = insertNode(root, key);
    }

    // 삽입 메소드에서 재귀호출할 메소드 (루트 노드부터 시작할 진입점)
    public Node insertNode(Node node, int key) {
        // node가 null -> 부모 노드의 자식 노드가 null 이였다 (존재 X)
        // 여기에 삽입(추가) 한다.
        if (node == null) {
            node = new Node(key); // node 할당
            return node;
        }

        if (node.key == key) {
            // 탐색에 성공한 경우 더 확인하지 않는다. (break)
            return node;
        }

        // 현재 노드보다 데이터가 작을경우는 왼쪽 트리로 이동 (재귀 호출)
        if (key < node.key) {
            node.left = insertNode(node.left, key);
        }

        // 현재 노드보다 데이터가 클 경우 오른쪽 트리로 이동 (재귀 호출)
        else if (key > node.key) {
            node.right = insertNode(node.right, key);
        }

        // 삽입이 일어나지 않았을 경우, 본래의 자식 노드를 그대로 반환
        return node;
    }


    // 탐색 메소드
    // (갱신이 발생하지않으며, 발견하면 true, 발견하지 못하면 false 인 논리값을 반환한다)
    public boolean search(int key) {
        return searchNode(root, key);
    }

    // 탐색 재귀함수
    private boolean searchNode(Node node, int key) {
        // 현재 노드가 null 인경우
        if (node == null) {
            return false;
        }
        // 탐색 성공인 경우
        if (key == node.key) {
            return true;
        }

        // 재귀 호출
        // 현재 노드보다 데이터가 더 작을 경우
        if (key < node.key) {
            // 왼쪽 서브트리 탐색 결과 반환
            return searchNode(node.left, key);
        }
        // 현재 노드보다 데이터가 더 클 경우
        else {
            // 오른쪽 서브트리 탐색 결과 반환
            return searchNode(node.right, key);
        }
    }

    // 중위 순회
    public void inorderTraversal() {
        inorder(root);
    }

    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.key + " ");
            inorder(node.right);
        }
    }

    public static void main(String[] args) {
        int[] data = {50, 30, 70, 20, 40, 60, 80};
        BinarySearchTree bst = new BinarySearchTree();
        for (int i = 0; i < data.length; i++) {
            bst.insert(data[i]);
        }

        System.out.println("중위 순회");
        bst.inorderTraversal();
        System.out.println();

        System.out.println(bst.search(40));
        System.out.println(bst.search(80));
    }
}
