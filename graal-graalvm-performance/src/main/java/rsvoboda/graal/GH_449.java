package rsvoboda.graal;

import java.math.BigInteger;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import java.util.concurrent.TimeUnit;

public class GH_449 {

    private static final BigInteger MINUS_ONE = BigInteger.ONE.negate();

    private static BigInteger factorialTail(BigInteger aNumber, BigInteger anAccu) {
        if (BigInteger.ZERO.equals(aNumber)) {
            return anAccu;
        }
        return factorialTail(aNumber.add(MINUS_ONE), aNumber.multiply(anAccu));
    }

    private static BigInteger factorial(BigInteger aNumber) {
        return factorialTail(aNumber, BigInteger.ONE);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void testFactorial(Blackhole bh) {
        bh.consume(factorial(BigInteger.valueOf(10000)));
    }

    public static void main(String... args) {
        System.out.println(factorial(BigInteger.valueOf(15)));
    }
}
