import Adder
import spock.lang.Specification;

class AdderSpec extends Specification {
    def "adder-test"() {
        given: "a new Adder class is created"
        def adder = new Adder();

        expect: "Adding two numbers to return the sum"
        adder.add(3, 4) == 7
    }
}