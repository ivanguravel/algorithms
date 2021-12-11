package org.ivzh.stack;

public class Stack {

    NodeList start;
    NodeList current;

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.add(1);
        stack.add(2);
        stack.add(3);

        System.out.println(stack.poll());
        System.out.println(stack.poll());
        System.out.println(stack.poll());

        stack.add(4);
        System.out.println(stack.poll());
    }

    public void add(int add) {
        NodeList nodeList = new NodeList(add);
        if (start == null) {
            start = nodeList;
            current = start;
        } else {
            current.next = nodeList;
            current.prev = current;
            current = current.next;

        }
    }

    public int poll() {
        int res = start.val;

        start = start.next;
        if (start != null) {
            start.prev = null;
        }

        return res;
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
