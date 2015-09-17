/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.btx8052.test_hs14_final.model.implementation;

import java.util.Observable;
import java.util.Random;

import ch.bfh.btx8052.test_hs14_final.model.definition.BloodPressureSample;
import ch.bfh.btx8052.test_hs14_final.model.definition.HaemodynAnometer;
import ch.bfh.btx8052.test_hs14_final.model.enumerations.Status;
import ch.bfh.btx8052.test_hs14_final.model.enumerations.StatusIntention;

/**
 * This class represents an observable instance of a {@link HaemodynAnometer}.
 * (Blood pressure monitor) {
 *
 * As an observable it notifies the observer in the following ways:
 * <ul>
 * <li>Indication of a state change: The actual {@link Status} is pushed</li>
 * <li>Indication of a new sample: The indication allows to pull a new
 * {@link BloodPressureSample}.</li>
 * </ul>
 * During a run, a typical indication for a new sample comes every
 * {@link #SAMPLE_PERIOD_IN_MS}. (Careful)
 *
 * The HaemodynAnometer's internal state machine runs within its own deamon
 * thread. It is controlled via the non blocking method
 * {@link#setStatusIntention(ch.bfh.medinfo.bloodp.model.implementation.StatusIntention) }
 * .
 *
 *
 *
 * @author Reto E. Koenig <reto.koenig@bfh.ch>
 */
public class HaemodynAnometerModel extends Observable implements HaemodynAnometer {

	private final static int SAMPLE_PERIOD_IN_MS = 251;
	private final BloodPressureSampler sampler;
	private final Thread pressureThread;

	private StatusIntention latestStatusIntention;
	private Status currentStatus;
	private BloodPressureSample latestBloodPressureSample;

	public HaemodynAnometerModel() {
		this.latestStatusIntention = StatusIntention.Stop;
		sampler = new BloodPressureSampler();
		this.pressureThread = new Thread(sampler);
		this.pressureThread.setDaemon(true);
		this.pressureThread.start();
	}

	/**
	 *
	 * @return
	 */
	@Override
	public BloodPressureSample getLatestBloodPressureSample() {
		return latestBloodPressureSample;
	}

	@Override
	public Status getLatestStatus() {
		return currentStatus;
	}

	@Override
	public StatusIntention getLatestStatusIntention() {
		return latestStatusIntention;
	}

	@Override
	public void setStatusIntention(StatusIntention statusIntention) {
		if (this.latestStatusIntention == statusIntention) {
			return;
		}
		this.latestStatusIntention = statusIntention;
		synchronized (sampler) {
			sampler.notifyAll();
		}
	}

	class BloodPressureSampler implements Runnable {

		private final Random random = new Random();
		private int systolicTop;
		private int diastolicTop;
		private int pulseMedian;

		public BloodPressureSampler() {
			currentStatus = Status.Stopped;
		}

		private boolean changeCurrentStatus(Status status) {
			if (currentStatus != status) {
				currentStatus = status;
				setChanged();
				notifyObservers(currentStatus);// All observers will receive the
												// status change Intention
				return true;
			}
			return false;
		}

		@Override
		public void run() {
			try {
				while (true) {
					synchronized (this) {
						if (latestStatusIntention == StatusIntention.Stop) {
							changeCurrentStatus(Status.Stopped);
							latestBloodPressureSample = null;
							wait();
							continue;
						}

						if (latestStatusIntention == StatusIntention.Pause) {
							changeCurrentStatus(Status.Paused);
							wait();
							continue;
						}

						if (latestStatusIntention == StatusIntention.Run) {
							if (currentStatus == Status.Finished) {
								latestStatusIntention = null;
								wait();
								currentStatus = null;
								latestBloodPressureSample = null;
								continue;
							} else if (changeCurrentStatus(Status.Running)) {
								if (latestBloodPressureSample == null) {
									systolicTop = random.nextInt(120) + 60;
									diastolicTop = systolicTop - (random.nextInt(10) + 40);
									pulseMedian = random.nextInt(80) + 40;
									latestBloodPressureSample = new BloodPressureSampleDTO(0, 0, 0);
								}
							} else {
								Thread.sleep(SAMPLE_PERIOD_IN_MS);
								int pulse = pulseMedian + random.nextInt(10);
								int diastolic = latestBloodPressureSample.getDiastolicInMMHg();
								int systolic = latestBloodPressureSample.getSystolicInMMHg();
								if (systolic < systolicTop) {
									systolic += 3;
									diastolic += 3;
								} else if (diastolic > diastolicTop) {
									diastolic -= 3;

								}
								latestBloodPressureSample = new BloodPressureSampleDTO(systolic, diastolic, pulse);
								setChanged();
								notifyObservers(); // Which requires the
													// observer to pull the data
								if (latestBloodPressureSample.getDiastolicInMMHg() <= diastolicTop && latestBloodPressureSample.getSystolicInMMHg() >= systolicTop) {
									changeCurrentStatus(Status.Finished);
								}

							}

						}

					}
				}
			} catch (InterruptedException ex) {
				// OK, that is it, we are requested to leave!
				currentStatus = Status.Broken;
				latestBloodPressureSample = null;
			}

		}

	}

	@Override
	public String toString() {
		return "HaemodynAnometerModel{" + "currentStatus=" + currentStatus + '}';
	}
}
