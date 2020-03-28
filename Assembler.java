import java.io.*;

public class Assembler 
{
	private File file;
	private BufferedWriter bW;
	private Encoder encoder;
	
	public Assembler(File source, File target) throws IOException 
	{
		this.file = source;
		
		FileWriter fw = new FileWriter(target);
		this.bW = new BufferedWriter(fw);
		
		this.encoder = new Code();
	}
	
	public void translate() throws IOException 
	{
		this.parse();
	}
		
	private void parse() throws IOException 
	{
		Parser parser = new Parser(this.file);
		while (parser.hasNexLine()) {
			parser.getNextLine();
	
			CommandType commandType = parser.commandType();
			String instruction = null;
			
			if (commandType.equals(CommandType.A_COMMAND)) 
			{
				String symbol = parser.symbol();
				address = symbol;
				instruction = this.formatAInstruction(address);
			} 
			
			else if (commandType.equals(CommandType.C_COMMAND)) 
			{
				String comp = parser.comp();
				String dest = parser.dest();
				String jump = parser.jump();
				instruction = this.formatCInstruction(comp, dest, jump);
			}
	
			if (!commandType.equals(CommandType.L_COMMAND)) 
			{
				this.bW.write(instruction);
				this.bW.newLine();
			}
		}
		
		parser.close();
		this.bW.close();
	}

	private String formatAInstruction(String address) 
	{
		String formatNum = this.encoder.formatNumberAsBinary(address);
		return "0" + formatNum;
	}
	
	private String formatCInstruction(String comp, String dest, String jump) 
	{
		StringWriter sW = new StringWriter();
		sW.append("111");
		sW.append(this.encoder.comp(comp));
		sW.append(this.encoder.dest(dest));
		sW.append(this.encoder.jump(jump));
		return sW.toString();
	}
}