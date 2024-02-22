package org.example.decompressors;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.github.luben.zstd.ZstdInputStream;

public class ZstdDecompressor extends Decompressor {

    @Override
    protected InputStream newObject(ByteArrayInputStream inputStream) throws IOException {
        return new ZstdInputStream(inputStream);
    }
}
