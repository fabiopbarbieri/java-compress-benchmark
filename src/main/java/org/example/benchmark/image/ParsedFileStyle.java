package org.example.benchmark.image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

import net.coobird.thumbnailator.Thumbnails;

public final class ParsedFileStyle {

    private static final Pattern STYLE_REGEX = Pattern.compile("^(\\d+)x(\\d+)(#|>)$");
    private static final int TWO = 2;
    private static final int THREE = 3;

    private final int width;
    private final int height;
    private final char kind;

    private ParsedFileStyle(int width, int height, char kind) {
        this.width = width;
        this.height = height;
        this.kind = kind;
    }

    public static ParsedFileStyle of(String style) {
        var matcher = STYLE_REGEX.matcher(style);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(style);
        }

        var widthString = matcher.group(1);
        var heightString = matcher.group(TWO);
        var kindString = matcher.group(THREE);

        var width = Integer.parseInt(widthString);
        var height = Integer.parseInt(heightString);
        var kind = kindString.charAt(0);

        return new ParsedFileStyle(width, height, kind);
    }

    public byte[] apply(byte[] bytes) throws IOException {
        try (var stream = new ByteArrayInputStream(bytes)) {
            Thumbnails.Builder<? extends InputStream> thumbnail = Thumbnails.of(stream);
            thumbnail.size(width, height);
            if ('#' == kind) {
                thumbnail.keepAspectRatio(false);
            }
            thumbnail.useExifOrientation(true);

            var os = new ByteArrayOutputStream();
            thumbnail.toOutputStream(os);

            return os.toByteArray();
        }
    }

    public String toURLSuffix() {
        return width + "x" + height + kindToString();
    }

    private String kindToString() {
        if ('#' == kind) {
            return "e"; // "exact"
        } else {
            return "k"; // "keep aspect ratio"
        }
    }

}
