package org.example;

public class Symbol implements Comparable<Symbol> {

    private long weight;

    private Symbol left;

    private Symbol right;

    public Symbol(long weight) {
        this.weight = weight;

    }
    public void setLeft(Symbol left) {
        this.left = left;
    }

    public void setRight(Symbol right) {
        this.right = right;
    }

    public Symbol getLeft() {
        return left;
    }

    public Symbol getRight() {
        return right;
    }

    public long getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Symbol o) {
        return Long.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return "Symbol{" +
                ", weight=" + weight +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
