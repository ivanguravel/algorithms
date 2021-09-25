package org.ivzh.queues;

public class Queue {

    NodeList current;

    public static void main(String[] args) {
        Queue q = new Queue();
        q.add(1);
        q.add(2);
        q.add(3);

        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());


        q.add(5);
        System.out.println(q.get());
        System.out.println(q.poll());
    }


    public void add(int val) {
        NodeList nodeList = new NodeList(val);
        if (current == null) {
            current = nodeList;
        } else {
            current.next = nodeList;
            nodeList.prev = current;
            current = current.next;
        }
    }

    public int poll() {
       int res = current.val;
       if (current.prev != null) {
           current = current.prev;
           current.next = null;
       }
       return res;
    }

    public int get() {
        return current.val;
    }


    static class NodeList {
        public int val;
        public NodeList next;
        public NodeList prev;

        public NodeList(int val) {
            this.val = val;
        }
    }
}
