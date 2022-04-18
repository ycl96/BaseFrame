package com.ycl.service;

import javafx.beans.binding.ObjectBinding;

import java.util.*;

/**
 * @author : YangChunLong
 * @date : Created in 2021/9/25 12:05
 * @description:
 * @modified By:
 * @version: :
 */
public class BinTree {
    private Object data;
    private BinTree root;
    private BinTree left;
    private BinTree right;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BinTree getRoot() {
        return root;
    }

    public void setRoot(BinTree root) {
        this.root = root;
    }

    public BinTree getLeft() {
        return left;
    }

    public void setLeft(BinTree left) {
        this.left = left;
    }

    public BinTree getRight() {
        return right;
    }

    public void setRight(BinTree right) {
        this.right = right;
    }

    public BinTree(Object data) {
        this.data = data;
    }

    public BinTree() {

    }

    public void create(Object[] data) {
        List<BinTree> binTrees = new ArrayList<>();
        BinTree binTree;
        for (int i = 0; i < data.length; i++) {
            binTree = new BinTree(data[i]);
            binTrees.add(binTree);
        }
        this.data = data[0];
        for (int i = 0; i < binTrees.size() / 2; i++) {
            if (i * 2 + 1 <= binTrees.size()) {
                if (i == 0) {
                    left = binTrees.get(1);
                }
                binTrees.get(i).setLeft(binTrees.get(i * 2 + 1));
            }
            if (i * 2 + 2 < binTrees.size()) {
                if (i == 0) {
                    right = binTrees.get(2);
                }
                binTrees.get(i).setRight(binTrees.get(i * 2 + 2));
            }
        }
    }

    public List<Object> DFS() {
        List<Object> list = new ArrayList<>();
        if (null == data) {
            return list;
        }
        Queue<BinTree> queue = new LinkedList<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            BinTree tree = queue.poll();
            if (tree.getLeft() != null) {
                queue.offer(tree.getLeft());
            }
            if (tree.getRight() != null) {
                queue.offer(tree.getRight());
            }
            list.add(tree.getData());
        }
        return list;
    }

    public List<Object> guangdu(){
        List<Object> guangduList = new ArrayList<>();
        Queue<BinTree> queue = new LinkedList<>();
        queue.offer(this);
        while (!queue.isEmpty()){
            BinTree binTree = queue.poll();
            guangduList.add(binTree.getData());
            if (null != binTree.getLeft()){
                queue.offer(binTree.getLeft());
            }
            if (null != binTree.getRight()){
                queue.offer(binTree.getRight());
            }
        }
        return guangduList;
    }
    List<Object> list = new ArrayList<>();

    public List<Object> preSearch(BinTree binTree) {

        if (null != binTree) {
            list.add(binTree.getData());
            preSearch(binTree.left);
            preSearch(binTree.right);
        }
        return list;
    }

    public List<Object> preSearch(){
        List<Object> preList = new ArrayList<>();
        Stack<BinTree> stack = new Stack<BinTree>();
        BinTree binTree = this;
        while (null != binTree || !stack.isEmpty()){
            if (null != binTree){
                preList.add(binTree.getData());
                stack.push(binTree);
                binTree = binTree.getLeft();
            }else {
                BinTree binTree1 = stack.pop();
                binTree = binTree1.getRight();
            }
        }
        return preList;
    }

    List<Object> middleList = new ArrayList<>();
    public List<Object> middleSearch (BinTree binTree){
        if (null != binTree){
            middleSearch(binTree.getLeft());
            middleList.add(binTree.getData());
            middleSearch(binTree.getRight());
        }
        return middleList;
    }

    public List<Object> middleSearch (){
        List<Object> objects = new ArrayList<>();
        Stack<BinTree> stack = new Stack<>();
        BinTree binTree = this;
        while (null != binTree || !stack.isEmpty()){
            if (null != binTree){
                stack.push(binTree);
                binTree = binTree.getLeft();
            }else {
                BinTree binTree1 = stack.pop();
                objects.add(binTree1.getData());
                binTree = binTree1.getRight();
            }
        }
        return objects;
    }

    public List<Object> behindList = new ArrayList<>();
    public List<Object> behindSearch (BinTree binTree){
        if (null != binTree){
            behindSearch(binTree.getLeft());
            behindSearch(binTree.getRight());
            behindList.add(binTree.getData());
        }
        return behindList;
    }

    public List<Object> behindSearch (){
        List<Object> objects = new ArrayList<>();
        Stack<BinTree> stack = new Stack<>();
        BinTree binTree = this;
        List<BinTree> opList = new ArrayList<>();
        while (null != binTree || !stack.isEmpty()){
            if (null != binTree){
                stack.push(binTree);
                binTree = binTree.getLeft();
            }else {
                BinTree binTree1 = stack.pop();
                binTree = binTree1.getRight();
                if (null != binTree){
                    if (!objects.contains(binTree.getData())){
                        stack.push(binTree1);
                    }
                }else {
                    stack.push(binTree1);
                }
                if (null == binTree){
                    objects.add(binTree1.getData());
                    stack.pop();
                }
            }
        }
        return objects;
    }
    public static void main(String[] args) {
        BinTree binTree = new BinTree();
        Object[] datas = {1, 2, 5, 6, 3, 10};
        binTree.create(datas);
        List<Object> list = binTree.DFS();
        List<Object> listPre = binTree.preSearch(binTree);
        List<Object> listPre1 = binTree.preSearch();
        System.out.println(list.toString());
        System.out.println(listPre.toString());
        System.out.println(listPre1.toString());

        List<Object> middleList = binTree.middleSearch(binTree);
        System.out.println("中序遍历(递归）："+middleList.toString());

        middleList = binTree.middleSearch();
        System.out.println("中序遍历(非递归）："+middleList.toString());


        List<Object> behindList = binTree.behindSearch(binTree);
        System.out.println("后序遍历（递归）："+behindList.toString());

        behindList = binTree.behindSearch();
        System.out.println("后序遍历（递归）："+behindList.toString());
    }
}
