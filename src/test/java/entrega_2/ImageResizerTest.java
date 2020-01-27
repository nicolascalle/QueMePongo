package entrega_2;

import java.io.IOException;

import org.junit.Test;

import prenda.ImageResizer;

public class ImageResizerTest {

	@Test
	public void creacionDeImagenes() throws IOException {
		String inputImagePath = "/Users/demon/Downloads/descarga.jpeg";
        String outputImagePath1 = "/Users/demon/Downloads/fixed1.jpg";
        String outputImagePath2 = "/Users/demon/Downloads/smaller.jpg";
        String outputImagePath3 = "/Users/demon/Downloads/bigger.jpg";
        String outputImagePath4 = "/Users/demon/Downloads/fixed2.jpg";
 
        // resize to a fixed width (not proportional)
        int scaledWidth = 400;
        int scaledHeight = 400;
        ImageResizer.resize(inputImagePath, outputImagePath1, scaledWidth, scaledHeight);
        scaledWidth = 600;
        scaledHeight = 600;
        ImageResizer.resize(inputImagePath, outputImagePath4, scaledWidth, scaledHeight);
            
 
        // resize smaller by 50%
        double percent = 0.5;
        ImageResizer.resize(inputImagePath, outputImagePath2, percent);
 
        // resize bigger by 50%
        percent = 1.5;
        ImageResizer.resize(inputImagePath, outputImagePath3, percent);
	}
	
}
