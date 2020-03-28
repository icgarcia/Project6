import java.io.*;

public class Parser 
{
	private BufferedReader bR;
	private String string;
	
	
	public Parser(File source) throws IOException 
	{
		if (source == null) 
		{
			throw new NullPointerException("source");
		}
		
		if (!source.exists()) 
		{
			throw new FileNotFoundException(source.getAbsolutePath());
		}
		
		this.bR = new BufferedReader(new FileReader(source));
		this.string = null;
		
	}
	
	public void close() 
	{
		try 
		{
			this.bR.close();
		} 
		catch (IOException e) 
		{
		}
	}
	
	public CommandType commandType() 
	{
		String trimmedLine = this.string.trim();
		
		if (trimmedLine.startsWith("(") && trimmedLine.endsWith(")")) 
			return CommandType.L_COMMAND;
		else if (trimmedLine.startsWith("@")) 
			return CommandType.A_COMMAND;
		else 
			return CommandType.C_COMMAND;
	}
	
	public String dest() 
	{
		String trimmedLine = this.string.trim();
		int index = trimmedLine.indexOf("=");
		
		if (index == -1) 
			return null; 
		else 
			return trimmedLine.substring(0, index);
	}
	
	public String comp() 
	{
		String trimmedLine = this.string.trim();
		int index = trimmedLine.indexOf("=");
		if (index != -1) 
			trimmedLine = trimmedLine.substring(index + 1);
		int compIndex = trimmedLine.indexOf(";");
		
		if (compIndex == -1) 
			return trimmedLine;
		else
			return trimmedLine.substring(0, compIndex);
	}
	
	public String jump() 
	{
		String trimmedLine = this.string.trim();
		int compIndex = trimmedLine.indexOf(";");
		
		if (compIndex == -1)
			return null;
		else
			return trimmedLine.substring(compIndex + 1);
	}
}