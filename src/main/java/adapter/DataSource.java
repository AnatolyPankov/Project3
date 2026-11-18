package adapter;

import java.util.List;

public interface DataSource<T> {
    List<T> loadAll();
}
