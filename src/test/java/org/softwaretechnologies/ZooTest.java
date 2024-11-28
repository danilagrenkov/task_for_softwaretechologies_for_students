package org.softwaretechnologies;

import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.softwaretechnologies.animals.AnimalType;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ZooTest
{
    @Test
    void soundAllAnimalsSortByName()
    {
        org.softwaretechnologies.Zoo zoo = new org.softwaretechnologies.Zoo();

        zoo.addAnimal(org.softwaretechnologies.AnimalFactory.createAnimal("zuzu", AnimalType.CAT));
        zoo.addAnimal(org.softwaretechnologies.AnimalFactory.createAnimal("abu", AnimalType.COW));
        zoo.addAnimal(org.softwaretechnologies.AnimalFactory.createAnimal("bob", AnimalType.DOG));
        zoo.addAnimal(org.softwaretechnologies.AnimalFactory.createAnimal("yoyo", AnimalType.CAT));

        List<String> sounds = zoo.soundAllAnimalsSortByName();
        List<String> expected = Arrays.asList("moo", "woof", "meow", "meow");

        int i = 0;
        for (String sound : sounds)
        {
            assertEquals(expected.get(i), sound);
            i++;
        }
    }
}