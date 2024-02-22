package org.example.benchmark;

import java.util.ArrayList;
import java.util.List;

import org.example.benchmark.result.ResultType;
import org.example.benchmark.result.ZstdResult;
import org.example.compressors.ZstdCompressor;
import org.example.decompressors.ZstdDecompressor;

import com.github.luben.zstd.Zstd;

public class ZstdCompressionBenchmark {

    public List<ZstdResult> benchmarkFile(String fileName, byte[] fileData, ResultType type) {
        var results = new ArrayList<ZstdResult>();
        var fileExtension = BenchmarkUtils.getFileExtension(fileName);

        var compressorName = ZstdCompressor.class.getSimpleName().replace("Compressor", "");

        for (var level = Math.max(Zstd.minCompressionLevel(), 0) ; level < Zstd.maxCompressionLevel(); level++) {

            var finalLevel = level;
            var compressResult = BenchmarkUtils.test(() -> new ZstdCompressor().compress(fileData, finalLevel));
            var compressTimeMillis = compressResult.getTimeMillis();
            var compressedData = compressResult.getResult();
            var compressMemoryUsage = compressResult.getMemoryUsage();

            // Decompress
            var decompressResult = BenchmarkUtils.test(() -> new ZstdDecompressor().decompress(compressedData));
            var decompressTimeMillis = decompressResult.getTimeMillis();
            var decompressMemoryUsage = decompressResult.getMemoryUsage();

            // Calculate compression ratio
            var compressRatio = (double) (fileData.length - compressedData.length) / fileData.length;

            // Create result object
            ZstdResult result = ZstdResult.builder().compressorName(compressorName)
                    .fileName(fileName)
                    .fileExtension(fileExtension)
                    .fileInitSize(fileData.length)
                    .compressionLevel(level)
                    .fileCompressSize(compressedData.length)
                    .compressTimeMillis(compressTimeMillis)
                    .decompressTimeMillis(decompressTimeMillis)
                    .compressRatio(compressRatio)
                    .type(type)
                    .compressMemoryUsage(compressMemoryUsage)
                    .decompressMemoryUsage(decompressMemoryUsage)
                    .build();

            // Add result to list
            results.add(result);
        }

        return results;
    }




}
