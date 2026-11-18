import adapter.HashOrderSourceAdapter;
import adapter.OrderSource;
import adapter.PipeOrderSourceAdapter;
import discount.DiscountStrategy;
import discount.SequentialDiscountStrategy;
import service.OrderCalculationService;
import writer.FileReportWriter;
import writer.ReportWriter;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        double initialDiscountPercent = 50.0;
        double stepPercent = 5.0;
        double pricePerKg = 10.0;

        List<OrderSource> sources = List.of(
                new PipeOrderSourceAdapter(Path.of("discount_day.txt")),
                new HashOrderSourceAdapter(Path.of("discount_day_without_ext"))
        );

        DiscountStrategy discountStrategy = new SequentialDiscountStrategy(initialDiscountPercent, stepPercent);
        OrderCalculationService service = new OrderCalculationService(discountStrategy, pricePerKg);

        Map<String, Double> companyTotals = service.calculateCompanyTotals(sources);

        Path outputFile = Path.of("fail.txt");
        ReportWriter<String, Double> writer = new FileReportWriter(outputFile);
        writer.write(companyTotals);

        System.out.println("Результат записан в: " + outputFile.toAbsolutePath());
    }
}

