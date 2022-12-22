package com.example.jdbcspringbootapp.repository.cardRepository;

import com.example.jdbcspringbootapp.model.dto.request.cardRequests.*;
import com.example.jdbcspringbootapp.model.dto.response.cardResponses.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CardRepositoryImpl implements CardRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Optional<CreateCardRespDto> createCard(CreateCardReqDto createCardReqDto) {
        var sql = "INSERT INTO dbo.cards                        "
                + "(amount, currencyId, cardName, bankName)     "
                + "VALUES                                       "
                + "(:amount, :currencyId, :cardname, :bankname) "
                + " RETURNING *                                 ";
        var params = new MapSqlParameterSource()
                .addValue("amount", createCardReqDto.getAmount())
                .addValue("cardname", createCardReqDto.getCardName())
                .addValue("bankname", createCardReqDto.getBankName())
                .addValue("currencyId", createCardReqDto.getCurrencyId());

        try {
            return Optional.ofNullable(namedParameterJdbcTemplate
                    .queryForObject(sql, params, new BeanPropertyRowMapper<>(CreateCardRespDto.class)));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<GetCardRespDto> getCardById(Long id) {
        var sql = "SELECT * FROM dbo.cards WHERE id=:id";
        var params = new MapSqlParameterSource()
                .addValue("id", id);

        try {
            return Optional.ofNullable(namedParameterJdbcTemplate
                    .queryForObject(sql, params, new BeanPropertyRowMapper<>(GetCardRespDto.class)));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<GetFirstCardRespDto> getFirstCard() {
        var sql = "SELECT * FROM dbo.cards LIMIT 1";
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate
                    .queryForObject(sql
                            ,new MapSqlParameterSource()
                            ,new BeanPropertyRowMapper<>(GetFirstCardRespDto.class)
                    ));

        }
        catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<DeleteCardRespDto> deleteCardById(Long id) {
        var sqlDel = "DELETE FROM dbo.cards WHERE id=:id RETURNING *";
        var params = new MapSqlParameterSource()
                .addValue("id", id);
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate
                    .queryForObject(sqlDel, params, new BeanPropertyRowMapper<>(DeleteCardRespDto.class)));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    //Returning old object
    public Optional<UpdateCardRespDto> updateCardById(Long id, UpdateCardReqDto updateCardReqDto) {
        var sqlGet = "SELECT * FROM dbo.cards WHERE id=:id";
        var paramsSqlGet = new MapSqlParameterSource()
                .addValue("id", id);
        UpdateCardRespDto oldCardUpdtRespDto;
        try {
            oldCardUpdtRespDto = namedParameterJdbcTemplate // getting deleting entity
                    .queryForObject(sqlGet, paramsSqlGet, new BeanPropertyRowMapper<>(UpdateCardRespDto.class));
        } catch (DataAccessException e) {
            oldCardUpdtRespDto = null;
        }

        var paramsSqlUpdate = new MapSqlParameterSource()
                .addValue("id", id);
        List<String> params = new ArrayList<>();

        if (updateCardReqDto.getAmount() != null) {
            params.add("amount=:amount");
            paramsSqlUpdate.addValue("amount", updateCardReqDto.getAmount());
        }
        if (updateCardReqDto.getCardName() != null) {
            params.add(" cardname=:cardname");
            paramsSqlUpdate.addValue("cardname", updateCardReqDto.getCardName());
        }
        if (updateCardReqDto.getCurrencyId() != null) {
            params.add(" currencyId=:currencyId");
            paramsSqlUpdate.addValue("currencyId", updateCardReqDto.getCurrencyId());
        }
        if (updateCardReqDto.getBankName() != null) {
            params.add(" bankname=:bankname");
            paramsSqlUpdate.addValue("bankname", updateCardReqDto.getBankName());
        }

        var sqlUpdate =   "UPDATE dbo.cards SET                                             "
                        +  params.stream().collect(Collectors.joining(","))
                        + ", uuid=gen_random_uuid(), modified_timestamp = current_timestamp "
                        + "WHERE id=:id                                                     ";
        try {
            namedParameterJdbcTemplate.update(sqlUpdate, paramsSqlUpdate);
            return Optional.ofNullable(oldCardUpdtRespDto);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }
}
