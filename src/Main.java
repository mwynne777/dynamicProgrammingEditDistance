import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		String str1 = "";
		String str2 = "";
		if(args.length != 0){
			FileReader fileReader = new FileReader(args[0]);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			try
			{
				bufferedReader.readLine();
				str1 = bufferedReader.readLine();
				str2 = bufferedReader.readLine();
				bufferedReader.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
		}else{
			str1 = "babb";
			str2 = "aabab";
		}
		System.out.println("Total edit distance between str1 and str2 is: " + editDistance(str1, str2)[str2.length()][str1.length()]);
		traceback(editDistance(str1, str2), str1, str2);
	}

	public static int[][] editDistance(String s1, String s2){		
		int[][] table = new int[s2.length() + 1][s1.length() + 1];	
		for(int i = 0; i < s2.length() + 1; i++){
			for(int j = 0; j < s1.length() + 1; j ++){
				if(i == 0){
					table[i][j] = j;	
				}else if(j == 0){
					table[i][j] = i;
				}else if(s2.charAt(i-1) == s1.charAt(j-1)){	
					table[i][j] = table[i-1][j-1];	
				}else{
					int temp = Math.min(table[i-1][j] + 1, table[i][j-1] + 1);
					table[i][j] = Math.min(temp, table[i-1][j-1] + 1);		
				}		
			}	
		}
		//Simply print the array
		for(int c = 0; c < s2.length() + 1; c++){
			System.out.println();
			for(int d = 0; d < s1.length() + 1; d++){
				System.out.print(table[c][d] + " ");
			}
		}
		System.out.println();
		return table;
	}
	public static void traceback(int[][] table, String str1, String str2){
		//Perform traceback on table to find where spaces belong
		int xLength = table.length - 1;  //Number of columns in the table
		int yLength = table[0].length - 1;  //Number of rows in the table
		System.out.println(xLength + "  " + yLength);
		
		while(xLength >= 1 && yLength >= 1){
			System.out.println("Hello, xLength = " + xLength + " yLength = " + yLength);
			System.out.println(table[xLength][yLength] + "   " + table[xLength-1][yLength-1]);
			/*if(yLength == 1){  //If we hit the top of the table, we need to move left
				yLength -= 1;
			}else if(xLength == 1){  //If we hit the left end of the table, we need to move up
				xLength -= 1;
			}else */
			if(str1.charAt(yLength-1) != str2.charAt(xLength-1) && (table[xLength-1][yLength] == (table[xLength][yLength] - 1) || (table[xLength-1][yLength] < table[xLength][yLength-1] && table[xLength-1][yLength] < table[xLength-1][yLength-1]))){
				str1 = str1.substring(0, yLength) + "_" + str1.substring(yLength, str1.length());
				xLength -= 1;
			}else if(str1.charAt(yLength-1) != str2.charAt(xLength-1) && (table[xLength][yLength-1] == (table[xLength][yLength] - 1) || (table[xLength][yLength-1] < table[xLength-1][yLength] && table[xLength][yLength-1] < table[xLength-1][yLength-1]))){
				str2 = str2.substring(0, xLength) + "_" + str2.substring(xLength, str2.length());
				yLength -= 1;
			}else if(table[xLength -1][yLength - 1] == table[xLength][yLength] /*&& str1.charAt(yLength-1) == str2.charAt(xLength-1) && table[xLength-1][yLength] != (table[xLength][yLength] - 1) && table[xLength][yLength - 1] != (table[xLength][yLength] - 1)*/){
				//System.out.println(str1.charAt(yLength-1) + "  " + str2.charAt(xLength-1));
				xLength -= 1;
				yLength -= 1;
			} else if(table[xLength -1][yLength - 1] == table[xLength][yLength] - 1){
				xLength -= 1;
				yLength -= 1;
			}
			System.out.println("Goodbye, xLength = " + xLength + " yLength = " + yLength);

		}
		System.out.println(str1);
		System.out.println(str2);
	}
}