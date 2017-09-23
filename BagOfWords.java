import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
interface BOW
{
	public String FileReading(File file);
	public String remove_Delimiters(String s1);
	//public HashMap<String,Integer> freq_Count(String words[]);
	//public ArrayList<String> common_substring(ArrayList<String> words1,ArrayList<String> words2);
	public int dot_Product(HashMap<String,Integer> hash,HashMap<String,Integer> hash2);
	public double euclidean_Norm(HashMap<String,Integer> hash);
	public String[] spliting(String s);

}
public class BagOfWords implements BOW{
	HashMap<String,Integer> hash=new HashMap<String,Integer>();
	public String FileReading(File file)
	{
		/* Input : File is taken as input 
			Functionality : File contents are stored in string
			Output : String is returned as output
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
		/*	Input : String s1 is given as input
			Functionality : It removes all the delimiters
			Output : Returns String
		*/
				s1=s1.toLowerCase();
				s1= s1.replaceAll("[^\\w\\s]","");
				return s1;
	}
	public HashMap<String,Integer> freq_Count(String words[])
	{
		/* Input : It takes Words HashMap as input
			Functionality : It counts frequencies for strings
			Output : It returns hashmap
		*/
			int len1=words.length;
			for (int i=0;i<len1;i++){
				if(!hash.containsKey(words[i])){
					hash.put(words[i],1);
				}
				else
				{
					hash.put(words[i],hash.get(words[i])+1);
				}
			}	
		return hash;
	}
	public int dot_Product(HashMap<String,Integer> hash,HashMap<String,Integer> hash2)
	{
		/* Input : It takes two hashmaps as inputs
			Funtionality : It calculates the dotproduct from both keys
			Output : It returns dotproduct value
		*/
				int num=0;
				for(String k:hash.keySet()){
					for (String m:hash2.keySet()) {
				
						if((k).equals(m)==true){
					
							int p=(Integer)hash.get(k);
							int q=(Integer)hash2.get(m);
							num=num+(p*q);

						}
					}
				}
		return num;
	}
	public double euclidean_Norm(HashMap<String,Integer> hash)
	{
		/*	Input :  It takes hashmap as input
			Functionality : It calculates the euclidean norm for 
			Output : It returns the euclidean norm value
		*/
			double den1=0.0;
			for(String k:hash.keySet()){
				den1=den1+Math.pow(hash.get(k),2);
			}
			den1=Math.sqrt(den1);
	//System.out.println(den1);
		return den1;
	}
	public String[] spliting(String s)
	{
		/*Input :It Takes string as an input
			Functionality : It splits the strings cotents in the file by space
			Output : It returns string
		*/
			String a[]=new String[30];
			a=s.split(" ");
			return a;
	}
	public static void main(String[] args) {
		File[] fileList = new File[100];
		Scanner in = new Scanner(System.in);
		//System.out.println("Enter the path:");
		// String path = in.next();
		String path=args[0];
		int l=0;
		try{
			File Directory = new File(path);
			File[] files = Directory.listFiles();
			for (File f : files){
				if (f.getName().endsWith(".txt")){	//It reads the files in an array
					fileList[l] = f;
					l++;
				}
			}
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		HashMap<String,Integer> hash=new HashMap<String,Integer>();
		HashMap<String,Integer> hash2=new HashMap<String,Integer>();
		String s1;
		String s2;
		BagOfWords f1=new BagOfWords();
		BagOfWords f2=new BagOfWords();
		System.out.print("\t\t");
		for (int i=0;i<l;i++) {
			System.out.print(fileList[i].getName() + "\t");
		}
		
		for(int i=0;i<l;i++) {
			System.out.println("\n");				//It is used to print the matrix in row format
			System.out.print(fileList[i].getName()+"\t");
			
			for (int j = 0; j < l; j++) {
				s1 = f1.FileReading(fileList[i]);
				s2 = f2.FileReading(fileList[j]);
				s1=f1.remove_Delimiters(s1);
				s2=f2.remove_Delimiters(s2);				
				String words[]=f1.spliting(s1);
				String words2[]=f2.spliting(s2);
				hash=f1.freq_Count(words);
				hash2=f2.freq_Count(words2);
				int num=f2.dot_Product(hash,hash2);
				double den1=f1.euclidean_Norm(hash);
				double den2=f2.euclidean_Norm(hash2);
				double denominator=den1*den2;
				double result=(num/denominator)*100.0;
				System.out.print(String.format("%.2f",result)+("\t\t"));
				hash.clear();
				hash2.clear();
			}
		}		
	}
}