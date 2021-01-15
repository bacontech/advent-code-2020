package com.bacontech.helpers;


import java.util.ArrayList;
import java.util.List;

public class Tuple2<T, U> {
    protected final T t;
    protected final U u;

    public Tuple2(T t, U u) {
        this.t = t;
        this.u = u;
    }

    /**
     * get the first value of the tuple
     * @return Type t
     */
    public T getFirst() {
        return t;
    }

    /**
     * get the second value of the tuple
     * @return Type U
     */
    public U getSecond() {
        return u;
    }


    /**
     * Similar to Python's <code>zip</code> function
     * Takes two lists and returns a list of tuples
     * containing each items of the lists up to the
     * number of items in the shortest list
     * so listA = { 1, 2, 3 } and listB = { 4, 5, 6, 7 }
     * will return { { 1, 4 } , { 2, 5 }, { 3, 6 }}
     * lists can be different object types.
     * @param first first list of items
     * @param second second list of items
     * @param <E> first object type
     * @param <V> second object type
     * @return list of tuples of objects E, V
     */
    public static <E, V> List<Tuple2<E, V>> zip(E[] first, V[] second) {
        int shortest = Math.min(first.length, second.length);
        List<Tuple2<E, V>> results = new ArrayList<>();
        for (int i = 0; i < shortest; i++) {
            results.add(new Tuple2<>(first[i], second[i]));
        }
        return results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple2)) return false;

        Tuple2<?, ?> tuple2 = (Tuple2<?, ?>) o;

        if (t != null ? !t.equals(tuple2.t) : tuple2.t != null) return false;
        return u != null ? u.equals(tuple2.u) : tuple2.u == null;

    }

    @Override
    public int hashCode() {
        int result = t != null ? t.hashCode() : 0;
        result = 31 * result + (u != null ? u.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{ " +
            t +
            ", " + u +
            " }";
    }
}
