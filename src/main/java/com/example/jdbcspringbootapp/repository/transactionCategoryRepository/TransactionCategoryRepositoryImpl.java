package com.example.jdbcspringbootapp.repository.transactionCategoryRepository;

import com.example.jdbcspringbootapp.model.dto.request.transactionCategoriesRequests.*;
import com.example.jdbcspringbootapp.model.dto.response.tranactionCategoriesResponses.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TransactionCategoryRepositoryImpl implements TransactionCategoryRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Optional<CreateTransactionCategoryRespDto> createTransactionCategory(CreateTransactionCategoryReqDto createTransactionCategoryReqDto) {
        var sql = "INSERT INTO dbo.transaction_categories "
                + "(name, type)                           "
                + "VALUES                                 "
                + "(:name, :type::dbo.TRANSACTION_DIRECTIONS_DBENUM)"
                + " RETURNING *                           ";
        var params = new MapSqlParameterSource()
                .addValue("name", createTransactionCategoryReqDto.getName())
                .addValue("type", createTransactionCategoryReqDto.getType().toString());

        try {
            return Optional.ofNullable(namedParameterJdbcTemplate
                    .queryForObject(sql, params, new BeanPropertyRowMapper<>(CreateTransactionCategoryRespDto.class)));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<GetTransactionCategoryRespDto> getTransactionCategoryById(Long id) {
        var sql = "SELECT * FROM dbo.transaction_categories WHERE id=:id";
        var params = new MapSqlParameterSource()
                .addValue("id", id);

        try {
            return Optional.ofNullable(namedParameterJdbcTemplate
                    .queryForObject(sql, params, new BeanPropertyRowMapper<>(GetTransactionCategoryRespDto.class)));
        } catch (DataAccessException e) {
            e.getStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<GetFirstTransactionCategoryRespDto> getFirstTransactionCategory() {
        var sql = "SELECT * FROM dbo.transaction_categories WHERE id=:id";
        var params = new MapSqlParameterSource()
                .addValue("id", 1);
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate
                    .queryForObject(sql, params, new BeanPropertyRowMapper<>(GetFirstTransactionCategoryRespDto.class)));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<DeleteTransactionCategoryRespDto> deleteTransactionCategoryById(Long id) {
        var sqlDel = "DELETE FROM dbo.transaction_categories WHERE id=:id RETURNING *";
        var params = new MapSqlParameterSource()
                .addValue("id", id);
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate
                    .queryForObject(sqlDel, params, new BeanPropertyRowMapper<>(DeleteTransactionCategoryRespDto.class)));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    //Returning old object
    public Optional<UpdateTransactionCategoryRespDto> updateTransactionCategoryById(
            Long id, UpdateTransactionCategoryReqDto updateTransactionCategoryReqDto
    ) {
        var sqlGet = "SELECT * FROM dbo.transaction_categories WHERE id=:id";
        var paramsSqlGet = new MapSqlParameterSource()
                .addValue("id", id);
        UpdateTransactionCategoryRespDto oldTransactionCategoryUpdtRespDto;
        try {
            oldTransactionCategoryUpdtRespDto = namedParameterJdbcTemplate // getting deleting entity
                    .queryForObject(sqlGet, paramsSqlGet, new BeanPropertyRowMapper<>(UpdateTransactionCategoryRespDto.class));
        } catch (DataAccessException e) {
            oldTransactionCategoryUpdtRespDto = null;
        }

        var paramsSqlUpdate = new MapSqlParameterSource()
                .addValue("id", id);
        List<String> params = new ArrayList<>();

        if (updateTransactionCategoryReqDto.getName() != null) {
            params.add("name=:name");
            paramsSqlUpdate.addValue("name", updateTransactionCategoryReqDto.getName());
        }
        if (updateTransactionCategoryReqDto.getType() != null) {
            params.add(" type=:type");
            paramsSqlUpdate.addValue("type", updateTransactionCategoryReqDto.getType().toString());
        }

        var sqlUpdate =   "UPDATE dbo.transaction_categories SET                            "
                        + params.stream().collect(Collectors.joining(","))
                        + ", uuid=gen_random_uuid(), modified_timestamp = current_timestamp "
                        + "WHERE id=:id                                                     ";
        try {
            namedParameterJdbcTemplate.update(sqlUpdate, paramsSqlUpdate);
            return Optional.ofNullable(oldTransactionCategoryUpdtRespDto);
        } catch (DataAccessException e) {
            e.getStackTrace();
            return Optional.empty();
        }
    }
}
