package org.ivzh.arrays;

import java.util.*;

public class DynamicArray<T> implements List<T> {

    private int size;
    private Object[] holder;

    public DynamicArray() {
        this.holder = new Object[10];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object oo : holder)
            if (o.equals(oo)) {
                return true;
            }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("todo");
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Object o : holder) {
            result[i++] = o;
        }
        return result;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException("todo");
    }

    @Override
    public boolean add(T t) {
        sanityIncreaseSize();
        holder[size++] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {

        int index = indexOf(o);
        if (index == -1) {
            return false;
        }

        int j = index;
        for (int i = index+1; i < size; i++ ) {
            holder[j] = holder[i];
            j = i;
        }
        holder[size--] = null;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null || c.isEmpty()) {
            return false;
        } else {
            for (T e : c) {
                add(e);
            }
            return true;
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        if (index > size) {
            throw new IllegalArgumentException("index is too big");
        }
        return (T) holder[index];
    }

    @Override
    public T set(int index, T element) {
        T old = get(index);
        holder[index] = element;
        return old;
    }

    @Override
    public void add(int index, T element) {
        sanityIncreaseSize();
        Object temp = get(index);
        for (int i = index + 1; i < size; i++) {
            Object temp1 = holder[i];
            holder[i] = temp;
            temp = temp1;
        }
    }

    @Override
    public T remove(int index) {
        T el = get(index);
        int j = index;
        for (int i = index+1; i < size; i++ ) {
            holder[j] = holder[i];
            j = i;
        }
        holder[size--] = null;
        return el;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(holder[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    private void sanityIncreaseSize() {
        if (holder.length * 0.75 <= size) {
            holder = Arrays.copyOf(holder, size*2);
        }
    }
}
