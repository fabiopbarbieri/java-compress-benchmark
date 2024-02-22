package org.example.compressors;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

public class Bzip2Compressor extends Compressor {

    @Override
    protected OutputStream newObject(ByteArrayOutputStream outputStream) throws IOException {
        return new BZip2CompressorOutputStream(outputStream);
    }

    @Override
    public byte[] compress(byte[] inputBytes, int level) {
        return this.compress(inputBytes);
    }
}
