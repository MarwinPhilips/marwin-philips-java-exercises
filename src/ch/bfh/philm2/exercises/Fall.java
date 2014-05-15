package ch.bfh.philm2.exercises;

import java.time.LocalDate;


public class Fall {
	private int fallId;
	private Patient patient;
	private LocalDate eintrittsDatum;
	private LocalDate austrittsDatum;
	private String aufnahmegrund;
	public Fall(int fallId, Patient patient, LocalDate eintrittsDatum, LocalDate austrittsDatum, String aufnahmegrund){
		this.fallId=fallId;
		this.patient=patient;
		this.eintrittsDatum=eintrittsDatum;
		this.austrittsDatum=austrittsDatum;
		this.aufnahmegrund=aufnahmegrund;
	}
	public int getFallId() {
		return fallId;
	}
	public void setFallId(int fallId) {
		this.fallId = fallId;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public LocalDate getEintrittsDatum() {
		return eintrittsDatum;
	}
	public void setEintrittsDatum(LocalDate eintrittsDatum) {
		this.eintrittsDatum = eintrittsDatum;
	}
	public LocalDate getAustrittsDatum() {
		return austrittsDatum;
	}
	public void setAustrittsDatum(LocalDate austrittsDatum) {
		this.austrittsDatum = austrittsDatum;
	}
	public String getAufnahmegrund() {
		return aufnahmegrund;
	}
	public void setAufnahmegrund(String aufnahmegrund) {
		this.aufnahmegrund = aufnahmegrund;
	}
}
