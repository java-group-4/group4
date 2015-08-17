package problem2;


import java.util.*;
import java.io.*;
import java.lang.*;
public class Problem2 {

	public static void main(String[] args) {
		Chat newChat=new Chat();
		new T1(newChat);
		new T2(newChat);
				
	}

}


 class Chat {
	
	boolean flag = false;
	
	public synchronized void Question (String msg){
		if(flag) {
			try{
				wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(msg);
		flag = true;
		notify();
		
	}
	
	public synchronized void Answer (String msg) {
		if (!flag) {
			try {
				wait();
				
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println(msg);
		flag = false;
		notify();
	}

}


 class T1 implements Runnable {
	Chat m;
	
	public T1 (Chat m1){
		
		this.m = m1;
		new Thread (this,"Question").start();
		
	}

	public void run() {
		 
		try(BufferedReader br= new BufferedReader(new FileReader("C:\\Users\\vikash.kumawat\\workspace\\13AugThread\\src\\p022_names1.txt")))  {
		    String line1;
		    while ((line1 = br.readLine()) != null) {
		       m.Question(line1);
		    	}
		    br.close();
		    
		}catch(FileNotFoundException exception){
			System.out.println("File not found\n");
		}
		catch(IOException exception){
			System.out.println(exception);
		}
		
		
		
	}

}


class T2 implements Runnable {
	Chat m;
	public T2 (Chat m2){
		
		this.m = m2;
		new Thread (this,"Answer").start();
	}

	public void run() {
		
		 
		try(BufferedReader br2= new BufferedReader(new FileReader("C:\\Users\\vikash.kumawat\\workspace\\13AugThread\\src\\p081_matrix1.txt")))  {
		    String line;
		    while ((line = br2.readLine()) != null) {
		    	m.Answer(line);
		    }
		    br2.close();
		    
		}catch(FileNotFoundException exception){
			System.out.println("File not found\n");
		}
		catch(IOException exception){
			System.out.println(exception);
		}
		
		
		
	}

}
