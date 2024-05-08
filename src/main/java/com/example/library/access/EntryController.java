package com.example.library.access;

import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.ACCEPTED)
    Optional<EntryRecord> findByID(@PathVariable Integer studentId) {
        return entryRepository.findByID(studentId);
    }

    @GetMapping("/findall")
    @ResponseStatus(HttpStatus.ACCEPTED)
    List<EntryRecord> findAll() {
        return entryRepository.findAll();
    }

    // POST
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void create(@RequestBody EntryRecord entryRecord) {
        entryRepository.create(entryRecord);
    }

    // PUT
    @PutMapping("/update/{studentId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void update(@PathVariable Integer studentId, @RequestBody EntryRecord entryRecord) {
        entryRepository.update(studentId, entryRecord);
    }

    // DELETE
    @DeleteMapping("delete/{studentId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleleById(@PathVariable Integer studentId) {
        entryRepository.deleteById(studentId);
    }

    // Count
    @GetMapping("/count")
    @ResponseStatus(HttpStatus.ACCEPTED)
    int getCount() {
        return entryRepository.getCount();
    }

}
