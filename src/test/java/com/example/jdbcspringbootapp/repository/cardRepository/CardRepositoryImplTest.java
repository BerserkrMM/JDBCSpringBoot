package com.example.jdbcspringbootapp.repository.cardRepository;

import com.example.jdbcspringbootapp.config.TestDataSourceConfig;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@SpringBootTest(classes = {CardRepositoryImplTest.class, TestDataSourceConfig.class})
class CardRepositoryImplTest {

    @Test
    void testMethod(){

    }
}