package com.example.library.access;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ntlib/")
public class EntryController {

    private EntryRepository entryRepository;

    EntryController (EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    // GET
    @GetMapping("/{studentId}")
    Optional<EntryRecord> findByID(@PathVariable Integer studentId) {
        return entryRepository.findByID(studentId);
    }

    @GetMapping("/findall")
    List<EntryRecord> findAll() {
        return entryRepository.findAll();
    }

    // POST
    @PostMapping("/create")
    void create(@RequestBody EntryRecord entryRecord) {
        entryRepository.create(entryRecord);
    }

    // PUT
    @PutMapping("/update/{studentId}")
    void update(@PathVariable Integer studentId, @RequestBody EntryRecord entryRecord) {
        entryRepository.update(studentId, entryRecord);
    }

    // DELETE
    @DeleteMapping("delete/{studentId}")
    void deleleById(@PathVariable Integer studentId) {
        entryRepository.deleteById(studentId);
    }

    // Count
    @GetMapping("/count")
    int getCount() {
        return entryRepository.getCount();

    }

}
