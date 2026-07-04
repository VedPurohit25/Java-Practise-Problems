import java.util.HashMap;
import java.util.Map;

class LRUCache {
    
    // Define the Doubly Linked List Node structure
    private class Node {
        int key;
        int value;
        Node prev;
        Node next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private final int capacity;
    private final Map<Integer, Node> map;
    // Dummy head and tail pointers to avoid null pointer checks
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        
        Node node = map.get(key);
        // Since it's accessed, move it to the head (most recently used)
        moveToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value; // Update value
            moveToHead(node);   // Mark as recently used
        } else {
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToHead(newNode);
            
            // If capacity is exceeded, evict the least recently used item
            if (map.size() > capacity) {
                Node lruNode = tail.prev;
                removeNode(lruNode);
                map.remove(lruNode.key);
            }
        }
    }
    
    // Helper method: Adds a node right after the dummy head
    private void addToHead(Node node) {
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }
    
    // Helper method: Detaches a node from its current position in the list
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    // Helper method: Moves an existing node to the front of the list
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */