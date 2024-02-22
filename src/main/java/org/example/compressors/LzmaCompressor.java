package org.example.compressors;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.compress.compressors.lzma.LZMACompressorOutputStream;

public class LzmaCompressor extends Compressor {

    @Override
    protected OutputStream newObject(ByteArrayOutputStream outputStream) throws IOException {
        return new LZMACompressorOutputStream(outputStream);
    }

    @Override
    public byte[] compress(byte[] inputBytes, int level) {
        return this.compress(inputBytes);
    }
}
