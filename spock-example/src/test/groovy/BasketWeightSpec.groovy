import spock.lang.Specification;
import BasketWeightCalculator;

class BasketWeightSpec extends Specification {

    def "one-item"() {
        given:
        def weightCalculator = new BasketWeightCalculator()

        when: "add only one item"
        weightCalculator.addItem(5)

        then: "expect value of the item"
        weightCalculator.getTotalWeight() == 5
    }

    def "two-items"() {
        given:
        def weightCalculator = new BasketWeightCalculator()

        when: "add two items in the basket"
        weightCalculator.addItem(5)
        weightCalculator.addItem(13)

        then: "expect the sum of both items"
        weightCalculator.getTotalWeight() == 18
    }

    def "order-of-items-does-not-matter"() {
        given:
        def weightCalculator1 = new BasketWeightCalculator()
        def weightCalculator2 = new BasketWeightCalculator()

        when: "add same items but with different order"
        weightCalculator1.addItem(5)
        weightCalculator1.addItem(13)

        weightCalculator2.addItem(13)
        weightCalculator2.addItem(5)

        then: "expect both baskets to weigh the same"
        weightCalculator1.getTotalWeight() == 18
        weightCalculator2.getTotalWeight() == 18
    }
}