package project4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import project3.project3.A;
import project3.project3.B;

public class Cilenta {

	private static Lock lock=new ReentrantLock();   //��
	private static boolean Aword=false,Bword=false,finish=false;  //A B�Ƿ������ַ�
	private static int as,bs;   //A B���ɵ��ַ�ֵ
	private static double asleep,bsleep;     //A B����ʱ��
	private static Condition aCondition=lock.newCondition(); 
	private static Condition bCondition=lock.newCondition();
	private static Condition cCondition=lock.newCondition();
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Socket socket=null;
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new A());
		executor.execute(new B());
		try {
			socket=new Socket("localhost",7001);
			DataInputStream cilentInputStream=new DataInputStream(socket.getInputStream());
			DataOutputStream cilentOutputStream=new DataOutputStream(socket.getOutputStream());
			int i=0;
			while(i<4) {	
				lock.lock();
				while(!Aword||!Bword) {     //A B����δ��ȭ
					cCondition.await(); 
				}
				cilentOutputStream.writeDouble(asleep);
				cilentOutputStream.writeInt(as);
				cilentOutputStream.writeDouble(bsleep);
				cilentOutputStream.writeInt(bs);
				while(!finish) {
					finish=cilentInputStream.readBoolean();
				}
				Aword=false;
				Bword=false;
				aCondition.signal();   //֪ͨA�Ƚ����
				bCondition.signal();   //֪ͨB�Ƚ����
				finish=false;
				i++;
				lock.unlock();
			}
			executor.shutdown();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	public static class A implements Runnable{
		@Override
		public void run() {
			// TODO �Զ����ɵķ������
			int i=0;
			while(i<4) {
			lock.lock();  //��������ֹ���ù�����Awordֵ���޸�
			try {
				while(Aword) {          //�Ѿ��������ַ����ȴ�
					aCondition.await();
				}
				asleep=Math.random();     //�������ʱ��
				Thread.sleep((long) (asleep*1000));
				Random random=new Random();
				as=random.nextInt(3);      //��������ַ�
				Aword=true;	      //�޸�Aword״̬�����������
				cCondition.signal();   //֪ͨc
				i++;
			}catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			finally {
				lock.unlock();   //�ͷ���
			}
		}
		}
	}
	public static class B implements Runnable{
		@Override
		public void run() {
			// TODO �Զ����ɵķ������
			int i=0;  
			while(i<4) {
			lock.lock();        //��������ֹ���ù�����Bwordֵ���޸�
 			try {
				while(Bword) {     //�Ѿ��������ַ����ȴ�
					bCondition.await();
				}
				bsleep=Math.random();
				Thread.sleep((long) (bsleep*1000)); //�������ʱ��
				Random random=new Random();
				bs=random.nextInt(3);
				Bword=true;	     //�޸�Aword״̬�����������
				cCondition.signal();   //֪ͨc
				i++;
		}catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally {
			lock.unlock();//֪ͨc
		}
			}
		
	}
	}

}
