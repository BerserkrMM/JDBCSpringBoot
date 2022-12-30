package com.example.jdbcspringbootapp.repository.cardRepository;

import com.example.jdbcspringbootapp.config.TestDataSourceConfig;
import com.example.jdbcspringbootapp.repository.card.CardRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(classes = {CardRepositoryImplTest.class, TestDataSourceConfig.class})
class CardRepositoryImplTest {

    @Autowired
    CardRepository cardRepository;

    @Test
    void testMethod(){

        assertThat(1L).isEqualTo(1L);
    }
}