public class BSTMap<K extends Comparable<? super K>, T> implements Map<K, T> {

  
  
   private class Node {
      K key;
      T data;
      Node left, right;
   
      Node(K key, T data) {
         this.key = key;
         this.data = data;
         left = right = null; }
   }//end class Node
   
   

   private Node root;
   private int size;

   public BSTMap() {
      root = null;
      size = 0; }

   @Override
   public int size() {
      return size; }

   @Override
   public boolean empty() {
      return size == 0; }

   @Override
   public void clear() {
      root = null;
      size = 0;}

   @Override
   public boolean insert(K key, T data) {
      if (root == null) {
         root = new Node(key, data);
         size++;
         return true;
      }
      
   
      Node current = root;
      Node parent = null;
   
      while (current != null) {
         parent = current;
      
         int cmp = key.compareTo(current.key);
      
         if (cmp == 0)
            return false;
         
         else if (cmp < 0)
            current = current.left;
         
         else
            current = current.right;
      }
   
      int cmp = key.compareTo(parent.key);
   
      if (cmp < 0)
         parent.left = new Node(key, data);
      
      else
         parent.right = new Node(key, data);
   
      size++;
      return true;
   }

   @Override
   public T get(K key) {
      Node current = root;
   
      while (current != null) {
      
         int cmp = key.compareTo(current.key);
      
         if (cmp == 0)
            return current.data;
         
         else if (cmp < 0)
            current = current.left;
         
         else
            current = current.right;
      }
   
      return null;
   }

   @Override
   public boolean update(K key, T e) {
   
      Node current = root;
   
      while (current != null) {
      
         int cmp = key.compareTo(current.key);
      
         if (cmp == 0) {
            current.data = e;
            return true;
         }
         
         else if (cmp < 0)
            current = current.left;
         
         else
            current = current.right;
      }
   
      return false;
   }

   @Override
   public boolean remove(K key) {
   
      Node current = root;
      Node parent = null;
   
      while (current != null) {
      
         int cmp = key.compareTo(current.key);
      
         if (cmp == 0)
            break;
      
         parent = current;
      
         if (cmp < 0)
            current = current.left;
         
         else
            current = current.right;
      }
   
      if (current == null)
         return false;
   
      // case 1: no children
      if (current.left == null && current.right == null) {
      
         if (current == root)
            root = null;
         
         else 
         
         if (parent.left == current)
            parent.left = null;
         
         else
            parent.right = null;
      }
      
      // case 2: one child
      else
      
       if (current.left == null || current.right == null) {
      
         Node child;
      
         if (current.left != null)
            child = current.left;
         
         else
            child = current.right;
      
         if (current == root)
            root = child;
         
         else if (parent.left == current)
            parent.left = child;
         
         else
            parent.right = child;
      }
      
      // case 3: two children
      else {
      
         Node successor = current.right;
         Node successorParent = current;
      
         while (successor.left != null) {
            successorParent = successor;
            successor = successor.left;
         }
      
         current.key = successor.key;
         current.data = successor.data;
      
         if (successorParent.left == successor)
            successorParent.left = successor.right;
         
         else
            successorParent.right = successor.right;
      }
   
      size--;
      return true;
   }

   @Override
   public int nbKeyComp(K key) {
   
      Node current = root;
      int count = 0;
   
      while (current != null) {
      
         count++;
      
         int cmp = key.compareTo(current.key);
      
         if (cmp == 0)
            return count;
         
         else if (cmp < 0)
            current = current.left;
         
         else
            current = current.right;
      }
   
      return count;
   }

   @Override
   public List<K> getKeys() {
   
      LinkedList<K> list = new LinkedList<>();
      inorder(root, list);
      return list;
   }

   private void inorder(Node node, LinkedList<K> list) {
   
      if (node != null) {
      
         inorder(node.left, list);
      
         list.insert(node.key);
      
         inorder(node.right, list);
      }
   }
}