package org.ivzh.other;

import java.util.*;

public class OrderBook {

    private static final String BID = "B";
    private static final String ASK = "S";

    int s;

    private Map<Long, Long> bid = new TreeMap<>(Collections.reverseOrder());
    private Map<Long, Long> ask = new TreeMap<>(Collections.reverseOrder());

    private List<java.util.Map.Entry<Long, Long>> result = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        new OrderBook().solve(sc);
    }

    private void solve(Scanner sc) {
        readData(sc);

        prepareResult(this.ask, ASK);
        prepareResult(this.bid, BID);

    }

    private void readData(Scanner sc) {
        int n = sc.nextInt();
        this.s = sc.nextInt();

        sc.nextLine();
        String line;
        String[] arr;
        Long price;
        Long quantity;
        for (int i = 0; i < n; i++) {
            line = sc.nextLine();
            arr = line.split(" ");
            price = Long.parseLong(arr[1]);
            quantity = Long.parseLong(arr[2]);
            if (BID.equalsIgnoreCase(arr[0])) {
                fillInOrderBook(this.bid, price, quantity);
            } else {
                fillInOrderBook(this.ask, price, quantity);
            }
        }
    }

    private void fillInOrderBook(Map<Long, Long> map, Long price, Long quantity) {
        if (map.containsKey(price)) {
            map.put(price, quantity + map.get(price));
        } else {
            map.put(price, quantity);
        }
    }

    private void prepareResult(Map<Long, Long> map, String side) {
        int index = 0;
        for (java.util.Map.Entry<Long, Long> e : map.entrySet()) {
            if (index++ == this.s) {
                break;
            }
            System.out.println(String.format("%s %s %s", side, e.getKey(), e.getValue()));
        }
    }
}
