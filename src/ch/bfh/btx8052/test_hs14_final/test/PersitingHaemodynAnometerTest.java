/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.btx8052.test_hs14_final.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import ch.bfh.btx8052.test_hs14_final.model.definition.BloodPressureSample;
import ch.bfh.btx8052.test_hs14_final.model.definition.PersistingHaemodynAnometer;
import ch.bfh.btx8052.test_hs14_final.model.enumerations.Status;
import ch.bfh.btx8052.test_hs14_final.model.enumerations.StatusIntention;
import ch.bfh.btx8052.test_hs14_final.model.implementation.PersistingHaemodynAnometerModel;

/**
 *
 * @author Reto E. Koenig <reto.koenig@bfh.ch>
 */
public class PersitingHaemodynAnometerTest implements Observer {

    private BloodPressureSample firstBlood;
    private ByteArrayOutputStream baos;

    @Override
    public void update(Observable o, Object arg) {
	if (((Status) arg) == Status.Running) {
	    System.out.print("Wait while measuring");
	}
	if (arg == null) {
	    System.out.print(".");
	}

	if (((Status) arg) == Status.Paused) {
	    System.out.println("");
	    System.out.println("-----------------");
	    System.out.println("Test if the sample can be persisted- and retreived and is equal to the 'getLatestSample'...");
	    PersistingHaemodynAnometer anometer = (PersistingHaemodynAnometer) o;
	    firstBlood = anometer.getLatestBloodPressureSample();
	    System.out.println("first blood: " + firstBlood);
	    baos = new ByteArrayOutputStream();
	    anometer.persistLatestBloodPressureSample(baos);
	    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
	    BloodPressureSample lastBlood = anometer.retrieveBloodPressureSample(bais);
	    System.out.println("persisted  : " + lastBlood);
	    System.out.println("...Are they equal? " + firstBlood.equals(lastBlood));
	    anometer.setStatusIntention(StatusIntention.Run);
	}
	if (((Status) arg) == Status.Finished) {
	    System.out.println("");
	    System.out.println("-----------------");
	    System.out.println("Test if the first persisted sample can still be retreived and is still equal to the first sample...");
	    PersistingHaemodynAnometer anometer = (PersistingHaemodynAnometer) o;
	    System.out.println("first blood: " + firstBlood);
	    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
	    BloodPressureSample lastBlood = anometer.retrieveBloodPressureSample(bais);
	    System.out.println("persisted  : " + lastBlood);
	    System.out.println("...Are they equal? " + firstBlood.equals(lastBlood));

	    System.out.println("-----------------");
	    System.out.println("Test if the last 'getLatestBloodPressureSample()' is really different from the first one...");
	    System.out.println("first blood: " + firstBlood);
	    BloodPressureSample sample = anometer.getLatestBloodPressureSample();
	    System.out.println("last blood:  " + sample);
	    System.out.println("...Are they not equal? " + !firstBlood.equals(sample));

	    System.out.println("-----------------");
	    System.out.println("Test if this last sample can be persisted... and retrieved...");
	    ByteArrayOutputStream localBaos = new ByteArrayOutputStream();
	    anometer.persistLatestBloodPressureSample(localBaos);
	    bais = new ByteArrayInputStream(localBaos.toByteArray());
	    BloodPressureSample latestSamplePersisted = anometer.retrieveBloodPressureSample(bais);
	    System.out.println("latestSamplePersisted:" + latestSamplePersisted);
	    BloodPressureSample latestBlood = anometer.getLatestBloodPressureSample();
	    System.out.println("latestSample         :" + latestBlood);
	    System.out.println("...Are they equal? " + latestSamplePersisted.equals(latestBlood));

	    System.out.println("-----------------");
	    System.out.println("Test if the two persisted samples are indeed different...");
	    System.out.println("latestSamplePersisted:" + latestSamplePersisted);
	    bais = new ByteArrayInputStream(baos.toByteArray());
	    BloodPressureSample samplePersisted = anometer.retrieveBloodPressureSample(bais);
	    System.out.println("persisted:            " + samplePersisted);
	    System.out.println("...Are they not equal? " + !latestSamplePersisted.equals(samplePersisted));
	    System.exit(0);
	}
    }

    public static void main(String[] args) throws InterruptedException, IOException {
	PersitingHaemodynAnometerTest anometerTest = new PersitingHaemodynAnometerTest();
	PersistingHaemodynAnometerModel anometer = new PersistingHaemodynAnometerModel();
	anometer.addObserver(anometerTest);
	anometer.setStatusIntention(StatusIntention.Run);
	Thread.sleep(2000);
	anometer.setStatusIntention(StatusIntention.Pause);

	System.in.read();
	System.out.println("You interrupted the test by hitting a key on the keyboard...");
    }

}
