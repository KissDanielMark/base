package hu.bme.mit.train.controller;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	public int speedLimit = 0;

	public int getSpeedLimit(){

		return speedLimit;
	}

	public TrainControllerImpl(){
		Runnable runRunrun = new Runnable()
		{
			public void run(){
				followSpeed();
			}
		};
		
		
		ScheduledExecutorService vegrehajto = Executors.newScheduledThreadPool(1);
		vegrehajto.scheduleAtFixedRate(runRunrun, 0,1,TimeUnit.SECONDS);

	}	

	

	//proba
	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}

		enforceSpeedLimit();
	}

	

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		if(speedLimit > 500)
		{
			speedLimit = 120;
		}
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;

	}

}
