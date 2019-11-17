package project9;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import project8.project8.A;
import project8.project8.B;

public class Cilenta {

	private static Lock lock=new ReentrantLock();   //锁
	private static boolean Aword=false,Bword=false,finish=false;  //A B是否生成字符
	private static int as,bs;   //A B生成的字符值
	private static double asleep,bsleep;     //A B休眠时间
	private static Condition aCondition=lock.newCondition(); 
	private static Condition bCondition=lock.newCondition();
	private static Condition cCondition=lock.newCondition();
	private static byte[] buf = new byte[1024];  //输出数组
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Socket socket=null;
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new A());
		executor.execute(new B());
		try {
			socket=new Socket("localhost",7001);
			DataInputStream cilentInputStream=new DataInputStream(socket.getInputStream());   //TCP接受数据
			DataOutputStream cilentOutputStream=new DataOutputStream(socket.getOutputStream()); //TCP发送数据
			DatagramSocket socket2=new DatagramSocket();
			DatagramPacket sendPacket=new DatagramPacket(buf, buf.length,InetAddress.getLocalHost(),9001);  //发送packet
			int i=0;
			while(i<4) {	
				lock.lock();
				while(!Aword||!Bword) {     //A B有人未出拳
					cCondition.await(); 
				}
				cilentOutputStream.writeDouble(asleep); //TCP发送
				cilentOutputStream.writeInt(as); //TCP发送
				sendPacket.setData(new Double(bsleep).toString().getBytes());  
				socket2.send(sendPacket);  //UDP发送
				Arrays.fill(buf, (byte) 0);
				sendPacket.setData(new Integer(bs).toString().getBytes());  
				socket2.send(sendPacket);  //UDP发送
				while(!finish) {
					finish=cilentInputStream.readBoolean();  //TCP接收
				}
				Aword=false;
				Bword=false;
				aCondition.signal();   //通知A比较完成
				bCondition.signal();   //通知B比较完成
				finish=false;
				i++;
				lock.unlock();
			}
			socket.close();
			executor.shutdown();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public static class A implements Runnable{
		@Override
		public void run() {
			// TODO 自动生成的方法存根
			int i=0;
			while(i<4) {
			lock.lock();  //拿锁，防止调用过程中Aword值被修改
			try {
				while(Aword) {          //已经生成了字符，等待
					aCondition.await();
				}
				asleep=Math.random();     //休眠随机时间
				Thread.sleep((long) (asleep*1000));
				Random random=new Random();
				as=random.nextInt(3);      //生成随机字符
				Aword=true;	      //修改Aword状态，已生成完毕
				cCondition.signal();   //通知c
				i++;
			}catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			finally {
				lock.unlock();   //释放锁
			}
		}
		}
	}
	public static class B implements Runnable{
		@Override
		public void run() {
			// TODO 自动生成的方法存根
			int i=0;  
			while(i<4) {
			lock.lock();        //拿锁，防止调用过程中Bword值被修改
 			try {
				while(Bword) {     //已经生成了字符，等待
					bCondition.await();
				}
				bsleep=Math.random();
				Thread.sleep((long) (bsleep*1000)); //休眠随机时间
				Random random=new Random();
				bs=random.nextInt(3);
				Bword=true;	     //修改Aword状态，已生成完毕
				cCondition.signal();   //通知c
				i++;
		}catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			lock.unlock();//通知c
		}
			}
		
	}
	}

}
