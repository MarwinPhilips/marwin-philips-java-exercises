package ch.bfh.philm2.exercises;

import java.time.LocalDate;
import java.util.List;

import ch.bfh.philm2.util.DeltaTime;

public class Patient{
	private int patientId;
	private String name;
	private String vorname;
	private char geschlecht;
	private int gewicht;
	private int Gr�sse;
	private LocalDate geburtsdatum;
	private List<Fall> f�lle;
	
	public Patient(int patientId, String name, String vorname, char geschlecht, int gewicht, int Gr�sse, LocalDate geburtsdatum){
		this.patientId=patientId;
		this.name=name;
		this.vorname=vorname;
		this.geschlecht=geschlecht;
		this.gewicht=gewicht;
		this.Gr�sse=Gr�sse;
		this.geburtsdatum=geburtsdatum;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public LocalDate getGeburtsdatum() {
		return geburtsdatum;
	}
	public void setGeburtsdatum(LocalDate geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}
	public List<Fall> getf�lle() {
		return f�lle;
	}
	public void setf�lle(List<Fall> f�lle) {
		this.f�lle = f�lle;
	}
	public void AddFall(Fall fall) {
		this.f�lle.add(fall);		
	}
	public void removeFall(Fall fallToRemove) {
		f�lle.remove(fallToRemove);		
	}
	public char getGeschlecht() {
		return geschlecht;
	}
	public void setGeschlecht(char geschlecht) {
		this.geschlecht = geschlecht;
	}
	public int getGewicht() {
		return gewicht;
	}
	public void setGewicht(int gewicht) {
		this.gewicht = gewicht;
	}
	public int getGr�sse() {
		return Gr�sse;
	}
	public void setGr�sse(int Gr�sse) {
		this.Gr�sse = Gr�sse;
	}
	public String getAge(){
		DeltaTime d = new DeltaTime(LocalDate.now(), geburtsdatum);	
		return d.getDeltaTimeInYearsMonthsDays();
	}
}