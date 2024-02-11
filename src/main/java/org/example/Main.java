package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyTree<Integer, Integer> tree = new MyTree<>();

        for (int i = 0; i < 7; i++) {
            tree.add(new Random().nextInt(1,20), 0);
        }

        printTreeColors(tree);
        printTreeKeys(tree);

        //Deleting
        System.out.println("\nDeleting");
        tree = new MyTree<>();
        tree.add(12, 34);
        tree.add(2, 10);
        tree.add(4, 6);
        tree.add(20, 6);
        tree.add(1, 67);
        tree.add(60, 12);
        tree.add(2, 7);

        System.out.println("It was");
        printTreeKeys(tree);
        tree.remove(20);

        printTreeKeys(tree);

        System.out.println();
        System.out.println("ket 20: " + tree.get(20));
        System.out.println("ket 2: " + tree.get(2));
    }

    static void printTreeKeys(MyTree tree){
        try{
            System.out.print("\t\t\t" + tree.getRoot().getKey());
        }catch (NullPointerException e){
            System.out.print("\t\t\tnull");
        }
        System.out.println();
        try{
            System.out.print("\t\t" + tree.getRoot().getLeftChild().getKey());
        }catch (NullPointerException e){
            System.out.print("\t\tnull");
        }
        try{
            System.out.print("\t\t" + tree.getRoot().getRightChild().getKey());
        }catch (NullPointerException e){
            System.out.print("\t\tnull");
        }
        System.out.println();
        try{
            System.out.print("\t" + tree.getRoot().getLeftChild().getLeftChild().getKey());
        }catch (NullPointerException e){
            System.out.print("\tnull");
        }
        try{
            System.out.print("\t" + tree.getRoot().getLeftChild().getRightChild().getKey());
        }catch (NullPointerException e){
            System.out.print("\tnull");
        }
        System.out.print("\t");
        try{
            System.out.print("\t" + tree.getRoot().getRightChild().getLeftChild().getKey());
        }catch (NullPointerException e){
            System.out.print("\tnull");
        }
        try{
            System.out.print("\t" + tree.getRoot().getRightChild().getRightChild().getKey());
        }catch (NullPointerException e){
            System.out.print("\tnull");
        }
        System.out.println();
    }

    static void printTreeColors(MyTree tree){
        try{
            System.out.print("\t\t\t" + tree.getRoot().getColor().getRed());
        }catch (NullPointerException e){
            System.out.print("\t\t\tnull");
        }

        System.out.println();

        try{
            System.out.print("\t\t" + tree.getRoot().getLeftChild().getColor().getRed());
        }catch (NullPointerException e){
            System.out.print("\t\tnull");
        }
        try{
            System.out.print("\t\t" + tree.getRoot().getRightChild().getColor().getRed());
        }catch (NullPointerException e){
            System.out.print("\t\tnull");
        }

        System.out.println();

        try{
            System.out.print("\t" + tree.getRoot().getLeftChild().getLeftChild().getColor().getRed());
        }catch (NullPointerException e){
            System.out.print("\tnull");
        }
        try{
            System.out.print("\t" + tree.getRoot().getLeftChild().getRightChild().getColor().getRed());
        }catch (NullPointerException e){
            System.out.print("\tnull");
        }
        System.out.print("\t");
        try{
            System.out.print("\t" + tree.getRoot().getRightChild().getLeftChild().getColor().getRed());
        }catch (NullPointerException e){
            System.out.print("\tnull");
        }
        try{
            System.out.print("\t" + tree.getRoot().getRightChild().getRightChild().getColor().getRed());
        }catch (NullPointerException e){
            System.out.print("\tnull");
        }

        System.out.println();
    }
}