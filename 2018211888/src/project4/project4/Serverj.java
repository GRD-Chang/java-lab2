package project4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.util.Arrays;

public class Serverj {

	private static int ascore=0,bscore=0;    //A B�÷�
	private static int as,bs;   //A B���ɵ��ַ�ֵ
	private static double asleep,bsleep;     //A B����ʱ��
	private static byte[] inbuf = new byte[1024];
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		String[] selection= {"rock","scissors","paper"};
		System.out.printf("%-18s%-33s%-18s\n","Round","Thread A","Thread B");
		System.out.printf("%-8s%-10s%-13s%-10s%-10s%-13s%-10s\n"," ","Sleep","Random","Points","Sleep","Random","Points");
		System.out.printf("%-8s%-10s%-13s%-10s%-10s%-13s%-10s\n"," ","time","selection","obtain","time","selection","obtain");
		try {
			ServerSocket serverSocket=new ServerSocket(7001);
     		Socket socket=serverSocket.accept();
     		DatagramSocket socket2=new DatagramSocket(9001);  //UDP��ʽ��������
     		DatagramPacket recievePacket=new DatagramPacket(inbuf, inbuf.length);  //����packet
			DataInputStream serverInputStream=new DataInputStream(socket.getInputStream());
			DataOutputStream serverDataOutputStream=new DataOutputStream(socket.getOutputStream());
			int i=0;
			while (i<4) {
			Arrays.fill(inbuf, (byte) 0);
			asleep=serverInputStream.readDouble();
			as=serverInputStream.readInt();
			socket2.receive(recievePacket);
			bsleep = Double.parseDouble(new String(inbuf).trim());
			Arrays.fill(inbuf, (byte) 0);
			socket2.receive(recievePacket);
			bs=Integer.parseInt(new String(inbuf).trim());
			int aincrease=0,bincrease=0;
			if(as==bs) {
				ascore++;
				bscore++;
				aincrease=1;
				bincrease=1;
			}
			else if (bs-as==1||as-bs==2) {  //aӮb
				ascore+=2;
				aincrease=2;
			}
			else {           //B��A��
				bscore+=2;
				bincrease=2;
			}
			System.out.printf("%-8d%-10f%-14s%-9d%-10f%-14s%-10d\n",i+1,asleep,selection[as],aincrease,bsleep,selection[bs],bincrease);
			i++;
			serverDataOutputStream.writeBoolean(true);
			}
			System.out.println("A's score:"+ascore+"\tB's score:"+bscore);
			if(ascore>bscore) {
				System.out.println("The winner is:A");
			}
			else if (bscore>ascore) {
				System.out.println("The winner is:B");
			}
			else {
				System.out.println("The winner is:None");
			}
			socket.close();
			socket2.close();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		

	}

}
