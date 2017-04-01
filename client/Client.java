/*
Mohammad Islam, TJ Cui
CSC 623
HW 2, part 2
10/24/16
Server - client program. Invoking the server and the client
*/

import java.io.*;
import java.net.*;

public class Client
{
   static Socket          sct;
   static PrintWriter     prwt;
   static BufferedReader  bufr;
   static String          response;;

  
   public static void main(String args[]) throws IOException
   {
       
      int filen = 88888;
      int zey;
      int ovp=0;
     
      Socket clientSocket= new Socket("127.0.0.1", 35000);
      byte[] array = new byte[filen];
      InputStream in = clientSocket.getInputStream();
      FileOutputStream abc= new FileOutputStream("Output.pdf");
      BufferedOutputStream def= new BufferedOutputStream(abc);
    
      zey =in.read(array,0,array.length);
      ovp =zey;
      do{
         zey= in.read(array,ovp,(array.length-ovp));
         if(zey>=0) ovp +=zey;
            
      } while (zey> -1);
      def.write(array,0,ovp);
      def.flush();
      def.close();
      clientSocket.close();
                
   }
}
