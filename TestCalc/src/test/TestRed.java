package test;

public class TestRed extends Test {
	public static void main(String[] args){
		Test.main(args);
		verbose = true;
		// no recursion
		test(verbose, "test/redFunc0.calc", "(define (zero) 0)(zero)", "0");
		test(verbose, "test/redId.calc", "(define (id x) x) (id 11)", "11");
		test(verbose, "test/redFuncs.calc", "(define (id x) x)(define (id2 x) x) 22", "22");
		test(verbose, "test/redFunc1.calc", "(define (inc x) (+ x 1))(inc 0)", "1");
		test(verbose, "test/redFunc2.calc", "(define (id x) x)(id (+ 0 1))", "1");
		test(verbose, "test/redFunc3.calc", "(define (id x) x)(= a 3)a", "3");
		test(verbose, "test/redFunc4.calc", "(define (inc x) (+ x 1))(inc (+ 3 1))", "5");
		test(verbose, "test/redFunc5.calc", "(define (inc x) (+ x 1))(= a 2)(inc (+ 1 a))", "4");
		test(verbose, "test/redOverride.calc", "(inc x) (inc x y)", "error");
		test(verbose, "test/redParam.calc", "(inc x) (= x 0)", "error");
		test(verbose, "test/redZarbi.calc", "(define (f) a)(= a 1)(f)", "error");
		// simple recursion
		test(verbose, "test/redFuncRedef.calc", "(define (f x)(= x 1) x)(f x)", "error");
		test(verbose, "test/redFib0.calc", "(define (fib n)... (fib 4)", "3");
		test(verbose, "test/redFib.calc", "(define (fib n)... (fib 4)", "3");
    	test(verbose, "test/redFib2.calc", "(define (fib n)... (fib 4))", "3");
		test(verbose, "test/redFib3.calc", "(define (fib n)... (fib 4))", "3");
		// mutual recursion
		test(verbose, "test/redMutRec.calc", "odd & even", "1");
		report();
	}
}


