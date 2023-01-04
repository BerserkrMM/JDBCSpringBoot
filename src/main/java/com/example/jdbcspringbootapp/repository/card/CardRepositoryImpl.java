package com.example.jdbcspringbootapp.repository.card;

import com.example.jdbcspringbootapp.model.dto.request.cardr.CreateCardReqDto;
import com.example.jdbcspringbootapp.model.dto.request.cardr.UpdateCardReqDto;
import com.example.jdbcspringbootapp.model.dto.response.card.*;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CardRepositoryImpl implements CardRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Optional<CreateCardRespDto> createCard(CreateCardReqDto createCardReqDto) {
        var sql = "INSERT INTO dbo.Cards                              "
                + "(amount, currency_id, name, bank_name)             "
                + "VALUES                                             "
                + "(:amount, :currency_id, :name, :bank_name);        "
                + "SELECT * FROM dbo.Cards WHERE id = SCOPE_IDENTITY()";
        var params = new MapSqlParameterSource()
                .addValue("amount", createCardReqDto.getAmount())
                .addValue("name", createCardReqDto.getName())
                .addValue("bank_name", createCardReqDto.getBank_name())
                .addValue("currency_id", createCardReqDto.getCurrency_id());

        return executeQueryWithOptionalResult(() -> namedParameterJdbcTemplate
                .queryForObject(sql, params, new BeanPropertyRowMapper<>(CreateCardRespDto.class)));
    }

    @Override
    public Optional<GetCardRespDto> getCardById(Long id) {
        var sql = "SELECT * FROM dbo.Cards WHERE id = :id";
        var params = new MapSqlParameterSource()
                .addValue("id", id);

        return executeQueryWithOptionalResult(() -> namedParameterJdbcTemplate
                .queryForObject(sql, params, new BeanPropertyRowMapper<>(GetCardRespDto.class)));
    }

    @Override
    public Optional<GetFirstCardRespDto> getFirstCard() {
        var sql = "SELECT TOP 1 * FROM dbo.Cards";
        return executeQueryWithOptionalResult(() -> namedParameterJdbcTemplate
                .queryForObject(sql
                        , new MapSqlParameterSource()
                        , new BeanPropertyRowMapper<>(GetFirstCardRespDto.class)));
    }

    @Override
    public Optional<DeleteCardRespDto> deleteCardById(Long id) {
        var sqlDel =  "delete dbo.Cards                                                                      "
                    + "output deleted.id, deleted.amount, deleted.name, deleted.bank_name,                   "
                    + "       deleted.currency_id, deleted.guid, deleted.created_time, deleted.modified_time "
                    + "where id = :id                                                                        ";
        var params = new MapSqlParameterSource()
                .addValue("id", id);
        return executeQueryWithOptionalResult(() -> namedParameterJdbcTemplate
                .queryForObject(sqlDel, params, new BeanPropertyRowMapper<>(DeleteCardRespDto.class)));
    }

    @Override
    //Returning old object
    public Optional<UpdateCardRespDto> updateCardById(Long id, UpdateCardReqDto updateCardReqDto) {
        var paramsForSqlSource = new MapSqlParameterSource()
                .addValue("id", id);
        List<String> paramsForSetClause = new ArrayList<>();
        List<String> paramsForTempTable = new ArrayList<>();

        if (updateCardReqDto.getAmount() != null) {
            paramsForTempTable.add("amount bigint");
            paramsForSetClause.add("amount=:amount");
            paramsForSqlSource.addValue("amount", updateCardReqDto.getAmount());
        }
        if (updateCardReqDto.getName() != null) {
            paramsForTempTable.add("name varchar(50)");
            paramsForSetClause.add(" name=:cardname");
            paramsForSqlSource.addValue("cardname", updateCardReqDto.getName());
        }
        if (updateCardReqDto.getCurrency_id() != null) {
            paramsForTempTable.add("currency_id bigint");
            paramsForSetClause.add(" currency_id=:currencyId");
            paramsForSqlSource.addValue("currencyId", updateCardReqDto.getCurrency_id());
        }
        if (updateCardReqDto.getBank_name() != null) {
            paramsForTempTable.add("bank_name varchar(50)");
            paramsForSetClause.add(" bank_name=:bankname");
            paramsForSqlSource.addValue("bankname", updateCardReqDto.getBank_name());
        }

        var sqlSource =   "declare @TempTable table(id bigint                 , guid uniqueidentifier     "
                        + "                       , created_time  datetime2(3), modified_time datetime2(3)"
                        + "                       , amount bigint             , name varchar(50)          "
                        + "                       , bank_name varchar(50)     , currency_id bigint       )"
                        + " update dbo.Cards set " + String.join(",", paramsForSetClause)
                        + " output deleted.id          , deleted.guid                                     "
                        + "      , deleted.created_time, deleted.modified_time                            "
                        + "      , deleted.amount      , deleted.name                                     "
                        + "      , deleted.bank_name   , deleted.currency_id                              "
                        + " into @TempTable                                                               "
                        + " where id=:id;                                                                 "
                        + " select * from @TempTable                                                      ";
        return executeQueryWithOptionalResult(()->namedParameterJdbcTemplate.queryForObject(
                sqlSource,paramsForSqlSource,new BeanPropertyRowMapper<>(UpdateCardRespDto.class)));
    }

    @Override
    public Optional<Long> tryExistenceByName(String cardName) {
        String sql = "SELECT id FROM dbo.Cards WHERE name=:name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", cardName);
        return executeQueryWithOptionalResult(() -> namedParameterJdbcTemplate.queryForObject(sql, params, Long.class));
    }
}
