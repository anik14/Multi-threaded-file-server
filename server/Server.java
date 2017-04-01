/*
Mohammad Islam, TJ Cui
CSC 623
HW 2, part 2
10/24/16
Server - client program. Invoking the server and the client
*/

import java.io.*;
import java.net.*;

public class Server
{
   static ServerSocket Servo;
   static int Integ = 6;


   public static void main(String args[])
   {
     
      try
      {
         Servo = new ServerSocket(Integer.parseInt(args[0]), Integ);
      
         while (true)
         {
            Socket Sct = Servo.accept();
     
            new Worker(Sct).start();
         }
      }
      catch(IOException e)
      {System.out.println(e);}
   }
}

class Worker extends Thread
{
   Socket          Sct;
   PrintWriter     prwt;
   BufferedReader  bufr;
  
   Worker(Socket S)
   {Sct=S;}

   public void run()
   {
      try
      {           
         
         bufr = new BufferedReader (new InputStreamReader (Sct.getInputStream()));
         prwt = new PrintWriter (new BufferedWriter (new OutputStreamWriter(Sct.getOutputStream())),true); 
      
        
         String R = bufr.readLine();
         System.out.println("Client says: " + R);
      
        
         System.out.println("Thread: " + getName());
         
      
         String[] strArray={"1.pdf","2.jpg","3.pdf"};
         
         boolean res =true;
                       
         for(int i=0;i<strArray.length&&(res == true);i++)
         {
          
            if(R.equals(strArray[i]))
            {
               res =true;
               break;
            }
            else
            {
               res =false;
            }
            System.out.println("R value:"+R);
            System.out.println("Response: "+ res);
         }
      
         
         prwt.println(res);
         System.out.println("Server says: " + res);
      
      
         File file = new File("1.pdf");
         byte[] bite = new byte[(int) file.length()];
         
         BufferedInputStream ois = new BufferedInputStream(new FileInputStream(file));
         ois.read(bite, 0, bite.length);
         
         OutputStream ouSt = Sct.getOutputStream();
         System.out.println("Sending Files...");
         ouSt.write(bite, 0, bite.length);
         ouSt.flush();
         Sct.close();
         System.out.println("File transfer complete");
      }
      
      catch(IOException e)
      {System.out.println(e);}
   }
}
