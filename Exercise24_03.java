//no intructions
import java.util.Iterator;
import java.util.ListIterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Collection;

public class Exercise24_03 {
 public static void main(String[] args) {
   new Exercise24_03();
 }

 public Exercise24_03() {
   TwoWayLinkedList<Double> list = new TwoWayLinkedList<>();
   System.out.print("Enter five numbers: ");
   Scanner input = new Scanner(System.in);
   double[] v = new double[5];
   for (int i = 0; i < 5; i++) 
     v[i] = input.nextDouble();

   list.add(v[1]);
   list.add(v[2]);
   list.add(v[3]);
   list.add(v[4]);
   list.add(0, v[0]);
   list.add(2, 10.55);
   list.remove(3);

   java.util.ListIterator<Double> iterator1 = list.listIterator();
   System.out.print("The list in forward order: ");
   while (iterator1.hasNext())
     System.out.print(iterator1.next() + " ");

   java.util.ListIterator<Double> iterator2 = list.listIterator(list.size() - 1);
   System.out.print("\nThe list in backward order: ");
   while (iterator2.hasPrevious())
     System.out.print(iterator2.previous() + " ");
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
   return true;
 }

 @Override
 public default boolean addAll(Collection<? extends E> c) {
   return true;
 }

 @Override
 public default boolean removeAll(Collection<?> c) {
   return true;
 }

 @Override
 public default boolean retainAll(Collection<?> c) {
   return true;
 }

 @Override
 public default Object[] toArray() {
   return null;
 }

 @Override
 public default <T> T[] toArray(T[] array) {
   return null;
 }
}

class TwoWayLinkedList<E> implements MyList<E> {
 private Node<E> head, tail;
 private int size;

 public TwoWayLinkedList() {
 }

 public TwoWayLinkedList(E[] objects) {
   for (E e : objects)
     add(e);
 }

 public E getFirst() {
   if (size == 0) {
     return null;
   } else {
     return head.element;
   }
 }

 public E getLast() {
   if (size == 0) {
     return null;
   } else {
     return tail.element;
   }
 }

 @Override
 public String toString() {
   StringBuilder result = new StringBuilder("[");

   Node<E> current = head;
   for (int i = 0; i < size; i++) {
     result.append(current.element);
     current = current.next;
     if (current != null) {
       result.append(", "); 
     } else {
       result.append("]");
     }
   }

   return result.toString();
 }

 public void clear() {
   head = tail = null;
 }

 public boolean contains(Object e) {
   System.out.println("Implementation left as an exercise");
   return true;
 }

  public E get(int index) {
   System.out.println("Implementation left as an exercise");
   return null;
 }

 public int indexOf(Object e) {
   System.out.println("Implementation left as an exercise");
   return 0;
 }

 public int lastIndexOf(Object e) {
   System.out.println("Implementation left as an exercise");
   return 0;
 }

 public E set(int index, E e) {
   System.out.println("Implementation left as an exercise");
   return null;
 }

 private class LinkedListIterator implements java.util.ListIterator<E> {
   private Node<E> current = head; 

   public LinkedListIterator() {
   }

   public LinkedListIterator(int index) {
     if (index < 0 || index >= size)
       throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
         + size);
     for (int nextIndex = 0; nextIndex < index; nextIndex++)
       current = current.next;
   }

   public void setLast() {
 	current = tail;
   }

   @Override
   public boolean hasNext() {
     return (current != null);
   }

   @Override
   public E next() {
     E e = current.element;
     current = current.next;
     return e;
   }

   @Override
   public void remove() {
     System.out.println("Implementation left as an exercise");
   }

   @Override
   public void add(E e) {
     System.out.println("Implementation left as an exercise");
   }

   @Override
   public boolean hasPrevious() {
     return current != null;
   }

   @Override
   public int nextIndex() {
     System.out.println("Implementation left as an exercise");
     return 0;
   }

   @Override
   public E previous() {
     E e = current.element;
     current = current.previous;
     return e;
   }

   @Override
   public int previousIndex() {
     System.out.println("Implementation left as an exercise");
     return 0;
   }

   @Override
   public void set(E e) {
     current.element = e; 
   }
 }

 private class Node<E> {
   E element;
   Node<E> next;
   Node<E> previous;

   public Node(E o) {
     element = o;
   }
 }

 @Override
 public int size() {
   return size;
 }

 public ListIterator<E> listIterator() {
   return new LinkedListIterator(); 
 }

 public ListIterator<E> listIterator(int index) {
   return new LinkedListIterator(index); 
 }

 @Override
 public Iterator<E> iterator() {
   return null;
 }

 public void addFirst(E e) {    
   Node<E> newNode = new Node<>(e);
   if (size == 0) {
       head = tail = newNode;
   } else {
       newNode.next = head;
       head.previous = newNode;
       head = newNode;
   }
   size++;
 }

 public void addLast(E e) {
   Node<E> newNode = new Node<>(e);
   if (size == 0) {
       head = tail = newNode;
   } else {
       newNode.previous = tail;
       tail.next = newNode;
       tail = newNode;
   }
   size++;
 }

 public void add(int index, E e) {
   if (index < 0 || index > size) {
       throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
   }
   if (index == 0) {
       addFirst(e);
   } else if (index == size) {
       addLast(e);
   } else {
       Node<E> newNode = new Node<>(e);
       Node<E> current = head;
       for (int i = 0; i < index; i++) {
           current = current.next;
       }
       newNode.previous = current.previous;
       newNode.next = current;
       current.previous.next = newNode;
       current.previous = newNode;
       size++;
 }
 }

 public E removeFirst() {
   if (size == 0) {
       return null;
   }
   E removedElement = head.element;
   if (size == 1) {
       head = tail = null;
   } else {
       head = head.next;
       head.previous = null;
   }
   size--;
   return removedElement;
 }

 public E removeLast() {
   if (size == 0) {
       return null;
   }
   E removedElement = tail.element;
   if (size == 1) {
       head = tail = null;
   } else {
       tail = tail.previous;
       tail.next = null;
   }
   size--;
   return removedElement;
 }

 public E remove(int index) {
   if (index < 0 || index >= size) {
       throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
   }
   if (index == 0) {
       return removeFirst();
   } else if (index == size - 1) {
       return removeLast();
   } else {
       Node<E> current = head;
       for (int i = 0; i < index; i++) {
           current = current.next;
       }
       E removedElement = current.element;
       current.previous.next = current.next;
       current.next.previous = current.previous;
       size--;
       return removedElement;
   }
}
}
