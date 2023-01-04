package com.example.jdbcspringbootapp.repository.utils;

import com.example.jdbcspringbootapp.exception.DatabaseOperationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;
import java.util.concurrent.Callable;


public interface JdbcExceptionWrappingRepository {
    String DATABASE_ERROR_MSG = "Database operation error";
    Logger log = LoggerFactory.getLogger(JdbcExceptionWrappingRepository.class);

    default <V> V executeQuery(Callable<V> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            log.error(DATABASE_ERROR_MSG, e);
            throw new DatabaseOperationException(DATABASE_ERROR_MSG);
        }
    }

    default <V> Optional<V> executeQueryWithOptionalResult(Callable<V> callable) {
        try {
            return Optional.ofNullable(callable.call());
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            log.error(DATABASE_ERROR_MSG, e);
            throw new DatabaseOperationException(DATABASE_ERROR_MSG+":\n"+e.getMessage());
        }
    }
}
