package org.example.compressors;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.github.luben.zstd.ZstdOutputStream;

public class ZstdCompressor extends Compressor {

    @Override
    protected OutputStream newObject(ByteArrayOutputStream outputStream) throws IOException {
        return new ZstdOutputStream(outputStream);
    }

    @Override
    public byte[] compress(byte[] inputBytes, int level) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (ZstdOutputStream zstdOutputStream = new ZstdOutputStream(outputStream, level)) {
            zstdOutputStream.write(inputBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}
