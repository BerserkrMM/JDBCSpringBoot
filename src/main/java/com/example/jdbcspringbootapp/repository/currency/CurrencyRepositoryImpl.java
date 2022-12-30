package com.example.jdbcspringbootapp.repository.currency;

import com.example.jdbcspringbootapp.model.dto.request.currency.*;
import com.example.jdbcspringbootapp.model.dto.response.currency.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CurrencyRepositoryImpl implements CurrencyRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Optional<CreateCurrencyRespDto> createCurrency(CreateCurrencyReqDto createCurrencyReqDto) {
        var sql = "INSERT INTO dbo.currency           "
                + "(name, code, exchangeRateToUSD)    "
                + "VALUES                             "
                + "(:name, :code, :exchangeRateToUSD) "
                + " RETURNING *                       ";
        var params = new MapSqlParameterSource()
                .addValue("name", createCurrencyReqDto.getName())
                .addValue("code", createCurrencyReqDto.getCode())
                .addValue("exchangeRateToUSD", createCurrencyReqDto.getExchangeRateToUSD());

        try {
            return Optional.ofNullable(namedParameterJdbcTemplate
                    .queryForObject(sql, params, new BeanPropertyRowMapper<>(CreateCurrencyRespDto.class)));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<GetCurrencyRespDto> getCurrencyById(Long id) {
        var sql = "SELECT * FROM dbo.currency WHERE id=:id";
        var params = new MapSqlParameterSource()
                .addValue("id", id);

        try {
            return Optional.ofNullable(namedParameterJdbcTemplate
                    .queryForObject(sql, params, new BeanPropertyRowMapper<>(GetCurrencyRespDto.class)));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<GetFirstCurrencyRespDto> getFirstCurrency() {
        var sql = "SELECT * FROM dbo.currency LIMIT 1";
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate
                    .queryForObject(sql
                            , new MapSqlParameterSource()
                            , new BeanPropertyRowMapper<>(GetFirstCurrencyRespDto.class)
                    ));

        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    // write logic for delition
    @Override
    public Optional<DeleteCurrencyRespDto> deleteCurrencyById(Long id) {
        var sqlDel = "DELETE FROM dbo.currency WHERE id=:id RETURNING *";
        var params = new MapSqlParameterSource()
                .addValue("id", id);
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate
                    .queryForObject(sqlDel, params, new BeanPropertyRowMapper<>(DeleteCurrencyRespDto.class)));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    //Returning old object
    public Optional<UpdateCurrencyRespDto> updateCurrencyById(Long id, UpdateCurrencyReqDto updateCurrencyReqDto) {
        var sqlGet = "SELECT * FROM dbo.currency WHERE id=:id";
        var paramsSqlGet = new MapSqlParameterSource()
                .addValue("id", id);
        UpdateCurrencyRespDto oldCurrencyUpdtRespDto;
        try {
            oldCurrencyUpdtRespDto = namedParameterJdbcTemplate // getting deleting entity
                    .queryForObject(sqlGet, paramsSqlGet, new BeanPropertyRowMapper<>(UpdateCurrencyRespDto.class));
        } catch (DataAccessException e) {
            oldCurrencyUpdtRespDto = null;
        }

        var paramsSqlUpdate = new MapSqlParameterSource()
                .addValue("id", id);
        List<String> params = new ArrayList<>();

        if (updateCurrencyReqDto.getName() != null) {
            params.add("name=:name");
            paramsSqlUpdate.addValue("name", updateCurrencyReqDto.getName());
        }
        if (updateCurrencyReqDto.getCode() != null) {
            params.add(" code=:code");
            paramsSqlUpdate.addValue("code", updateCurrencyReqDto.getCode());
        }
        if (updateCurrencyReqDto.getExchangeRateToUSD() != null) {
            params.add(" exchangeRateToUSD=:exchangeRateToUSD");
            paramsSqlUpdate.addValue("exchangeRateToUSD", updateCurrencyReqDto.getExchangeRateToUSD());
        }

        var sqlUpdate = "UPDATE dbo.currency SET                                          "
                + String.join(",", params)
                + ", uuid=gen_random_uuid(), modified_timestamp = current_timestamp "
                + "WHERE id=:id                                                     ";
        try {
            namedParameterJdbcTemplate.update(sqlUpdate, paramsSqlUpdate);
            return Optional.ofNullable(oldCurrencyUpdtRespDto);
        } catch (DataAccessException e) {
            e.getStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Long> tryExistenceByCode(String code) {

        String sql = "SELECT id FROM dbo.currency where Code = :code";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("code", code);

        return executeQueryWithOptionalResult(()->namedParameterJdbcTemplate.queryForObject(sql,params,Long.class));
    }
}
