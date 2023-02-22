package uk.co.huntersix.spring.rest.referencedata;

import org.junit.Before;
import org.junit.Test;
import uk.co.huntersix.spring.rest.model.Person;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;


public class PersonDataServiceTest {
    private PersonDataService personDataService;

    @Before
    public void setUp() throws Exception {
     personDataService  = new PersonDataService();
    }

    @Test
    public void findPerson_whenCalledProperly_shouldReturnResult() {
        Person person = personDataService.findPerson( "Smith", "Mary");
        assertThat(person).isNotNull();
    }

    @Test
    public void findPerson_whenCalledForANonExisting_shouldReturnNull() {
        Person person = personDataService.findPerson( "Doe", "Jhon");
        assertThat(person).isNull();
    }

    @Test
    public void findPerson_whenCalledWithLastName_shouldReturnPersonList() {
        List<Person> persons = personDataService.findPerson("Smith");
        assertNotNull(persons);
        assertThat(persons.size()).isEqualTo(1);
        assertThat(persons.get(0).getFirstName()).isEqualTo("Mary");
    }

    @Test
    public void findPerson_whenCalledWithANonExistingLastName_shouldReturnEmptyList() {
        List<Person> persons = personDataService.findPerson("Doe");
        assertNotNull(persons);
        assertThat(persons.size()).isEqualTo(0);
    }

    @Test
    public void addPerson_whenCalled_shouldWorkProperly() {
        Person personToAdd = new Person("Good", "Person");
        Person person = personDataService.addPerson(personToAdd);
        assertNotNull(person);
        assertThat(person).isEqualTo(personToAdd);
    }

    @Test
    public void addPerson_whenCalledForExistingItem_shouldUpdateUser() {
        Person mary = personDataService.findPerson( "Smith", "Mary");
        mary.setFirstName("Jane");
        personDataService.addPerson(mary);
        List<Person> persons = personDataService.findPerson("Smith");
        assertNotNull(persons);
        assertThat(persons.size()).isEqualTo(1);
    }
}
