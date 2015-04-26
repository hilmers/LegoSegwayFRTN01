package hardware;


public class Segway {
	private SegwayMotor leftMotor;
    private SegwayMotor rightMotor;
	private double distancePerDegree, prevPos;
	private boolean checkedTimeFirstTime = false;
	private long lastSample, time;
	
	public Segway(SegwayMotor leftMotor, SegwayMotor rightMotor, GyroSensor gyro) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
  
        distancePerDegree = 0.00071558;
       
	}
	
	public void forward(int leftSpeed, int rightSpeed) {
		leftMotor.rotateForward(leftSpeed);
		rightMotor.rotateForward(rightSpeed);
	}
	
	public void backward(int leftSpeed, int rightSpeed) {
		leftMotor.rotateBackward(leftSpeed);
		rightMotor.rotateBackward(rightSpeed);
	}
	
	public double getPosition() {
		double pos = (leftMotor.getTachoCount() + rightMotor.getTachoCount())/2;
		return pos * distancePerDegree;
	}
	
	public double getVelocity() throws Exception {
		if (!checkedTimeFirstTime) { 
			time = System.currentTimeMillis();
			checkedTimeFirstTime = true;
		}
		long diff = System.currentTimeMillis() - time; 
		time += diff;
		
		if (diff > 0) {
			double position = getPosition();
			double velocity = (position - prevPos)/diff;
			prevPos = position;
			return velocity;
		} else {
			throw new Exception("Negative time");
		}
	}

}
