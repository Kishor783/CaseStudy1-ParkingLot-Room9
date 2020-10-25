package Payment;

import java.time.Duration;
import java.time.LocalDateTime;

public class Payment {

	private double rate;
	private LocalDateTime entry;
	private LocalDateTime exit;
	private double duration;
	private boolean isCharge;
	private int charge = 5;

	public Payment(double rate, boolean isCharge, LocalDateTime ent, LocalDateTime ext) {
		this.rate = rate;
		entry = ent;
		exit = ext;
		this.isCharge = isCharge;
	}

	public void calculateDuration() {
		Duration dur = Duration.between(entry, exit);
		duration = (double) dur.getSeconds() / 3600;
	}

	public double calculateFee() {
		double fee = 0;

		if(duration < 1) {
			fee += (rate * duration);
		}
		else {
			fee += (rate * 1);
			
			if(duration < 3) {
				fee += (rate/2 * (duration - 1));
			}
			else {
				fee += (rate/2 * 2);
				fee += (rate/4 * (duration - 3));
			}
		}
		if(isCharge) {
			fee += charge * duration;
		}
		return fee;
	}


}
