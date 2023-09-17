package method2;

import java.math.BigDecimal;
import java.util.ArrayList;

/*实际执行计算的部分*/
public class Invoker {
	private Calculator calculator=new Calculator();
    //记录历史命令
	private ArrayList<Command> commands=new ArrayList<Command>();
    //记录undo符号的对应标签
    private ArrayList<Integer> undoList = new ArrayList<Integer>();
	private int current=0;

	public void redo() {
        if(undoList.size()==0){
            System.out.println("没有可以redo的部分");
            return;
        }
        commands.get(undoList.get(undoList.size()-1)).execute();
        //弹出最后一个undo的符号
        undoList.remove(undoList.size()-1);
	}

	public void undo() {
        int index = current-1;
        while(undoList.contains(index)){
            index = index-1;
        }
        if(index<0){
            System.out.println("回到初始化节点，无法继续回滚");
            return;
        }
        commands.get(index).unexecute();
        undoList.add(index);
	}
    
	public void compute(char operator,BigDecimal operand) {
        //封装一个计算
		Command command =new CalculatorCommand(calculator, operator, operand);
		command.execute();
		commands.add(command);
		current++;
	}
}
