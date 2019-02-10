import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.lang.Math;

public class client {
    public static void main(String args[]){
        try {
            packet p1 = new packet();



            Scanner s = new Scanner(System.in);

            System.out.println("Please Enter the data that you want to send to the server: ");

            String data = s.nextLine();

            int loopCount = 0;
            if ((data.length()) % 2 == 0){
                loopCount = data.length() / 2;
            } else {
                loopCount = data.length() / 2 + 1;
            }



            String[] sData = splitString(data);  //Splited data


            int i = 0;


            Socket sck = new Socket("localhost", 1234);

            OutputStream os = sck.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeInt(loopCount);

            p1.seq = i;
            p1.data = sData[i];
            p1.ack = 0;
            p1.tailer = 1;

            ObjectOutputStream oos = new ObjectOutputStream(sck.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(sck.getInputStream());

            while (i <= sData.length){
                p1.seq = i;
                p1.data = sData[i];
                p1.ack = 1;
                p1.tailer = 1;

                System.out.println("Sending packet-"+(i+1)+" : "+p1.data);

                oos.writeObject(p1);

                p1 = (packet) ois.readObject();

                if(p1.ack == 1){

                    System.out.println("[+] Ack received!");

                    i++;
                } else {
                    System.out.println("[-] Ack received!");
                }




            }
            oos.close();
            ois.close();

//            System.out.println(splited[data.length() / 2]);







            s.close();

        } catch (Exception e){

        }


    }

    public static String[] splitString(String data){
        String[] splited = new String[((int) Math.ceil(data.length() / 2)) + 1];

        int j = 0;

        for(int i = 0; i < ((data.length() / 2)); i++){

            splited[i] = data.substring(j, j+2);

//            System.out.println(splited[i]);
            j = j + 2;
        }

        if(data.length() % 2 != 0 ){
            splited[(int) Math.ceil(data.length() / 2)] = data.substring((data.length()) - 1);
        }

        return splited;
    }
}
