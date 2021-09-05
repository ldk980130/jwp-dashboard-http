package nextstep.jwp.http;

import nextstep.jwp.http.exception.UnsupportedExtensionException;

import java.util.Arrays;

public enum ContentType {
    HTML(".html", "text/html;charset=utf-8"),
    JS(".js", "application/js;charset=utf-8"),
    CSS(".css", "text/css;charset=utf-8"),
    ICO(".ico", "image/x-icon");

    private String extension;
    private String type;

    ContentType(String extension, String type) {
        this.extension = extension;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static ContentType findContentType(String extension) {
        return Arrays.stream(values())
                .filter(contentType -> contentType.extension.equals(extension))
                .findFirst()
                .orElseThrow(UnsupportedExtensionException::new);
    }
}