import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
interface StringMatching
{
	public String FileReading(File file);
	public String remove_Delimiters(String s1);
	public ArrayList<String> substrings(String s1);
	public ArrayList<String> common_substring(ArrayList<String> words1,ArrayList<String> words2);
}
class LCS implements StringMatching{
	public String FileReading(File file)
	{
		/*	to read file and move the characters into 
			string str and returns str
		*/   
		String line = null;
		StringBuilder str = new StringBuilder();
		try{
			BufferedReader br1 = new BufferedReader(new FileReader(file));
			while ((line = br1.readLine()) != null){
				str.append(line);
				str.append(" ");
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return str.toString();
	}
	public String remove_Delimiters(String s1){
		/*	Input : It accepts the String as an input

			Functionality : function removes delimiters i.e., special characters
							and returns the string

			Output : It returns the string without delimiters
		*/
				s1=s1.toLowerCase();
				s1= s1.replaceAll("[^\\w\\s]","");
				return s1;
	}
	public ArrayList<String> substrings(String s1){
	/*	Input : This method accepts String s1 as input
		
		Functionality : It add the substrings into the add arraylist

		Output : It returns the array containing substrings
	*/
	ArrayList<String> words =new ArrayList<String>();
		int l=s1.length();
		for (int i=0;i<=l;i++) {
			for (int j=i+1;j<=l;j++) {
				words.add(s1.substring(i,j));
		}
	}
	return words;
	}
	public ArrayList<String> common_substring(ArrayList<String> words1,ArrayList<String> words2){
		/* Input : It accepts the two array String arrays
			
			Functionality : It stores the common substring from two arrays 
							to another array
			
			Output : It returns words array with common substrings
		*/
		ArrayList<String> words =new ArrayList<String>();
		int l1=words1.size();
		int l2=words2.size();
		for (int i=0;i<l1;i++) {
			for (int j=0;j<l2;j++) {
				if((words1.get(i)).equals(words2.get(j)))
				{

					words.add(words1.get(i));
				}
			}
		}
		return words;
	}
	public String Largest_Common_Substring(ArrayList<String> common_substrings){
		/*	Input : It accepts String array as input

			Functionality : It calculates the largest common substring 
			
			Output : Returns the largest common substring				

		*/
		int max=0;
		String substrng="";
		for(int k=0;k<common_substrings.size();k++){
			String p=common_substrings.get(k);
			int n=p.length();
			if(n>max){
				max=n;
				substrng=p;
			}			
		}
		return substrng;
	}
	public static void main(String[] args) {
		ArrayList<String> words1 =new ArrayList<String>();
		ArrayList<String> words2 =new ArrayList<String>();
		ArrayList<String> common_substrings =new ArrayList<String>();
		ArrayList<String> largest =new ArrayList<String>();
		File[] fileList = new File[100];
		Scanner in = new Scanner(System.in);
		String path=args[0];
		int l=0;
		try{
			File Directory = new File(path);	
			File[] files = Directory.listFiles();// Stores all the files in files array
			for (File f : files){
				if (f.getName().endsWith(".txt")){
					fileList[l] = f;	//Stores the files in fileList array
					l++;
				}
			}
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		LCS str=new LCS();
		LCS str2=new LCS();
		LCS str3=new LCS();
		System.out.print("\t\t");
		for (int i=0;i<l;i++) {
			System.out.print(fileList[i].getName() + "\t");
		}
		
		for(int i=0;i<l;i++) {
			System.out.println("\n");
			System.out.print(fileList[i].getName()+"\t");
			
			for (int j = 0; j < l; j++) {
				String s = str.FileReading(fileList[i]);// FileReading method reads file
				String s1 = str2.FileReading(fileList[j]);
				s=str.remove_Delimiters(s);				//Removes Delimiters
				s1=str2.remove_Delimiters(s1);				
				int l1=s.length();
				int l2=s1.length();
		
		String LCS="";

		words1=str.substrings(s);	//subtrings methods are stored in array
		// int length1=words1.size();
		words2=str2.substrings(s1);
		// int length2=words2.size();
		common_substrings =str3.common_substring(words1,words2);// common substring are stored in array
		LCS=str3.Largest_Common_Substring(common_substrings);
		largest.add(LCS);
		int max=LCS.length();
		double numerator=max-1;
		double denominator=(l1+l2)-2;					// calculation of plagiarism
		double result=numerator*200/denominator;
		System.out.print(String.format("%.2f",result)+("\t\t"));
}
}
System.out.println();
for (int i=0;i<largest.size();i++) {
	
	System.out.println(largest.get(i));
}
// int k=0;
// for (int i=0;i<l;i++) {
// 	for(int j=0;j<l;j++){
// 			System.out.println((fileList[i].getName()) + " && " +(fileList[j].getName())+ " is " +(largest.get(k)));
// 		}
// 		k=k+1;
// 	}
}
}