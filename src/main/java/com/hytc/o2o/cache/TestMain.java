package com.hytc.o2o.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TestMain {


    public static void main(String[] args) {


        List<String> lists = new ArrayList<>();
        lists.add("中国");
        lists.add("顾灿林");
        lists.add("计算机");

        try {
            List<String> all = new ArrayList<>();
            for (int i = 0; i < lists.size() - 1; i++) {
                List<String> find = ShowMeWords(lists);
                all.addAll(find);
                //变换list
                String tmp = null;
                tmp = lists.get(i);
                for (int j = i; j < lists.size() - j+1; j++) {
                    lists.set(j, lists.get(j + 1));
                }
                lists.set(lists.size() - 1, tmp);
            }
            all.forEach(s -> System.out.print(s + " \t"));
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }

    private static List<String> ShowMeWords(List<String> args) {

        if (args.size() > 8) {
            throw new RuntimeException("条数过多");
        }


        List<String> ss = getBuffer(args);
        return ss;

    }

    //1----2
    public static List<String> getBuffer(List<String> args) {

        Stack stack = new Stack();
        args.stream().forEach(s -> stack.push(s));

        String s1 = (String) stack.pop();
        List<String> lists = new ArrayList<>();
        for (char s : s1.toCharArray()) {
            lists.add(String.valueOf(s));
        }
        while (!stack.isEmpty()) {

            String s2 = (String) stack.pop();

            if (!lists.contains(s1)) {
                List looplists = new ArrayList<>();
                for (String list : lists) {
                    List<String> newList = getLoopBuffer(list, s2);
                    looplists.addAll(newList);
                }
                lists = new ArrayList<>();
                lists.addAll(looplists);
            } else {
                lists = getLoopBuffer(s1, s2);
            }
        }

        return lists;
    }


    private static List<String> getLoopBuffer(String first, String second) {

        List<String> result = new ArrayList<>();
        for (char achar : second.toCharArray()) {
            StringBuilder sb = new StringBuilder();
            sb.append(first);
            sb.append(achar);
            result.add(String.valueOf(sb));

        }
        return result;
    }

}

