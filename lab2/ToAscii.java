import java.io.*;
import java.util.Scanner;

public class ToAscii {
    static final int maxline=100;
    public static void main(String[] args){
        byte[] strline=new byte[maxline];
        int strlen;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Input the file you want to read from ");
        String fromname=scanner.next();  //reads the name of the file to read from
        System.out.println("Input the output file you want to save in");
        String toname=scanner.next();
        InputStream from=null;
        OutputStream to=null;

        byte[] buffer=new byte[4096];
        try{
                from=new BufferedInputStream(new FileInputStream(fromname));
                to=new BufferedOutputStream(new FileOutputStream(toname));
                for(int length=0; (length=from.read(buffer)) !=-1;){

                    //System.out.write(buffer,0,length);
                    //to.write(buffer,0,length);
                    //to.write()
                    for (int i=0;i<length;i++){
                        to.write(buffer[i]);
                        to.write(' ');
                        to.write(String.valueOf(buffer[i]).getBytes());
                        to.write('\n');
                    }
                        
                }
            }
        catch(Exception e){
            System.out.println("Error while reading the file. Make sure the file exists!");
        }
        finally{

            if (from != null) try { from.close(); } catch (IOException e) { ; }
            if (to !=null) try {to.close();} catch (IOException e) {; }
        }
        
    }
}