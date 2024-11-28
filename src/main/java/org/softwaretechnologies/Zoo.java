package org.softwaretechnologies;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import org.softwaretechnologies.animals.Animal;

public class Zoo
{
    private final List<Animal> animalList = new ArrayList<>();

    public void addAnimal(Animal animal)
    {
        animalList.add(animal);
    }

    public List<String> soundAllAnimalsSortByName()
    {
        return animalList.stream().sorted(Comparator.comparing(Animal::getName)).map(Animal::sound).collect(Collectors.toList());
    }
}