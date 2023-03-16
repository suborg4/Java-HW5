package org.example.hw05;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Подсчитать количество искомого слова,
 * через map (наполнением значение, где искомое слово будет являться ключом)
 */
public class Task01 {
    public static void main(String[] args) {
        String wordSearch;
        int select = 3;

        System.out.print("Введите вариант поиска:\n1 - По вводимому тексту;\n2 - По файлу;\nВведите цифру: ");
        Scanner s = new Scanner(System.in);
        if (s.hasNextInt()) {
            select = s.nextInt();
            s.nextLine();
        }

        if (select == 1) {
            System.out.print("Введите текст: ");
            String text = s.nextLine();
            System.out.print("Введите слово для поиска: ");
            wordSearch = s.nextLine();
            System.out.printf("Слово \"%s\" встречается %d раз(а).", wordSearch, countWord(text, wordSearch));
        } else if (select == 2) {
            System.out.print("Введите слово для поиска: ");
            wordSearch = s.nextLine();
            int count = 0;
            try (BufferedReader br = new BufferedReader(new FileReader("text.txt"))) {
                String text;
                while ((text = br.readLine()) != null) {
                    count += countWord(text, wordSearch);
                }
                System.out.printf("Слово \"%s\" встречается %d раз(а).", wordSearch, count);
            } catch (IOException e) {
                System.out.println("Ошибка при выполнении чтения файла: " + e);
            }
        } else System.out.println("Неизвестная команда!");
    }

    public static int countWord(String text, String wordSearch) {
        wordSearch = wordSearch.toLowerCase();
        text = text.replaceAll("[^а-яА-я\\s]", "").replaceAll("\\s{2,}", " ");
        String[] word = text.toLowerCase().split(" ");
        Map<String, Integer> countWordMap = new HashMap<>();
        countWordMap.put(wordSearch, 0);
        for (String s : word) {
            if (countWordMap.containsKey(s)) countWordMap.put(s, countWordMap.get(s) + 1);
        }
        return countWordMap.get(wordSearch);
    }
}