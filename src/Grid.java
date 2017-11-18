import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Grid extends Frame implements ActionListener
{
	static Grid frm = new Grid();
	
	final static int map_len = 20;
	final static int map_width = 30;
	final static int border_len = 20;
	final static int x_d = 20;
	final static int y_d = 40;
	
	final static int frame_len = map_len * border_len + y_d;
	final static int frame_width = map_width * border_len + x_d * 3;

	
	int map[][]= new int[map_len][map_width];
	
	public static void main (String args[])
	{
		BorderLayout br=new BorderLayout();
		frm.setTitle("Map");
		frm.setLayout(br);
		frm.setSize(frame_len,frame_width);
		frm.setVisible(true);
	}
	
	public void initMap(Graphics g, int len, int width){		
		for(int i = 0; i < len; i++){
			for(int j = 0; j < width; j++){
				System.out.println(i + " " + j);
				g.drawRect(x_d + i * border_len, y_d + j * border_len, border_len, border_len);
				if(i == j)
					map[i][j] = 1;	
				if(map[i][j] == 1){
					g.fillRect(x_d + i * border_len, y_d + j * border_len, border_len, border_len);
				}
			}			
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Graphics g=getGraphics();
		paint(g);
		

	}
	
	
	public void paint(Graphics g)
	{
		initMap(g, map_len, map_width);
	}
	

}

