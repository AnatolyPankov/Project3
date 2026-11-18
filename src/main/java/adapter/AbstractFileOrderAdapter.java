package adapter;

import model.Order;
import reader.DelimitedFileReader;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

public abstract class AbstractFileOrderAdapter implements OrderSource{
    private final DelimitedFileReader reader;
    private final Path filePath;

    protected AbstractFileOrderAdapter(DelimitedFileReader reader, Path filePath) {
        this.reader = reader;
        this.filePath = filePath;
    }

    @Override
    public List<Order> loadAll() {
        return reader.readRows(filePath).stream()
                .map(this::adaptRowToOrder)
                .toList();
    }

    private Order adaptRowToOrder(String[] columns) {
        if (columns.length != 3) {
            throw new IllegalArgumentException("Неверный формат строки в файле! Необходимо 3 колонки: " + filePath);
        }
        LocalDateTime time = LocalDateTime.parse(columns[0].trim());
        String company = columns[1].trim();
        double weight = Double.parseDouble(columns[2].trim());
        return  new Order(time, company, weight);
    }
}
