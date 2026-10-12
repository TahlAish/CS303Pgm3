
import java.util.*;

public class MyArrayList<E> implements MyList<E> {

    public static final int INITIAL_CAPACITY = 5;

    private E[] data;
    private int size;

    // PRE: None
    // POST: Creates an empty array
    @SuppressWarnings("unchecked")
    public MyArrayList() {
        data = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    // PRE: An array of objects is provided
    // POST: Adds the objects to the list
    public MyArrayList(E[] objects) {
        this();

        for (int i = 0; i < objects.length; i++) {
            add(objects[i]);
        }
    }

    // PRE: Array has been created
    // POST: Makes the array bigger if needed
    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (size >= data.length) {
            E[] newData = (E[]) new Object[Math.max(INITIAL_CAPACITY, data.length * 2)];
            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }

            data = newData;
        }
    }

    // PRE: Index must be valid
    // POST: Adds an item at the given index
    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        ensureCapacity();

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = e;
        size++;
    }

    // PRE: Array has been created
    // POST: Removes all items from the array
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }

        size = 0;
    }

    // PRE: An object is provided
    // POST: Returns true if the object is found
    @Override
    public boolean contains(Object e) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(data[i], e)) {
                return true;
            }
        }

        return false;
    }

    // PRE: Index must be valid
    // POST: Returns the item at the index
    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    // PRE: An index is provided
    // POST: Checks if the index is valid
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                "Index " + index + " is not in range."
            );
        }
    }

    // PRE: Array has been created
    // POST: Returns the number of items
    @Override
    public int size() {
        return size;
    }

    // PRE: An object is provided
    // POST: Returns the first index or -1
    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(data[i], e)) {
                return i;
            }
        }

        return -1;
    }

    // PRE: An object is provided
    // POST: Returns the last index or -1
    @Override
    public int lastIndexOf(E e) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(data[i], e)) {
                return i;
            }
        }

        return -1;
    }

    // PRE: Index must be valid
    // POST: Removes the item at the index
    @Override
    public E remove(int index) {
        checkIndex(index);

        E removedItem = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;
        data[size] = null;

        return removedItem;
    }

    // PRE: Index must be valid
    // POST: Replaces the item and returns old value
    @Override
    public E set(int index, E e) {
        checkIndex(index);

        E oldItem = data[index];
        data[index] = e;

        return oldItem;
    }

    // PRE: Array has been created
    // POST: Returns the array as a string
    @Override
    public String toString() {
        String result = "[";

        for (int i = 0; i < size; i++) {
            result += data[i];

            if (i < size - 1) {
                result += ", ";
            }
        }

        result += "]";
        return result;
    }

    // PRE: Array has been created
    // POST: Removes extra empty spaces
    @SuppressWarnings("unchecked")
    public void trimToSize() {
        if (size < data.length) {
            E[] newData = (E[]) new Object[size];

            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }

            data = newData;
        }
    }

    // PRE: A collection is provided
    // POST: Returns true if all items are found
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    // PRE: A collection is provided
    // POST: Adds all items to the array
    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean changed = false;

        for (E item : c) {
            add(size, item);
            changed = true;
        }

        return changed;
    }

    // PRE: A collection is provided
    // POST: Removes matching items from the array
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;

        for (int i = 0; i < size; i++) {
            if (c.contains(data[i])) {
                remove(i);
                i--;
                changed = true;
            }
        }

        return changed;
    }

    // PRE: A collection is provided
    // POST: Keeps only items in the collection
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;

        for (int i = 0; i < size; i++) {
            if (!c.contains(data[i])) {
                remove(i);
                i--;
                changed = true;
            }
        }

        return changed;
    }

    // PRE: Array has been created
    // POST: Returns the items as an Object array
    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size];

        for (int i = 0; i < size; i++) {
            newArray[i] = data[i];
        }

        return newArray;
    }

    // PRE: An array is provided
    // POST: Copies the items into an array
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) java.lang.reflect.Array.newInstance(
                a.getClass().getComponentType(), size
            );
        }

        for (int i = 0; i < size; i++) {
            a[i] = (T) data[i];
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    // PRE: Array has been created
    // POST: Returns an iterator for the list
    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    // Iterator class to go through the array
    private class ArrayListIterator implements Iterator<E> {

        private int current = 0;
        private int lastReturned = -1;

        // PRE: Iterator has been created
        // POST: Returns true if more items exist
        @Override
        public boolean hasNext() {
            return current < size;
        }

        // PRE: Another item must exist
        // POST: Returns the next item
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            lastReturned = current;
            return data[current++];
        }

        // PRE: next() must have been called
        // POST: Removes the last returned item
        @Override
        public void remove() {
            if (lastReturned < 0) {
                throw new IllegalStateException();
            }

            MyArrayList.this.remove(lastReturned);
            current = lastReturned;
            lastReturned = -1;
        }
    }
}
