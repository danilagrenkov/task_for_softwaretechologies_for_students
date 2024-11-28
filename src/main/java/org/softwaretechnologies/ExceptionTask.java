package org.softwaretechnologies;;

import java.util.Optional;

public class ExceptionTask
{
    public static void printMessage()
    {
        System.out.println("Вызвана функция printMessage");
        throwRuntimeException();
    }

    public static void printMessage2() throws Exception
    {
        System.out.println("Вызвана функция printMessage2");
        throwCatchableException();
    }

    private static void throwCatchableException() throws Exception
    {
        throw new Exception();
    }

    private static void throwRuntimeException()
    {
        throw new RuntimeException();
    }

    public static int divide(int dividend, int divisor) throws DivideOnNullException
    {
        if (divisor == 0)
        {
            throw new DivideOnNullException();
        }

        return dividend / divisor;
    }

    public static Optional<String> mergeStrings(String first, String second)
    {
        if (first == null && second == null)
        {
            return Optional.empty();
        }

        if (first == null)
        {
            return Optional.of(second);
        }

        if (second == null)
        {
            return Optional.of(first);
        }

        return Optional.of(first.length() > second.length() ? first + second : second + first);
    }
}
