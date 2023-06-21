package sg.edu.nus.iss.paf_d22workshop.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf_d22workshop.model.RSVP;

@Repository
public class RsvpRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private String findAllSQL = "select * from rsvp";
    private String findByNameSQL = "select * from rsvp where id = ?";
    private String insertSQL = "insert into RSVP (full_name,email,phone,confirmation_date,comments) values (?,?,?,?,?)"; 
    private String updateSQL = "update RSVP set email = ?, phone = ?, confirmation_date = ? where id = ?";
    private String countSQL = "select count(*) from RSVP";

    public int count(){
        int iResult = jdbcTemplate.queryForObject(countSQL,Integer.class);
        return iResult;
    }

    public List<RSVP> findAll(){
        List<RSVP> rsvps = new ArrayList<>();
        rsvps = jdbcTemplate.queryForList(findAllSQL,RSVP.class);
        return rsvps;
    }

    public RSVP findByName(int id){
        RSVP rsvp = new RSVP();
        rsvp = jdbcTemplate.queryForObject(findByNameSQL, BeanPropertyRowMapper.newInstance(RSVP.class), id);
        return rsvp;
    }

    public Boolean save(RSVP rsvp){
        int result = 0;
        result = jdbcTemplate.update(insertSQL, rsvp.getFullName(),rsvp.getEmail(), rsvp.getPhone(),rsvp.getConfirmationDate(),rsvp.getComment());
        return result > 0 ? true: false;
    }

    public Boolean update (RSVP rsvp){
        int result = 0;
        result = jdbcTemplate.update(updateSQL, rsvp.getEmail(),rsvp.getPhone(),rsvp.getConfirmationDate(),rsvp.getId());
        return result > 0 ? true: false;
    }

}
