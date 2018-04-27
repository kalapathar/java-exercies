
import java.util.*;

public class Timesprint{
	static final int maxline=100;
	public static void main(String[] args){

		byte[] strline=new byte[maxline];
		int n_times=Integer.parseInt(args[0]);
		int strlen;
		System.out.println("Enter the string that you want to print multiple times");
		
		String str1="sample";
		String str2;
		try{
			strlen=System.in.read(strline,0,maxline);
			str1=new String(strline);
			System.out.println("Re-enter the same string again");
			Scanner sc=new Scanner(System.in);
			str2=sc.nextLine();
			
		}
		catch(Exception e){
			System.out.println("Error reading from standard input");
		}
		str1="peacock";
		int yetitimes=printTimes(str1,n_times);
		System.out.println(yetitimes);

	}
		public static int printTimes(String s, int n){
			for (int i=0;i<n;i++){
				System.out.println(s);
			}
			int length=s.length()*n;
			System.out.println("s.length"+s.length());
			System.out.println("n=="+n);
			System.out.println("lenth=="+length);
			return length;
		}
}