package utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utility {
    public static String getProperty(String property)
    {
        Properties prop = new Properties();
        InputStream input = null;
        String gotProp = null;
        try
        {

            String filename = "chrome.properties";
            input = Utility.class.getClassLoader().getResourceAsStream(filename);
            if (input == null)
            {
                System.out.println("Sorry, unable to find " + filename);
            }

            //load a properties file from class path, inside static method
            prop.load(input);
            //get the property value and print it out
            gotProp = prop.getProperty(property);

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (input != null)
            {
                try
                {
                    input.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        return gotProp;
    }
}
