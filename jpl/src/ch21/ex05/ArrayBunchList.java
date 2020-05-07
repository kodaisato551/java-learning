package ch21.ex05;

import java.util.AbstractList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * TODO prevの実装
 *
 * @param <E>
 */
public class ArrayBunchList<E> extends AbstractList<E> {
    private final E[][] arrays;
    private final int size;

    public ArrayBunchList(E[][] arrays) {
        this.arrays = arrays;
        int s = 0;
        for (E[] array : arrays) {
            s += array.length;
        }
        size = s;
    }

    @Override
    public E set(int index, E element) {
        int off = 0;
        for (int i = 0; i < arrays.length; i++) {
            if (index < off + arrays[i].length) {
                return arrays[i][index - off];
            }
            off += arrays[i].length;
        }
        throw new ArrayIndexOutOfBoundsException(index);
    }

    @Override
    public E get(int index) {
        int off = 0;
        for (int i = 0; i < arrays.length; i++) {
            if (index < off + arrays[i].length) {
                return arrays[i][index - off];
            }
            off += arrays[i].length;
        }
        throw new ArrayIndexOutOfBoundsException(index);
    }

    @Override
    public int size() {
        return size;
    }

    private class ABLIterator implements ListIterator<E> {
        private int off;
        private int array;
        private int pos;


        private E latestElem = null;

        ABLIterator() {
            off = 0;
            array = 0;
            pos = 0;
            for (array = 0; array < arrays.length; array++) {
                if (arrays[array].length > 0) {
                    break;
                }
            }
        }


        /**
         * リストを順方向にトラバースしたときに、このリスト・イテレータがさらに要素を持っている場合にtrueを返します。
         *
         * @return
         */
        @Override
        public boolean hasNext() {
            return off + pos < size();
        }

        /**
         * リスト内の次の要素を返し、カーソル位置を前に進めます。
         *
         * @return
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E ret = arrays[array][pos++];

            while (pos >= arrays[array].length) {
                off += arrays[array++].length;
                pos = 0;
                if (array >= arrays.length) {
                    break;
                }
            }
            return ret;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public E previous() {

            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * next()またはprevious()から最後に返された要素を指定された要素で置き換えます(オプションの操作)。
         *
         * @param e
         */
        @Override
        public void set(E e) {
            if (latestElem == null) {
                return;
            }
            latestElem = e;
        }

        @Override
        public void add(E e) {

        }
    }

}
