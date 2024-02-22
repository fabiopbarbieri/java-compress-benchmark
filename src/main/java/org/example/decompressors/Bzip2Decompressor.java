package org.example.decompressors;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

public class Bzip2Decompressor extends Decompressor {

    @Override
    protected InputStream newObject(ByteArrayInputStream inputStream) throws IOException {
        return new BZip2CompressorInputStream(inputStream);
    }
}
