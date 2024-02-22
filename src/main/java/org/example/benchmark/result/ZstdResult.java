package org.example.benchmark.result;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ZstdResult {

    String compressorName;

    String fileName;
    String fileExtension;

    ResultType type;

    int compressionLevel;
    double compressRatio;

    long fileInitSize;
    long fileCompressSize;

    long compressTimeMillis;
    long decompressTimeMillis;

    long compressMemoryUsage;
    long decompressMemoryUsage;
}
