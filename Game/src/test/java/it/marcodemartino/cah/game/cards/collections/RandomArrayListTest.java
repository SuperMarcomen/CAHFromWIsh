package it.marcodemartino.cah.game.cards.collections;

import it.marcodemartino.cah.game.collections.RandomArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class RandomArrayListTest {

    private static List<String> arrayList1;
    private static List<String> arrayListShuffle;
    private static List<String> linkedList;
    private static RandomArrayList<String> randomArrayList;
    private static ArrayDeque<String> arrayQueue;
    private static List<String> arrayQueueListToShuffle;
    private static final int AMOUNT_TO_REMOVE = 10_000_000;
    private static final int SIZE = 20_000_000;

    @BeforeAll
    static void setup() {
        arrayList1 = new ArrayList<>();
        arrayListShuffle = new ArrayList<>();
        linkedList = new LinkedList<>();
        arrayQueueListToShuffle = new ArrayList<>();
        arrayQueue = new ArrayDeque<>();
        for (int i = 0; i < SIZE; i++) {
            //arrayList1.add("Hello World");
            arrayListShuffle.add("Hello World");
            //linkedList.add("Hello World");
            arrayQueue.add("Hello World");
            arrayQueueListToShuffle.add("Hello World");
        }
    }

    @Test
    @Order(1)
    void buildRandomArray() {
        randomArrayList = new RandomArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            randomArrayList.add("Hello World");
        }
    }

    @Test
    @Order(2)
    void buildArrayShuffle() {
        arrayListShuffle = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            arrayListShuffle.add("Hello World");
        }
    }

    @Test
    @DisplayName("Remove " + AMOUNT_TO_REMOVE + " from ArrayList")
    @Disabled
    void removeFromArrayList() {
        for (int i = 0; i < AMOUNT_TO_REMOVE; i++) {
            arrayList1.remove(i);
        }
    }

    @Test
    @DisplayName("Shuffle and remove " + AMOUNT_TO_REMOVE + " time the last element from ArrayList")
    @Order(4)
    void shuffleAndRemoveLastFromArrayList() {
        Collections.shuffle(arrayListShuffle);
        for (int i = 0; i < AMOUNT_TO_REMOVE; i++) {
            arrayListShuffle.remove(arrayListShuffle.size() - 1);
        }
    }

    @Test
    @DisplayName("Remove " + AMOUNT_TO_REMOVE + " from LinkedList")
    @Disabled
    void removeFromLinkedList() {
        for (int i = 0; i < AMOUNT_TO_REMOVE; i++) {
            linkedList.remove(i);
        }
    }

    @Test
    @DisplayName("Remove " + AMOUNT_TO_REMOVE + " from RandomArrayList")
    @Order(3)
    void removeFromRandomList() {
        for (int i = 0; i < AMOUNT_TO_REMOVE; i++) {
            randomArrayList.remove(randomArrayList.size() - 1);
        }
    }

    @Test
    @DisplayName("Shuffle and remove " + AMOUNT_TO_REMOVE + " time from ArrayDeque")
    void removeFromArrayDeque() {
        Collections.shuffle(arrayQueueListToShuffle);
        arrayQueue = new ArrayDeque<>(arrayQueueListToShuffle);
        for (int i = 0; i < AMOUNT_TO_REMOVE; i++) {
            arrayQueue.remove();
        }
    }
}