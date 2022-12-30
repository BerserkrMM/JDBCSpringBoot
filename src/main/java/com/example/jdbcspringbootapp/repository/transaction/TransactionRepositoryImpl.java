package com.example.jdbcspringbootapp.repository.transaction;

import com.example.jdbcspringbootapp.model.dto.request.transaction.CreateTransactionReqDto;
import com.example.jdbcspringbootapp.model.dto.request.transaction.UpdateTransactionReqDto;
import com.example.jdbcspringbootapp.model.dto.response.transaction.*;
import lombok.RequiredArgsConstructor;
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
public class TransactionRepositoryImpl implements TransactionRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Optional<CreateTransactionRespDto> createTransaction(CreateTransactionReqDto createTransactionReqDto) {
        var sql = "INSERT INTO dbo.transactions                                                                                       "
                + "(direction, amount, cardId, currencyId, transactionCategoryID, infoExtra)                                          "
                + "VALUES                                                                                                             "
                + "(:direction::dbo.TRANSACTION_DIRECTIONS_DBENUM, :amount, :cardId, :currencyId, :transactionCategoryID, :infoExtra) "
                + "RETURNING *                                                                                                        ";
        var params = new MapSqlParameterSource()
                .addValue("direction", createTransactionReqDto.getDirection().toString())
                .addValue("amount", createTransactionReqDto.getAmount())
                .addValue("cardId", createTransactionReqDto.getCardId())
                .addValue("currencyId", createTransactionReqDto.getCurrencyId())
                .addValue("transactionCategoryID", createTransactionReqDto.getTransactionCategoryID())
                .addValue("infoExtra", createTransactionReqDto.getInfoExtra());

        try {
            return Optional.ofNullable(namedParameterJdbcTemplate
                    .queryForObject(sql, params, new BeanPropertyRowMapper<>(CreateTransactionRespDto.class)));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<GetTransactionRespDto> getTransactionById(Long id) {
        var sql = "SELECT * FROM dbo.transactions WHERE id=:id";
        var params = new MapSqlParameterSource()
                .addValue("id", id);

        try {
            return Optional.ofNullable(namedParameterJdbcTemplate
                    .queryForObject(sql, params, new BeanPropertyRowMapper<>(GetTransactionRespDto.class)));
        } catch (DataAccessException e) {
            e.getStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<GetFirstTransactionRespDto> getFirstTransaction() {
        var sql = "SELECT * FROM dbo.transactions LIMIT 1";
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate
                    .queryForObject(sql
                            ,new MapSqlParameterSource()
                            ,new BeanPropertyRowMapper<>(GetFirstTransactionRespDto.class)
                    ));

        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<DeleteTransactionRespDto> deleteTransactionById(Long id) {
        var sqlDel = "DELETE FROM dbo.transactions WHERE id=:id RETURNING *";
        var params = new MapSqlParameterSource()
                .addValue("id", id);
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate
                    .queryForObject(sqlDel, params, new BeanPropertyRowMapper<>(DeleteTransactionRespDto.class)));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    //Returning old object
    public Optional<UpdateTransactionRespDto> updateTransactionById(
            Long id, UpdateTransactionReqDto updateTransactionReqDto
    ) {
        var sqlGet = "SELECT * FROM dbo.transactions WHERE id=:id";
        var paramsSqlGet = new MapSqlParameterSource()
                .addValue("id", id);
        UpdateTransactionRespDto oldTransactionUpdtRespDto;
        try {
            oldTransactionUpdtRespDto = namedParameterJdbcTemplate // getting deleting entity
                    .queryForObject(sqlGet, paramsSqlGet, new BeanPropertyRowMapper<>(UpdateTransactionRespDto.class));
        } catch (DataAccessException e) {
            e.getStackTrace();
            oldTransactionUpdtRespDto = null;
        }

        var paramsSqlUpdate = new MapSqlParameterSource()
                .addValue("id", id);
        List<String> params = new ArrayList<>();

        if (updateTransactionReqDto.getDirection() != null) {
            params.add("direction=:direction::dbo.TRANSACTION_DIRECTIONS_DBENUM");
            paramsSqlUpdate.addValue("direction", updateTransactionReqDto.getDirection().toString());
        }
        if (updateTransactionReqDto.getAmount() != null) {
            params.add(" amount=:amount");
            paramsSqlUpdate.addValue("amount", updateTransactionReqDto.getAmount());
        }
        if (updateTransactionReqDto.getCardId() != null) {
            params.add("cardId=:cardId");
            paramsSqlUpdate.addValue("cardId", updateTransactionReqDto.getCardId());
        }
        if (updateTransactionReqDto.getCurrencyId() != null) {
            params.add(" currencyId=:currencyId");
            paramsSqlUpdate.addValue("currencyId", updateTransactionReqDto.getCurrencyId());
        }
        if (updateTransactionReqDto.getTransactionCategoryID() != null) {
            params.add("transactionCategoryID=:transactionCategoryID");
            paramsSqlUpdate.addValue("transactionCategoryID", updateTransactionReqDto.getTransactionCategoryID());
        }
        if (updateTransactionReqDto.getInfoExtra() != null) {
            params.add(" infoExtra=:infoExtra");
            paramsSqlUpdate.addValue("infoExtra", updateTransactionReqDto.getInfoExtra());
        }

        var sqlUpdate =   "UPDATE dbo.transactions SET                                      "
                        + String.join(",", params)
                        + ", uuid=gen_random_uuid(), modified_timestamp = current_timestamp "
                        + "WHERE id=:id                                                     ";
        try {
            namedParameterJdbcTemplate.update(sqlUpdate, paramsSqlUpdate);
            return Optional.ofNullable(oldTransactionUpdtRespDto);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }
}
