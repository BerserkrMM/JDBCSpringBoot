package com.example.jdbcspringbootapp.repository.currency;

import com.example.jdbcspringbootapp.model.dto.request.currency.CreateCurrencyReqDto;
import com.example.jdbcspringbootapp.model.dto.request.currency.UpdateCurrencyReqDto;
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
        var sql = "INSERT INTO dbo.Currencies                              "
                + "(name, code, exchange_rate_to_usd)                      "
                + "VALUES                                                  "
                + "(:name, :code, :exchange_rate_to_usd);                  "
                + "SELECT * FROM dbo.Currencies WHERE id = SCOPE_IDENTITY()";
        var params = new MapSqlParameterSource()
                .addValue("name", createCurrencyReqDto.getName())
                .addValue("code", createCurrencyReqDto.getCode())
                .addValue("exchange_rate_to_usd", createCurrencyReqDto.getExchange_rate_to_usd());
        return executeQueryWithOptionalResult(() -> (namedParameterJdbcTemplate
                .queryForObject(sql, params, new BeanPropertyRowMapper<>(CreateCurrencyRespDto.class))));
    }

    @Override
    public Optional<GetCurrencyRespDto> getCurrencyById(Long id) {
        var sql = "SELECT * FROM dbo.Currencies WHERE id=:id and is_deleted = 'N'";
        var params = new MapSqlParameterSource()
                .addValue("id", id);
        return executeQueryWithOptionalResult(() -> (namedParameterJdbcTemplate
                .queryForObject(sql, params, new BeanPropertyRowMapper<>(GetCurrencyRespDto.class))));
    }

    @Override
    public Optional<GetFirstCurrencyRespDto> getFirstCurrency() {
        var sql = "SELECT TOP 1 * FROM dbo.Currencies and is_deleted = 'N'";

        return executeQueryWithOptionalResult(() -> (namedParameterJdbcTemplate
                .queryForObject(sql
                        , new MapSqlParameterSource()
                        , new BeanPropertyRowMapper<>(GetFirstCurrencyRespDto.class)
                )));
    }

    @Override
    public Optional<DeleteCurrencyRespDto> deleteCurrencyById(Long id) {
        var sqlDel =  "update dbo.Currencies                      "
                    + "set is_deleted = 'Y'                       "
                    + "where id = :id;                            "
                    + "select * from dbo.Currencies where id = :id";
        var params = new MapSqlParameterSource()
                .addValue("idCur", id);
        return executeQueryWithOptionalResult(() -> (namedParameterJdbcTemplate
                .queryForObject(sqlDel, params, new BeanPropertyRowMapper<>(DeleteCurrencyRespDto.class))));
    }

    @Override
    //Returning old object
    public Optional<UpdateCurrencyRespDto> updateCurrencyById(Long id, UpdateCurrencyReqDto updateCurrencyReqDto) {
        var paramsForSqlSource = new MapSqlParameterSource()
                .addValue("id", id);
        List<String> paramsForSetClause = new ArrayList<>();

        if (updateCurrencyReqDto.getName() != null) {
            paramsForSetClause.add(" name= '" + updateCurrencyReqDto.getName() + "' ");
        }
        if (updateCurrencyReqDto.getCode() != null) {
            paramsForSetClause.add("code= '" + updateCurrencyReqDto.getCode() + "' ");
        }
        if (updateCurrencyReqDto.getExchangeRateToUSD() != null) {
            paramsForSetClause.add(" exchange_rate_to_usd=" + updateCurrencyReqDto.getExchangeRateToUSD());
        }

        var sqlSource =   "declare @TempTable table(id bigint                 , guid uniqueidentifier     "
                        + "                       , created_time  datetime2(3), modified_time datetime2(3)"
                        + "                       , name varchar(50)          , code varchar(50)          "
                        + "                       , exchange_rate_to_usd numeric                         )"
                        + " update dbo.Currencies set " + String.join(",", paramsForSetClause)
                        + " output deleted.id          , deleted.guid , deleted.created_time              "
                        + "      , deleted.modified_time, deleted.exchange_rate_to_usd                    "
                        + " into @TempTable                                                               "
                        + " where id=:id and is_deleted = 'N';                                            "
                        + " select * from @TempTable                                                      ";

        return executeQueryWithOptionalResult(() -> namedParameterJdbcTemplate
                .queryForObject(sqlSource, paramsForSqlSource, new BeanPropertyRowMapper<>(UpdateCurrencyRespDto.class)));
    }

    @Override
    public <C> Optional<C> isPresentById(Long id, Class<C> responseClassToMapOn) {
        String sql = "SELECT * FROM dbo.Currencies WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return executeQueryWithOptionalResult(() -> namedParameterJdbcTemplate
                .queryForObject(sql, params, new BeanPropertyRowMapper<>(responseClassToMapOn)));
    }
    @Override
    public <C> Optional<C> isPresentByName(String cardName, Class<C> responseClassToMapOn) {
        String sql = "SELECT * FROM dbo.Currencies WHERE name=:name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", cardName);
        return executeQueryWithOptionalResult(() -> namedParameterJdbcTemplate
                .queryForObject(sql, params, new BeanPropertyRowMapper<>(responseClassToMapOn)));
    }
    @Override
    public <C> Optional<C> isPresentByCode(String currencyCode, Class<C> responseClassToMapOn) {
        String sql = "SELECT * FROM dbo.Currencies WHERE code=:code";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("code", currencyCode);
        return executeQueryWithOptionalResult(() -> namedParameterJdbcTemplate
                .queryForObject(sql, params, new BeanPropertyRowMapper<>(responseClassToMapOn)));
    }
}
