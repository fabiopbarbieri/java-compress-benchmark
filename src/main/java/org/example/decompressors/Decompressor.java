package org.example.decompressors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class Decompressor {

    public byte[] decompress(byte[] compressedData) {
        var outputStream = new ByteArrayOutputStream();
        try (var inputStream = new ByteArrayInputStream(compressedData);
                InputStream decompressionStream = newObject(inputStream)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = decompressionStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return outputStream.toByteArray();
    }

    protected abstract InputStream newObject(ByteArrayInputStream inputStream) throws IOException;
}
