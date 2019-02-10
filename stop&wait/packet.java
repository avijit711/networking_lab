//import java.util.Scanner;
import java.io.Serializable;

public class packet implements Serializable{
    public int seq;
    public String data;
    public int ack;
    public int tailer;


}
