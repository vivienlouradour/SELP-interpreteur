package test;

public class TestTab extends Test {
	
	public static void main(String[] args){
		Test.main(args);
		// Calc Green	
		verbose = true;
		test(verbose, "test/greenZeroTab.calc", "0", "0");
 
		report();
	}
}
