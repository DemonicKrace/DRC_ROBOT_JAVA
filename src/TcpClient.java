import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient
{
    public static void main( String[] args ) throws IOException
    {
        String host = "127.0.0.1";
        int port = 5555;
        Socket socket = null;
        Scanner consoleInput = new Scanner( System.in );
//        System.out.println("請輸入Server端位址");
//        host = consoleInput.nextLine();
        try
        {
            socket = new Socket( host, port );
            DataInputStream input = null;
            DataOutputStream output = null;
            
            try
            {
                input = new DataInputStream( socket.getInputStream() );
                output = new DataOutputStream( socket.getOutputStream() );
                
                while ( true )
                {
                	output.writeUTF("12334567890");
                    System.out.println( input.readUTF() );
                    break;
                }
            }
            catch ( IOException e )
            {
            }
            finally 
            {
                if ( input != null )
                    input.close();
                if ( output != null )
                    output.close();
            }
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        finally
        {
            if ( socket != null )
                socket.close();
            if ( consoleInput != null )
                consoleInput.close();
        }
    }
}