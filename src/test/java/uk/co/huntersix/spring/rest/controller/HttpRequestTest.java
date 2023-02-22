package uk.co.huntersix.spring.rest.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.huntersix.spring.rest.model.Person;
import uk.co.huntersix.spring.rest.referencedata.PersonDataService;

import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnPersonDetails() throws Exception {
        assertThat(
            this.restTemplate.getForObject(
                "http://localhost:" + port + "/person/smith/mary",
                String.class
            )
        ).contains("Mary");
    }

    @Test
    public void shouldReturnListOfPersonDetailsBySurname() throws Exception {
        assertThat(
                this.restTemplate.getForObject(
                        "http://localhost:" + port + "/person/smith",
                        String.class
                )
        ).contains("Mary");

        List<LinkedHashMap<?,?>> result = this.restTemplate.getForObject(
                "http://localhost:" + port + "/person/smith",
                List.class
        );
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).get("firstName")).isEqualTo("Mary");
    }
}
