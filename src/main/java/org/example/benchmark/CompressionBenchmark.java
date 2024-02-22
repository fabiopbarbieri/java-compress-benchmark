package org.example.benchmark;

import java.util.ArrayList;
import java.util.List;

import org.example.benchmark.result.BenchmarkResult;
import org.example.benchmark.result.ResultType;
import org.example.compressors.Bzip2Compressor;
import org.example.compressors.Compressor;
import org.example.compressors.LzmaCompressor;
import org.example.compressors.ZstdCompressor;
import org.example.decompressors.Bzip2Decompressor;
import org.example.decompressors.Decompressor;
import org.example.decompressors.LzmaDecompressor;
import org.example.decompressors.ZstdDecompressor;

public class CompressionBenchmark {

    private static final Compressor[] COMPRESSORS = { new ZstdCompressor(), new LzmaCompressor(), new Bzip2Compressor() };

    private static final Decompressor[] DECOMPRESSORS = { new ZstdDecompressor(), new LzmaDecompressor(), new Bzip2Decompressor() };

    public  List<BenchmarkResult> benchmarkFile(String fileName, byte[] fileData, ResultType type) {
        var results = new ArrayList<BenchmarkResult>();
        var fileExtension = BenchmarkUtils.getFileExtension(fileName);

        for (int i = 0; i < COMPRESSORS.length; i++) {
            var compressor = COMPRESSORS[i];
            var decompressor = DECOMPRESSORS[i];
            var compressorName = compressor.getClass().getSimpleName().replace("Compressor","");

            var compressResult = BenchmarkUtils.test(() -> compressor.compress(fileData));
            var compressTimeMillis = compressResult.getTimeMillis();
            var compressedData = compressResult.getResult();
            var compressMemoryUsage = compressResult.getMemoryUsage();

            // Decompress
            var decompressResult = BenchmarkUtils.test(() -> decompressor.decompress(compressedData));
            var decompressTimeMillis = decompressResult.getTimeMillis();
            var decompressMemoryUsage = decompressResult.getMemoryUsage();

            // Calculate compression ratio
            var compressRatio = (double) (fileData.length - compressedData.length) / fileData.length;

            // Create result object
            var result = BenchmarkResult.builder()
                    .compressorName(compressorName)
                    .fileName(fileName)
                    .fileExtension(fileExtension)
                    .fileInitSize(fileData.length)
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
