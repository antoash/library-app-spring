package com.example.library.access;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class EntryDataLoader implements CommandLineRunner {

    List<EntryRecord> entryRecord;
    ObjectMapper objectMapper;
    EntryRepository entryRepository;
    private static final Logger log = LoggerFactory.getLogger(EntryDataLoader.class);

    EntryDataLoader(List<EntryRecord> entryRecord, ObjectMapper objectMapper, EntryRepository entryRepository) {
        this.entryRecord = entryRecord;
        this.objectMapper = objectMapper;
        this.entryRepository = entryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (entryRepository.getCount()<1) {
            try {
                File file = new File("C:\\Users\\asher\\Desktop\\Spring Learning\\Library\\demo\\src\\main\\resources\\Data\\patrons.json");
                entryRecord = objectMapper.readValue(file, new TypeReference<List<EntryRecord>>(){});
                log.info("Reading {} patrons from JSON and saving to the database", entryRecord.size());
                entryRepository.saveAll(entryRecord);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
