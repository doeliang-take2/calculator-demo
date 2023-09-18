package method2;

import java.math.BigDecimal;

/*客户端 */
public class Client {

	public static void main(String[] args) {
		Invoker invoker=new Invoker();
	        invoker.redo();
	        invoker.undo();
		invoker.compute('+', new BigDecimal(100));
		invoker.compute('-', new BigDecimal(50));
		invoker.compute('*', new BigDecimal(20));
		invoker.compute('/', new BigDecimal(2));
		invoker.undo();
	        invoker.compute('+', new BigDecimal(10));
	        invoker.compute('+', new BigDecimal(20));
	        invoker.compute('+', new BigDecimal(30));
	        invoker.undo();
	        invoker.undo();
	        invoker.undo();
	        invoker.undo();
	        invoker.undo();
	        invoker.undo();
	        invoker.undo();
	        invoker.undo();
	        invoker.undo();
	        invoker.undo();
	        invoker.undo();
	        invoker.redo();
	        invoker.redo();
	        invoker.redo();
	        invoker.redo();
	        invoker.redo();
	        invoker.redo();
	        invoker.redo();
	        invoker.redo();
	        invoker.redo();
	        invoker.redo();
	}
}
