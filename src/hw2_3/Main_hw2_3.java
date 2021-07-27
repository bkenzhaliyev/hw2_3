package hw2_3;
import java.util.*;

public class Main_hw2_3 {
    public static void main(String[] args) {
        arrList();
        phone();
    }

    /**
     * 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
     * Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
     * Посчитать сколько раз встречается каждое слово.
     */
    public static void arrList() {
        ArrayList<String> arrayList = new ArrayList<>();
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();

        arrayList.add("каска");
        arrayList.add("вата");
        arrayList.add("стена");
        arrayList.add("дерево");
        arrayList.add("магазин");

        arrayList.add("каска");
        arrayList.add("ведро");
        arrayList.add("игрушка");
        arrayList.add("дерево");
        arrayList.add("ларек");

        arrayList.add("каска");
        arrayList.add("вата");
        arrayList.add("кирпич");
        arrayList.add("дерево");
        arrayList.add("магазин");

        System.out.println(arrayList);

        set.addAll(arrayList);
        System.out.println(set);

        int num = 0;
        for (String a : arrayList) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        System.out.println(map);
    }

    /**
     * 2. Написать простой класс ТелефонныйСправочник,
     * который хранит в себе список фамилий и телефонных номеров.
     * В этот телефонный справочник с помощью метода add() можно добавлять записи.
     * С помощью метода get() искать номер телефона по фамилии.
     * Следует учесть, что под одной фамилией может быть несколько телефонов,
     * тогда при запросе такой фамилии должны выводиться все телефоны.
     */
    public static void phone() {
        ArrayList<String> arrayList = new ArrayList<>();
        PhoneBook phoneBook = new PhoneBook();

        arrayList.add("+7(777)101-00-10");
        arrayList.add("+7(777)121-00-11");
        arrayList.add("+7(777)161-00-12");


        phoneBook.add("Ivanov", arrayList);
        arrayList.removeAll(arrayList);

        arrayList.add("+7(707)100-10-10");
        arrayList.add("+7(701)100-10-12");

        phoneBook.add("Petrov", arrayList);
        arrayList.removeAll(arrayList);

        arrayList.add("+7(701)235-03-90");

        phoneBook.add("Sidorov", arrayList);


        System.out.println(phoneBook.get("Sidorov"));
        System.out.println(phoneBook.get("Ivanov"));
        System.out.println(phoneBook.get("Petrov"));

    }
}
