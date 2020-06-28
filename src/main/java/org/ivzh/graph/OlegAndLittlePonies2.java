package org.ivzh.graph;

import java.io.PrintWriter;
import java.util.*;

// https://timus.online/problem.aspx?space=1&num=2108
public class OlegAndLittlePonies2 {

    private static final String EMPTY = "";
    private static final char ONE = '1';

    int n;
    int m;
    Map<Node, List<Node>> wishes;
    Deque<Node> deque = new ArrayDeque<>();
    String base;
    Node presentToys;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new OlegAndLittlePonies2().solve(in, out);
    }

    // 0(n^3)
    void solve(Scanner in, PrintWriter out) {
        // read data
        readData(in);

        dfs(presentToys);

        out.println(presentToys.toString());
        out.flush();
    }

    // 0(n^3)
    void dfs(Node answer) {
        for (String wish : wishes) {
            if (containsSimilarBits(answer, wish)) {
                deque.add(wish);
            }
        }

        while (!deque.isEmpty()) {
            String wish = deque.pollFirst();
            fillAnswer(answer, wish);
            dfs(answer, wish);
        }
    }

    private void readData(Scanner in) {
        this.n = in.nextInt();
        this.m = in.nextInt();
        this.wishes = new HashMap<>(m + 1);
       // this.base = Stream.generate(() -> "0").limit(n).collect(Collectors.joining());
        in.nextLine();
        for (int i = 0 ; i < m; i++) {
            String s = in.nextLine();
            String[] split = s.split(" ");

            Node k = new Node(split[0]);
            Node v = new Node(split[1]);

            List<Node> nodes;
            if (this.wishes.containsKey(k)) {
                nodes = this.wishes.get(k);
                nodes.add(v);
                this.wishes.put(k, nodes);
            } else {
                nodes = new LinkedList<>();
                nodes.add(v);
                wishes.put(k, nodes);
            }
        }
        this.presentToys = new Node(in.nextLine());
    }

    private void fillAnswer(StringBuilder answerBuilder, String wish) {
        for (int i = 0; i < wish.length(); i++) {
            if (wish.charAt(i) == ONE) {
                answerBuilder.setCharAt(i, ONE);
            }
        }
    }

    // 0(1)
    private String createStringWithZerosInSuffix(int prefixSize, StringBuilder answerBuilder) {
        return new StringJoiner(EMPTY, answerBuilder.toString().substring(0, prefixSize), base.substring(prefixSize)).toString();
    }

    static class Node {
        Map<Integer, Character> bits = new HashMap<>();
        int enabledBitsCount = 0;

        public Node(String s) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ONE) {
                    ++enabledBitsCount;
                }
                bits.put(i, s.charAt(i));
            }
        }

        boolean containsSimilarBits(Node other) {
            if (this.enabledBitsCount == other.enabledBitsCount) {
                int count = 0;
                while (count <= enabledBitsCount) {
                    Character fromThis = this.bits.get(count);
                    Character fromThat = other.bits.get(count);

                    if (fromThis == null || fromThat == null) {
                        break;
                    } else if (fromThis == ONE && fromThat == ONE) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return containsSimilarBits(node);
        }

        @Override
        public int hashCode() {
            return Objects.hash(bits, enabledBitsCount);
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            for (Map.Entry<Integer, Character> e : this.bits.entrySet()) {
                result.setCharAt(e.getKey(), e.getValue());
            }
            return result.toString();
        }
    }
}
