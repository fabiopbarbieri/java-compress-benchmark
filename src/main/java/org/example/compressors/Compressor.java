package org.example.compressors;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class Compressor {

    public final byte[] compress(byte[] inputBytes) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (OutputStream compressorStream = newObject(outputStream)) {
            compressorStream.write(inputBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    public abstract byte[] compress(byte[] inputBytes, int level);

    protected abstract OutputStream newObject(ByteArrayOutputStream outputStream) throws IOException;

}
