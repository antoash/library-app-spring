package com.example.library.access;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EntryRepository {

    private final JdbcClient jdbcClient;

    public EntryRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    // findByID - optional to deal with NULL values
    public Optional<EntryRecord> findByID(Integer studentId) {
        return jdbcClient.sql("SELECT * FROM Entries WHERE studentId=?")
                .params(studentId)
                .query(EntryRecord.class)
                .optional();
    }

    // findAll - optional to deal with NULL values
    public List<EntryRecord> findAll() {
        return jdbcClient.sql("SELECT * FROM Entries")
                .query(EntryRecord.class)
                .list();
    }

    // create
    public void create(EntryRecord entryRecord) {
        var updated = jdbcClient.sql("INSERT INTO Entries (studentId, name, branch, entryTime, exitTime, booksBorrowed) VALUES (?,?,?,?,?,?) ")
                .params(entryRecord.studentId(), entryRecord.name(), entryRecord.branch().toString(), entryRecord.entryTime(), entryRecord.exitTime(), entryRecord.booksBorrowed())
                .update();

        Assert.state(updated == 0, "Creation unsuccessful!");
    }

    // update
    public void update(Integer studentId, EntryRecord entryRecord) {
        var updated = jdbcClient.sql("UPDATE Entries SET name=?, branch=?, entryTime=?, exitTime=?, booksBorrowed=? where studentId=?")
                .params(entryRecord.name(), entryRecord.branch().toString(), entryRecord.entryTime(), entryRecord.exitTime(), entryRecord.booksBorrowed(), studentId)
                .update();

        Assert.state(updated == 0, "Update unsuccessful!");
    }

    // delete
    public void deleteById(Integer studentId) {
        var updated = jdbcClient.sql("DELETE FROM Entries WHERE studentId=?")
                .params(studentId)
                .update();
        Assert.state(updated == 0, "Delete unsuccessful!");
    }

    // save
    public void saveAll(List<EntryRecord> entryRecord) {
        entryRecord.forEach(this::create);
    }

    // count
    public int getCount() {
        return (jdbcClient.sql("select * from Entries").query(EntryRecord.class).list().size());
    }

}
