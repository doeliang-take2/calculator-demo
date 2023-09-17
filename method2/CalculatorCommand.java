package method2;

import java.math.BigDecimal;

/*实现了计算命令的类 */
public class CalculatorCommand implements Command {
	private Calculator calculator;
	private char operator;
	private BigDecimal operand;
	
	CalculatorCommand(Calculator calculator, char operator, BigDecimal operand) {
		super();
		this.calculator = calculator;
		this.operator = operator;
		this.operand = operand;
	}

	public Calculator getCalculator() {
		return calculator;
	}

	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}

	public char getOperator() {
		return operator;
	}

	public void setOperator(char operator) {
		this.operator = operator;
	}

	public BigDecimal getOperand() {
		return operand;
	}

	public void setOperand(BigDecimal operand) {
		this.operand = operand;
	}

	public void execute() {
		calculator.cal(operator, operand);

	}

	public void unexecute() {
		calculator.cal(undo(operator), operand);

	}

	private char undo(char operator2) {
		char ch = 0;
		switch(operator2) {
		case '+':ch='-';break;
		case '-':ch='+';break;
		case '*':ch='/';break;
		case '/':ch='*';break;
		}
		return ch;
	}
}
