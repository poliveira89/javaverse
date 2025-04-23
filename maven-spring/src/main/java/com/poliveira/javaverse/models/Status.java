package com.poliveira.javaverse.models;

import static lombok.AccessLevel.PRIVATE;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum Status {
  OPEN("Open"),
  IN_PROGRESS("In Progress"),
  BLOCKED("Blocked"),
  DONE("Done");

  private final String status;

  public static Status from(String status) {
    for (Status s : Status.values()) {
      if (s.getStatus().equalsIgnoreCase(status)) {
        return s;
      }
    }
    throw new IllegalArgumentException("Invalid status: " + status);
  }
}
