package Payment;

import java.time.Duration;
import java.time.LocalDateTime;

public class Payment implements pay {//implements pay interface

	private double rate;//rate of the chosen vehicle type
	private LocalDateTime entry;//entry time of the customer
	private LocalDateTime exit;//exit time of the customer
	private double duration;//duration for which customer has stayed
	private boolean isCharge;//if customer opted for charging in case of electric car
	private int charge = 5;//charging rate of an electric vehicle

	public Payment(double rate, boolean isCharge, LocalDateTime ent, LocalDateTime ext) {//constructor to initialize the payment attributes 
		this.rate = rate;//set the rate
		entry = ent;//set the entry time of the customer
		exit = ext;//set the exit time of the customer
		this.isCharge = isCharge;//if electric charging is opted
	}

	public void calculateDuration() {//method to calculate stay duration of the customer
		Duration dur = Duration.between(entry, exit);//calculate the duration
		duration = (double) dur.getSeconds() / 3600;//adjust the duration into hours
	}

	public double calculateFee() {//method to calculate the fee
		double fee = 0;

		if(duration < 1) {//if duration is less than an hour
			fee += rate;
		}
		else {//if duration is more than 1 hour
			fee += (rate * 1);
			
			if(duration < 3) {//if duration is less than 3 hours
				fee += (rate/2 * (duration - 1));
			}
			else {//if duration is more than 3 hours
				fee += (rate/2 * 2);
				fee += (rate/4 * (duration - 3));
			}
		}
		if(isCharge) {//if charging was opted
			fee += charge * duration;
		}
		return fee;
	}


}
