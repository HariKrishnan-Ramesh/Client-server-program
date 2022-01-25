import java.io.*;
import java.net.Socket;
import java.util.Scanner;

 class Client {

    public static void main(String[] args) {

        Socket s;
        // InputStreamReader in;
        // OutputStreamWriter out;
        // BufferedReader br;
        // BufferedWriter bw ;
        Scanner sc;
        DataInputStream din;
        DataOutputStream dout;
        String str1;
        String str2="";
        String str3="";
        try {

            s = new Socket("localhost", 4999);
            System.out.println("SUCSESSFULLY CONNECTED TO SERVER");
            sc = new Scanner(System.in);
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            do {
                System.out.println("\n\tENTER ANY COMMAND\t\n");
                System.out.println("create <file_name.txt> : CREATE A FILE");
                System.out.println("  edit <file_name.txt> : EDIT THE FILE");
                System.out.println("  cat  <file_name.txt> : READ THE FILE");
                System.out.println(" delete <file_name.txt> : DELETE THE FILE");
                System.out.println("  exit : EXIT FROM THE PROGRAM");
                str1 = sc.nextLine().trim();
                dout.writeUTF(str1);
                str2=din.readUTF();
                if(str2.startsWith("edit")){
                    System.out.println("Enter the string to be added: ");
                    str2 =sc.nextLine().trim();
                    dout.writeUTF(str2);
                    str3 = din.readUTF();
                    System.out.println(sc.nextLine());
                }
                else{
                    System.out.println(sc.nextLine());
                }
                System.out.println("SERVER :"+str2);

            } while (!str1.equals("exit"));
            s.close();
            sc.close();

        } catch (IOException e) {
            System.out.println("CONNECTION NOT ESTABLISHED");

        }

    }

}