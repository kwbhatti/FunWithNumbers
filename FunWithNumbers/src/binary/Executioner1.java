package binary;

import java.util.Scanner;

public class Executioner1 {

/**
 * Main method
 * @param args : Main method args
 */
public static void main(String[] args) {
	convertFromNumberToOctate();	
	convertFromOctateToNumber();
}

/**
 * Converts a Number to an Octate based on reading the value from the Console using Scanner utility
 */
private static void convertFromNumberToOctate() {
	String value = scanner("Enter the Total");
	String octate = binaryToOctate(value);
	System.out.println("the octate for this total is: "+octate);
}

/**
 * Converts an Octate to a Number based on reading the value from the Console using Scanner utility
 */
private static void convertFromOctateToNumber() {
	String octate = scanner("Enter the Octate value");
	int value = octateToNum(octate);
	System.out.println("the total for this octate is: "+value);
}

/**
 * @param message : Message to print out
 * @return String : Returns user entry to the console
 */
private static String scanner(String message) {
	Scanner snr = new Scanner(System.in);
    System.out.println(message);
    String octate = snr.nextLine();
    snr.close();
    return octate;    
}

/**
 * @param octate : Eight digits number with 1's and 0's
 * @return int : Returns a number after converting the octate to binary
 */
private static int octateToNum(String octate) {
	int answer = 0;
	String[] eachValueOfOctate  = octate.split("");	
	if (!(eachValueOfOctate.length == 8)) {
		System.out.println("The Octate should be 8 numbers");
		System.exit(0);
	}
	for (int i = 0; i < eachValueOfOctate.length; i++) {
		if (!eachValueOfOctate[i].equals("0") && !eachValueOfOctate[i].equals("1")) {
			System.out.println("The Octate should only have 0's or 1's");
			System.exit(0);
		}
	}
	for (int i = 0; i<eachValueOfOctate.length; i++) {
		answer = answer+binarySwitch(eachValueOfOctate[i], i+1);
	}			
	return answer;
}

/**
 * @param onOffValue : The value of a single digit in an octate
 * @param placement : The placement of the single digit in an octate where, 1 would equal to 128 and 8 would equal to 1
 * @return int : Returns the value of the octate digit based on its placement and whether it is 1 or 0 
 */
private static int binarySwitch(String onOffValue, int placement) {
	int answer = 0;
		if (onOffValue.equals("1")) {
			switch(placement) {
			case 1:
				answer = 128;
				break;
			case 2:
				answer = 64;
				break;
			case 3:
				answer = 32;
				break;
			case 4:
				answer = 16;
				break;
			case 5:
				answer = 8;
				break;
			case 6:
				answer = 4;
				break;
			case 7:
				answer = 2;
				break;
			case 8:
				answer = 1;
				break;
			}
			return answer;
		}
	return answer;
}

/**
 * @param sValue : Takes a number as a string from 0 to 255
 * @return String : Returns an octate as a string after converting
 */
private static String binaryToOctate(String sValue) {
	if (!sValue.matches("[0-9]+") || sValue.length() > 3) {
		System.out.println("Please use numbers between 0 and 255");
		System.exit(0);
	}
	int value = Integer.parseInt(sValue);
	if (value > 255) {
		System.out.println("Please use numbers between 0 and 255");
		System.exit(0);
		
	}
	String octate = "00000000";
	int binaryValue = 128;
	int placement = 1;
	while (value > 0) {
		octate = replaceEachOctVal(octate, value, binaryValue, placement);
		if (value >= binaryValue) {
			value = value-binaryValue;
		}
		binaryValue = binaryValue/2;
		placement++;
	}		
	return octate;
}

/**
 * @param octate : Takes the current octate
 * @param value : The total value of the number to be converted 
 * @param binaryValue : The value of the binary depending on the placement, eg 128 if its the first placement
 * @param placement : Which placement would it replace 
 * @return String : Returns an octate (partically processed)
 */
public static String replaceEachOctVal(String octate, int value, int binaryValue, int placement) {
	if (value >= binaryValue) {
		octate = replaceChar(octate, '1', placement-1);
	}
	return octate;
}

/**
 * @param str : Full string , input
 * @param ch : The replacement for the one to be replaced
 * @param index : The index of the character in the string that needs to be replaced
 * @return String : After replacing the value of the character based on its index
 */
private static String replaceChar(String str, char ch, int index) {
    StringBuilder myString = new StringBuilder(str);
    myString.setCharAt(index, ch);
    return myString.toString();
}
}
