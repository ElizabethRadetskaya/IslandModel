package com.radetskaya.java.simulation.thread.animalLifecycleTask.task;



import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Задача для размноження тварин на острові
 */
public class AnimalMultiplyTask implements Runnable {
    private int babies;//Це поле тримає кількість новонароджених тварин у рамках виконання цього завдання
    private final CountDownLatch latch;

    public AnimalMultiplyTask(CountDownLatch latch) {
        this.latch = latch;//Це об’єкт типу CountDownLatch, який використовується для синхронізації між потоками. Він дозволяє потокам чекати, поки всі задачі завершаться.
    } //Конструктор приймає об’єкт CountDownLatch, який буде використовуватись для зменшення лічильника по завершенню задачі.


    @Override
    public void run() {//Метод run викликається при виконанні потоку.
        // Основна його функція — знайти пари тварин одного виду в одному місці, які ще не розмножувалися, і запустити процес розмноження.
        babies = 0;//Встановлюється початкове значення babies = 0.
        List<Animal> animals = IslandField.getInstance().getAllAnimals();//Отримується список всіх тварин на острові
        List<Animal> animalsMultiplied = new ArrayList<>();//Створюється порожній список animalsMultiplied, який буде зберігати тварин, які вже взяли участь у розмноженні.
        if (animals.size() > 0) {//Перебір тварин: Для кожної тварини з основного списку перевіряється, чи вже вона брала участь у розмноженні.Якщо ні, знаходиться її локація на острові за допомогою координат і витягується список тварин у цій локації.
            for (Animal currentAnimal : animals) {
                if (!animalsMultiplied.contains(currentAnimal)) {
                    Location location = IslandField.getInstance().getLocation(currentAnimal.getRow(), currentAnimal.getColumn());
                    List<Animal> locationAnimals = location.getAnimals();


                    if (locationAnimals.size() > 1) {//Якщо в локації більше однієї тварини, то з них фільтруються тільки ті, що мають таку ж назву (вид) і не є поточною твариною.
                        locationAnimals = locationAnimals.stream().filter(c -> c.getName().equals(currentAnimal.getName()) && c != currentAnimal).toList();

                        if (locationAnimals.size() > 0) {
                            Animal partner = locationAnimals.get(0);

                            if (!animalsMultiplied.contains(partner)) {
                                currentAnimal.multiply(partner);

                                //Якщо знаходиться хоча б один можливий партнер для розмноження, тварини розмножуються методом multiply, і обидва партнери додаються до списку animalsMultiplied.
                                animalsMultiplied.add(currentAnimal);
                                animalsMultiplied.add(partner);

                                babies++;//новонароджених babies збільшується на 1
                            }
                        }
                    }
                }
            }
        }
        latch.countDown();
    }

    public int getBabies() {//Цей метод повертає кількість новонароджених тварин після завершення роботи задачі.
        return babies;
    }
}