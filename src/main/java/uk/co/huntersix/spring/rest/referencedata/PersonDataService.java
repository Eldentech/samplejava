package uk.co.huntersix.spring.rest.referencedata;

import org.springframework.stereotype.Service;
import uk.co.huntersix.spring.rest.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonDataService {
    public final HashMap<Long, Person> PERSON_DATA = new HashMap<>();

    public PersonDataService() {
        Person mary = new Person("Mary", "Smith");
        Person brian = new Person("Brian", "Archer");
        Person archer = new Person("Collin", "Brown");
        PERSON_DATA.put(mary.getId(), mary);
        PERSON_DATA.put(brian.getId(), brian);
        PERSON_DATA.put(archer.getId(), archer);
    }

    public Person findPerson(String lastName, String firstName) {
        List<Person> collect = PERSON_DATA.values().stream()
                .filter(p -> p.getFirstName().equalsIgnoreCase(firstName)
                        && p.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
        return collect.isEmpty() ? null : collect.get(0);
    }

    public List<Person> findPerson(String lastName) {
        return PERSON_DATA.values().stream()
                .filter(p -> p.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    public Person addPerson(Person person) {
        PERSON_DATA.put(person.getId(), person);
        return person;
    }
}
