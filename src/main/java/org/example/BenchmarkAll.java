package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.example.benchmark.CompressionBenchmark;
import org.example.benchmark.result.BenchmarkResult;
import org.example.benchmark.result.ResultType;

import com.google.gson.Gson;
import lombok.Data;

@Data
public class BenchmarkAll {

    private static final String PATH = "src/main/java/org/example/files";

    public static void main(String[] args) {
        var benchMark = new CompressionBenchmark();

        var allList = new ArrayList<>();

        for (String fileName : getFiles()) {
            System.out.println("reading file " + fileName);
            List<BenchmarkResult> benchmarkResults = benchMark.benchmarkFile(fileName, readFile(fileName), ResultType.BYTES);
            allList.addAll(benchmarkResults);

            System.out.println("reading file (Base64) " + fileName);
            List<BenchmarkResult> results2 = benchMark.benchmarkFile(fileName, toBase64(readFile(fileName)), ResultType.BASE64);
            allList.addAll(results2);
        }

        var resultFile = Paths.get("benchmarkResults.json");

        try {
            Files.deleteIfExists(resultFile);
            Files.write(resultFile,
                        new Gson().toJson(allList).getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE);
        }catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Fim - resultados em " + resultFile.toAbsolutePath());
    }

    private static byte[] toBase64(byte[] bytes) {
        return Base64.getEncoder().encode(bytes);
    }

    public static List<String> getFiles() {
        List<String> fileNames = new ArrayList<>();

        Path filesDirectory = Paths.get(PATH);
        try (var stream = Files.walk(filesDirectory)) {
            stream.filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .forEach(fileNames::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileNames;
    }

    public static byte[] readFile(String fileName) {
        try {
            Path filePath = Paths.get(PATH, fileName);
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            return new byte[0];
        }
    }

}


