import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;


/*
This problem was asked by Google.

An XOR linked list is a more memory efficient doubly linked list.
Instead of each node holding next and prev fields, it holds a field named both,
which is an XOR of the next node and the previous node. 
Implement an XOR linked list; it has an add(element) 
which adds the element to the end, and a get(index) which returns the node at index.

If using a language that has no pointers (such as Python),
you can assume you have access to get_pointer and dereference_pointer functions 
that converts between nodes and memory addresses.
*/
public class problem6 {

	public static void main(String[] args) {

		var list = new XoredList();

		list.add(834876);
		list.add(876);
		list.add(99287345);
		list.add(9928744);
		list.add(0);
		list.add(1);

		var iterator = list.iterator();
		assert iterator.next().value == 834876;
		assert iterator.next().value == 876;
		assert iterator.next().value == 99287345;
		assert iterator.next().value == 9928744;
		assert iterator.next().value == 0;
		assert iterator.next().value == 1;

		var reverseIterator = list.reverseIterator();
		assert reverseIterator.next().value == 1;
		assert reverseIterator.next().value == 0;
		assert reverseIterator.next().value == 9928744;
		assert reverseIterator.next().value == 99287345;
		assert reverseIterator.next().value == 876;
		assert reverseIterator.next().value == 834876;

		assert list.get(0).value == 834876;
		assert list.get(1).value == 876;
		assert list.get(2).value == 99287345;
		assert list.get(3).value == 9928744;
		assert list.get(4).value == 0;
		assert list.get(5).value == 1;

		System.out.println("success!!");
	}

	static private class XoredList implements Iterable {
		long firstElementPointer = 0;
		long lastElementPointer = 0;

		void add(int elementValue) {
			Element newElement = new Element(elementValue);
			long newPointer = TheMemory.allocate(newElement);
			newElement.xoredPointers = lastElementPointer;

			if (firstElementPointer == 0) {
				firstElementPointer = newPointer;
			} 

			if(lastElementPointer != 0) {
				Element lastElement = TheMemory.dereferencePointer(lastElementPointer);
				lastElement.xoredPointers = lastElement.xoredPointers ^ newPointer;
			}
			
			lastElementPointer = newPointer;
		}
		

		Element get(long index) {
			int count = 0;
			var iterator =  iterator();
			while(iterator.hasNext() && index >= count) {
				var current = iterator.next();
				
				if(index == count) {
					return current;
				}

				count++;
			}

			return null;
		}

		public Iterator<Element> iterator() {
			return new XoredListIterator(firstElementPointer, lastElementPointer);
		}

		public Iterator<Element> reverseIterator() {
			return new XoredListIterator(lastElementPointer, firstElementPointer);
		}
	}

	static class XoredListIterator implements Iterator<Element> {
		long beggining;
		long end;

		Element current;
		Element previous;
		
		XoredListIterator(long beggining, long end) {
			this.beggining = beggining;
			this.end = end;
		}

		public boolean hasNext() {
			if (current == null) {
				return beggining != 0;
			} else {
				return current.getPointer() != end;
			}
		}

		public Element next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			} else {
				if (current == null) {
					current = TheMemory.dereferencePointer( beggining );
					return current;
				} else if(previous == null) {
					previous = current;
					current = TheMemory.dereferencePointer(current.xoredPointers);
					return current;
				} else {
					
					var opposite = current.xor(previous);
					if (opposite == null) {
						throw new RuntimeException();
					}
		
					previous = current;
					current = opposite;
		
					return current;
				}
			}
		}
	}

	static private class Element {
		final int value;
		long self;
		long xoredPointers;

		Element(int value) {
			this.value = value;
		}

		Element xor(Element other){
			long otherPointer = other == null ? 0 
											  : other.getPointer();

			long oppositePointer = xoredPointers ^ otherPointer;
			return TheMemory.dereferencePointer(oppositePointer);
		}

		long getPointer() {
			return self;
		}
	}

	static private class TheMemory {
		// I have no pointers, and decided it was not worth it to learn C just to have real pointers for this exercise
		// so this maps will be the 'memory', where the keys are the 'pointers'
		// and the values are the allocated memory space
		private static final Map<Long, Element> THE_MEMORY = new HashMap<>();

		long getPointer(Element element) {
			return element.getPointer();
		}

		static Element dereferencePointer(long pointer) {
			return THE_MEMORY.get(pointer);
		}

		static long allocate(Element element) {
			long memoryAddress = (long)(Math.random() *10000000);
			THE_MEMORY.put(memoryAddress, element);
			element.self = memoryAddress;

			return memoryAddress;
		}
	}
}