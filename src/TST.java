/*
Below class taken from Algorithms 4th Edition by Roger Sedgewick and Kevin Wayne
 */
public class TST {
    private Node root;

    private class Node {
        private char c;
        private Node left, mid, right;
        private int val;
    }

    public int get(String key){
        Node x = get(root, key, 0);
        if (x == null){
            return -1;
        }
        return x.val;
    }

    private Node get(Node x, String key, int d){
        if (x == null){
            return null;
        }
        char c = key.charAt(d);
        if (c < x.c){
            return get(x.left, key, d);
        }
        else if (c > x.c){
            return get(x.right, key, d);
        }
        else if (d < key.length() - 1){
            return get(x.mid, key, d+1);
        }
        else{
            return x;
        }

        public void put(String key, int val){
            root = put(root, key, val, 0);
        }

        private Node put(Node x, String key, int val, int d){
            char c = key.charAt(d);
            if (x == null){
                x = new Node();
                x.c = c;
            }
            if (c < x.c){
                x.left = put(x.left, key, d);
            }
            else if (c > x.c){
                x.right = put(x.right, key, d);
            }
            else if (d < key.length() - 1){
                x.mid = put(x.mid, key, d+1);
            }
            else{
                x.val = val;
            }
        }
        return x;
    }
}
