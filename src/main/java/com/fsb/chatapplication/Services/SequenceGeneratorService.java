package com.fsb.chatapplication.Services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {

    private JdbcTemplate jdbcTemplate;

    public SequenceGeneratorService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long generateSequence(String seqName) {
        String sql = "SELECT seq FROM database_sequences WHERE id = ?";
        Integer seq = jdbcTemplate.queryForObject(sql, Integer.class, seqName);
        if (seq == null) {
            seq = 1;
            String insertSql = "INSERT INTO database_sequences (id, seq) VALUES (?, ?)";
            jdbcTemplate.update(insertSql, seqName, seq);
        } else {
            String updateSql = "UPDATE database_sequences SET seq = seq + 1 WHERE id = ?";
            jdbcTemplate.update(updateSql, seqName);
            seq = seq + 1;
        }
        return seq;
    }
}

