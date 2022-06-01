package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class javaToPython {

	public static String line_recognation() throws IOException{
		String result = runCmd("cmd.exe /c conda activate myenv && python C:\\Users\\Mali\\eclipse-workspace-web\\Handwriting\\src\\main\\webapp\\upload\\main.py");
		//System.out.println(result);
		result = result.split(" n")[0];
		result = result.split(" mn")[0];
		return result;
	}
	private static String runCmd(String command) throws IOException
	{
		String s = null;

        try {
            
        // run the Unix "ps -ef" command
            // using the Runtime exec method:
            Process p = Runtime.getRuntime().exec(command);
            
            BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(p.getErrorStream()));

            // read the output from the command
            while ((s = stdInput.readLine()) != null) {
            	return s;
            }
            
            // read any errors from the attempted command
            while ((s = stdError.readLine()) != null) {
            	return s;
            }
            
            return s;
        }
        catch (IOException e) {
            System.out.println("exception happened");
            e.printStackTrace();
            return null;
        }
	}
	
}