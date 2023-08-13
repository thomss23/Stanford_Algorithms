package org.example;

import java.util.Objects;

public class Pair {
    private int value;
    private int weight;

    public Pair(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return value == pair.value && weight == pair.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, weight);
    }
}
