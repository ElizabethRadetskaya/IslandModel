package com.radetskaya.java.lifeform;

import java.util.concurrent.Executors;

public class ExecutorService {
    ExecutorService executor = Executors.newFixedThreadPool(10);

// Пример выполнения нескольких действий животных параллельно
executor.submit(() -> wolf.eat());
executor.submit(() -> rabbit.move());
executor.submit(() -> bear.reproduce());

executor.shutdown();
}
