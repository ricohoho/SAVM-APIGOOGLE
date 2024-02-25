package ApiGoogle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SheetSAVM {
	
	public ArrayList<Object> listeDepart=null;
	public ArrayList<Object> ListeArrive=null;
	public HashMap< String,Integer> ordreChamps = new HashMap<String,Integer >();
	
/**
 * ORder des champs de la Sheet Google
 * chainePAram = NOM,PRENOM,ADR_NUM ..
 */
	public void initOdreChamps(String chainePAram) {
		ordreChamps = new  HashMap<String,Integer >();
		
		String[] tab=chainePAram.split(",");
		int i=1;
		for (String val : tab) {
			ordreChamps.put( val,i);
			i++;
		}

	    System.out.println(ordreChamps.size());
	}
	
	
	public static void main(String[] args) {
		SheetSAVM _SheetSAVM = new SheetSAVM();
		_SheetSAVM.initOdreChamps("NOM,PRENOM,ADR_NUM,ADR_RUE");			
		System.out.println(_SheetSAVM.ordreChamps.size());
		int var;
		try {
			var =_SheetSAVM.ordreChamps.get("PRENOM");			
		} catch (Exception e) {
			System.out.println(e);
			var=-1;
		}
		System.out.println(var);
	}
	
	public static void main2(String[] args) {
		//INIT orde des champs
		SheetSAVM _SheetSAVM = new SheetSAVM();
		_SheetSAVM.initOdreChamps("NOM,PRENOM,ADR_NUM,ADR_RUE");			
		System.out.println(_SheetSAVM.ordreChamps.size());
		int var= _SheetSAVM.ordreChamps.get("PRENOM");
		System.out.println("Value at index PRENOM is: "+var);
	  
		//Liste de depart
		_SheetSAVM.listeDepart = new ArrayList<Object>( Arrays.asList("NOM","FASSEL","ADR_RUE","DUMESNIL","PRENOM","ERIC"));        
		//ArrayList<String> aListDays = new ArrayList<String>( Arrays.asList("Sun", "Mon", "Tue") );		
		//ArrayList<String> places = new ArrayList<>(Arrays.asList("Buenos Aires", "Córdoba", "La Plata"));		
		_SheetSAVM.creteListeShhet();
		System.out.println("----");
		for (Object val : _SheetSAVM.ListeArrive) {
			System.out.println(val);
		}			  
		System.out.println("Fin");
	}

	//MEthode List<String>   ===> List<String>
	//FORMAT : NAME1=VALUEA,NAME2=VALUE2, ...   ===> ORDONENCEMENT [NAMEX,MAMEY,NAMEZ ..]  ===> VALUEX,VALUET,VALUEZ ...   
	public void creteListeShhet() {
		System.out.println("creteListeShhet Debut 1");
		int i=0;
		String[] tab = new String[25];
		String colonne="";
		String valeur="";
		int index=0;
		for (Object val : listeDepart) {
			System.out.println("creteListeShhet i="+i);
			if (val==null) val="";
			System.out.println("creteListeShhet val="+val );
			i++;
			if (i % 2 != 0) { //ImPair
				colonne = val.toString();
			} else { //pair
				valeur=val.toString();				
				try {
					index=ordreChamps.get(colonne);	
				} catch (Exception e) {					
					index=-1;
				}
				System.out.println("creteListeShhet colonne="+colonne+"/index="+index);
				if (index>0) {
					//System.out.println("creteListeShhet avant : tab[index-1]=valeur;");
					tab[index-1]=valeur;
					//System.out.println("creteListeShhet apres : tab[index-1]=valeur;");
				} else {
					System.out.println("colonne non trouvé:"+colonne);
				}
			}			        	
        }
		
		//On remplie a "" les chmaps null
		System.out.println("null=>''");
		int j=0;
		for (String val : tab) {
			if (val==null)
				tab[j]="";
			j++;
		}
		
		//Liste de depart : Conversion tab en  liste
		ListeArrive = new ArrayList<Object> (Arrays.asList(tab));
	}
	
}
