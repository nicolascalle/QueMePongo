package configuracion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Propiedades {
	
	public Properties propiedades = new Properties();
	public InputStream is = null;
	
	public void cargarArchivoDePropiedades() throws FileNotFoundException {	
		try{
			is = new FileInputStream("C:\\Users\\calle\\Desktop\\2019-mi-no-group-07\\src\\main\\java\\configuracion\\config.txt");
			propiedades.load(is);
		}catch(IOException ex) {
			System.out.println(ex.toString());
		}
	}
}
