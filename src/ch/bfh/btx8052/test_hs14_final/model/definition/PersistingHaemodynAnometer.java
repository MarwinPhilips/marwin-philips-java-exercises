/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.btx8052.test_hs14_final.model.definition;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * This interface represents the requirements for a specialized HaemodynAnometer with the ability to persist the most
 * recent {@link BloodPressureSample} to an {@link OutputStream}. Furthermore it can retrieve a
 * {@link BloodPressureSample} provided an {@link InputStream}. The format of the persisted sample must be obey a
 * key-value separated record e.g.:</br>
 * Systolic 120\n Diastolic 80\n Pulse 86\n Timestamp 123456789\n
 *
 * @author Reto E. Koenig <reto.koenig@bfh.ch>
 */
public interface PersistingHaemodynAnometer extends HaemodynAnometer {

    public static final String DIASTOLIC_TAG = "Diastolic";
    public static final String PULSE_TAG = "Pulse";
    public static final String SEPARATOR_TAG = " ";
    public static final String SYSTOLIC_TAG = "Systolic";
    public static final String TIMESTAMP_TAG = "Timestamp";

    /**
     * This method allows to persist the most recent {@link BloodPressureSample} to a given outputStream. It is
     * important that the implementing method flushes the stream before returning! It is important that the implementing
     * method does not close the stream.
     *
     * @param outputStream The output stream to write the record to.
     */
    public void persistLatestBloodPressureSample(OutputStream outputStream);

    /**
     * This method allows to read a {@link  BloodPressureSample} that has been persisted via {@link #persistLatestBloodPressureSample(java.io.OutputStream) .
     *
     * @param inputStream The input stream to read the record from.
     * @return Returns the persisted {@link BloodPressureSample}.
     */
    public BloodPressureSample retrieveBloodPressureSample(InputStream inputStream);

}
