package eval;

import java.util.Scanner;

import node.BaseNode;

public class Driver {

	public static void main(String[] args) {
		Boolean keepgoing = true;
		Scanner scanner = new Scanner(System.in);
		
		while(keepgoing) {
			System.out.println("Enter an Expression or STOP");
			String expression = scanner.nextLine();
			if (!expression.equals("STOP")) {
				try {
					Parser parser = new Parser(expression);
					BaseNode root = parser.parse();
					System.out.println(root.evaluate());
				}
				catch(Exception e) {
					System.out.println("Not a Valid Expression or STOP");
				}
			}
			else {
				keepgoing = false;
			}
			
		}	
		
		scanner.close();
	}

}
