package hardware;

public class CompFilter {
	private double ygs, yas, yf, h, eh;

	// h is the sample-time
	public CompFilter(double h) {
		this.h = h/1000;
		eh = Math.exp(-this.h);
	}

	public double compFilt(double ya, double yg) {
		yf = eh * yf + (1 - eh) * yas + yg - ygs;
		ygs = yg; // Update state
		yas = ya; // Update state
		return yf;
	}

}
