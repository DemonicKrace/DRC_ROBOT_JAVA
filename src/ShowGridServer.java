import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;


public class ShowGridServer extends Frame implements ActionListener{
	final static int port = 5555;

//	static ShowGridServer frm = new ShowGridServer();
	
	final static int map_height = 30;
	final static int map_width = 20;
	final static int border_len = 20;
	final static int width_d = 100;
	final static int height_d = 50;
	
	
	final static int frame_height = map_height * border_len + height_d * 3;
	final static int frame_width = map_width * border_len + width_d;

	final static String input_format = ".RDCVBL";
	
	int map[][]= new int[map_height][map_width];
	
    public static void main(String args[]) throws Exception {
	    
    	
    	ShowGridServer server = new ShowGridServer(); // 建立 UdpServer 伺服器物件。
    	
        server.setTitle("Map");
        server.setLayout(new BorderLayout());
        server.setSize(frame_width, frame_height);
        server.setVisible(true);
    	
        server.run();                           // 執行該伺服器。
        
    }
 
    public ShowGridServer() {

    }
 
    public void run() throws Exception {
        final int SIZE = 8192;                    // 設定最大的訊息大小為 8192.
        byte buffer[] = new byte[SIZE];            // 設定訊息暫存區
        for (int count = 1; ; count++) {
        	System.out.println(count + ". waiting data...");  
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            DatagramSocket socket = new DatagramSocket(port);         // 設定接收的 UDP Socket.
            socket.receive(packet);                                    // 接收封包。
            String msg = new String(buffer, 0, packet.getLength());    // 將接收訊息轉換為字串。
            System.out.println(count + ". receive data length = " + msg.length() + ", data = " + msg + "\n\n");  // 印出接收到的訊息。
            updateMap(msg);
            socket.close();                                            // 關閉 UDP Socket.
        }
    }

	public void updateMap(String msg){
		//check msg format is correct
		if(msg.length() == map_height * map_width) {
			for (int i = 0, n = msg.length(); i < n; i++) {
				map[i / map_width][i % map_width] = msg.charAt(i);
			}
			Graphics g = getGraphics();
			paint(g);
		}
		
	}
    
	public void drawMap(Graphics g, int height, int width){		
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				
				//white = empty '.'
				//black = robot 'R'
				//green = door 'D'
				//red = ladder 'C'
				//orange = box 'V'
				//blue = barrier 'B'
				//yellow = stepped 'L'
				switch(map[i][j]){
				case '.':
					drawOneGrid(g, Color.WHITE, i, j);
					break;
				case 'R':case 'r':						
					drawOneGrid(g, Color.BLACK, i, j);					
					break;
				case 'D':case 'd':
					drawOneGrid(g, Color.GREEN, i, j);
					break;
				case 'C':case 'c':
					drawOneGrid(g, Color.RED, i, j);					
					break;
				case 'V':case 'v':
					drawOneGrid(g, Color.orange, i, j);
					break;
				case 'B':case 'b':
					drawOneGrid(g, Color.BLUE, i, j);
					break;
				case 'L':case 'l':
					drawOneGrid(g, Color.YELLOW, i, j);
					break;
				default:
					drawOneGrid(g, Color.WHITE, i, j);
					break;
				}
				
			}			
		}
	}
	
	public void drawOneGrid(Graphics g, Color color, int i, int j)
	{
		g.setColor(color);
		g.fillRect(height_d + j * border_len, width_d + i * border_len, border_len, border_len);
		g.setColor(Color.BLACK);					
		g.drawRect(height_d + j * border_len, width_d + i * border_len, border_len, border_len);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Graphics g = getGraphics();
		paint(g);
	}
	
	
	public void paint(Graphics g)
	{
		drawMap(g, map_height, map_width);
	}
}
