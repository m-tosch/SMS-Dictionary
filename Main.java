import java.io.File;
import java.util.Scanner;

public class Main{

	public static void main(String[] args){
		PhoneDict p = new PhoneDict(new File("words.txt"));
		
		// Example sequence
		String seq = "0002776468330768630252773300444440";
		System.out.println(p.decodeSequence(seq));
		
		//// Optionally scan a sequence from System in
		// Scanner sc = new Scanner(System.in);
		// while(sc.hasNextLine()){
		//	System.out.println(p.decodeSequence(sc.nextLine()));
		// }
		// sc.close();
		
	}
}