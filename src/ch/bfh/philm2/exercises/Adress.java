/*
 * My Starter-Exercise. 
 */
package ch.bfh.philm2.exercises;

public class Adress{
	private String name;
	private String prename;
	private String street;
	private String streetnumber;
	private int plz;
	private String postLeitZahl;
	private String city;
	public Adress(){
	
	}
	public Adress(String street, String streetnumber, String postLeitZahl, String city){
		this.street = street;
		this.streetnumber=streetnumber;
		this.setPostLeitZahl(postLeitZahl);
		this.city=city;
	}
	public String getName() {
		return name;
	}
	
	public String toString(){
		char blank = ' ';
		char newLine = '\n';
		
		StringBuilder b = new StringBuilder();
		
		b.append(prename);
		b.append( blank );
		b.append(name);
		b.append(newLine);
		b.append(street);
		b.append(blank);
		b.append(streetnumber);
		b.append(newLine);
		b.append(plz);
		b.append(blank);
		b.append(city);
		return b.toString();
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrename() {
		return prename;
	}
	public void setPrename(String prename) {
		this.prename = prename;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStreetnumber() {
		return streetnumber;
	}
	public void setStreetnumber(String streetnumber) {
		this.streetnumber = streetnumber;
	}
	public int getPlz() {
		return plz;
	}
	public void setPlz(int plz) {
		this.plz = plz;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostLeitZahl() {
		return postLeitZahl;
	}
	public void setPostLeitZahl(String postLeitZahl) {
		this.postLeitZahl = postLeitZahl;
	}
}
