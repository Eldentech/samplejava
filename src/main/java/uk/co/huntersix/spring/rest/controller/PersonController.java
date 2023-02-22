package uk.co.huntersix.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.huntersix.spring.rest.model.Person;
import uk.co.huntersix.spring.rest.referencedata.PersonDataService;

import java.util.List;

@RestController
public class PersonController {
    private PersonDataService personDataService;

    public PersonController(@Autowired PersonDataService personDataService) {
        this.personDataService = personDataService;
    }

    @GetMapping(value = "/person/{lastName}/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity<?> person(@PathVariable(value="lastName") String lastName,
                         @PathVariable(value="firstName") String firstName) {
        Person person = personDataService.findPerson(lastName, firstName);
        if(person  != null) {
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/person/{lastName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity<?> person(@PathVariable(value="lastName") String lastName){
        List<Person> person = personDataService.findPerson(lastName);
        return ResponseEntity.ok(person);
    }

    @PostMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    Person person(@RequestBody Person person){
        return personDataService.addPerson(person);
    }
}
