import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Login {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    private String password;

    public Login(Socket socket, String username, String password){
        try{
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
            this.password = password;
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);

        }
    }

    public static void main(String[] args) {


        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print(" Enter user name => ");
            String userName = scanner.nextLine();

            System.out.print(" Enter password => ");
            String password = scanner.nextLine();

            if ("shaked".equals(userName) && "123".equals(password)) {
                System.out.println(" User successfully logged-in.. ");
                Socket socket = new Socket("localhost",1234);
                Login login = new Login(socket,userName,password);
            } else {
                System.out.println(" In valid userName of password ");
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        try{
            if(bufferedReader != null){
                bufferedReader.close();
            }
            if (bufferedWriter != null){
                bufferedWriter.close();
            }
            if (socket != null){
                socket.close();
            }
        } catch (IOException e){

            e.printStackTrace();


        }
    }
}
