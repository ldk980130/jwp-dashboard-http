package nextstep.jwp.session;

import java.util.HashMap;
import java.util.Map;

public class HttpSession {
    private final String id;
    private final Map<String, Object> values = new HashMap<>();

    public HttpSession(String sessionId) {
        this.id = sessionId;
    }

    public void setAttribute(String key, Object value) {
        values.put(key, value);
    }

    public Object getAttributes(String key) {
        return values.get(key);
    }

    public void removeAttribute(String key) {
        values.remove(key);
    }

    public void invalidate() {
        values.clear();
    }
}