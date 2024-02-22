package org.example.benchmark.result;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestResult {

    long timeMillis;
    long memoryUsage;
    byte[] result;

}
