package writer;

import java.util.Map;

public interface ReportWriter<K, V> {
    void write(Map<K, V> data);
}
