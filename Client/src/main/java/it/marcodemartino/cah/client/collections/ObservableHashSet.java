package it.marcodemartino.cah.client.collections;

import java.util.HashSet;

public class ObservableHashSet<E> extends HashSet<E> {

    private HashSetChangeListener<E> listener;

    @Override
    public boolean add(E element) {
        boolean result = super.add(element);
        if (listener != null && result) {
            listener.elementAdded(element);
        }
        return result;
    }

    public void setListener(HashSetChangeListener<E> listener) {
        this.listener = listener;
    }
}
