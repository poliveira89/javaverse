package com.poliveira.javaverse.utils;

import static java.lang.System.currentTimeMillis;
import static java.time.ZoneOffset.UTC;

import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class TimeUtils {
  public LocalDateTime toLocalDateTime(long epochSecond) {
    return LocalDateTime.ofEpochSecond(epochSecond / 1000, 0, UTC);
  }

  public long toEpochMilli(LocalDateTime localDateTime) {
    if (Objects.isNull(localDateTime)) {
      return currentTimeMillis();
    }

    return localDateTime.toEpochSecond(UTC) * 1000;
  }
}
