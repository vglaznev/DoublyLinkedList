package doublylinkedlist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    final DoublyLinkedList<Integer> testEmptyList = new DoublyLinkedList<>();
    final DoublyLinkedList<Integer> testList = new DoublyLinkedList<>(1, 2, 3, 4, 5);


    @Test
    void testIsEmpty() {
        assertTrue(testEmptyList.isEmpty());
        assertFalse(testList.isEmpty());
    }

    @Test
    void testGetSize() {
        assertEquals(0, testEmptyList.getSize());
        assertEquals(5, testList.getSize());
    }

    @Test
    void testGetFirst() {
        assertThrows(NoSuchElementException.class, () -> testEmptyList.getFirst());
        assertEquals(1, testList.getFirst());
    }

    @Test
    void testGetLast() {
        assertThrows(NoSuchElementException.class, () -> testEmptyList.getLast());
        assertEquals(5, testList.getLast());
    }

    @Test
    void testGet() {
        assertThrows(IndexOutOfBoundsException.class, () -> testList.get(5));
        Assertions.assertAll(() -> assertEquals(1, testList.get(0)),
                             () -> assertEquals(2, testList.get(1)),
                             () -> assertEquals(3, testList.get(2)),
                             () -> assertEquals(4, testList.get(3)),
                             () -> assertEquals(5, testList.get(4)));
    }

    @Test
    void testAddToBegin() {
        DoublyLinkedList<Integer> testListForAdd = new DoublyLinkedList<>(3, 4, 5);

        testListForAdd.addToBegin(2);
        testListForAdd.addToBegin(1);
        assertEquals(testList, testListForAdd);
    }

    @Test
    void testAddToEnd() {
        DoublyLinkedList<Integer> testListForAdd = new DoublyLinkedList<>(1, 2, 3);

        testListForAdd.addToEnd(4);
        testListForAdd.addToEnd(5);
        assertEquals(testList, testListForAdd);

    }

    @Test
    void testAdd() {
        DoublyLinkedList<Integer> testListForAdd = new DoublyLinkedList<>(1, 3, 4, 5);

        assertThrows(IndexOutOfBoundsException.class, () -> testList.add(10, 1));

        testListForAdd.add(1, 2);
        assertEquals(testList, testListForAdd);
    }

    @Test
    void testEquals() {
        assertTrue(testList.equals(testList));
        assertTrue(testList.equals(new DoublyLinkedList<Integer>(1, 2, 3, 4, 5)));

        assertFalse(testList.equals(null));
        assertFalse(testList.equals(new String("test")));
        assertFalse(testList.equals(new DoublyLinkedList<Integer>(1, 2, 4, 4, 5)));
    }

    @Test
    void testRemoveFirst(){
        DoublyLinkedList<Integer> testListForRemove = new DoublyLinkedList<>(10, 11);

        assertEquals(10, testListForRemove.removeFirst());
        assertEquals(11, testListForRemove.removeFirst());
        assertEquals(testEmptyList, testListForRemove);
        assertThrows(NoSuchElementException.class, ()->testListForRemove.removeFirst());
    }

    @Test
    void testRemoveLast(){
        DoublyLinkedList<Integer> testListForRemove = new DoublyLinkedList<>(50, 51);

        assertEquals(51, testListForRemove.removeLast());
        assertEquals(50, testListForRemove.removeLast());
        assertEquals(testEmptyList, testListForRemove);
        assertThrows(NoSuchElementException.class, ()->testListForRemove.removeLast());
    }

    @Test
    void testRemove(){
        DoublyLinkedList<Integer> testListForRemove = new DoublyLinkedList<>(1, 2, 10, 3, 4, 10, 5);

        assertThrows(IndexOutOfBoundsException.class, ()->testListForRemove.remove(7));
        testListForRemove.remove(2);
        testListForRemove.remove(4);
        assertEquals(testList, testListForRemove);
    }
}