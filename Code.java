import java.util.*;

public class Code 
{
	public Code() 
	{
		this.jumpInstructions();
		this.compInstructions();
		this.destInstructions();
	}
	
	private void jumpInstructions() 
	{
		this.jumpInstructions.add("NULL", "000");
		this.jumpInstructions.add("JGT", "001");
		this.jumpInstructions.add("JEQ", "010");
		this.jumpInstructions.add("JGE", "011");
		this.jumpInstructions.add("JLT", "100");
		this.jumpInstructions.add("JNE", "101");
		this.jumpInstructions.add("JLE", "110");
		this.jumpInstructions.add("JMP", "111");
	}
	
	private void compInstructions() 
	{
		this.compInstructions.add("0", "0101010");
		this.compInstructions.add("1", "0111111");
		this.compInstructions.add("A", "0001100");
		this.compInstructions.add("M", "0110000");
		this.compInstructions.add("D", "1110000");
	}
	
	private void destInstructions() 
	{
		this.destInstructions.add("NULL", "000");
		this.destInstructions.add("A", "001");
		this.destInstructions.add("M", "010");
		this.destInstructions.add("D", "100");
	}
	
	public String dest(String instruction) 
	{
		if (instruction == null || instruction.isEmpty()) 
			instruction = "NULL";
		return this.destInstructions.get(instruction);
	}
	
	public String comp(String instruction) 
	{
		return this.compInstructions.get(instruction);
	}
	
	public String jump(String instruction) 
	{
		if (instruction == null || instruction.isEmpty())
			instruction = "NULL";
		return this.jumpInstructions.get(instruction);
	}
}