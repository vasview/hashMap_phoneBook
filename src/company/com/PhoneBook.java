package company.com;

import java.util.*;

public class PhoneBook {
    //объяляем карту для номеров телефонов. в качестве ключа будем использовать номер телефона.
    //значение будет представлять имя пользователя
    public static HashMap<String, String> phones = new HashMap<String, String>();

    public static void main(String[] args) {
        //получаем к-во запросов
        int numberOfRequests = getRequestNumbers();

        while (numberOfRequests > 0) {
            phoneBookOperations();
            numberOfRequests -= 1;
        }
    }

    //получаем к-во запросов пользователя
    public static Integer getRequestNumbers() {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите к-во запросов : ");
        Integer requestNumber = input.nextInt();
        //проверяем на установленные ограничения
        while (requestNumber == 0 || requestNumber > 105) {
            if (requestNumber == 0) {
                System.out.println("Количество запросов должжно быть больше 0");
            }
            if (requestNumber > 105) {
                System.out.println("Количество запросов должжно быть меньше 105");
            }
            System.out.print("Введите к-во запросов : ");
            requestNumber = input.nextInt();
        }
        return requestNumber;
    }

    //метод для манипуляции с телефонной книгой
    public static void phoneBookOperations() {
        //объявляем переменные для хранения имени и телефона пользователя
        String userName;
        String userPhone;
        //объявляем переменную для получения строки запроса.
        Scanner input = new Scanner(System.in);
        //объявляем массив для временного хранения строки ввода
        Queue<String> commandList = new LinkedList<>();
        //объявляем переменную для получения строки ввода
        String userRequest;

        System.out.print("Введите запрос: ");
        userRequest = input.nextLine();

        //заполняем очередь введенных коменд пользователя
        for(String s : userRequest.split(" ")) {
            commandList.add(s);
        }

        //выполняем требуемое действие пользователя в зависимости от введенной команды
        switch (commandList.poll()) {
            case "add":
                userPhone = commandList.poll();
                userName = capitalize(commandList.poll());
                phones.put(userPhone, userName);
                System.out.println(userName + " added");
                break;
            case "find":
                userPhone = commandList.poll();
                System.out.println(phones.getOrDefault(userPhone, "Not found"));
                break;
            case "del":
                userPhone = commandList.poll();
                if (phones.containsKey(userPhone)) {
                    userName = phones.get(userPhone);
                    phones.remove(userPhone);
                    System.out.println(userName + " deleted");
                } else {
                    System.out.println(" ");
                }
        }
    }

    //метод для получения имени пользователя с заглавной буквы
    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }
}
