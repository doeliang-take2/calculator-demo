package method1;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


public class Calculator {
    //操作结果
    BigDecimal sumVal = null;
    //当前操作符
    String curSymbol;
    //记录操作符
    List<String> symbolList = new ArrayList<>();
    //记录操作结果集
    List<BigDecimal> sumList = new ArrayList<>();
    //记录参数
    List<BigDecimal> paramList = new ArrayList<>();
    //记录历史回溯的位置
    List<Integer> undoList = new ArrayList<>();
    //记录回溯标签
    int index = 0;
    //判断是否是回溯操作
    boolean track = false;

    public void cal(){
        //计算起始需要两个参数
        if(paramList.size()<2){
            return;
        }
        //默认计算最新参数
        BigDecimal param = paramList.get(paramList.size()-1);
        //如果是回溯操作则要指向回溯标签
        if(track){
            param = paramList.get(index+1);
        }

        switch (curSymbol) {
            case "+":
                sumVal = sumVal.add(param);
                break;
            case "-":
                sumVal = sumVal.subtract(param).setScale(2, RoundingMode.HALF_UP);
                break;
            case "*":
                sumVal = sumVal.multiply(param).setScale(2, RoundingMode.HALF_UP);
                break;
            case "/":
                sumVal = sumVal.divide(param, RoundingMode.HALF_UP);
                break;
        }
        System.out.println("result is "+ sumVal);
        sumList.add(sumVal);
    }

    public void redo(){
        //redo 就是从undo集里面恢复，所以每次弹出一个最新的undo符号，计算完后删除，undo集为空时无法redo
        if(undoList.size()==0){
            System.out.println("没有可以redo的部分");
            return;
        }
        index = undoList.get(undoList.size()-1);
        curSymbol = symbolList.get(undoList.get(undoList.size()-1));
        track = true;
        cal();
        track = false;
        //redo完后，删除插入的符号
        undoList.remove(undoList.size()-1);
    }

    public void undo(){
        index = symbolList.size()-1;
        //从符号集尾部遍历，查找到下一个没有被undo的符号
        while(undoList.contains(index)){
            index = index-1;
        }
        //当指向第0个符号时，无法再回滚
        if(index<1){
            System.out.println("回到初始化节点，无法继续回滚");
            return;
        }
        curSymbol = symbolList.get(index);
        switch(curSymbol) {
            case "+":
            curSymbol = "-";
            break;
            case "-":
            curSymbol = "+";
            break;
            case "*":
            curSymbol = "/";
            break;
            case "/":
            curSymbol = "*";
            break;               
        }
        track = true;
        cal();
        track = false;
        //将undo的符号指针存入，下次遍历跳过
        undoList.add(index);
        // System.out.println(sumVal);
        // System.out.println(undoList.toString());
        // System.out.println(symbolList);
        // System.out.println(sumList);
        // System.out.println(paramList);
    }

    public void setSumVal(BigDecimal value){
        if(sumVal==null){
            sumVal = value;
        }
        paramList.add(value);
    }

    public BigDecimal getSumVal(){
        return sumVal;
    }

    public void setCurSymbol(String symbol){
        curSymbol = symbol;
        symbolList.add(symbol);
    }

    public String getCurSymbol(){
        return curSymbol;
    }


    public static void main(String args[]){
        Calculator calculator = new Calculator();
        calculator.setSumVal(new BigDecimal(2));
        calculator.setCurSymbol("+");
        calculator.setSumVal(new BigDecimal(5));
        calculator.cal();
        calculator.setCurSymbol("*");
        calculator.setSumVal(new BigDecimal(3));
        calculator.cal();
        calculator.setCurSymbol("+");
        calculator.setSumVal(new BigDecimal(4));
        calculator.cal();
        calculator.setCurSymbol("-");
        calculator.setSumVal(new BigDecimal(2));
        calculator.cal();
        calculator.undo();
        calculator.undo();
        calculator.undo();
        calculator.undo();
        calculator.undo();
        calculator.setCurSymbol("+");
        calculator.setSumVal(new BigDecimal(1111));
        calculator.cal();
        calculator.undo();
        // calculator.undo();
        calculator.redo();
        calculator.redo();
        calculator.redo();
        calculator.redo();
        calculator.redo();
    }
}