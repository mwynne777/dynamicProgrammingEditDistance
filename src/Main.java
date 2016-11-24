
public class Main {

	
	public static void main(String[] args) {
		
		String str1 = "babb";
		String str2 = "aabab";
		System.out.println("Total edit distance between str1 and str2 is: " + editDistance(str1, str2));

	}

	public static int editDistance(String s1, String s2){
		
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
		
		for(int c = 0; c < s2.length() + 1; c++){
			System.out.println();
			for(int d = 0; d < s1.length() + 1; d++){
				System.out.print(table[c][d]);
			}
		}
		System.out.println();
		
		return table[s2.length()][s1.length()];
		
	}
	
	public static void traceback(){
		
		
		
	}
	
}