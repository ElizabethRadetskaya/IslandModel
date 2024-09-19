package com.radetskaya.java.error;
/**
 * Виняток, що викидається при спробі обробити об'єкт, який не є формою життя.
 */
public class ObjectNotLifeFormException extends Exception {
    /**
     * Конструктор класу ObjectNotLifeFormException.
     *
     * @param msg Повідомлення про помилку
     */
    public ObjectNotLifeFormException(String msg) {
        super(msg);
    }
}
