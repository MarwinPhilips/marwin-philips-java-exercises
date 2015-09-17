/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.btx8052.test_hs14_final.model.definition;

/**
 * Any data object representing the values of a {@link HaemodynAnometer} requires to implement this interface
 *
 * @author Reto E. Koenig <reto.koenig@bfh.ch>
 */
public interface BloodPressureSample {

    /**
     *
     * @return Systolic blood pressure in mm Hg
     */
    public int getSystolicInMMHg();

    /**
     *
     * @return Diastolic blood pressure in mm Hg
     */
    public int getDiastolicInMMHg();

    /**
     *
     * @return The time this measurement has been taken
     */
    public long getTimestampInMillis();

    /**
     *
     * @return The actual pulse rate
     */
    public int getPulse();
}
