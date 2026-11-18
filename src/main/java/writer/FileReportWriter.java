package writer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class FileReportWriter implements ReportWriter<String, Double> {
    private final Path outputPath;

    public FileReportWriter(Path outputPath) {
        this.outputPath = outputPath;
    }

    @Override
    public void write(Map<String, Double> companyTotals) {
        List<String> lines = companyTotals.entrySet().stream()
                .map(entry -> String.format(Locale.US, "%s - %.2f",
                        entry.getKey(), entry.getValue()))
                .toList();
        try {
            Files.write(outputPath, lines);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при записи файла отчета: " + outputPath, e);
        }
    }
}
