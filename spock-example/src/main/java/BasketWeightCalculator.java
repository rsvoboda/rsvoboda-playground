public class BasketWeightCalculator {
    private int totalWeight = 0;

    public void addItem(int itemWeight) {
        totalWeight = totalWeight + itemWeight;
    }

    public int getTotalWeight() {
        return totalWeight;
    }
}