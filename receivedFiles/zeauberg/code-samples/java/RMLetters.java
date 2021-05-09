import java.util.*;

class RMLetters{
	private static boolean rmLetter(int currentL, int toDelLetter, StringBuilder sb){
			
		if(toDelLetter >= sb.length()) return false;

		else if(sb.charAt(currentL) == 'A' && sb.charAt(toDelLetter) == 'B'){
			sb.deleteCharAt(currentL);	
			sb.deleteCharAt(currentL);
			return true;
		}
		else if(sb.charAt(currentL) == 'B' && sb.charAt(toDelLetter) == 'A'){	
			sb.deleteCharAt(currentL);
			sb.deleteCharAt(currentL);
			return true;
		}
		else if(sb.charAt(currentL) == 'C' && sb.charAt(toDelLetter) == 'D'){			
			sb.deleteCharAt(currentL);
			sb.deleteCharAt(currentL);
			return true;
		}
		else if(sb.charAt(currentL) == 'D' && sb.charAt(toDelLetter) == 'C'){	
			sb.deleteCharAt(currentL);
			sb.deleteCharAt(currentL);
			return true;
		}

		return false;
	}

	public static String Solution(String s){
		
		StringBuilder sb = new StringBuilder(s);
		
		for(int i=0; i+1<sb.length(); ++i){
			while(rmLetter(i, i+1, sb)){ }
		}

		return sb.toString();
	}

	public static void main(String [] args){
		String s = "BACBACD";
		System.out.println(s);
		System.out.println(Solution(s));

	}
}
