package binary;

import java.util.Scanner;

public class Executioner1 {

public static void main(String[] args) {
	convertFromNumberToOctate();	
	convertFromOctateToNumber();
}

private static void convertFromNumberToOctate() {
	String value = scanner("Enter the Total");
	String octate = totalToOctate(value);
	System.out.println("the octate for this total is: "+octate);
}

private static void convertFromOctateToNumber() {
	String octate = scanner("Enter the Octate value");
	int value = octateToNum(octate);
	System.out.println("the total for this octate is: "+value);
}

private static String scanner(String message) {
	Scanner snr = new Scanner(System.in);
    System.out.println(message);
    String octate = snr.nextLine();
    snr.close();
    return octate;    
}

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

private static String totalToOctate(String sValue) {
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

public static String replaceEachOctVal(String octate, int value, int binaryValue, int placement) {
	if (value >= binaryValue) {
		octate = replaceChar(octate, '1', placement-1);
	}
	return octate;
}

private static String replaceChar(String str, char ch, int index) {
    StringBuilder myString = new StringBuilder(str);
    myString.setCharAt(index, ch);
    return myString.toString();
}
}
