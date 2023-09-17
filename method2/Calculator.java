package method2;
import java.math.BigDecimal;
import java.math.RoundingMode;

/*实际执行计算部分 */
public class Calculator {
    private BigDecimal sumVal = new BigDecimal(0);
    public void cal(char operation, BigDecimal param){
        switch (operation) {
            case '+':
                sumVal = sumVal.add(param);
                break;
            case '-':
                sumVal = sumVal.subtract(param).setScale(2, RoundingMode.HALF_UP);
                break;
            case '*':
                sumVal = sumVal.multiply(param).setScale(2, RoundingMode.HALF_UP);
                break;
            case '/':
                sumVal = sumVal.divide(param, RoundingMode.HALF_UP);
                break;
        }
        System.out.println(operation+" "+param+" ="+sumVal);
    }
}