
import Tests.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    //  РЕЕСТР ТЕСТОВ — добавляйте новые тесты сюда
    private static final Map<Integer, ITest> TEST_REGISTRY = new LinkedHashMap<>();

    static {
        TEST_REGISTRY.put(1, new Neg_Registration());
        TEST_REGISTRY.put(2, new True_Registration());
        TEST_REGISTRY.put(3, new CartObjAdd());
        TEST_REGISTRY.put(4, new OrderComp());
        TEST_REGISTRY.put(5, new Sorting());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Показываем меню
        printMenu();

        System.out.print("Введите номер теста (или 'all' для запуска всех): ");
        String input = scanner.nextLine().trim().toLowerCase();

        if (input.equals("all")) {
            System.out.println("\n=== Запуск ВСЕХ тестов ===\n");
            for (Map.Entry<Integer, ITest> entry : TEST_REGISTRY.entrySet()) {
                runTest(entry.getKey(), entry.getValue());
            }
        } else {
            try {
                int testNumber = Integer.parseInt(input);
                ITest test = TEST_REGISTRY.get(testNumber);

                if (test != null) {
                    System.out.println("\n=== Запуск теста №" + testNumber + " ===\n");
                    runTest(testNumber, test);
                } else {
                    System.out.println("❌ Тест с номером " + testNumber + " не найден!");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Неверный ввод! Введите число или 'all'.");
            }
        }

        scanner.close();
        System.out.println("\n=== Работа завершена ===");
    }

    private static void runTest(int number, ITest test) {
        String testName = test.getClass().getSimpleName();
        System.out.println("--- [" + number + "] " + testName + " ---");
        try {
            test.setUp();
            test.execute();
            System.out.println("✅ " + testName + " пройден!\n");
        } catch (Exception e) {
            System.out.println("❌ " + testName + " упал: " + e.getMessage() + "\n");
            e.printStackTrace();
        } finally {
            test.tearDown();
        }
    }

    private static void printMenu() {
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║         МЕНЮ ЗАПУСКА ТЕСТОВ      ║");
        System.out.println("╠══════════════════════════════════╣");
        for (Map.Entry<Integer, ITest> entry : TEST_REGISTRY.entrySet()) {
            String testName = entry.getValue().getClass().getSimpleName();
            System.out.printf("║  %-5d -> %-30s║%n", entry.getKey(), testName);
        }
        System.out.println("║  all -> Запустить все тесты     ║");
        System.out.println("╚══════════════════════════════════╝");
    }
}