package telran.list.model;

import java.util.Arrays;
import java.util.Iterator;

import telran.list.interfaces.IList;

public class MyArrayList<E> implements IList<E> {
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	private Object[] elements;
	private int size;

	public MyArrayList() {
//		elements = new Object[10];
		this(10);
	}

	public MyArrayList(int initialCapacity) {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException("Illegal capacity " + initialCapacity);
		}
		if (initialCapacity > MAX_ARRAY_SIZE) {
			initialCapacity = MAX_ARRAY_SIZE;
		}
		elements = new Object[initialCapacity];
	}

	@Override
	public Iterator<E> iterator() {

		return new Iterator<E>() {
			private int currPos = 0;

			@Override
			public boolean hasNext() {
				return currPos < size();
			}

			@Override
			public E next() {
				@SuppressWarnings("unchecked")
				E current = (E) elements[currPos];
				currPos++;
				return current;
			}

			@Override
			public void remove() {
				MyArrayList.this.remove(--currPos);
				size--;
			}
		};
	}

	// O(1) ??
	@Override
	public void clear() {
		elements = new Object[10];
	}

	@Override
	// O(1)
	public int size() {
		return size;
	}

	// O(n) - linear
	@Override
	public boolean add(int index, E element) {
		ensureCapacity();
		if (index == size) {
			elements[index] = element;
		} else {
			checkIndex(index);
			System.arraycopy(elements, index, elements, index + 1, size - index);
			elements[index] = element;
		}
		size++;
		return true;
	}

	// O(1) - const
	private void checkIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index + " out of bounds");
		}

	}

	// O(1) - const
	private void ensureCapacity() {
		if (size == MAX_ARRAY_SIZE) {
			throw new OutOfMemoryError();
		}
		if (size == elements.length) {
			int newCapacity = elements.length + elements.length / 2 + 1;
			if (newCapacity < 0 || newCapacity > MAX_ARRAY_SIZE) {
				newCapacity = MAX_ARRAY_SIZE;
			}
			elements = Arrays.copyOf(elements, newCapacity);
		}
	}

	// O(1) - const
	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		checkIndex(index);
		return (E) elements[index];
	}

	// O(n) - linear
	@Override
	public int indexOf(Object o) {
		if (o == null) {
			for (int i = 0; i < size; i++) {
				if (elements[i] == o) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (o.equals(elements[i])) {
					return i;
				}
			}
		}

		return -1;
	}

	// O(n) - linear
	@Override
	public int lastIndexOf(Object o) {
		if (o == null) {
			for (int i = size; i >= 0; i--) {
				if (elements[i] == o) {
					return i;
				}
			}
		} else {
			for (int i = size; i >= 0; i--) {
				if (o.equals(elements[i])) {
					return i;
				}
			}
		}

		return -1;
	}

	// O(n) - linear --System.arraycopy
	@Override
	public E remove(int index) {
		checkIndex(index);
		@SuppressWarnings("unchecked")
		E victim = (E) elements[index];
		if (index == size) {
			elements[index] = null;
		} else {
			System.arraycopy(elements, index + 1, elements, index, size - index);
		}
		size--;
		return victim;
	}

	// O(1)
	@Override
	public E set(int index, E element) {
		checkIndex(index);
		@SuppressWarnings("unchecked")
		E victim = (E) elements[index];
		elements[index] = element;
		return victim;
	}

}
