import java.util.*;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n=== Лабораторная №4 (Generics) | Вариант 9 ===");
            System.out.println("1) 1.3 + 1.4: Student compare (сравнение по среднему)");
            System.out.println("2) 2.3: Box + добавить случайную Point3D");
            System.out.println("3) 3.1: map");
            System.out.println("4) 3.2: filter");
            System.out.println("5) 3.3: reduce");
            System.out.println("6) 3.4: collect");
            System.out.println("0) Выход");

            int choice = readInt("Выбор: ");

            switch (choice) {
                case 1 -> task1_studentCompare();
                case 2 -> task2_boxPoint3D();
                case 3 -> task3_map();
                case 4 -> task3_filter();
                case 5 -> task3_reduce();
                case 6 -> task3_collect();
                case 0 -> {
                    System.out.println("Пока!");
                    return;
                }
                default -> System.out.println("Нет такого пункта меню.");
            }
        }
    }

    // ================== 1.3 + 1.4 ==================

    private static void task1_studentCompare() {

        System.out.println("\n--- 1.3 + 1.4: Сравнение студентов по среднему ---");

        String name1 = readString("Имя студента 1: ");
        int[] g1 = readGrades("Оценки студента 1 (через пробел): ");

        String name2 = readString("Имя студента 2: ");
        int[] g2 = readGrades("Оценки студента 2 (через пробел): ");

        Student s1 = new Student(name1, g1);
        Student s2 = new Student(name2, g2);

        System.out.println("S1 = " + s1 + ", avg=" + s1.getAverageGrade());
        System.out.println("S2 = " + s2 + ", avg=" + s2.getAverageGrade());

        int cmp = s1.compare(s2);

        if (cmp > 0) System.out.println("Студент 1 сильнее по среднему баллу");
        else if (cmp < 0) System.out.println("Студент 2 сильнее по среднему баллу");
        else System.out.println("Средние баллы равны");
    }

    // ================== 2.3 ==================

    private static void task2_boxPoint3D() {

        System.out.println("\n--- 2.3: Box + добавить случайную 3D точку ---");

        Box<Object> box = new Box<>();

        System.out.println("До: " + box);

        Point3DAdd.addRandomPoint3D(box);

        System.out.println("После addRandomPoint3D: " + box);

        Object taken = box.take();
        System.out.println("Достали: " + taken);

        System.out.println("После take: " + box);
    }

    // ================== 3.1 ==================

    private static void task3_map() {

        System.out.println("\n--- 3.1: map ---");

        List<String> words = List.of("qwerty", "asdfg", "zx");
        System.out.println("Слова: " + words);

        List<Integer> lengths = GenericMethods.map(words, String::length);
        System.out.println("Длины: " + lengths);

        List<Integer> nums = List.of(1, -3, 7);
        System.out.println("Числа: " + nums);

        List<Integer> abs = GenericMethods.map(nums, Math::abs);
        System.out.println("ABS: " + abs);

        List<int[]> arrays = List.of(new int[]{1, 5, 2}, new int[]{-7, -2}, new int[]{10, 3, 9});
        List<Integer> maxs = GenericMethods.map(arrays, arr -> {
            int m = arr[0];
            for (int v : arr) if (v > m) m = v;
            return m;
        });

        System.out.println("Максимумы массивов: " + maxs);
    }

    // ================== 3.2 ==================

    private static void task3_filter() {

        System.out.println("\n--- 3.2: filter ---");

        List<String> words = List.of("qwerty", "asdfg", "zx");
        System.out.println("Слова: " + words);

        System.out.println(">=3 символов: " + GenericMethods.filter(words, s -> s.length() >= 3));

        List<Integer> nums = List.of(1, -3, 7, 0, -2);
        System.out.println("Числа: " + nums);

        System.out.println("Только <=0: " + GenericMethods.filter(nums, x -> x <= 0));

        List<int[]> arrays = List.of(new int[]{0, -1}, new int[]{-7, -2}, new int[]{1, -3});
        List<int[]> noPositive = GenericMethods.filter(arrays, arr -> {
            for (int v : arr) if (v > 0) return false;
            return true;
        });

        System.out.println("Массивы без положительных:");
        for (int[] a : noPositive) {
            System.out.println(Arrays.toString(a));
        }
    }

    // ================== 3.3 ==================

    private static void task3_reduce() {

        System.out.println("\n--- 3.3: reduce ---");

        List<String> words = List.of("qwerty", "asdfg", "zx");
        String joined = GenericMethods.reduce(words, (a, b) -> a + b, "");
        System.out.println("Склейка строк: " + joined);

        List<Integer> nums = List.of(1, -3, 7);
        Integer sum = GenericMethods.reduce(nums, Integer::sum, 0);
        System.out.println("Сумма: " + sum);

        // Пример: общее количество элементов во всех списках
        List<List<Integer>> lists = List.of(List.of(1, 2, 3), List.of(), List.of(10, 20));
        List<Integer> sizes = GenericMethods.map(lists, List::size);
        Integer total = GenericMethods.reduce(sizes, Integer::sum, 0);
        System.out.println("Всего элементов во всех списках: " + total);

        // Пустой список (проверка на отсутствие ошибок)
        List<Integer> empty = List.of();
        Integer safe = GenericMethods.reduce(empty, Integer::sum, 0);
        System.out.println("Пустой список -> " + safe);
    }

    // ================== 3.4 ==================

    private static void task3_collect() {

        System.out.println("\n--- 3.4: collect ---");

        // 1) числа -> pos/neg в map
        List<Integer> nums = List.of(1, -3, 7, 0, -2);

        Map<String, List<Integer>> split = GenericMethods.collect(
                nums,
                HashMap::new,
                (map, x) -> {
                    map.computeIfAbsent("pos", k -> new ArrayList<>());
                    map.computeIfAbsent("neg", k -> new ArrayList<>());
                    if (x >= 0) map.get("pos").add(x);
                    else map.get("neg").add(x);
                }
        );

        System.out.println("pos/neg: " + split);

        // 2) строки -> группы по длине
        List<String> words = List.of("qwerty", "asdfg", "zx", "qw", "a");

        Map<Integer, List<String>> byLen = GenericMethods.collect(
                words,
                HashMap::new,
                (map, str) -> map.computeIfAbsent(str.length(), k -> new ArrayList<>()).add(str)
        );

        System.out.println("Группы по длине: " + byLen);

        // 3) строки -> Set
        List<String> words2 = List.of("qwerty", "asdfg", "qwerty", "qw");
        Set<String> uniq = GenericMethods.collect(words2, HashSet::new, Set::add);
        System.out.println("Уникальные (Set): " + uniq);
    }

    // ================== helpers ==================

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

    private static String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
            if (s != null && !s.trim().isEmpty()) return s.trim();
            System.out.println("Ошибка: строка не должна быть пустой.");
        }
    }

    private static int[] readGrades(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();

            if (line.isEmpty()) {
                return new int[0]; // можно без оценок
            }

            String[] parts = line.split("[,\\s]+");
            int[] res = new int[parts.length];

            try {
                for (int i = 0; i < parts.length; i++) {
                    res[i] = Integer.parseInt(parts[i]);
                }
                return res;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: вводи числа через пробел или запятую. Пример: 3 4 5");
            }
        }
    }
}
