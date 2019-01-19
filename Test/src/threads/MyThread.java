package threads;

public class MyThread implements Runnable {

	private String message;

	public MyThread(String s) {
		this.message = s;
	}

	public void run() {
		for (int i = 1; i <= 5; i++) {
			System.out.println(Thread.currentThread().getName() + message + " :" +i);
//			processmessage();
		}
	}

	private void processmessage() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
