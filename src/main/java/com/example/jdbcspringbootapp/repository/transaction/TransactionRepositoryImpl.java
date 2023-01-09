package com.example.jdbcspringbootapp.repository.transaction;

import com.example.jdbcspringbootapp.model.dto.request.transaction.CreateTransactionReqDto;
import com.example.jdbcspringbootapp.model.dto.request.transaction.UpdateTransactionReqDto;
import com.example.jdbcspringbootapp.model.dto.response.transaction.*;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.jdbcspringbootapp.model.enums.IsDeleted;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Optional<CreateTransactionRespDto> createTransaction(CreateTransactionReqDto createTransactionReqDto) {
        var sql = "INSERT INTO dbo.Transactions                                                        "
                + "(direction, amount, card_id, currency_id, transaction_category_id, info_extra)      "
                + "VALUES                                                                              "
                + "(:direction, :amount, :card_id, :currency_id, :transaction_category_id, :info_extra);"
                + "SELECT * FROM dbo.Transactions WHERE id = SCOPE_IDENTITY()                          ";
        var params = new MapSqlParameterSource()
                .addValue("direction", createTransactionReqDto.getDirection().toString())
                .addValue("amount", createTransactionReqDto.getAmount())
                .addValue("card_id", createTransactionReqDto.getCard_id())
                .addValue("currency_id", createTransactionReqDto.getCurrency_id())
                .addValue("transaction_category_id", createTransactionReqDto.getTransaction_category_id())
                .addValue("info_extra", createTransactionReqDto.getInfo_extra());
        return executeQueryWithOptionalResult(() -> namedParameterJdbcTemplate
                .queryForObject(sql, params, new BeanPropertyRowMapper<>(CreateTransactionRespDto.class)));
    }

    @Override
    public Optional<GetTransactionRespDto> getTransactionById(Long id) {
        var sql = "SELECT * FROM dbo.Transactions WHERE id=:id and is_deleted = 'N'";
        var params = new MapSqlParameterSource()
                .addValue("id", id);
        return executeQueryWithOptionalResult(() -> namedParameterJdbcTemplate
                .queryForObject(sql, params, new BeanPropertyRowMapper<>(GetTransactionRespDto.class)));
    }

    @Override
    public Optional<GetFirstTransactionRespDto> getFirstTransaction() {
        var sql = "SELECT TOP 1 * FROM dbo.Transactions where is_deleted = 'N'";
        return executeQueryWithOptionalResult(() -> namedParameterJdbcTemplate
                .queryForObject(sql
                        , new MapSqlParameterSource()
                        , new BeanPropertyRowMapper<>(GetFirstTransactionRespDto.class)
                ));
    }

    @Override
    public Optional<DeleteTransactionRespDto> deleteTransactionById(Long id) {
        var sqlDel =  "update dbo.Transactions                      "
                    + "set is_deleted = 'Y'                         "
                    + "where id = :id;                              "
                    + "select * from dbo.Transactions where id = :id";
        var params = new MapSqlParameterSource()
                .addValue("id", id);
        return executeQueryWithOptionalResult(() -> namedParameterJdbcTemplate
                .queryForObject(sqlDel, params, new BeanPropertyRowMapper<>(DeleteTransactionRespDto.class)));
    }

    @Override
    //Returning old object
    public Optional<UpdateTransactionRespDto> updateTransactionById(
            Long id, UpdateTransactionReqDto updateTransactionReqDto)
    {
        var paramsForSqlSource = new MapSqlParameterSource()
                .addValue("id", id);
        List<String> paramsForSetClause = new ArrayList<>();
        if (updateTransactionReqDto.getDirection() != null) {
            paramsForSetClause.add(" direction='"+updateTransactionReqDto.getDirection().toString()+"' ");
        }
        if (updateTransactionReqDto.getAmount() != null) {
            paramsForSetClause.add(" amount="+updateTransactionReqDto.getAmount());
        }
        if (updateTransactionReqDto.getCard_id() != null) {
            paramsForSetClause.add(" card_id="+updateTransactionReqDto.getCard_id());
        }
        if (updateTransactionReqDto.getCurrency_id() != null) {
            paramsForSetClause.add(" currency_id="+updateTransactionReqDto.getCurrency_id());
        }
        if (updateTransactionReqDto.getTransaction_category_id() != null) {
            paramsForSetClause.add(" transaction_category_id="+updateTransactionReqDto.getTransaction_category_id());
        }
        if (updateTransactionReqDto.getInfo_extra() != null) {
            paramsForSetClause.add(" info_extra='"+updateTransactionReqDto.getInfo_extra()+"' ");
        }
        var sqlSource =   "declare @TempTable table(  id                      bigint                   "
                        + "                         , direction               varchar(7)               "
                        + "                         , amount                  bigint                   "
                        + "                         , card_id                 bigint                   "
                        + "                         , currency_id             bigint                   "
                        + "                         , transaction_category_id bigint                   "
                        + "                         , info_extra              varchar(100)             "
                        + "                         , guid                    uniqueidentifier         "
                        + "                         , created_time            datetime2(3)             "
                        + "                         , modified_time           datetime2(3)      )      "
                        + " update dbo.Transactions set " + String.join(",", paramsForSetClause)
                        + " output deleted.id          , deleted.direction                             "
                        + "      , deleted.amount      , deleted.card_id                               "
                        + "      , deleted.currency_id , deleted.transaction_category_id               "
                        + "      , deleted.info_extra  , deleted.guid                                  "
                        + "      , deleted.created_time, deleted.modified_time                         "
                        + " into @TempTable                                                            "
                        + " where id=:id and is_deleted = 'N';                                         "
                        + " select * from @TempTable                                                   ";
        return executeQueryWithOptionalResult(() -> namedParameterJdbcTemplate.queryForObject(
                sqlSource, paramsForSqlSource, new BeanPropertyRowMapper<>(UpdateTransactionRespDto.class)));
    }

    @Override
    public <C> Optional<C> isPresentById(Long id, Class<C> responseClassToMapOn) {
        String sql = "SELECT * FROM dbo.Transactions WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return executeQueryWithOptionalResult(() -> namedParameterJdbcTemplate
                .queryForObject(sql, params, new BeanPropertyRowMapper<>(responseClassToMapOn)));
    }

    public Optional<IsDeleted> isDeletedById (Long id){
        String sql =  " if (:id = (SELECT id FROM dbo.Cards WHERE id=:id and is_deleted='N'))     "
                    + "      select 0                                                             "
                    + " else if (:id = (SELECT id FROM dbo.Cards WHERE id=:id and is_deleted='Y'))"
                    + "           select 1                                                        "
                    + "      else select null                                                     ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id",id);
        return executeQueryWithOptionalResult(()->namedParameterJdbcTemplate
                .queryForObject(sql,params,new BeanPropertyRowMapper<>(IsDeleted.class)));
    }
}
