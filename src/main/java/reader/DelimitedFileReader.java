package reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public abstract class DelimitedFileReader {
    private final String delimiterRegex;

    protected DelimitedFileReader(String delimiterRegex) {
        this.delimiterRegex = delimiterRegex;
    }

    public List<String[]> readRows(Path path) {
        try (var lines = Files.lines(path)) {
            return lines
                    .filter(line -> !line.isBlank())
                    .map(line -> line.split(delimiterRegex))
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла: " + path, e);
        }
    }
}
