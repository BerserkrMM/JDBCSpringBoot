package com.example.jdbcspringbootapp.repository.cardRepository;

import com.example.jdbcspringbootapp.config.TestDataSourceConfig;
import com.example.jdbcspringbootapp.repository.card.CardRepository;
import com.example.jdbcspringbootapp.repository.card.CardRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(classes = {CardRepositoryImpl.class, TestDataSourceConfig.class})
class CardRepositoryImplTest {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    DataSource dataSource;

    //todo
    @Test
    void createCardTast() {
        assertThat(cardRepository).isNotNull();
    }
}