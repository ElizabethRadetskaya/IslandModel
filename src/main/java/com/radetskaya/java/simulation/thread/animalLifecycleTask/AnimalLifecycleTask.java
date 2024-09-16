package com.radetskaya.java.simulation.thread.animalLifecycleTask;



import com.radetskaya.java.simulation.thread.animalLifecycleTask.task.AnimalEatTask;
import com.radetskaya.java.simulation.thread.animalLifecycleTask.task.AnimalHpDecreaseTask;
import com.radetskaya.java.simulation.thread.animalLifecycleTask.task.AnimalMoveTask;
import com.radetskaya.java.simulation.thread.animalLifecycleTask.task.AnimalMultiplyTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Реалізує задачу, яка управляє основними етапами життєвого циклу тварин на острові.
 * Він об’єднує кілька важливих процесів: харчування, розмноження, зменшення здоров’я та переміщення тварин
 * Цей клас моделює життєвий цикл тварин на острові у багатопотоковій симуляції, забезпечуючи виконання всіх важливих процесів паралельно і синхронізуючи їх для плавного виконання.
 */
public class AnimalLifecycleTask implements Runnable {
    private final AnimalEatTask animalEatTask;
    private final AnimalMultiplyTask animalMultiplyTask;
    private final AnimalHpDecreaseTask animalHpDecreaseTask;
    private final CountDownLatch latch;//використовується для синхронізації виконання цих трьох основних процесів.
    // Він ініціалізується зі значенням 3, оскільки три задачі повинні завершитися перед тим, як буде запущено наступний етап — переміщення тварин.

    public AnimalLifecycleTask() {//Конструктор створює всі необхідні задачі
        latch = new CountDownLatch(3);
        animalEatTask = new AnimalEatTask(latch);
        animalMultiplyTask = new AnimalMultiplyTask(latch);
        animalHpDecreaseTask = new AnimalHpDecreaseTask(latch);
    }

    @Override
    public void run() {//Цей метод викликається при запуску задачі, і в ньому виконується основний життєвий цикл тварин.
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.submit(animalEatTask); // тварини шукають їжу і харчуються
        executorService.submit(animalMultiplyTask); // тварини розмножуються
        executorService.submit(animalHpDecreaseTask); // зменшується здоров’я тварин
        try {
            latch.await(); // дозволяє зупинити потік до того моменту, поки всі три задачі не завершаться.
            // Тобто, він чекає, поки лічильник CountDownLatch зменшиться до нуля.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.submit(new AnimalMoveTask()); // Після завершення основних трьох етапів запускається нове завдання
        // — переміщення тварин на інші локації за допомогою задачі AnimalMoveTask.
    }
// це геттер-методи, які дозволяють отримати об’єкти відповідних задач
    public AnimalMultiplyTask getObjectMultiplyTask() {
        return animalMultiplyTask;
    }

    public AnimalEatTask getAnimalEatTask() {
        return animalEatTask;
    }

    public AnimalHpDecreaseTask getAnimalHpDecreaseTask() {
        return animalHpDecreaseTask;
    }
}