package service;

import adapter.OrderSource;
import discount.DiscountStrategy;
import model.Order;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderCalculationService {
    private final DiscountStrategy discountStrategy;
    private final double pricePerKg;

    public OrderCalculationService(DiscountStrategy discountStrategy, double pricePerKg) {
        if (pricePerKg < 0) {
            throw new IllegalArgumentException("Цена за кг не может быть отрицательной");
        }
        this.discountStrategy = discountStrategy;
        this.pricePerKg = pricePerKg;
    }

    public Map<String, Double> calculateCompanyTotals(List<OrderSource> sources) {
        List<Order> sortedOrders = sources.stream()
                .flatMap(source -> source.loadAll().stream())
                .sorted(Comparator.comparing(Order::orderTime))
                .toList();

        Map<String, Double> normalizedTotals = new LinkedHashMap<>();
        Map<String, String> originalNames = new HashMap<>();

        for (int i = 0; i < sortedOrders.size(); i++) {
            Order order = sortedOrders.get(i);
            String key = order.companyName().trim().toLowerCase();
            originalNames.putIfAbsent(key, order.companyName().trim());

            double discountFraction = discountStrategy.getDiscountFraction(i);
            double orderCost = order.weightKg() * pricePerKg * (1.0 - discountFraction);

            normalizedTotals.merge(key, orderCost, Double::sum);
        }

        Map<String, Double> result = new LinkedHashMap<>();
        normalizedTotals.forEach((key, total) -> result.put(originalNames.get(key), total));
        return result;
    }
}
