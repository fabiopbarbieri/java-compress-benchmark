package org.example.decompressors;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.compressors.lzma.LZMACompressorInputStream;

public class LzmaDecompressor extends Decompressor {

    @Override
    protected InputStream newObject(ByteArrayInputStream inputStream) throws IOException {
        return new LZMACompressorInputStream(inputStream);
    }
}
