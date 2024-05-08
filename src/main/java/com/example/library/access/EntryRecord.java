package com.example.library.access;

import jakarta.validation.constraints.Positive;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

public record EntryRecord(
        @NonNull @Positive
        Integer studentId,
        String name,
        LibBranch branch,
        @NonNull
        LocalDateTime entryTime,
        @NonNull
        LocalDateTime exitTime,
        @Positive
        Integer booksBorrowed
) {
    public EntryRecord {

        if (booksBorrowed>4) {
            throw new IllegalArgumentException("Max number of books that can be borrowed is less than 5!");
        }

        if (entryTime.isAfter(exitTime)) {
            throw new IllegalArgumentException("Entry time cannot be after Exit time!");
        }
    }

}
