import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.lang.*;

public class Ex1 extends JFrame implements ActionListener{
   private JButton[] btn=new JButton[101];

   boolean open = false;
   public Ex1() {

      Container c = getContentPane();
      c.setLayout(new GridLayout(20,50));
      for(int i=1;i<=100;i++)
      	{
         btn[i]=new JButton();
         c.add(btn[i]);
         btn[i].addActionListener(this);
        }

		btn[5].setLabel("開啟格子地圖");
      
		btn[11].setLabel("X=0,Y=0,A=0");
		btn[12].setLabel("走");
		btn[13].setLabel("停");

		btn[16].setLabel("X = 1");
		btn[17].setLabel("X = -3");
		btn[18].setLabel("X + 1");
		btn[19].setLabel("X - 1");

		btn[21].setLabel("Y = 14");
		btn[22].setLabel("Y = -14");
		btn[23].setLabel("Y + 1");
		btn[24].setLabel("Y - 1");

		btn[26].setLabel("A = 10");
		btn[27].setLabel("A = -10");
		btn[28].setLabel("A = 15");
		btn[29].setLabel("A = -15");

		btn[31].setLabel("重心 + 0.5");
		btn[32].setLabel("重心 - 0.5");

		btn[33].setLabel("腳高 = 40");
		btn[34].setLabel("腳高 + 5");
		btn[35].setLabel("腳高 - 5");

		btn[36].setLabel("速度 = 700");
		btn[37].setLabel("速度 + 10");
		btn[38].setLabel("速度 - 10");

		btn[41].setLabel("頭歸正");
		btn[42].setLabel("tilt+5");
		btn[43].setLabel("tilt-5");
		btn[44].setLabel("pan+5");
		btn[45].setLabel("pan-5");

		btn[46].setLabel("視覺避障");
		btn[47].setLabel("視覺follower");

		btn[51].setLabel("避障");
		btn[52].setLabel("follower");

		btn[61].setLabel("避障");
		btn[62].setLabel("action登階(上)");
		btn[63].setLabel("action登階(下)");
		btn[64].setLabel("action站立");

		btn[65].setLabel("顯示地圖"); //replace with 106
		btn[66].setLabel("閥門"); //replace with 99
		btn[67].setLabel("爬梯"); // replace with 102
		
		btn[68].setLabel("原地轉(左)"); 
		btn[69].setLabel("原地轉(右)");

		btn[70].setLabel("視覺建置"); // replace with 105
		
		btn[84].setLabel("重心 = 16");
		btn[85].setLabel("重心 = 13.4");
		btn[87].setLabel("重心 + 0.1");
		btn[88].setLabel("重心 - 0.1");

		btn[98].setLabel("重設GYRO");
   }

 public void actionPerformed(ActionEvent evt)
 {
    DatagramPacket dp;
   	String msg;

      for(int i=1;i<=100;i++)
      {
         if(evt.getSource() == btn[i])
         {
         	try
         	{
           		DatagramSocket ds=new DatagramSocket();
           		String strInfo = Integer.toString(i);
           		
           		if(i == 5 && !open){ //open grid map
           			open = true;
           			Thread thread_grid = new Thread(){
           				public void run(){
           					try {
           						ShowGridServer server = new ShowGridServer();
           						server.main(null);
           					} catch (Exception e) {
           						// TODO Auto-generated catch block
           						e.printStackTrace();
           					}
           				}
           			};
           			thread_grid.start();
           			break;
           		}
           		switch(i){
           		case 65:
           			strInfo = "106";
           			break;
           		case 66:
           			strInfo = "99";
           			break;
           		case 67:
           			strInfo = "102";           			
           			break;
           		case 70:
           			strInfo = "105";           			
           			break;
           		}
           		
           		for(int j = 0; j < 5 ; j++){
           			ds.send(new DatagramPacket(strInfo.getBytes(),strInfo.length(),
                                    InetAddress.getByName("192.168.123.1"),8080));               //192.168.0.109
           		}
           		System.out.println("send status " + strInfo);
           		
           		ds.close();
       			break;
         	}
         	catch (Exception ex)
         	{
               ex.printStackTrace();
        	}
         }
      }
}

 	public static void main(String[] args)throws Exception {
		
		Ex1 app = new Ex1();
		app.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		app.setSize(600,600);
		app.setVisible(true);
		        

		Thread thread_web = new Thread(){
			public void run(){
				try {
					Web web = new Web();
					web.main(null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		thread_web.start();
		
		/*
		Thread thread_grid = new Thread(){
			public void run(){
				try {
					ShowGridServer server = new ShowGridServer();
					server.main(args);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		thread_grid.start();
		*/
 	}
}