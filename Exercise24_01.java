import java.util.*;

public class Exercise24_01 {
  public static void main(String[] args) {
    new Exercise24_01();
  }

  public Exercise24_01() {
    Scanner input = new Scanner(System.in);
    String[] name1 = new String[5];
    String[] name2 = new String[5];
    String[] name3 = new String[2];
    System.out.print("Enter five strings for array name1 separated by space: ");
    for (int i = 0; i < 5; i++) {
      name1[i] = input.next();
    }
    
    System.out.print("Enter five strings for array name2 separated by space: ");
    for (int i = 0; i < 5; i++) {
      name2[i] = input.next();
    }

    System.out.print("Enter two strings for array name3 separated by space: ");
    for (int i = 0; i < 2; i++) {
      name3[i] = input.next();
    }
    
    MyList<String> list1 = new MyArrayList<>(name1);   
    MyList<String> list2 = new MyArrayList<>(name2);   
    System.out.println("list1: " + list1);
    System.out.println("list2: " + list2);
    list1.addAll(list2);
    System.out.println("After addAll: list1 is " + list1 + "\n");
    
    list1 = new MyArrayList<>(name1);
    list2 = new MyArrayList<>(name2);   
    System.out.println("list1: " + list1);
    System.out.println("list2: " + list2);
    list1.removeAll(list2);
    System.out.println("After removeAll: list1 is " + list1 + "\n");
    
    list1 = new MyArrayList<>(name1);
    list2 = new MyArrayList<>(name2);   
    System.out.println("list1: " + list1);
    System.out.println("list2: " + list2);
    list1.retainAll(list2);
    System.out.println("After retainAll: list1 is " + list1 + "\n");
    
    list1 = new MyArrayList<>(name1);
    list2 = new MyArrayList<>(name2);   
    System.out.println("list1: " + list1);
    System.out.println("list2: " + list2);
    list1.retainAll(list2);
    System.out.println("list1 contains all list2? " + list1.containsAll(list2) + "\n");
    
    list1 = new MyArrayList<>(name1);
    list2 = new MyArrayList<>(name3);
    System.out.println("list1: " + list1);
    System.out.println("list2: " + list2);
    System.out.println("list1 contains all list2? " + list1.containsAll(list2) + "\n");
    
    Object[] name4 = list1.toArray();
    System.out.print("name4: ");
    for (Object e: name4) {
      System.out.print(e + " ");
    }
    
    String[] name5 = new String[list1.size()];
    String[] name6 = list1.toArray(name5);
    System.out.print("\nname6: ");
    for (Object e: name6) {
      System.out.print(e + " ");
    }
  }
}

class MyArrayList<E> implements MyList<E> {
    public static final int INITIAL_CAPACITY = 16;
    private E[] data = (E[])new Object[INITIAL_CAPACITY];
    private int size = 0;

    public MyArrayList() {
    }

    public MyArrayList(E[] objects) {
      for (int i = 0; i < objects.length; i++)
        add(objects[i]);
    }

    @Override
    public void add(int index, E e) {   
      ensureCapacity();
      for (int i = size - 1; i >= index; i--)
        data[i + 1] = data[i];

      data[index] = e;
      size++;
    }

    private void ensureCapacity() {
      if (size >= data.length) {
        E[] newData = (E[])(new Object[size * 2 + 1]);
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
      }
    }

    @Override
    public void clear() {
      data = (E[])new Object[INITIAL_CAPACITY];
      size = 0;
    }

    @Override
    public boolean contains(Object e) {
      for (int i = 0; i < size; i++)
        if (e.equals(data[i])) return true;

      return false;
    }

    @Override
    public E get(int index) {
      checkIndex(index);
      return data[index];
    }

    private void checkIndex(int index) {
      if (index < 0 || index >= size)
        throw new IndexOutOfBoundsException
          ("Index: " + index + ", Size: " + size);
    }
    
    @Override
    public int indexOf(Object e) {
      for (int i = 0; i < size; i++)
        if (e.equals(data[i])) return i;

      return -1;
    }

    @Override
    public int lastIndexOf(E e) {
      for (int i = size - 1; i >= 0; i--)
        if (e.equals(data[i])) return i;

      return -1;
    }

    @Override
    public E remove(int index) {
      checkIndex(index);
      
      E e = data[index];

      for (int j = index; j < size - 1; j++)
        data[j] = data[j + 1];

      data[size - 1] = null;
      size--;
      return e;
    }

    @Override
    public E set(int index, E e) {
      checkIndex(index);
      E old = data[index];
      data[index] = e;
      return old;
    }

    @Override
    public String toString() {
      StringBuilder result = new StringBuilder("[");

      for (int i = 0; i < size; i++) {
        result.append(data[i]);
        if (i < size - 1) result.append(", ");
      }

      return result.toString() + "]";
    }

    public void trimToSize() {
      if (size != data.length) { 
        E[] newData = (E[])(new Object[size]);
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
      }
    }

    @Override
    public java.util.Iterator<E> iterator() {
      return new ArrayListIterator();
    }
   
    private class ArrayListIterator 
        implements java.util.Iterator<E> {
      private int current = 0;

      @Override
      public boolean hasNext() {
        return (current < size);
      }

      @Override
      public E next() {
        return data[current++];
      }

      @Override
      public void remove() {
        if (current == 0)
          throw new IllegalStateException(); 
        MyArrayList.this.remove(--current);
      }
    }
    
    @Override
    public int size() {
      return size;
    }
}

interface MyList<E> extends java.util.Collection<E> {
    public void add(int index, E e);

    public E get(int index);

    public int indexOf(Object e);

    public int lastIndexOf(E e);

    public E remove(int index);
  
    public E set(int index, E e);
    
    @Override
    public default boolean add(E e) {
      add(size(), e);
      return true;
    }

    @Override
    public default boolean isEmpty() {
      return size() == 0;
    }

    @Override
    public default boolean remove(Object e) {
      if (indexOf(e) >= 0) {
        remove(indexOf(e));
        return true;
      }
      else
        return false;
    }

    @Override
    public default boolean containsAll(Collection<?> c) {
      for (Object e: c)
        if (!this.contains(e))
          return false;
      
      return true;
    }

    @Override
    public default boolean addAll(Collection<? extends E> c) {
      for (E e: c)
        this.add(e);
      
      return c.size() > 0;
    }

    @Override
    public default boolean removeAll(Collection<?> c) {
      boolean changed = false;
      for (Object e: c) {
        if (remove(e))
          changed = true;
      }
        
      return changed;
    }  

    @Override
    public default boolean retainAll(Collection<?> c) {
      boolean changed = false;
      for (int i = 0; i < this.size(); ) {
        if (!c.contains(this.get(i))) {
          this.remove(get(i));
          changed = true;
        }
        else
          i++;
      }
        
      return changed;
    }

    @Override
    public default Object[] toArray() {
      Object[] result = new Object[size()];
      for (int i = 0; i < size(); i++) {
        result[i] = this.get(i);
      }
      return result;
    }

    @Override
    public default <T> T[] toArray(T[] a) {
      for (int i = 0; i < size(); i++) {
        a[i] = (T)this.get(i);
      }
      return a;
    }
  }
