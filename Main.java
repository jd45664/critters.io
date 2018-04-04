package assignment4;
/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */

import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */

    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        while(true) {
        System.out.print("critters>");
    	kb.useDelimiter("\\s+");
    	String[] line = kb.nextLine().split("\\s+");
    	try {
        int number;
    	if(line[0].equals("quit")) {		//quit
    		 if(line.length == 1) {
    			 break;
    		 }else {
    			 throw new InvalidInputException(line);
    		 }
    	}else if(line[0].equals("show")) {		//show
   		 	if(line.length == 1) {
   		 	Critter.displayWorld();
   		 	}else {
   		 	throw new InvalidCritterException(line[1]);
   		 	}
    	}else if(line[0].equals("step")) {		//step [<count>]
    		if(line.length == 1) {
    			Critter.worldTimeStep();
    		}else if(line.length == 2){
    			number = Integer.parseInt(line[1]);
    			for(int i = 0; i < number;i++) {
    				Critter.worldTimeStep();
    			}
    		}else {
    			throw new InvalidCritterException(line[1]);
    		}
    	}else if(line[0].equals("seed")) {		//seed <number>
    		if(line.length == 2) {
    			number = Integer.parseInt(line[1]);
    			Critter.setSeed(number);
    		}else {
    			throw new InvalidCritterException(line[1]);
    		}
		}else if(line[0].equals("make")) {		//make <class_name> [<count>]
			if(line.length == 2) {
				Critter.makeCritter(line[1]);
    			}else 
			if(line.length == 3) {
				number = Integer.parseInt(line[2]);
				for(int i = 0; i< number; i++) {
					Critter.makeCritter(line[1]);
				}
			}else {
				throw new InvalidCritterException(line[1]);
    			}
			
    	}else if(line[0].equals("stats")) {		//stats <class_name>
    		if(line.length == 2) {
    			List<Critter> result = Critter.getInstances(line[1]);
    			String pack = Critter.class.getPackage().getName() + ".";
    			pack += line[1];
    			Class C = Class.forName(pack);
    			Critter crit = (Critter) C.newInstance();
    			Method method = crit.getClass().getMethod("runStats",List.class);
    			method.invoke(crit, result);
    		}else {
    			throw new InvalidCritterException(line[1]);
			}
        }else {
        	throw new InvalidInputException(line);
        }
    	}catch(NumberFormatException e) {
    		System.out.print("error processing:");
    		for(int i = 0; i < line.length;i++) {
    			System.out.print(" " + line[i]);
    		}
    		System.out.println();
    	}catch(InvalidCritterException e) {
    		System.out.print("error processing:");
    		for(int i = 0; i < line.length;i++) {
    			System.out.print(" " + line[i]);
    		}
    		System.out.println();
    	}catch(NoClassDefFoundError e){
    		System.out.print("error processing:");
    		for(int i = 0; i < line.length;i++) {
    			System.out.print(" " + line[i]);
    		}
    		System.out.println();
        }
        catch(InvalidInputException e) {
        	System.out.print("invalid command:");
    		for(int i = 0; i < line.length;i++) {
    			System.out.print(" " + line[i]);
    		}
    		System.out.println();
    	} catch (ClassNotFoundException e) {
    		System.out.print("error processing:");
    		for(int i = 0; i < line.length;i++) {
    			System.out.print(" " + line[i]);
    		}
    		System.out.println();
		} catch (NoSuchMethodException e) {
    		System.out.print("error processing:");
    		for(int i = 0; i < line.length;i++) {
    			System.out.print(" " + line[i]);
    		}
    		System.out.println();
		} catch (SecurityException e) {
    		System.out.print("error processing:");
    		for(int i = 0; i < line.length;i++) {
    			System.out.print(" " + line[i]);
    		}
    		System.out.println();
		} catch (InstantiationException e) {
    		System.out.print("error processing:");
    		for(int i = 0; i < line.length;i++) {
    			System.out.print(" " + line[i]);
    		}
    		System.out.println();
		} catch (IllegalAccessException e) {
    		System.out.print("error processing:");
    		for(int i = 0; i < line.length;i++) {
    			System.out.print(" " + line[i]);
    		}
    		System.out.println();
		} catch (IllegalArgumentException e) {
    		System.out.print("error processing:");
    		for(int i = 0; i < line.length;i++) {
    			System.out.print(" " + line[i]);
    		}
    		System.out.println();
		} catch (InvocationTargetException e) {
    		System.out.print("error processing:");
    		for(int i = 0; i < line.length;i++) {
    			System.out.print(" " + line[i]);
    		}
    		System.out.println();
		} 
    	
        }
        /* Write your code above */
        System.out.flush();
}
}

