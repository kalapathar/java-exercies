public class CommandLineArgs{
	public static void main (String[] args){
		for (int i=0;i<args.length;i++){
			int factorial=1;
			for (int j=Integer.parseInt(args[i]);j>0;j--)
				factorial=factorial*j;
			System.out.println("Factorial of "+args[i]+" without using Factorial Class is "+factorial); //without using the Factorial Class
			long factorial_method2=Factorial.iterative(Integer.parseInt(args[i]));  //using the Factorial Class
			System.out.println("Factorial of "+args[i]+" using Factorial Class is "+factorial_method2);
		}
		
	}
}