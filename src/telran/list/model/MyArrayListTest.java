package telran.list.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.list.interfaces.IList;

class MyArrayListTest {
	IList<Integer> list = new MyArrayList<>();

	@BeforeEach
	void setUp() throws Exception {

		list.add(1); 	// 0
		list.add(6); 	// 1
		list.add(3); 	// 2
		list.add(3); 	// 3
		list.add(null); // 4
		list.add(76); 	// 5
	}

	@Test

	void testIterator() {
		System.out.println("==============testIterator============");
		for (Integer element : list) {
			System.out.print(element + " ");
		}
	}

	@Test
	void testClear() {
		list.clear();
		for (Integer element : list) {
			assertNull(element);
		}
	}

	@Test
	void testSize() {
		assertEquals(6, list.size());
		list.remove(5);
		assertEquals(5, list.size());
		list.add(588);
		assertEquals(6, list.size());
	}

	@Test
	void testAdd() {
		list.add(1);
		assertEquals(7, list.size());

		assertEquals(1, list.get(6));

	}

	@Test
	void testGet() {
		assertNull(list.get(4));
		assertEquals(76, list.get(5));
	}

	@Test
	void testIndexOf() {
		assertEquals(2, list.indexOf(3));
	}

	@Test
	void testLastIndexOf() {
		assertEquals(3, list.lastIndexOf(3));
	}

	@Test
	void testRemove() {
		list.remove(1);
		assertEquals(5, list.size());
		assertEquals(3, list.get(1));
		assertEquals(76, list.get(4));

	}

	@Test
	void testSet() {
		list.set(3, 555);
		assertEquals(555, list.get(3));
	}

}
