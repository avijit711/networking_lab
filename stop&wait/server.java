import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class server {
    public static void main(String args[]) throws IOException {
        try {
            packet p1 = new packet();
            ServerSocket sers = new ServerSocket(1234);
            Socket s = sers.accept();
            Random rand = new Random();

            InputStream is = s.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            int loopCount = dis.readInt();

            int i = 0;

//            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
//            p1 = (packet) ois.readObject();
//            System.out.println(p1.data);



            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

            String fullData = "";

            while (i < loopCount){


                p1 = (packet) ois.readObject();




                int random_number = rand.nextInt(2);
//
                p1.ack = random_number;

//

                oos.writeObject(p1);
//



                if (p1.ack == 1){
                    System.out.println("[+]Received: "+p1.data);
                    fullData = fullData+p1.data;
                    i++;
                } else {
                    System.out.println("[-]Not received");
                }



            }

            System.out.println(fullData);

            sers.close();
            ois.close();
            oos.close();
            s.close();



        } catch (Exception e){

        }
    }
}
