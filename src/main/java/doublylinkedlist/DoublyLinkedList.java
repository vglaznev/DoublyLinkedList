package doublylinkedlist;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * This class implements doubly-linked list container
 * @author Vsevolod Glaznev
 * @version 1.0
 * @param <E> type of stored elements
 */
public class DoublyLinkedList<E> {
    /** First node of the list */
    private Node<E> head;
    /** Last node of the list */
    private Node<E> tail;

    /** Amount of elements stored in the list */
    private int size;

    /**
     * Creates an empty list
     */
    public DoublyLinkedList() {
        size = 0;
    }

    /**
     * Creates a list that contains the elements
     * @param elements elements to be added
     */
    public DoublyLinkedList(E... elements) {
        size = 0;
        for (E element : elements) {
            add(element);
        }
    }

    /**
     * Checks if the list has no elements
     * @return true if the list has no elements
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Gets node before node with specified index
     * @param index position of the specified node
     * @return node before specified node
     */
    private Node<E> getNodeBefore(int index) {
        Node<E> tmp;
        if (index <= size / 2) {
            tmp = head;
            for (int i = 0; i < index - 1; i++) {
                tmp = tmp.next;
            }
        } else {
            tmp = tail;
            for (int i = 0; i < size - index; i++) {
                tmp = tmp.prev;
            }
        }
        return tmp;
    }

    /**
     * Gets node with specified index
     * @param index position of the wanted node
     * @return node with specified index
     */
    private Node<E> getNode(int index) {
        return getNodeBefore(index).next;
    }

    /**
     * Gets size of the list
     * @return size of the list
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets first element of the list
     * @return first element of the list
     * @throws NoSuchElementException if the list is empty
     */
    public E getFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        } else {
            return head.element;
        }
    }

    /**
     * Gets last element of the list
     * @return last element of the list
     * @throws NoSuchElementException
     */
    public E getLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        } else {
            return tail.element;
        }
    }

    /**
     * Gets element by index
     * @param index position of the wanted element
     * @return wanted element
     * @throws IndexOutOfBoundsException if the index out of the list range
     */
    public E get(int index) throws IndexOutOfBoundsException {
        if (index > 0 && index < size - 1) {
            return getNode(index).element;

        } else if (index == 0) {
            return getFirst();

        } else if (index == size - 1) {
            return getLast();

        } else {
            throw new IndexOutOfBoundsException("Index out of the list range");
        }
    }

    /**
     * Adds element to the beginning of the list
     * @param element element to be added
     */
    public void addToBegin(E element) {
        Node<E> tmp = new Node<>(element);
        if (isEmpty()) {
            head = tmp;
            tail = head;

        } else {
            tmp.next = head;
            head.prev = tmp;
            head = tmp;
        }
        size++;
    }

    /**
     * Adds element to the end of the list
     * @param element element to be added
     */
    public void addToEnd(E element) {
        Node<E> tmp = new Node<>(element);
        if (isEmpty()) {
            tail = tmp;
            head = tail;

        } else {
            tmp.prev = tail;
            tail.next = tmp;
            tail = tmp;
        }
        size++;
    }

    /**
     * Adds element to the end of the list
     * Similar to removeLast method. Created to provide easier coding process
     * @param element element to be added
     */
    public void add(E element) {
        addToEnd(element);
    }

    /**
     * Adds element to specified position in the list
     * @param index   specified position
     * @param element element to be added
     * @throws IndexOutOfBoundsException if the index out of the list range
     */
    public void add(int index, E element) throws IndexOutOfBoundsException {
        if (index > 0 && index < size) {
            Node<E> nodeBeforeAddedNode = getNodeBefore(index);
            Node<E> nodeAfterAddedNode = nodeBeforeAddedNode.next;

            Node<E> addedNode = new Node<>(element, nodeBeforeAddedNode, nodeAfterAddedNode);
            nodeBeforeAddedNode.next = addedNode;
            nodeAfterAddedNode.prev = addedNode;
            size++;

        } else if (index == 0) {
            addToBegin(element);

        } else if (index == size) {
            addToEnd(element);

        } else {
            throw new IndexOutOfBoundsException("List index out of range");
        }
    }

    /**
     * Retrieves the element at the beginning of the list
     * @return first element of the list
     * @throws NoSuchElementException if the list is empty
     */
    public E removeFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        } else {
            E firstElement = head.element;
            Node<E> newHead = head.next;
            if (size != 1) {
                head.next = null;
                newHead.prev = null;
            } else {
                tail = null;
            }
            head = newHead;
            size--;
            return firstElement;
        }
    }

    /**
     * Gets and removes the element at the end of the list
     * @return last element of the list
     * @throws NoSuchElementException if the list is empty
     */
    public E removeLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        } else {
            E lastElement = tail.element;
            Node<E> newTail = tail.prev;
            if (size != 1) {
                tail.prev = null;
                newTail.next = null;
            } else {
                head = null;
            }
            tail = newTail;
            size--;
            return lastElement;
        }
    }

    /**
     * Gets and removes the element at the end of the list.
     * Similar to removeLast method. Created to provide easier coding process
     * @return last element of the list
     * @throws NoSuchElementException if the list is empty
     */
    public E remove() throws NoSuchElementException {
        return removeLast();
    }

    /**
     * Gets and removes the element at the specified position
     * @param index position of retrieved element
     * @return element at the specified position
     * @throws IndexOutOfBoundsException if index out of the list range
     */
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index > 0 && index < size - 1) {
            Node<E> removedNode = getNode(index);
            removedNode.next.prev = removedNode.prev;
            removedNode.prev.next = removedNode.next;
            removedNode.next = null;
            removedNode.prev = null;
            size--;
            return removedNode.element;
        } else if (index == 0) {
            return removeFirst();

        } else if (index == size - 1) {
            return removeLast();

        } else {
            throw new IndexOutOfBoundsException("List index out of range");
        }
    }

    /**
     * Compares two lists
     * @param other other list to be compared with this list
     * @return true if lists are equal
     */
    private boolean isListEquals(DoublyLinkedList<?> other) {
        if (size != other.size) {
            return false;
        }
        Node<?> otherIter = other.head;
        Node<E> thisIter = head;
        while (thisIter != null) {
            if (!Objects.equals(thisIter.element, otherIter.element)) {
                return false;
            }
            thisIter = thisIter.next;
            otherIter = otherIter.next;
        }
        return true;
    }

    /**
     * Override of the Object.equals method
     * @param obj object that's compared to this object
     * @return true if objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        DoublyLinkedList<?> other = (DoublyLinkedList<?>) obj;

        return isListEquals(other);
    }

    /**
     * Class that implements DoublyLinkedList's node structure
     * @param <E> type of stored element
     */
    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        Node() {
        }

        Node(E element) {
            this.element = element;
        }

        Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

}
