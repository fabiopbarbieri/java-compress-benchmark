package org.example;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import org.example.benchmark.BenchmarkUtils;
import org.example.benchmark.ZstdCompressionBenchmark;
import org.example.benchmark.image.ParsedFileStyle;
import org.example.benchmark.result.ResultType;

import com.google.gson.Gson;
import lombok.Data;

@Data
public class BenchmarkZstd {

    private static final String PATH = "src/main/java/org/example/files";

    private static final Set<String> SUPPORTED_IMAGES = Set.of("jpg", "jpeg", "tif", "tiff", "gif", "bmp", "png", "jfif");

    public static void main(String[] args) {
        var benchMark = new ZstdCompressionBenchmark();
        var allList = new ArrayList<>();

        for (String fileName : getFiles()) {
            System.out.println("reading file " + fileName);
            var results = benchMark.benchmarkFile(fileName, readFile(fileName), ResultType.BYTES);
            allList.addAll(results);

            System.out.println("reading file (Base64) " + fileName);
            var results2 = benchMark.benchmarkFile(fileName, toBase64(readFile(fileName)), ResultType.BASE64);
            allList.addAll(results2);

            var fileExtension = BenchmarkUtils.getFileExtension(fileName);

            if (SUPPORTED_IMAGES.contains(fileExtension)){
                System.out.println("reading file (thumb) " + fileName);
                var results3 = benchMark.benchmarkFile(fileName, resize(readFile(fileName)), ResultType.THUMB);
                allList.addAll(results3);
            }

        }

        var resultFile = Paths.get("zstdBenchmarkResults.json");

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


    private static byte[] resize(byte[] bytes)   {
        try {
            return ParsedFileStyle.of("560x560>").apply(bytes);
        }catch (IOException e){
            e.printStackTrace();
        }
        return new byte[0];
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


