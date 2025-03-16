class LRUCache {
    //for doubly linked list
    class Node{
        int key;
        int value;
        Node prev;
        Node next;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    Node head;
    Node tail;
    int capacity;
    HashMap<Integer, Node> map;

    public LRUCache(int capacity) {
        
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        map = new HashMap<>();
        head.next = tail;
        tail.prev = head;
    }
    public void removeNode(Node curr){
        if(curr == null) return;
        
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
    }
    public void addToHead(Node curr){
        if(curr == null) return;
        
        curr.next = head.next;
        curr.prev = head;
        head.next = curr;
        curr.next.prev = curr;
    }
    public int get(int key) {
        Node curr;
        if(map.containsKey(key)){
            curr = map.get(key);
            //remove curr
            removeNode(curr);
            //add curr to head
            addToHead(curr);
            return curr.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        Node curr = new Node(key, value);
        if(map.containsKey(key)){
            Node node = map.get(key);
            map.remove(key);
            removeNode(node);
        }
        else if(capacity == map.size()){
            map.remove(tail.prev.key);
            removeNode(tail.prev);
        }
        map.put(key, curr);
        addToHead(curr);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
