import java.util.Iterator;

@SuppressWarnings("unchecked")
public class ArrayQueue<T> implements Iterable<T> {
	private T[] array;
	private int totalNumberOfElements;
	private int front;
	private int rear;
	private int capacity;

	public ArrayQueue() {
		array = (T[]) new Object[15];
		totalNumberOfElements = 0;
		front = 0;
		rear = -1;
		capacity = 15;
	}

	public boolean isEmpty() {
		return totalNumberOfElements == 0;
	}

	public boolean isFull() {
		return totalNumberOfElements == capacity;
	}

	public void insert(T data) {
		if (isFull())
			throw new ArrayIndexOutOfBoundsException();

		rear++;
		array[rear % capacity] = data;
		totalNumberOfElements++;
	}

	public T delete() {
		if (isEmpty())
			throw new ArrayIndexOutOfBoundsException();

		T temp = array[front % capacity];
		array[front % capacity] = null;
		front++;
		totalNumberOfElements--;
		return temp;
	}

	public void clear() {
		for (int i = 0; i < array.length; i++) {
			array[i] = null;
		}
		totalNumberOfElements = 0;
		front = 0;
		rear = -1;
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayQueueIterator();
	}

	private class ArrayQueueIterator implements Iterator<T> {

		private int index;

		public ArrayQueueIterator() {
			index = front;
		}

		@Override
		public boolean hasNext() {
			return index <= rear;
		}

		@Override
		public T next() {
			if (!hasNext())
				throw new ArrayIndexOutOfBoundsException();
			T temp = array[index % capacity];
			index++;
			return temp;
		}
	}

	public static void main(String[] args) {
		ArrayQueue<String> queue = new ArrayQueue<String>();

		String[] people = { "Tom", "Jay", "Pat", "Meghan", "Tom", "Mark",
				"Kasey", "John", "Helen" };

		for (int i = 0; i < people.length; i++)
			queue.insert(people[i]);

		for (int i = 0; i < 3; i++)
			queue.delete();

		for (int i = 0; i < 3; i++)
			queue.insert(people[i]);

		Iterator<String> itr = queue.iterator();
		while (itr.hasNext())
			System.out.println(itr.next());
	}
}
