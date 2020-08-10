package org.ivzh.strings

public class StringExpressionsCalculator {

    static Map<String, Integer> numbers = new HashMap<>();
    static Map<Integer, String> numbersReversed = new HashMap<>();
    static Map<String, String> operations = new HashMap<>();

    static {
        numbers.put("zero", 0);
        numbers.put("one", 1);
        numbers.put("two", 2);
        numbers.put("three", 3);
        numbers.put("four", 4);
        numbers.put("five", 5);
        numbers.put("six", 6);
        numbers.put("seven", 7);
        numbers.put("eight", 8);
        numbers.put("nine", 9);

        operations.put("plus", "+");
        operations.put("minus", "-");


        for (Map.Entry<String, Integer> e : numbers.entrySet()) {
            numbersReversed.put(e.getValue(), e.getKey());
        }
    }

    public static void main(String[] args) {
        List<String> stack = string2int("onezeropluseight");
        int result = calculate(stack);
        System.out.println(convert2String(result));
    }

    private static int calculate(List<String> list) {
        Integer result = 0;
        List<Integer> numbersHolder = new ArrayList<>();
        List<String> operationsHolder = new ArrayList<>();
        for (String expression : list) {
            if (numbers.containsKey(expression)) {
                int partOfNumber = numbers.get(expression);
                result = result * 10 + partOfNumber;

            } else if (operations.containsKey(expression)) {
                numbersHolder.add(result);
                result = 0;

                operationsHolder.add(operations.get(expression));
            }

        }
        numbersHolder.add(result);

        int i = 0;
        int j = 1;
        int k = 0;
        result = 0;
        while (i < operationsHolder.size()) {
            String operation = operationsHolder.get(k++);
            int buffer = 0;
            if ("+".equalsIgnoreCase(operation) ) {
                buffer = numbersHolder.get(i) + numbersHolder.get(j);
            } else {
                buffer = numbersHolder.get(i) - numbersHolder.get(j);
            }
            result = result +buffer;
            i = j + 1;
            j+=2;

        }
        return result;
    }

    private static List<String> string2int(String expression) {
        List<String> result = new LinkedList<>();
        int i = 0;
        int j = 1;
        String forValidate = "" + expression.charAt(i);
        while (j < expression.length()) {
            forValidate = forValidate + expression.charAt(j);
            if (numbers.containsKey(forValidate) || operations.containsKey(forValidate)) {
                result.add(forValidate);
                i = j + 1;
                ++j;
                if (i <= expression.length() -1) {
                    forValidate = "" + expression.charAt(i);
                }
            }
            ++j;
        }

        return result;
    }

    private static String convert2String(int number) {
        StringBuilder result = new StringBuilder(number < 0 ? "negative" : "");
        if (number < 0) {
            number = number * -1;
        }
        String string = Integer.toString(number);
        for (char ch : string.toCharArray()) {
            result.append(numbersReversed.get(Integer.valueOf(String.format("%s", ch))));
        }
        return result.toString();
    }
}
