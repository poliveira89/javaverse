package com.poliveira.javaverse.model;

import static lombok.AccessLevel.PRIVATE;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum Status {
    TODO("To Do"),
    IN_PROGRESS("In Progress"),
    DONE("Done");

    private final String status;
}
