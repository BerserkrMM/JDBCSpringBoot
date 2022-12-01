package com.example.jdbcspringbootapp.repository;

import com.example.jdbcspringbootapp.model.dto.request.CreateCardReqDto;
import com.example.jdbcspringbootapp.model.entity.CardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class CardRepositoryImpl implements CardRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public CardEntity getCardEntityById(Long id) {
        return new CardEntity();
    }

    @Override
    public void save(CreateCardReqDto createCardReqDto) {


        var sql = "INSERT INTO dbo.cards              "
                + " (amount, cardName, bankName)      "
                + " VALUES                            "
                + " (:amount, :simplename, :bankname) ";

        var params = new MapSqlParameterSource()
                .addValue("amount", createCardReqDto.getAmount())
                .addValue("simplename", createCardReqDto.getSimpleName())
                .addValue("bankname", createCardReqDto.getBankName());
        try {
            namedParameterJdbcTemplate.update(sql, params);
        } catch (DataAccessException e) {
            int i = 0;
        }
    }
}
