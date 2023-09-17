package it.marcodemartino.cah.game.cards.collections;

import it.marcodemartino.cah.game.collections.RandomArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class RandomArrayListTest {

    private static List<String> arrayList1;
    private static List<String> arrayListShuffle;
    private static List<String> linkedList;
    private static List<String> randomArrayList;
    private static final int AMOUNT_TO_REMOVE = 100000;
    private static final int SIZE = 200000;

    @BeforeAll
    static void setup() {
        arrayList1 = new ArrayList<>();
        arrayListShuffle = new ArrayList<>();
        linkedList = new LinkedList<>();
        randomArrayList = new RandomArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            arrayList1.add("Hello World");
            arrayListShuffle.add("Hello World");
            linkedList.add("Hello World");
            randomArrayList.add("Hello World");
        }
    }

    @Test
    @DisplayName("Remove " + AMOUNT_TO_REMOVE + " from ArrayList")
    void removeFromArrayList() {
        for (int i = 0; i < AMOUNT_TO_REMOVE; i++) {
            arrayList1.remove(i);
        }
    }

    @Test
    @DisplayName("Shuffle and remove " + AMOUNT_TO_REMOVE + " time the last element from ArrayList")
    void shuffleAndRemoveLastFromArrayList() {
        Collections.shuffle(arrayListShuffle);
        for (int i = 0; i < AMOUNT_TO_REMOVE; i++) {
            arrayListShuffle.remove(arrayListShuffle.size() - 1);
        }
    }

    @Test
    @DisplayName("Remove " + AMOUNT_TO_REMOVE + " from LinkedList")
    void removeFromLinkedList() {
        for (int i = 0; i < AMOUNT_TO_REMOVE; i++) {
            linkedList.remove(i);
        }
    }

    @Test
    @DisplayName("Remove " + AMOUNT_TO_REMOVE + " from RandomArrayList")
    void removeFromRandomList() {
        for (int i = 0; i < AMOUNT_TO_REMOVE; i++) {
            randomArrayList.remove(i);
        }
    }
}