package jCLI.example;
import java.io.IOException;

import jCLI.utils.CLI;

public class Example_0 {

	public static void main(String[] args) {
		CLI cli = (CLI) new DummyClass(); // create an object which implemets CommandLineInterface-Class CLI
		
		/* guided control */
		// example with two options
		String question = "Do you want to go left or right?";
		int parsedInputNumber = cli.DEFAULT_VALUE;
		
		try {
			parsedInputNumber = cli.readUserinput(question, "left", "right"); // give CLI your question and your options
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		switch (parsedInputNumber) {
			case 0: System.out.println("You choosed left"); break;
			case 1: System.out.println("You choosed right"); break;
		}
		
		
		
		// example with four options
		question = "Which mode do you want to use?";
		parsedInputNumber = cli.DEFAULT_VALUE;
		
		try {
			parsedInputNumber = cli.readUserinput(question, "Beginner", "Basic", "Experienced", "Professional"); // give CLI your question and your options
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		switch (parsedInputNumber) {
		case 0: System.out.println("You will continue in Beginner-mode"); break;
		case 1: System.out.println("You will continue in Basic-mode"); break;
		case 2: System.out.println("You will continue in Experienced-mode"); break;
		case 3: System.out.println("You will continue in Professional-mode"); break;
		}
		
		// example without option (type in command)
		// todo!
	}
}
