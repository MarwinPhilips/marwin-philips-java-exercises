/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.btx8052.test_hs14_final.model.definition;

import ch.bfh.btx8052.test_hs14_final.model.enumerations.Status;
import ch.bfh.btx8052.test_hs14_final.model.enumerations.StatusIntention;


/**
 *
 * This interface represents a HaemodynAnometer (Blood pressure monitor) {
 *
 * @see http://de.wikipedia.org/wiki/Blutdruckmessgerät}. An implementing model must simulate a complete measurement
 * cycle presenting the result(s) as {@link ch.bfh.medinfo.bloodp.model.definition.BloodPressureSample}s. It is
 * controlled via {@link ch.bfh.medinfo.bloodp.model.enumerations.StatusIntention}s. As an observable it notifies the
 * observer in the following ways:
 * <ul>
 * <li>Indication of a state change: The actual state is pushed</li>
 * <li>Indication of a new sample: The indication allows to pull a new sample</li>
 * </ul>
 *
 * The HaemodynAnometer's internal state machine runs within its own deamon thread. It is controlled via the non
 * blocking method {@link#setStatusIntention(ch.bfh.medinfo.bloodp.model.implementation.StatusIntention) }.
 *
 *
 *
 * @author Reto E. Koenig <reto.koenig@bfh.ch>
 */
public interface HaemodynAnometer {

    /**
     *
     * @return The latest measurement represented by a {@link BloodPressureSample}
     */
    BloodPressureSample getLatestBloodPressureSample();

    /**
     *
     * @return The latest {@link Status} of the internal 'machine'
     */
    Status getLatestStatus();

    /**
     *
     * @return The latest {@link StatusIntention} that has been set via {@link #setStatusIntention(ch.bfh.medinfo.bloodp.model.enumerations.StatusIntention)
     */
    StatusIntention getLatestStatusIntention();

    /**
     * This is a non-blocking method allowing to influence the internal 'machine' working within its own thread.
     *
     * @param statusIntention The {@link StatusIntention} for the internal 'machine'
     */
    void setStatusIntention(StatusIntention statusIntention);

}
