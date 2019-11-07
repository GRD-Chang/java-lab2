package project3;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class project3 {
	private static Lock lock=new ReentrantLock();   //��
	private static boolean Aword=false,Bword=false;  //A B�Ƿ������ַ�
	private static char ach,bch;   //A B���ɵ��ַ�ֵ
	private static int ascore=0,bscore=0;    //A B�÷�
	private static double asleep,bsleep;     //A B����ʱ��
	private static Condition aCondition=lock.newCondition(); 
	private static Condition bCondition=lock.newCondition();
	private static Condition cCondition=lock.newCondition();
	public static void main(String[] args) {
		System.out.printf("%-20s%-30s%-18s\n","Round","Thread A","Thread B");
		System.out.printf("%-8s%-10s%-15s%-10s%-10s%-10s%-10s\n"," ","Sleep","Random","Points","Sleep","Random","Points");
		System.out.printf("%-8s%-10s%-15s%-10s%-10s%-10s%-10s\n"," ","time","character","obtain","time","character","obtain");
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.execute(new A());
		executor.execute(new B());
		executor.execute(new C());
		executor.shutdown();
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
				int ch=random.nextInt(26);      //��������ַ�
				ch+=(int)'a';
				ach=(char) ch;
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
				int ch=random.nextInt(26);
				ch+=(int)'a';
				bch=(char) ch;     //��������ַ�
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
	public static class C extends Thread{
		@Override
		public void run() {
			int i=0;
			while(i<4) {
			lock.lock();  //��������ֹ���ù�����Aword,Bwordֵ���޸�
			try {
			while(!Aword||!Bword) {  //���A,B��δ����ַ�����
				cCondition.await();  //�ȴ��ַ�����
				} 
			int aincrease=0,bincrease=0;
			if(ach>bch) {    //A��B��
				ascore+=2;
				aincrease=2;
			}
			else if(bch>ach) {    //B��A��
				bscore+=2;
				bincrease=2;
			}
			else {       //һ����
				ascore++;
				bscore++;
				aincrease=1;
				bincrease=1;
			}
			System.out.printf("%-8d%-13f%-14s%-10d%-13f%-10s%-10d\n",i+1,asleep,ach,aincrease,bsleep,bch,bincrease);
			Aword=false;
			Bword=false;
			aCondition.signal();   //֪ͨA�Ƚ����
			bCondition.signal();   //֪ͨB�Ƚ����
			i++;
			}catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
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
			
}
}
}
