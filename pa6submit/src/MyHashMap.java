import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyHashMap<K, V> implements DefaultMap<K, V> {
	public static final double DEFAULT_LOAD_FACTOR = 0.75;
	public static final int DEFAULT_INITIAL_CAPACITY = 16;
	public static final String ILLEGAL_ARG_CAPACITY = "Initial Capacity must be non-negative";
	public static final String ILLEGAL_ARG_LOAD_FACTOR = "Load Factor must be positive";
	public static final String ILLEGAL_ARG_NULL_KEY = "Keys must be non-null";
	
	private double loadFactor;
	private int capacity;
	private int size;

	// Use this instance variable for Separate Chaining conflict resolution
	private List<HashMapEntry<K, V>>[] buckets;  
	
	// Use this instance variable for Linear Probing
	private HashMapEntry<K, V>[] entries; 	

	public MyHashMap() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	
	/**
	 * 
	 * @param initialCapacity the initial capacity of this MyHashMap
	 * @param loadFactor the load factor for rehashing this MyHashMap
	 * @throws IllegalArgumentException if initialCapacity is negative or loadFactor not
	 * positive
	 */
	@SuppressWarnings("unchecked")
	public MyHashMap(int initialCapacity, double loadFactor) throws IllegalArgumentException {
		if (initialCapacity < 0 || loadFactor <= 0)
		{
			throw new IllegalArgumentException();
		}
		this.capacity = initialCapacity;
		this.loadFactor = loadFactor;
		this.size = 0;

		// if you use Separate Chaining
		this.buckets = (List<HashMapEntry<K, V>>[]) new List[capacity];
		//this.buckets = (List<HashMapEntry<K, V>>[]) new List<?>[capacity];

		// if you use Linear Probing
		this.entries = (HashMapEntry<K, V>[]) new HashMapEntry<?, ?>[initialCapacity];
	}

	@Override
	public boolean put(K key, V value) throws IllegalArgumentException {
		// can also use key.hashCode() assuming key is not null
		boolean added = false;
		if (key == null)
		{
			throw new IllegalArgumentException();
		}
		int keyHash = Objects.hashCode(key);
		if (keyHash < 0)
		{
			keyHash *= -1;
		}
		int index = keyHash % this.buckets.length;
		if (this.buckets[index] == null)
		{
			this.buckets[index] = new ArrayList<>();
			this.buckets[index].add(new HashMapEntry(key, value));
			size +=1;
			added = true;
		}
		else
		{
			for (MyHashMap.HashMapEntry<K, V> e : buckets[index])
			{
				if (e.getKey().equals(key))
				{
					return false;
					
				}
			}
			buckets[index].add(new HashMapEntry(key, value));
			this.size += 1;
			added = true;
		}
		return added;
		
	}

	@Override
	public boolean replace(K key, V newValue) throws IllegalArgumentException {
		if (key == null)
		{
			throw new IllegalArgumentException();
		}
		int keyHash = Objects.hashCode(key);
		if (keyHash < 0)
		{
			keyHash *= -1;
		}
		int index = keyHash % this.buckets.length;
		
		
		if (this.buckets[index] == null)
		{
			return false;
		}
		else
		{
			for (HashMapEntry e: this.buckets[index])
			{
				if (e.getKey().equals(key))
				{
					e.setValue(newValue);
					return true;
				}
			}
			return false;
		}
		
	}

	@Override
	public boolean remove(K key) throws IllegalArgumentException {
		if (key == null)
		{
			throw new IllegalArgumentException();
		}
		int keyHash = Objects.hashCode(key);
		if (keyHash < 0)
		{
			keyHash *= -1;
		}
		int index = keyHash % this.buckets.length;
		
		if (this.buckets[index] == null)
		{
			return false;
		}
		else
		{
			for (HashMapEntry e: this.buckets[index])
			{
				if (e.getKey().equals(key))
				{
					this.buckets[index].remove(e);
					size -= 1;
					return true;
				}
			}
			return false;
		}
	}

	@Override
	public void set(K key, V value) throws IllegalArgumentException {
		if (key == null)
		{
			throw new IllegalArgumentException();
		}
		int keyHash = Objects.hashCode(key);
		if (keyHash < 0)
		{
			keyHash *= -1;
		}
		int index = keyHash % this.buckets.length;
		if (this.buckets[index] == null)
		{
			this.buckets[index] = new ArrayList<>();
			this.buckets[index].add(new HashMapEntry(key, value));
			
		}
		else
		{
			for (HashMapEntry e: this.buckets[index])
			{
				if (e.getKey().equals(key))
				{
					e.setValue(value);
					return;
				}
			}
			this.buckets[index].add(new HashMapEntry(key, value));
		}
		this.size += 1;
		
	}

	@Override
	public V get(K key) throws IllegalArgumentException {
		if (key == null)
		{
			throw new IllegalArgumentException();
		}
		int keyHash = Objects.hashCode(key);
		if (keyHash < 0)
		{
			keyHash *= -1;
		}
		int index = keyHash % this.buckets.length;
		
		if(this.buckets[index] == null)
		{
			return null;
		}
		else
		{
			for (HashMapEntry e : this.buckets[index])
			{
				if (e.getKey().equals(key))
				{
					return (V) e.getValue();
				}
			}
			return null;
		}
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		if (this.size == 0)
		{
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsKey(K key) throws IllegalArgumentException {
		if (key == null)
		{
			throw new IllegalArgumentException();
		}
		int keyHash = Objects.hashCode(key);
		if (keyHash < 0)
		{
			keyHash *= -1;
		}
		int index = keyHash % this.buckets.length;
		
		if(this.buckets[index] == null)
		{
			return false;
		}
		else
		{
			for (HashMapEntry e : this.buckets[index])
			{
				if (e.getKey().equals(key))
				{
					return true;
				}
			}
			return false;
		}
		
	}

	@Override
	public List<K> keys() {
		//may be wrong
		List<K> submit = new ArrayList<K>();
		
		if (this.size == 0)
		{
			return submit;
		}
		
		for (int i = 0; i < this.buckets.length; i++)
		{
			if (buckets[i] != null)
			{
				for (MyHashMap.HashMapEntry<K, V> e : buckets[i])
				{
					
					submit.add(e.getKey());
					
				}
			}
			
		}
		
		return submit;
	}
	
	private static class HashMapEntry<K, V> implements DefaultMap.Entry<K, V> {
		
		K key;
		V value;
		
		private HashMapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}
		
		@Override
		public void setValue(V value) {
			this.value = value;
		}
	}
}
