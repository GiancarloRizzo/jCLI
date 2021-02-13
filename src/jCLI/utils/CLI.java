package jCLI.utils;

import java.util.Scanner;
import java.io.IOException;

/**
*
* @author giancarlo_rizzo
*/

public interface CLI {

    /**
     * Interface to support guided control of java-programms using the commandline.
     * 
     * Takes a question String 'question' and a variable count of options (String 'option_0', String 'option_1', ...)
     * and waits for the user's input. The input have to be integer-String which is proposed by the function. If it does
     * not match a guilty option, it asks again. You can stop the question by typing [INSERT]. If you do it will ask you to
     * quit or to go on with default value (DEFAULT_VALUE == 0).
     * 
     * 
     * @param question
     * @param options
     * @return validUserInput
     * @throws IOException
     */
	
	final int DEFAULT_VALUE = 0;

    public default int readUserinput(String question, String... options) throws IOException{
    	Validator validator = new Validator(); //inner-class to allow using this interface with java7 and lower
    	boolean isValid = false;
        Scanner console = new Scanner(System.in);
        String input;
        
        do {           
            System.out.print(question);
            
            int counter = 0;
            for (String option : options){
                System.out.print(" ("+ counter +") "+option);
                counter++;
            }     
            
            System.out.println(); 
            
            input = console.next();
            
            // validation: positive integer? && input matches guilty option?
            if (validator.isPostiveIntegerString(input)){
                isValid = true;
            } else {
                continue;
            }
            if (validator.isValidUserInput(input, options.length)) {
                isValid = true;
            } else {
                isValid = false;
            }
        } while (!isValid); 
        
        //return parsed number of String
        return Integer.parseInt(input);
    }

    // Using class to hide methods (needed until java8)
    class Validator {
    	// input matches guilty option?
        private boolean isValidUserInput (String input, int numOfOptions) {
            boolean isValid = false;
            if (!input.matches("")){
                if (numOfOptions > 1) {
                    isValid = (Integer.parseInt(input) < numOfOptions);
                } else { //if numOfOptions isnt specified, the user is allowed to type any number
                    isValid = true;
                }
            }
            return isValid;
        }

        // check if is positive number within String
        private boolean isPostiveIntegerString (String input) {
            for( int i = 0, n = input.length(); i<n; i++ ) {
                if(! Character.isDigit(input.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }
}