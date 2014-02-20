package startExercises;

public class ExOne {
		H h;
	public ExOne(){
		h = new H();
		h.openScanner();		
		class Adress {
			public String name;
			public String adresse;
			public String plz;
		}
		
		Adress a = new Adress();
		a.name= h.rL("Geben Sie Ihren Namen ein:");
		a.adresse = h.rL("Geben Sie Ihre Adresse ein");
		a.plz = h.rL("Geben Sie Ihre Postleitzahl und Ort ein");
		h.closeScanner();
		int longest=a.name.length();
		if(a.name.length()< a.adresse.length())
			longest=a.adresse.length();
		if(a.adresse.length()< a.plz.length())
			longest=a.plz.length();
		StringBuilder minusses = new StringBuilder("+");
		for(int i = 0;i < longest+2; i++){
			minusses.append("-");
		}
		minusses.append('+');		
		H.pL(minusses.toString());
		H.pL("| "+ a.name + addSpaces(longest-a.name.length())+ "|");
		H.pL("| "+ a.adresse + addSpaces(longest-a.adresse.length())+ "|");
		H.pL("| "+ a.plz + addSpaces(longest-a.plz.length())+ "|");
		H.pL(minusses.toString());		
	}
	private String addSpaces(int numberOfSpaces){
		StringBuilder returner = new StringBuilder();
		for(int i = 0; i<numberOfSpaces+1;i++)
			returner.append(' ');
		return returner.toString();
	}
	
	
}
