import java.util.Scanner;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Code for HW7
 * @author
 */
/**
   This program provides lookups by city and zip code
*/
public class ZipLookupWeb
{  
   public static void main(String[] args)
         throws IOException 
   {
	
	   Scanner in = new Scanner(System.in);
	   boolean more = true;
	   while (more){
		   System.out.println("Lookup Z)ip, C)ity name, Q)uit?");
	       String cmd = in.nextLine();
	       
	       if (cmd.equalsIgnoreCase("Q")){
	    	   more = false;
	       }else if (cmd.equalsIgnoreCase("Z")){
	    	   System.out.println("Enter Zipcode:");
	           String n = in.nextLine();
	           String pageurl = "http://www.melissadata.com/lookups/ZipCityPhone.asp?InData=" +n;
			   URL u = new URL(pageurl);
	           URLConnection connection = u.openConnection();
	            
	           InputStream inStream = connection.getInputStream();
	           Scanner ins = new Scanner(inStream);
				
	           String statename = "";
	           String cityname = "";

	           // read each line, detect if the line contains city name or state name,
			   // extract and store the information
	           while (ins.hasNextLine()){
	        	  String currentLine = ins.nextLine();
	        	  if (currentLine.contains(">USPS Preferred City Name<")) {
	        	  
	        		int begin_index = currentLine.indexOf("<b>") + 3;
	        		int end_index = currentLine.indexOf("</b>");
	        		
	        			
	        	    cityname = currentLine.substring(begin_index, end_index).trim();
	        		
	        		
	        	  }
	        	  if (currentLine.contains(">State<")) {
		        	  
		        		int begin_index = currentLine.indexOf("<b>") + 3;
		        		int end_index = currentLine.indexOf("</b>");
		        		
		        			
		        			statename = currentLine.substring(begin_index, end_index).trim();
		        		
		        		  //System.out.println(currentLine);
		        	  }
	               
	           	}
	            System.out.println(cityname + ", " + statename);
	            ins.close();
	            
	       }else if (cmd.equalsIgnoreCase("C")){
	    	   System.out.println("Enter city name:");
	    	   String ct = in.nextLine().replace(' ', '+');
	    	  
	    	   System.out.println("Enter State name:");
	    	   String st = in.nextLine();

			   String pageurl = "http://www.melissadata.com/lookups/ZipCityPhone.asp?InData=" +ct + "+" +st;
					   
	    	   URL u = new URL(pageurl);
	    	   URLConnection connection = u.openConnection();
		            
	    	   InputStream inStream = connection.getInputStream();
	    	   Scanner ins = new Scanner(inStream);
		       String zipCode = "";
			   // read each line, detect if the line contains zip code,
			   // extract zip code and print
	    	   while (ins.hasNext())
	    	   {  
	    		   String currentLine = ins.nextLine();
		        	  if (currentLine.contains("ZipCityPhone.asp?zip=")) {
		        	  
		        		int begin_index = currentLine.indexOf("?zip") + 5;
		        		int end_index = currentLine.indexOf("\">");
		        		
		        			
		        		zipCode = currentLine.substring(begin_index, end_index).trim();
		        		System.out.println(zipCode);
		        		
		        	  }
		        	  
	    	   }
	    	   ins.close(); 
	       }
	   } // end while
	         
	   in.close();
   }
}
