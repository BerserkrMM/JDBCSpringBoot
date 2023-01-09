package com.example.jdbcspringbootapp.repository.transactioncategory;

import com.example.jdbcspringbootapp.model.dto.request.transactioncategories.*;
import com.example.jdbcspringbootapp.model.dto.response.tranactioncategories.*;
import com.example.jdbcspringbootapp.model.dto.response.transaction.UpdateTransactionRespDto;
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
    public Optional<CreateTransactionCategoryRespDto> createTransactionCategory(
            CreateTransactionCategoryReqDto createTransactionCategoryReqDto)
    {
        var sql = "INSERT INTO dbo.Transaction_Categories (name, type)                 "
                + "VALUES (:name, :type);                                              "
                + "SELECT * FROM dbo.Transaction_Categories WHERE id = SCOPE_IDENTITY()";
        var params = new MapSqlParameterSource()
                .addValue("name", createTransactionCategoryReqDto.getName())
                .addValue("type", createTransactionCategoryReqDto.getType().toString());
        return executeQueryWithOptionalResult(()->namedParameterJdbcTemplate
                .queryForObject(sql, params, new BeanPropertyRowMapper<>(CreateTransactionCategoryRespDto.class)));
    }

    @Override
    public Optional<GetTransactionCategoryRespDto> getTransactionCategoryById(Long id) {
        var sql = "SELECT * FROM dbo.Transaction_Categories WHERE id=:id and is_deleted = 'N'";
        var params = new MapSqlParameterSource()
                .addValue("id", id);
        return executeQueryWithOptionalResult(()->namedParameterJdbcTemplate
                .queryForObject(sql, params, new BeanPropertyRowMapper<>(GetTransactionCategoryRespDto.class)));
    }

    @Override
    public Optional<GetFirstTransactionCategoryRespDto> getFirstTransactionCategory() {
        var sql = "SELECT TOP 1 * FROM dbo.Transaction_Categories and is_deleted = 'N'";
        return executeQueryWithOptionalResult(()->namedParameterJdbcTemplate
                .queryForObject(sql
                        ,new MapSqlParameterSource()
                        ,new BeanPropertyRowMapper<>(GetFirstTransactionCategoryRespDto.class)
                ));
    }

    @Override
    public Optional<DeleteTransactionCategoryRespDto> deleteTransactionCategoryById(Long id) {
        var sqlDel =  "update dbo.Transaction_Categories                      "
                    + "set is_deleted = 'Y'                                   "
                    + "where id = :id;                                        "
                    + "select * from dbo.Transaction_Categories where id = :id";
        var params = new MapSqlParameterSource()
                .addValue("id", id);
        return executeQueryWithOptionalResult(()->namedParameterJdbcTemplate
                .queryForObject(sqlDel, params, new BeanPropertyRowMapper<>(DeleteTransactionCategoryRespDto.class)));
    }

    @Override
    //Returning old object
    public Optional<UpdateTransactionCategoryRespDto> updateTransactionCategoryById(
            Long id, UpdateTransactionCategoryReqDto updateTransactionCategoryReqDto)
    {
        var paramsForSqlSource = new MapSqlParameterSource()
                .addValue("id", id);
        List<String> paramsForSetClause = new ArrayList<>();
        if (updateTransactionCategoryReqDto.getName() != null) {
            paramsForSetClause.add(" name='"+updateTransactionCategoryReqDto.getName()+"'");
        }
        if (updateTransactionCategoryReqDto.getType() != null) {
            paramsForSetClause.add(" type='"+updateTransactionCategoryReqDto.getType()+"'");
        }
        var sqlSource =   "declare @TempTable table(  id            bigint                               "
                + "                                 , name          varchar(50)                          "
                + "                                 , type          varchar(7)                           "
                + "                                 , guid          uniqueidentifier                     "
                + "                                 , created_time  datetime2(3)                         "
                + "                                 , modified_time datetime2(3) )                       "
                + " update dbo.Transaction_Categories set " + String.join(",", paramsForSetClause)
                + " output deleted.id          , deleted.name                                            "
                + "      , deleted.type        , deleted.guid                                            "
                + "      , deleted.created_time, deleted.modified_time                                   "
                + " into @TempTable                                                                      "
                + " where id=:id and is_deleted = 'N';                                                   "
                + " select * from @TempTable                                                             ";
        return executeQueryWithOptionalResult(() -> namedParameterJdbcTemplate.queryForObject(
                sqlSource, paramsForSqlSource, new BeanPropertyRowMapper<>(UpdateTransactionCategoryRespDto.class)));
    }

    @Override
    public <C> Optional<C> isPresentByName(String categoryName, Class<C> responseClassToMapOn) {
        String sql = "SELECT * FROM dbo.Transaction_Categories WHERE name=:categoryName";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("categoryName", categoryName);
        return executeQueryWithOptionalResult(() -> namedParameterJdbcTemplate
                .queryForObject(sql, params, new BeanPropertyRowMapper<>(responseClassToMapOn)));
    }

    @Override
    public <C> Optional<C> isPresentById(Long id, Class<C> responseClassToMapOn) {
        String sql = "SELECT * FROM dbo.Transaction_Categories WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return executeQueryWithOptionalResult(() -> namedParameterJdbcTemplate
                .queryForObject(sql, params, new BeanPropertyRowMapper<>(responseClassToMapOn)));
    }
}