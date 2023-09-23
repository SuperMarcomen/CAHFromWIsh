package it.marcodemartino.cah.game.cards.collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LmaoTest {

    private static List<String> arrayListShuffle;
    private static List<String> arrayListSwapOriginal;
    private static List<String> arrayListSwapImproved;
    private static List<String> arrayListSwapBad;
    private static List<String> arrayListSwapSequential;
    private static List<String> arrayListSwapSequentialLocal;
    private static final int AMOUNT_TO_REMOVE = 5_000_000;
    private static final int SIZE = 10_000_000;

    @BeforeAll
    static void setup() {
        String test = "Hello World";

        arrayListShuffle = new ArrayList<>();
        arrayListSwapOriginal = new ArrayList<>();
        arrayListSwapImproved = new ArrayList<>();
        arrayListSwapBad = new ArrayList<>();
        arrayListSwapSequential = new ArrayList<>();
        arrayListSwapSequentialLocal = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            arrayListShuffle.add(test);
            arrayListSwapOriginal.add(test);
            arrayListSwapImproved.add(test);
            arrayListSwapBad.add(test);
            arrayListSwapSequential.add(test);
            arrayListSwapSequentialLocal.add(test);
        }
    }

    @Test
    void shuffleAndRemoveLastFromArrayList() {
        int noop = 0;

        Collections.shuffle(arrayListShuffle);
        for (int i = 0; i < AMOUNT_TO_REMOVE; i++) {
            String result = arrayListShuffle.remove(arrayListShuffle.size() - 1);
            noop += result.length();
        }

        System.out.println(noop);
    }

    @Test
    void removeRandomAndSwapArrayListOriginal() {
        List<String> list = LmaoTest.arrayListSwapOriginal;
        int noop = 0;

        Random random = new Random();
        for (int i = 0; i < AMOUNT_TO_REMOVE; i++) {
            int index = random.nextInt(list.size());

            String result = list.get(index);

            String lastElement = list.get(list.size() - 1);
            list.set(index, lastElement);
            list.set(list.size() - 1, result);

            String removed = list.remove(list.size() - 1);
            noop += removed.length();
        }

        System.out.println(noop);
    }

    @Test
    void removeRandomAndSwapArrayList() {
        List<String> list = LmaoTest.arrayListSwapImproved;
        int noop = 0;

        Random random = new Random();
        for (int i = 0; i < AMOUNT_TO_REMOVE; i++) {
            int size = list.size();
            int index = random.nextInt(size);

            String result;
            if (index < size - 1) {
                result = list.get(index);

                String last = list.remove(size - 1);
                list.set(index, last);
            } else {
                result = list.remove(size - 1);
            }

            noop += result.length();
        }

        System.out.println(noop);
    }

    @Test
    void removeRandomMathAndSwapArrayList() {
        List<String> list = LmaoTest.arrayListSwapBad;

        int noop = 0;

        for (int i = 0; i < AMOUNT_TO_REMOVE; i++) {
            int size = list.size();
            int index = (int) (Math.random() * list.size());

            String result;
            if (index < size - 1) {
                result = list.get(index);

                String last = list.remove(size - 1);
                list.set(index, last);
            } else {
                result = list.remove(size - 1);
            }

            noop += result.length();
        }

        System.out.println(noop);
    }

    @Test
    void removeSequentialAndSwapNonLocalArrayList() {
        List<String> list = LmaoTest.arrayListSwapSequential;

        int noop = 0;
        int offset = 0;
        final int nonLocality = 50000;

        for (int i = 0; i < AMOUNT_TO_REMOVE; i++) {
            int size = list.size();
            int index = Math.max((i + (offset = (offset + nonLocality))) % size - 1, 0);

            String result;
            if (index < size - 1) {
                result = list.get(index);

                String last = list.remove(size - 1);
                list.set(index, last);
            } else {
                result = list.remove(size - 1);
            }

            noop += result.length();
        }

        System.out.println(noop);
    }

    @Test
    void removeSequentialAndSwapLocalArrayList() {
        List<String> list = LmaoTest.arrayListSwapSequentialLocal;

        int noop = 0;

        for (int index = 0; index < AMOUNT_TO_REMOVE; index++) {
            int size = list.size();

            String result;
            if (index < size - 1) {
                result = list.get(index);

                String last = list.remove(size - 1);
                list.set(index, last);
            } else {
                result = list.remove(size - 1);
            }

            noop += result.length();
        }

        System.out.println(noop);
    }

}
