package ApiGoogle;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



public class SheetsQuickstart {
   
    
    public static void main(String... args) throws IOException, GeneralSecurityException {


        //INIT
        SpreadsheetSnippets spreadsheetSnippets=  new SpreadsheetSnippets();
        spreadsheetSnippets.init();
  
        
        //ID OF spreedSheet
        //final String spreadsheetId = "1U93DT1uYezFQLec2jRGkYf8lb4DenHQuUM0mY6pKOrQ";//"1_wYqKTN57uwoshND3snSbuzzhOHqFQnYkMoDCAaotLw";
        final String spreadsheetId = "1R_dZQNYTqObHshByD447M3Qk2lG5Rr4lOhORre4qsic"; // !!!!!!!!!!!! ID SAVM  !!!!
                                       
        lectureLignes(spreadsheetSnippets, spreadsheetId);
        //updateCell(spreadsheetSnippets, spreadsheetId);
        //ajouteLigne(spreadsheetSnippets,spreadsheetId);
        //lectureLignes(spreadsheetSnippets, spreadsheetId);
        
        
        //Exmeple de recherche sur une colonne E (par exmple le pay) et mise a jour la colonne F par le code pays
        /*
        String numLigneOuRange="LIGNE";
        String sheetName="Sheet2";
        String numLignFind = rechercheValeurDansUneColonne(spreadsheetSnippets, spreadsheetId,"20200420_035010",sheetName,"S",numLigneOuRange);
        System.out.println("numLignFind="+numLignFind);
        int numLignFindInt = Integer.parseInt(numLignFind);
        System.out.println("numLignFind="+numLignFind);        
        if (numLignFindInt >=0) {
        	//http://davic.mkdh.fr/savm-data/file/20200420_035010_20200409_185533.jpg
  //!!      	updateCell(spreadsheetSnippets, spreadsheetId,sheetName+"!R"+numLignFind+":R","NOUVEAU_FICHIER");
        }
    	
        */
        //Exemple de remplacemetn d'une valeur
        //remplaceVelCellule(spreadsheetSnippets, spreadsheetId,"Russe","Suede","E");
    }
    
    /**
     * 
     * @param spreadsheetSnippets
     * @param spreadsheetId
     * @param valueRech
     * @param valueRemplace
     * @param colRech
     * @throws IOException
     * @throws GeneralSecurityException
     */
    static void remplaceVelCellule(SpreadsheetSnippets spreadsheetSnippets,String spreadsheetId, String valueRech, String valueRemplace ,String  colRech) throws IOException, GeneralSecurityException {
    	String numLigneOuRange="RANGE"; 
    	String rangeFind = rechercheValeurDansUneColonne(spreadsheetSnippets, spreadsheetId,"Sheet1",valueRech,colRech,numLigneOuRange);
         if (rangeFind != null && ! rangeFind.equals("")) 
         	updateCell(spreadsheetSnippets, spreadsheetId,"Sheet1!"+rangeFind,valueRemplace);
    }
    
    static void  lectureLignes(SpreadsheetSnippets spreadsheetSnippets,String spreadsheetId) throws IOException, GeneralSecurityException {
        //READ Sample
        String range = "Inscription!A:J";
        List<List<Object>> values = spreadsheetSnippets.getValues(spreadsheetId, range);

        //============ Display value =================
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
        } else {
            System.out.println("Name, Major");
            for (List row : values) {
                // Print columns A and E, which correspond to indices 0 and 4.
            	String Cel0 = (String) row.get(0);
            	String Cel4 = "";
            	if (row.size()>=3)  Cel4= (String) row.get(0);
                System.out.printf("%s, %s\n",Cel0, Cel4);
            }
        }
        //==========================================        
    }
    
    /**
     * 
     * @param spreadsheetSnippets
     * @param spreadsheetId
     * @return
     * @throws IOException
     * @throws GeneralSecurityException
     */
    static String rechercheValeurDansUneColonne(SpreadsheetSnippets spreadsheetSnippets,String spreadsheetId, String valueRech, String sheetName,String  colRech, String numLigneOuRange) throws IOException, GeneralSecurityException {
    	String  rangeToUpdate="";
        //String range = "Sheet1!A:E";    
    	String range = sheetName+"!"+colRech+":"+colRech;
        List<List<Object>> values = spreadsheetSnippets.getValues(spreadsheetId, range);
        
        //int numColRech=spreadsheetSnippets.columnLetterToNumber(colRech)-1;
        int numColRech=0;
        System.out.println("rechercheValeurDansUneColonne : numColRech:"+numColRech);

		int i = 0;
		int ligneQuiMAtch=-1;
		boolean trouve=false;
		Object value = null;
		if (values != null) {
			//System.out.println("values size:"+values.size());
		    for (List row : values) {
		    	System.out.println("values i:"+i);
		        i += 1;
		        // values == null || values.isEmpty()) 
		        value = null;
		        System.out.println("values row size:"+row.size());
		        if (row != null && row.size()>numColRech) 
		        	value = row.get(numColRech);
		        if (value!= null) {
		        	if (value.equals(valueRech)) {
		        		ligneQuiMAtch = i;
		        		System.out.println( "IT'S A MATCH! ligneQuiMAtch= " + ligneQuiMAtch);
		        		
		        		rangeToUpdate = colRech + ligneQuiMAtch  + ":" +colRech; //row to be updated
		        		trouve = true;
		        	}
		        }
		    }
		}
		System.out.println( "rechercheValeurDansUneColonne trouv� ! Range / ligne :  "+rangeToUpdate	+"/"+ligneQuiMAtch);
		String sRetour="";
		if (numLigneOuRange.contentEquals("LIGNE")) {
			sRetour=new Integer(ligneQuiMAtch).toString();
		} else {
			sRetour=rangeToUpdate;
		}
    	return sRetour;    	
    }
    
    
    
    
    
    
    
    
    
    
    static void updateLigne(SpreadsheetSnippets spreadsheetSnippets,String spreadsheetId) throws IOException, GeneralSecurityException {
    	 //UPdate Sample
        String range = "Sheet1!A10:E";
        List<List<Object>> values = spreadsheetSnippets.getValues(spreadsheetId, range);
        values = Arrays.asList(
    	        Arrays.asList(
    	        		new Object[]{"abc","","xyz","pqr","Americain"}
    	        )
    	        // Additional rows ...
    	        , Arrays.asList(
    	        		new Object[]{"abc","klm","xyz","pqr","Canadien"}
    	        )
    	);
        
        String  valueInputOption = "USER_ENTERED";
        spreadsheetSnippets.updateValues(spreadsheetId, range, valueInputOption, values);
    }

    
    static void updateCell(SpreadsheetSnippets spreadsheetSnippets,String spreadsheetId,String range , String NouvelleValeur) throws IOException, GeneralSecurityException {
    	//UPdate Sample de la cellule E10 ! par la valeur : Americains 
       //String range = "Sheet1!E10:E";
       List<List<Object>> values = spreadsheetSnippets.getValues(spreadsheetId, range);
       values = Arrays.asList(
   	        Arrays.asList(
   	        		new Object[]{NouvelleValeur}
   	        )
   	);
       
       String  valueInputOption = "USER_ENTERED";
       spreadsheetSnippets.updateValues(spreadsheetId, range, valueInputOption, values);
   }
    
    static  void ajouteLigne(SpreadsheetSnippets spreadsheetSnippets,String spreadsheetId) throws IOException, GeneralSecurityException {
    	 SheetSAVM _SheetSAVM = new SheetSAVM();
         _SheetSAVM.initOdreChamps("NOM,PRENOM,ADR_NUM,ADR_RUE,ADR_CODE_POSTAL,ADR_VILLE,TEL,EMAIL,OEUVRE_TYPE,OEUVRE_TITRE,OEUVRE_DETAIL,OEUVRE_DIM,OEUVRE_PRIX,SIRET_MDA,DISPO_GARDE,SITE_INTERNET,PHOTO");								  
 		//Liste de depart
 		_SheetSAVM.listeDepart = new ArrayList<Object>( Arrays.asList("NOM","FASSEL","ADR_RUE","DUMESNIL","OEUVRE_TITRE","Peinture"));        
 		//ArrayList<String> aListDays = new ArrayList<String>( Arrays.asList("Sun", "Mon", "Tue") );		
 		//ArrayList<String> places = new ArrayList<>(Arrays.asList("Buenos Aires", "C�rdoba", "La Plata"));		
 		_SheetSAVM.creteListeShhet();
 		System.out.println("----");
         List<List<Object>> value2 = new ArrayList<List<Object>>(); 			
         value2.add(_SheetSAVM.ListeArrive); 
         
         String range = "Sheet1!A10:D";
     	String  valueInputOption = "USER_ENTERED";
         spreadsheetSnippets.appendValues(spreadsheetId, range, valueInputOption, value2);
    }
    
}