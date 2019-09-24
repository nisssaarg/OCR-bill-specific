import net.sourceforge.tess4j.*;

import java.io.*;

public class Tess4JDemo {
    private static Tesseract getTesseract() {
        Tesseract instance = new Tesseract();
        instance.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");//make changes here for the location of your tesseract OCR
        instance.setLanguage("eng");
        //instance.setHocr(true);
        return instance;
    }

    public static void main(String[] args) throws TesseractException{
        try {
    	Tesseract tesseract = getTesseract();
        File file = new File("C:\\Users\\pakku\\Desktop\\testing2.jpeg");//image location
        String result = tesseract.doOCR(file);
        System.out.println(result);
        }
        catch(Exception e)
        {
        	System.out.println("Enter the details in the fields\n");
        }
    }
}