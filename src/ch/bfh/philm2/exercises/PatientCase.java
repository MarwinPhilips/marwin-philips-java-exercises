package ch.bfh.philm2.exercises;

import java.time.LocalDate;

public class PatientCase {
	private Fall fall;
	private Patient patient;
	
	public PatientCase(int patientId, String name, String vorname, char geschlecht, int gewicht,int gr�sse, LocalDate geburtsdatum,
			int fallId, LocalDate eintrittsDatum, LocalDate austrittsDatum, String aufnahmegrund){
		patient = new Patient(patientId, name, vorname, geschlecht, gewicht, gr�sse, geburtsdatum);
		fall = new Fall(fallId, patient, eintrittsDatum, austrittsDatum, aufnahmegrund);
		patient.AddFall(fall);
	}
	public String getAdmissionDate(){
		return fall.getEintrittsDatum().toString();
	}
	public String getAdmissionReason(){
		return fall.getAufnahmegrund();
	}
	public String getAge(){
		return patient.getAge();
	}
}
