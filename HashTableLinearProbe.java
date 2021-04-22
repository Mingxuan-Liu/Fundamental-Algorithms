package TenthWeek;

public class HashTableLinearProbe<Key, Value> {

    private int size;
    private int M = 16;
    private Key[] keys;
    private Value[] vals;

    public HashTableLinearProbe(int capacity) {
        this.M = capacity;
        this.keys = (Key[]) new Object[M];
        this.vals = (Value[]) new Object[M];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int capacity) {
        HashTableLinearProbe<Key, Value> t;
        t = new HashTableLinearProbe<Key, Value>(capacity);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null)
                t.put(keys[i],vals[i]);
        }
        this.keys = t.keys;
        this.vals = t.vals;
        this.M = t.M;
    }

    public void put(Key key, Value val) {
        if (this.size >= M/2)   // do a resize before it's full, so you have room to handle collisions
            resize(2*M);
        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        this.keys[i] = key;
        this.vals[i] = val;
        this.size++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (keys[i].equals(key))
                return vals[i];
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void delete(Key key) {

        if (! contains(key)) return;

        int i = hash(key);            // find where key is in array
        while (!key.equals(keys[i]))
            i = (i+1) % M;

        keys[i] = null;               // remove the key value pair there
        vals[i] = null;

        i = (i+1) % M;
        while (keys[i] != null) {          // reinsert keys possibly affected by deletion
            Key keyToRedo = keys[i];   // (think carefully both on why this is needed
            Value valToRedo = vals[i]; //  and why it works)
            keys[i] = null;
            vals[i] = null;
            size--;
            put(keyToRedo, valToRedo);
            i = (i+1) % M;
        }

        size--;                           // decrement count of items in hash table
        if (size > 0 && size == M/8)      // resize down, if necessary, so we don't waste space
            resize(M/2);
    }
}

