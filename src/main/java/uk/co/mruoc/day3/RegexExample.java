package uk.co.mruoc.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {

    public static void main(String[] args) {
        // Input string
        String input = "xmul(2,4)&mul[3,7]!@^don't()_mul(5,5)+mul(32,64]then(mul(11,8)undo()?mul(8,5))";

        Pattern pattern2 = Pattern.compile("do\\(\\)|don't\\(\\)|mul\\((\\d{1,3}),(\\d{1,3})\\)");

        List<List<Integer>> mulPairs2 = new ArrayList<>();

        boolean isEnabled = true;

        Matcher matcher = pattern2.matcher(input);

        while (matcher.find()) {

            String match = matcher.group();

            if (match.equals("don't()")) {
                isEnabled = false;
            } else if (match.equals("do()")) {
                isEnabled = true;
            } else if (isEnabled && matcher.group(1) != null && matcher.group(2) != null) {
                List<Integer> temp = new ArrayList<>();
                temp.add(Integer.parseInt(matcher.group(1)));
                temp.add(Integer.parseInt(matcher.group(2)));
                mulPairs2.add(temp);
            }
        }

        int sum2 = 0;
        for (List<Integer> list : mulPairs2) {
            sum2 += list.get(0) * list.get(1);
        }

        System.out.println("Part 2 Answer:" + sum2);
    }
}
