import java.util.function.BiFunction;
import java.util.function.Function;

// This problem was asked by Jane Street.

// cons(a, b) constructs a pair, and car(pair) and cdr(pair) returns the first and last element of that pair.
// For example, car(cons(3, 4)) returns 3, and cdr(cons(3, 4)) returns 4.


// Given this implementation of cons:

// def cons(a, b):
//     def pair(f):
//         return f(a, b)
//     return pair
// Implement car and cdr.

//##########################################################################


// Trying it in Java, because life is suffering

class Pair {
    final int a;
    final int b;

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }
}

public class Problem5 {

    Function<BiFunction<Integer, Integer, Pair>, Pair> cons(Integer a, Integer b) {
        return (BiFunction<Integer, Integer, Pair> c) -> (Pair) c.apply(a,b);
    }

    Pair f(Integer a , Integer b){
        return new Pair(a,b);
    }

    int car(Function<BiFunction<Integer, Integer, Pair>, Pair> supplier) {
        return supplier.apply(this::f).a;
    }

    int cdr(Function<BiFunction<Integer, Integer, Pair>, Pair> supplier) {
        return supplier.apply(this::f).b;
    }

    
    public static void main(String[] args) {
        Problem5 problem = new Problem5();

        assert 3 == problem.car(problem.cons(3,4));
        assert 4 == problem.cdr(problem.cons(3,4));
    }
}
