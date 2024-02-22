package org.example.benchmark;

import java.util.function.Supplier;

import org.example.benchmark.result.TestResult;
import org.example.memory.MemoryUtils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BenchmarkUtils {

    public TestResult test(Supplier<byte[]> supplier) {
        var beforeMemory = MemoryUtils.getReallyUsedMemory();

        var startTime = System.currentTimeMillis();
        var result = supplier.get();
        var endTime = System.currentTimeMillis();

        var afterMemory = MemoryUtils.getReallyUsedMemory();
        var memoryUsage = afterMemory - beforeMemory;

        var timeMillis = endTime - startTime;

        return TestResult.builder()
                .result(result)
                .memoryUsage(memoryUsage)
                .timeMillis(timeMillis)
                .build();
    }

    public String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex == -1) {
            return ""; // no extension found
        }
        return fileName.substring(lastIndex + 1);
    }
}
