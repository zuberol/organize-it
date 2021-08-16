package com.zuber.organizeit.integration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.SpringBootWebTestClientBuilderCustomizer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskTest {
    @Test
    void shouldDoSomething() {
        assertThat(List.of("Kuba", "Jakub")).hasSizeGreaterThan(0);
    }

    @Test
    void shouldGetTasks() {


        assertThat(List.of("Kuba", "Jakub")).hasSizeGreaterThan(0);
    }

}
