package Testing;
import java.io.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.text.PDFTextStripper;
import java.util.regex.*;
class OlaUber {
	
	public void uber(String addr)throws IOException{
		int index=0;
		File file=new File(addr);
		PDDocument doc=PDDocument.load(file);
		StringBuilder sb=new StringBuilder();
		PDFTextStripper stripper=new PDFTextStripper();
		sb.append(stripper.getText(doc));
		String text =stripper.getText(doc);
		//To find the total ride fare
		Pattern p=Pattern.compile("Total \\S\\d\\d\\d\\S\\d\\d");
		Matcher m=p.matcher(sb);
		while(m.find()) {
			System.out.println(m.group());
		}
		//To find the date
		Pattern p1=Pattern.compile("\\w\\w\\w\\S\\s\\w\\w\\w\\s\\d\\d\\S\\s\\d\\d\\d\\d");
		Matcher m1=p1.matcher(sb);
		if(m1.find()) {
			System.out.println(m1.group());
		}
		//Finding travel distance
		int i=text.indexOf("km");
		String dist=text.substring(i-6,i);
		System.out.println("Distance="+dist+"km");
		//Findng the from and to points of travel
		Pattern p3=Pattern.compile("\\d\\d:\\d\\d\\w\\w");
		Matcher m3=p3.matcher(sb);
		System.out.println("From:");
		while(m3.find()) {
		 index=text.indexOf("India");
		int index2=text.indexOf(m3.group());
		String add=text.substring(index2+7,index);
		System.out.println(add);
		break;
		}
		System.out.println("To:");
		Pattern p4=Pattern.compile("\\d\\d:\\d\\d");
		Matcher m4=p4.matcher(sb);
		while(m4.find()) {
			if(m4.find()==m3.find()) {
		 index=text.lastIndexOf("India");
		int index2=text.indexOf(m4.group());
		System.out.println(text.substring(index2+7,index));
			}
		}
		if(doc!=null)
			doc.close();
	}
	public  void ola(String addr)throws IOException{
		File file=new File(addr);
		PDDocument doc=PDDocument.load(file);
		StringBuilder sb=new StringBuilder();
		PDFTextStripper stripper=new PDFTextStripper();
		sb.append(stripper.getText(doc));
		String text =stripper.getText(doc);
		//To find the date of travel
		Pattern p1=Pattern.compile("\\d\\d \\w\\w\\w, \\d\\d\\d\\d");
		Matcher m1=p1.matcher(sb);
		while(m1.find()) {
			System.out.println(m1.group());
		}
		//To find the cost of travel
		int index=text.indexOf("CRN");
		String fare=text.substring(index-10,index-3);
		System.out.println("Total bill:"+fare);
		//To find the From and to points of travel
		Pattern p2=Pattern.compile("\\d\\d:\\d\\d");
		Matcher m2=p2.matcher(sb);
		String[] count=new String[2];
		int i=0;
		while(m2.find()) {
			count[i]=m2.group();
			i++;
		}
		System.out.println("from:\n"+text.substring(text.indexOf(count[0])+8,text.indexOf(count[1])));
		System.out.println("to:\n"+text.substring(text.indexOf(count[1])+8,text.lastIndexOf("Paid")));
	}
	public static void main(String args[])throws IOException{
		String addr="C:/Users/pakku/Desktop/Test/uber.pdf";
		File file=new File(addr);
		PDDocument doc=PDDocument.load(file);
		PDFTextStripper stripper=new PDFTextStripper();
		String text =stripper.getText(doc);
		//Selecting the Taxi service
		if(text.indexOf("Ola")!=-1) {
			new OlaUber().ola(addr);
		}
		if(text.indexOf("uber")!=-1) {
			new OlaUber().uber(addr);
		}
	}
}
