package hardware;

public class GyroTest implements Runnable {
	GyroSensor gyro;

	public GyroTest() {
		gyro = new GyroSensor();
	}

	@Override
	public void run() {
		float angle;
		while (true) {
			angle = gyro.getAngle();
			System.out.println("vel: " + angle);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public static void main(String[] args) {
		Thread thread = new Thread(new GyroTest());
		thread.start();
	}

}