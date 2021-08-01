package hw2_5;

import java.util.Arrays;
import java.util.Date;

public class Main {
    public static final int SIZE = 10000000;
    public static Date dateNow = new Date();

    public static void main(String[] args) throws InterruptedException {
        float[] arr = new float[SIZE];
        System.out.printf("Заполнение массива. Время: %tc \n", System.currentTimeMillis());
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }
        processArray(arr);
        processArray1(arr);
    }

    public static void processArray(float[] arr) {
        float[] arr1 = new float[SIZE];
        System.arraycopy(arr, 0, arr1, 0, SIZE);
        long time;

        time = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.printf("Переопределение массива. Время: %tc \n", System.currentTimeMillis());
        System.out.printf("Переопределение массива заняло %s милисекунд \n", (System.currentTimeMillis() - time));
    }

    public static void processArray1(float[] arr) throws InterruptedException {
        float[] arr1 = new float[SIZE / 2];
        float[] arr2 = new float[SIZE / 2];
        long time;

        Thread thread1 = new Thread(() -> {
            System.out.println("Обработка 1 массива");
            for (int i = 0; i < SIZE / 2; i++) {
                arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("Обработка 2 массива");
            for (int i = 0; i < SIZE / 2; i++) {
                arr2[i] = (float) (arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        System.out.printf("Заполнение массива в паралельном потоке. Время: %tc \n", System.currentTimeMillis());
        time = System.currentTimeMillis();

//        System.out.println(Arrays.toString(arr));
        System.out.println("Разбивка массива");
        System.arraycopy(arr, 0, arr1, 0, SIZE/2);
        System.arraycopy(arr, 0, arr2, 0, SIZE/2);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("Склеивание массива");
        System.arraycopy(arr1, 0, arr, 0, arr1.length);
        System.arraycopy(arr2, 0, arr, SIZE/2, arr2.length);

        System.out.printf("Переопределение массива. Время: %tc \n", System.currentTimeMillis());
        System.out.printf("Переопределение массива заняло %s милисекунд \n", (System.currentTimeMillis() - time));
    }
}
