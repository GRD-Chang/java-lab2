package project3;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class project3 {
	private static Lock lock=new ReentrantLock();   //锁
	private static boolean Aword=false,Bword=false;  //A B是否生成字符
	private static char ach,bch;   //A B生成的字符值
	private static int ascore=0,bscore=0;    //A B得分
	private static double asleep,bsleep;     //A B休眠时间
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
				int ch=random.nextInt(26);      //生成随机字符
				ch+=(int)'a';
				ach=(char) ch;
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
				int ch=random.nextInt(26);
				ch+=(int)'a';
				bch=(char) ch;     //生成随机字符
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
	public static class C extends Thread{
		@Override
		public void run() {
			int i=0;
			while(i<4) {
			lock.lock();  //拿锁，防止调用过程中Aword,Bword值被修改
			try {
			while(!Aword||!Bword) {  //如果A,B还未完成字符生成
				cCondition.await();  //等待字符生成
				} 
			int aincrease=0,bincrease=0;
			if(ach>bch) {    //A比B大
				ascore+=2;
				aincrease=2;
			}
			else if(bch>ach) {    //B比A大
				bscore+=2;
				bincrease=2;
			}
			else {       //一样大
				ascore++;
				bscore++;
				aincrease=1;
				bincrease=1;
			}
			System.out.printf("%-8d%-13f%-14s%-10d%-13f%-10s%-10d\n",i+1,asleep,ach,aincrease,bsleep,bch,bincrease);
			Aword=false;
			Bword=false;
			aCondition.signal();   //通知A比较完成
			bCondition.signal();   //通知B比较完成
			i++;
			}catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
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
