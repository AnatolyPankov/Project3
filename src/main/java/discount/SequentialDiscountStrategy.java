package discount;

public class SequentialDiscountStrategy implements DiscountStrategy {
    private final double initialDiscountPercent;
    private final double stepPercent;

    public SequentialDiscountStrategy(double initialDiscountPercent, double stepPercent) {
        if (initialDiscountPercent < 0 && stepPercent < 0) {
            throw new IllegalArgumentException("Скидка и шаг не могут быть отрицательными");
        }
        this.initialDiscountPercent = initialDiscountPercent;
        this.stepPercent = stepPercent;
    }

    @Override
    public double getDiscountFraction(int orderIndex) {
        double currentPercent = initialDiscountPercent - (orderIndex * stepPercent);
        double clampedPercent = Math.max(currentPercent, 0.0);
        return clampedPercent / 100.00;
    }
}
