package mainpack;

import java.io.*;
import java.util.*;

public class FileWorker {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static String read (String path) throws FileNotFoundException  {
		String str = "";
		File file = new File(path);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
        str = str + scanner.nextLine();
        str = str + "\r\n";
        }
        scanner.close();
		return str;    
	}
	
	public static void write (String path, String str) {
		FileWriter writeFile = null;
		try {
		    File file = new File(path);
		    writeFile = new FileWriter(file);
		    writeFile.write(str);
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if(writeFile != null) {
		        try {
		            writeFile.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
	}
}
