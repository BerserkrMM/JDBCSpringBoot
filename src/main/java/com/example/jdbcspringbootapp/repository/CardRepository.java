package com.example.jdbcspringbootapp.repository;

import com.example.jdbcspringbootapp.model.entity.CardEntity;

public interface CardRepository {

    CardEntity getCardEntityById(Long id);

}
