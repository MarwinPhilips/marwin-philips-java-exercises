/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.btx8052.test_hs14_final.model.implementation;

import java.io.Serializable;

import ch.bfh.btx8052.test_hs14_final.model.definition.BloodPressureSample;


/**
 *
 * @author Reto E. Koenig <reto.koenig@bfh.ch>
 */
public class BloodPressureSampleDTO implements BloodPressureSample, Serializable {

	private final int systolicInMMHg;
	private final int diastolicInMMHg;
	private final int pulse;
	private final long timestampInMillis;

	public BloodPressureSampleDTO(int systolicInMMHg, int diastolicInMMHg, int pulse, long timeStampInMillis) {
		this.systolicInMMHg = systolicInMMHg;
		this.diastolicInMMHg = diastolicInMMHg;
		this.pulse = pulse;
		this.timestampInMillis = timeStampInMillis;
	}

	public BloodPressureSampleDTO(int systolicInMMHg, int diastolicInMMHg, int pulse) {
		this(systolicInMMHg, diastolicInMMHg, pulse, System.currentTimeMillis());
	}

	@Override
	public int getSystolicInMMHg() {
		return systolicInMMHg;
	}

	@Override
	public int getDiastolicInMMHg() {
		return diastolicInMMHg;
	}

	@Override
	public int getPulse() {
		return pulse;
	}

	@Override
	public long getTimestampInMillis() {
		return timestampInMillis;
	}

	@Override
	public String toString() {
		return "InternalBloodPressureSample{" + "systolicInMMHg=" + systolicInMMHg + ", diastolicInMMHg=" + diastolicInMMHg + ", pulse=" + pulse + ", timestampInMillis="
				+ timestampInMillis + '}';
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 79 * hash + this.systolicInMMHg;
		hash = 79 * hash + this.diastolicInMMHg;
		hash = 79 * hash + this.pulse;
		hash = 79 * hash + (int) (this.timestampInMillis ^ (this.timestampInMillis >>> 32));
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final BloodPressureSampleDTO other = (BloodPressureSampleDTO) obj;
		if (this.systolicInMMHg != other.systolicInMMHg) {
			return false;
		}
		if (this.diastolicInMMHg != other.diastolicInMMHg) {
			return false;
		}
		if (this.pulse != other.pulse) {
			return false;
		}
		if (this.timestampInMillis != other.timestampInMillis) {
			return false;
		}
		return true;
	}

}
