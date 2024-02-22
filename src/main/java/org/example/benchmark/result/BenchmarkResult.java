package org.example.benchmark.result;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BenchmarkResult {

    String compressorName;

    String fileName;
    String fileExtension;

    ResultType type;

    double compressRatio;

    long fileInitSize;
    long fileCompressSize;

    long compressTimeMillis;
    long decompressTimeMillis;

    long compressMemoryUsage;
    long decompressMemoryUsage;
}
