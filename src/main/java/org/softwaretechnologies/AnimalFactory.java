package org.softwaretechnologies;

import org.softwaretechnologies.animals.*;

public class AnimalFactory
{
    public static Animal createAnimal(String name, AnimalType type)
    {
        return switch (type)
        {
            case CAT -> new Cat(name);
            case DOG -> new Dog(name);
            case COW -> new Cow(name);
            default -> throw new IllegalArgumentException("Unknown animal type: " + type);
        };
    }
}