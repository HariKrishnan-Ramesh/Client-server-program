import java.net.*;
import java.util.Scanner;
import java.io.*;
import java.io.File;
import java.io.FileWriter;

 class Server{

        public static void main(String []args){
        Socket s;
        String filename = "";
        String str2="";
        String out="";

        try{
            DataOutputStream dout;
            DataInputStream din;
        Scanner sc;
        ServerSocket  ss = new ServerSocket(4999);
        System.out.println("Server is listening\n Waiting for client........");
        s = ss.accept();
        System.out.println("Client is connected...." + s);
        din = new DataInputStream(s.getInputStream());
        dout = new DataOutputStream(s.getOutputStream());

        String msg=din.readUTF();
        do{
        if(msg.startsWith("exit")){
            System.exit(0);
        }
        else if(msg.startsWith("create")){
            filename = msg.trim();
            filename = filename.substring(7);
            out = filename;
            File fin = new File(filename);
            if (fin.createNewFile()) {
              out = "FILE " + fin.getName() + " CREATED SUCCESSFULLY";
            }
            else {
              out = "FILE ALREADY EXISTS";
            }
            str2 = "hello world";
            FileWriter f = new FileWriter(filename);
            f.write(str2);
            f.close();
        }
        else if(msg.startsWith("delete")){
            try {
                filename = msg.trim();
                filename = filename.substring(7);
                File f = new File(filename);
                if (f.delete()){
                  out = "FILE " + f.getName() + " DELETED SUCCESSFULLY!";
                }else{
                  out = "FAILED TO DELETE THE FILE!";
                }
              }catch (Exception e) {
                out = "FILE DOESN'T EXISTS!";
              }
            }
        else if(msg.startsWith("edit")){
            filename = msg.trim();
            File f1 = new File(filename);
            if(f1.exists()){
              FileWriter f = new FileWriter(filename,true);
              str2 = din.readUTF();
              f.write(str2);
              f.close(); 
              out = "File Successfully edited";
            }
            else{
              out = "File not found";
            }
            out = out.trim();
        }
        else if(msg.startsWith("cat")){
          filename = msg.substring(4);
          File f = new File(filename);
          if(f.exists()){
            sc = new Scanner(f);
            out = "";
            while (sc.hasNextLine()) {
              String data = sc.nextLine();
              out = out + data;
              out = out + "\n";
            }
            sc.close();
          }else{
            out = "File not found";
          }
          out = out.trim();
        }
        else{
            out = "INVALID COMMAND RECEIVED!";
        }
        dout.writeUTF(out);
        }while(!msg.equals("exit"));
        s.close();
        ss.close();
        }catch(IOException e)
        {
            e.printStackTrace();
            
        }

        

    }
}