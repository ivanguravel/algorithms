package org.ivzh.sqrt.decomposition;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Long.parseLong;


// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=20&id_topic=45&id_problem=600
public class SqrtDecomposition {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new SqrtDecomposition().run();
    }

    private void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out))) {
            this.reader = reader;
            this.writer = writer;
            this.tokenizer = null;
            solve();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void solve() {
        long n = nextLong();

        Decomposition decomposition = new Decomposition((int) n);

        long[] arr = new long[(int)n+1];

        for (int i = 1; i <= n; i++) {
            arr[i] = nextLong();
            decomposition.insert(i, arr);
        }

        long m = nextLong();
        String operationType;
        for (int i = 0; i < m; i++) {
            operationType = nextToken();

            if ("rsq".equals(operationType)) {
                writer.println(decomposition.rsq((int) nextLong(), (int) nextLong(), arr));
            } else if ("rmq".equals(operationType)) {
                writer.println(decomposition.rmq((int) nextLong(), (int) nextLong(), arr));
            } else if ("get".equals(operationType)) {
                writer.println(decomposition.get((int) nextLong(), arr));
            } else if ("update".equals(operationType)) {
                decomposition.update((int) nextLong(), (int) nextLong(), nextLong(), arr);
            } else {
                decomposition.add((int) nextLong(), (int) nextLong(), nextLong(), arr);
            }
        }
    }

    private String nextToken() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    private long nextLong() {
        return parseLong(nextToken());
    }



    static class Decomposition {
        long[] min;
        long[] sum;
        long[] add;
        long[] value;
        Long[] update;
        int k;
        int n;

        public Decomposition(int n) {
            this.n = n;
            this.k = 128;
            int arrSize= 1 + (n-1) / k;
            this.min = new long[arrSize];
            Arrays.fill(this.min, Integer.MAX_VALUE);
            this.sum = new long[arrSize];
            this.add = new long[arrSize];
            this.value = new long[arrSize];
            this.update = new Long[arrSize];
        }

        public long get (int i, long[] array) {
            if (update[i/k] != null) {
                return update[i/k];
            } else {
                return array[i] + add[i/k];
            }
        }

        public void update(int l, int r, long val, long[] array) {
            int leftGroup = l / k;
            int rightGroup = r / k;

            if (leftGroup == rightGroup) {
                push(leftGroup, array);
                for (int i = l; i <= r; i++) {
                    array[i] = val;
                }
                updateSumAndMin(leftGroup, array);
            } else {
                push(leftGroup, array);
                for (int i = l; i <= Math.min(n+1, (leftGroup + 1) * k); i++) {
                    array[i] = val;
                }
                updateSumAndMin(leftGroup, array);
                for (int i = leftGroup + 1; i < rightGroup; i++) {
                    update[i] = val;
                }
                push(rightGroup, array);
                for (int i = groupStart(rightGroup); i <= r; i++) {
                    array[i] = val;
                }
                updateSumAndMin(rightGroup, array);
            }

        }

        private void updateSumAndMin(int group, long[] array) {
            int start = groupStart(group);
            int end = Math.min(n+1, (group + 1) * k);
            min[group] = Integer.MAX_VALUE;
            sum[group] = 0;
            for (int i = start; i < end; i++) {
                min[group] = Math.min(min[group], array[i]);
                sum[group] = sum[group] + array[i];
            }
        }

        void push(int group, long[] array) {
            int start = groupStart(group);
            int end = Math.min(n+1, (group + 1) * k);
            if (update[group] != null) {
                min[group] = Integer.MAX_VALUE;
                sum[group] = 0;
                for (int i = start; i < end; i++) {
                    array[i] = update[group];
                    min[group] = Math.min(min[group], array[i]);
                    sum[group] += array[i];
                }

                update[group] = null;
                add[group] = 0;

            } else {
                for (int i = start; i < end; i++) {
                    array[i] = array[i] + add[group];
                }
                add[group] = 0;
            }
        }


        public void add(int l, int r, long val, long[] array) {
            int leftGroup = l / k;
            int rightGroup = r / k;

            if (leftGroup == rightGroup) {
                push(leftGroup, array);
                for (int i = l; i <= r; i++) {
                    array[i] += val;
                }
                updateSumAndMin(leftGroup, array);
            } else {
                push(leftGroup, array);
                for (int i = l; i <= Math.min(n+1, (leftGroup + 1) * k); i++) {
                    array[i] += val;
                }
                updateSumAndMin(leftGroup, array);
                for (int i = leftGroup + 1; i < rightGroup; i++) {
                    if (update[i] == null) {
                        add[i] += val;
                        min[i] += val;
                        sum[i] += k* val;
                    } else {
                        update[i] += val;
                    }
                }
                push(rightGroup, array);
                for (int i = groupStart(rightGroup); i <= r; i++) {
                    array[i] += val;
                }
                updateSumAndMin(rightGroup, array);
            }
        }

        public void insert(int i, long[] array) {
            min[i/k] = Math.min(min[i/k], array[i]);
            sum[i/k] = sum[i/k] + array[i];
        }

        public long rmq(int l, int r, long[] array) {
            long answer = Integer.MAX_VALUE;

            int leftGroup = l / k;
            int rightGroup = r / k;

            if (leftGroup == rightGroup) {
                push(leftGroup, array);
                for (int i = l; i <= r; i++) {
                    answer = Math.min(answer, array[i]);
                }
                updateSumAndMin(leftGroup, array);
            } else {
                push(leftGroup, array);
                for (int i = l; i <= Math.min(n+1, (leftGroup + 1) * k); i++) {
                    answer = Math.min(answer, array[i]);
                }
                updateSumAndMin(leftGroup, array);
                for (int i = leftGroup + 1; i < rightGroup; i++) {
                    if (update[i] == null) {
                        answer = Math.min(answer, min[i]);
                    } else {
                        answer = Math.min(answer, update[i]);
                    }
                }
                push(rightGroup, array);
                for (int i = groupStart(rightGroup); i <= r; i++) {
                    answer = Math.min(answer, array[i]);
                }
                updateSumAndMin(rightGroup, array);
            }
            return answer;
        }

        public long rsq(int l, int r, long[] array) {
            long answer = 0;

            int leftGroup = l / k;
            int rightGroup = r / k;

            if (leftGroup == rightGroup) {
                push(leftGroup, array);
                for (int i = l; i <= r; i++) {
                    answer += array[i];
                }
                updateSumAndMin(leftGroup, array);
            } else {
                push(leftGroup, array);
                for (int i = l; i <= Math.min(n+1, (leftGroup + 1) * k); i++) {
                    answer += array[i];
                }
                updateSumAndMin(leftGroup, array);
                for (int i = leftGroup + 1; i < rightGroup; i++) {
                    if (update[i] == null) {
                        answer += add[i];
                    } else {
                        answer = update[i] * k;
                    }
                }
                push(rightGroup, array);
                for (int i = groupStart(rightGroup); i <= r; i++) {
                    answer += array[i];
                }
                updateSumAndMin(rightGroup, array);
            }
            return answer;
        }

        int groupStart(int g) {
            if (g ==0) {
                return 1;
            } else {
                return g * k;
            }
        }
    }


}