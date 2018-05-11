
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Stack;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.errors.TransportException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.deque.axe.AXE;


public class Demo1{
	
	static final URL scriptUrl = Demo1.class.getResource("axe.min.js");
	static Axe_InputCollector collect=new Axe_InputCollector();
	static List<String> filelist =new ArrayList();
	static ArrayList<String> pagename = new ArrayList<String>();
	public static void main(String[] args) throws FileNotFoundException, InvalidRemoteException, org.eclipse.jgit.api.errors.TransportException, IOException, ParseException, GitAPIException, JSONException {
		
		
		 File file = new File("/home/ec2-user/docker_accessibility/datafile.properties");
			FileInputStream fileInput = null;
			try {
				fileInput = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Properties prop = new Properties();
			
			//load properties file
			try {
				prop.load(fileInput);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		
		File file1 = new File("/usr/local/bin/phantomjs");
        System.setProperty("phantomjs.binary.path", file1.getAbsolutePath());  

		WebDriver driver = new PhantomJSDriver();

		
		driver.get("http://demo.testfire.net/");
	
		//calling method axe
		filelist=collect.inputCollector(driver, 1,scriptUrl);
		pagename=collect.Pagename("Demo");
		
		
		driver.get("http://demo.testfire.net/default.aspx?content=personal_deposit.htm");
		
		
		//calling method axe
		filelist=collect.inputCollector(driver, 2, scriptUrl);
		pagename=collect.Pagename("PersonalDeposit");
		
		
		
		driver.get("https://www.neotys.com/");
		
		
		//calling method axe
		filelist=collect.inputCollector(driver, 3, scriptUrl);
		pagename=collect.Pagename("Neotys");
		
		
		collect.parsingfile(filelist, pagename);
		
		
	}
}
