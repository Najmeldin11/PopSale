import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class PopSalesArray {
	static String iFName;//first name
	static String iLName;//last name
	static String iAddress; //Address
	static String iCity; //City
	static String iTeam; //Team
	static int    cPopType; //PopType
	static int     cCases; //Cases
	static int      cZip; //ZipCode
	static int      ErrorCount=0; //ErrorCount
	static String   iZip; //ZipCode
	static PrintWriter pw; //used to write data to the 
	static String number, msg; //number and messages
	private  static boolean eof = false; //used to control user loop 
	private  static boolean valid;
	private  static double cDeposit=0; // calculated 
	private  static double  cTotalDollar=0; // calculated TotalDollar
	private  static double cTotalA=0;      // calculated TotalA
	private  static double cTotalB=0;     // calculated TotalB
	private  static double cTotalC=0;    // calculated TotalC
	private  static double cTotalD=0;   // calculated TotalD
	private  static double cTotalE=0;   // calculated TotalE
	private  static String iState;     //State
	private  static String iPopType;   //PopType
	private  static String iCases;     //PopType
    private static Scanner myScanner; //input device to read from 
	private static PrintWriter PW;  //used to write data to the 
	private static int cCoke=0 ;  // calculated coke
	private static int cDCoke=0;   // calculated 
	private static int cMelloYell=0; // calculated 
	private static int cCCoke=0;  // calculated CCoke
	private static int cDCCoke=0; // calculated DCCoke
	private static int cSprite=0; // calculated sprite
	private static String record,data; //the whole record and date formatted
	private static int pageNo=0;
	private static final int LINES_PER_PAGE =40;
	private static int lineCounter;
	public static void main(String[] args) {
	
		init();
		
		while(!eof){
			input();
			validate();
		
		    if(!valid){
		    	Error();
		    }
		    else{
		    	calcs();
		    	good(); 
		    }    
		}
		grandTotal();
		TeamTotal();
		closing();
	}
 private static void init() {
			// open input file
			try {
				PW = new PrintWriter( new FileOutputStream( new File("JAVAPOPSL.PRT"), false));
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "File Problem");
			}
			try {
				pw = new PrintWriter( new FileOutputStream( new File("JAVPER.PRT"), false));
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "File Problem");
			}
			try {
				myScanner= new Scanner(new File("POPSL.DAT")); //instantiate an anonymous file object
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "the file is Not available ,cancelling");
				System.exit(1);
			}
			
	        myScanner.useDelimiter("\r\n");
	        heading();
	        input();
	        headingErr(); 
	       }//end of init
		
private static void input(){
			String  data;
			
			if(myScanner.hasNext() ){
				record = myScanner.next();
				data = record.substring(0, 15);
				iLName = data; 
				data = record.substring(15, 30);
				iFName = data;
				data =record.substring(30, 45);
				iAddress=data;
				
				data =record.substring(45, 55);
				iCity=data;
				
				data =record.substring(55, 57);
				iState=data;
				
				data =record.substring(57, 66);
				iZip=data;
				data =record.substring(66,68);
				iPopType=data;
				data =record.substring( 68,70);
				iCases=data;
				data =record.substring(70, 71);
				iTeam = data;
			}	
			else{
				eof = true;
			}
			
		}
private static void validateFName(String FN) {			
			iFName = FN.trim();
			if(iFName.isEmpty()){
					msg=("First Name  is blank");
					ErrorCount = ErrorCount +1;
					valid= false;
					
					return;
			}
			
			valid = true;			
		}	
public static void validateLName(String LN) {			
			 iLName = LN.trim();
			if(iLName.isEmpty()){
					msg=("last name is blank");
					ErrorCount = ErrorCount +1;
					valid= false;
					
					return;
			}
			
			valid = true;			
		}	
private static void validateAddress(String add) {			
			iAddress = add.trim();
			if(iAddress.isEmpty()){
					msg =("Address  is blank");
					ErrorCount = ErrorCount +1;
					valid= false;
					
					return;
			}
			
			valid = true;			
		}	
private static void validateCity(String city) {			
			iCity = city.trim();
			if(iCity.isEmpty()){
					msg = ("City is blank");
					ErrorCount = ErrorCount +1;
					valid= false;
					
					return;
			}
			
			valid = true;			
		}	
private static void validateState(String state) {			
			 iState = state.trim();
			if(iState.isEmpty()){
					msg = ("s is blank");
					ErrorCount = ErrorCount +1;
					valid= false;
					return;
			}
			iState = iState.toUpperCase();
			if(!iState.matches("IA|IL|MI|MO|NE|WI")){
			msg = ("state has invalid letter"+iState);
			ErrorCount = ErrorCount +1;
				valid= false;
				
				return;
			}
			valid = true;			
		}	
		
		private static void validateZip(String number) {	
			//System.out.println(number);
		   try {
			   cZip= Integer.parseInt(number);
			   } catch (NumberFormatException e) {
				   msg = ("Zip Code non numeric");
				   ErrorCount = ErrorCount +1;
				   valid= false;
				 return;
			  }	
		   valid= true;
			 if(iZip.matches("[0-9]{5}-[0-9]{4}")){
				msg = ("Zip must be between0 to 999999999");
				//ErrorCount = ErrorCount +1;
				valid= false;
				
				return;
			}
			valid= true;
				
			}	
		
private static void validatePopType(String number) {			
			try{
				
			    cPopType=Integer.parseInt(number);
			    
			    }
			catch (NumberFormatException e) {
				    msg = ("Pop Type is non numeric");
				   // ErrorCount = ErrorCount +1;
				return;
				}
				
			if(cPopType < 01 || cPopType>06){
			msg= ("Pop Type number is  between 1-3");
			//ErrorCount = ErrorCount +1;
				return;
			}
				valid = true;			
			}
private static void validateCases(String number) {			
			try{
				
			    cCases=Integer.parseInt(number);
			    
			    }
			catch (NumberFormatException e) {
				   msg=("Cases is non numeric");
				  // ErrorCount = ErrorCount +1;
				return;
				}
				
			if(cCases < 1 || cCases>6){
				msg=("Cases number is  between 1-6");
				//ErrorCount = ErrorCount +1;
				return;
			}
				valid = true;			
		}
		
		
private static void validateTeam(String state) {			
			 iTeam = state.trim();
			if(iTeam.isEmpty()){
					msg= ("Team is blank");
					//ErrorCount = ErrorCount +1;
					return;
			}
			iTeam = iTeam.toUpperCase();
			if(!iTeam.matches("[ABCDE]")){
			msg=("Team has invalid letter"+iTeam);
			//ErrorCount = ErrorCount +1;
				return;
			}
			valid = true;			
		}	
		
private static void validate() {
			validateFName(iFName);
			if(!valid){
				return;
			}
			validateLName(iLName);
			if(!valid){
				return;
			}
			validateAddress(iAddress);
			if(!valid){
				return;
			}
			validateCity(iCity);
			if(!valid){
				return;
			}
			
			validateState(iState);
			if(!valid){
				return;
			}
			
			validateZip(iZip);
			if(!valid){
				return;
			}
			
			validatePopType(iPopType);
			if(!valid){
				return;
			}
			
			validateCases(iCases);
			if(!valid){
				return;
			}
			
			validateTeam(iTeam);
			if(!valid){
				return;
			}
			
		}//end of validate
 public static void calcs() {
			 
			if(iState.equalsIgnoreCase("IA")) {
				cDeposit=0.05*24*cCases;
				
			}
			if(iState.equalsIgnoreCase("WI")) {
				cDeposit=0;
				
			}
			if(iState.equalsIgnoreCase("MI")) {
				cDeposit=0.10*24*cCases;
				
			}
			if(iState.equalsIgnoreCase("MO")) {
				cDeposit=0;
				
			}
			if(iState.equalsIgnoreCase("NE")) {
				cDeposit=0.05*24*cCases;
				
			}
			if(iState.equalsIgnoreCase("iState")) {
				cDeposit=0.05*24*cCases;
				
			}
			
			if(cPopType==01){
				cCoke=cCoke+cCases;
			
			}
			if(cPopType==02){
				cDCoke=cDCoke+cCases;
		
			}
			if(cPopType==03){
				cMelloYell=cMelloYell+cCases;
		
			}
			if(cPopType==04){
				cCCoke=cCCoke+cCases;
		
			}
			if(cPopType==05){
				cDCCoke=cDCCoke+cCases;
			
			}
			if(cPopType==06){
				cSprite=cSprite+cCases;
				
			}
			 cTotalDollar=18.71*cCases+cDeposit;
			 
			 if(iTeam.equalsIgnoreCase("A")){
				cTotalA=cTotalA+cTotalDollar; 
			 }
			 if(iTeam.equalsIgnoreCase("B")){
					cTotalB=cTotalB+cTotalDollar; 
				 }
			 if(iTeam.equalsIgnoreCase("C")){
					cTotalC=cTotalC+cTotalDollar; 
				 }
			 if(iTeam.equalsIgnoreCase("D")){
					cTotalD=cTotalD+cTotalDollar; 
				 }
			 if(iTeam.equalsIgnoreCase("E")){
					cTotalE=cTotalE+cTotalDollar; 
				 }
		 } 
		 
private static void Error() {
			lineCounter++;
	 	   if(lineCounter>LINES_PER_PAGE)
	 		  heading(); 	
	         // pw.format("%15s%5s%15s%5s%15s%5s%10s%5s%2s%5s%9s%5s%2s%5s%2s%30s\n",iLName,"",iFName,"",iAddress,"",iCity,"",iState,"",iZip,"",iPopType,"",iCases,"",iTeam,"",msg);
			data = String.format("%15s", iLName) + String.format("%15s", iFName)+ String.format("%15s", iAddress) + String.format("%10s", iCity) +
	        		String.format("%2s", iState) + String.format("%9s", iZip) + String.format("%2s", iPopType) +
	        		String.format("%2s", iCases)+String.format("%1s", iTeam)+String.format("%20s", msg);
	         pw.println(data);
			
				}
		
private static void good() {
			
			lineCounter++;
		 	   if(lineCounter>LINES_PER_PAGE)
		 		  heading();
				DecimalFormat df = new DecimalFormat("$.00");
		    	String oDeposit = df.format(cDeposit);
		    	DecimalFormat df1 = new DecimalFormat("$.00");
		    	String oTotalDollar = df1.format(cTotalDollar);
		    	
		    	if(cPopType==01){
					
					iPopType = ("Coke             ");
				}
				if(cPopType==02){
					
					iPopType = ("Diet Coke        ");
				}
				if(cPopType==03){
					
					iPopType = ("Mello Yello      ");
				}
				if(cPopType==04){
					
					iPopType = ("Cherry Coke      ");
				}
				if(cPopType==05){
					
					iPopType =	("Diet Cherry Coke ");
				}
				if(cPopType==06){
					
					iPopType = ("Sprite           ");
				}
		    	
				record=(String.format("%-15s", iLName) + String.format("%15s  ", iFName)+ String.format("%10s  ", iCity) +
		        		String.format("%10s  ", iState) + String.format("%9s      ", iZip) + String.format("%16s               ", iPopType) +
		        		String.format("%2s          ", iCases)+String.format("%7s         ", oDeposit)+String.format("%9s", oTotalDollar));
		        PW.println(record);
				}// end of good method
		
		
   static void heading(){
		   SimpleDateFormat ft =  new SimpleDateFormat ("MM/dd/yyyy");//date
		   Date date = new Date();
	  	   pageNo ++; //Increment page counter
	  	   PW.println(ft.format(date)+"                                            Albia Soccer Club Fundraiser                                            page: "+pageNo);
	  	   PW.println("JAVAI05                                                   Khamiss    Division                                                        ");
	  	   PW.println("                                                              Sales Report                                                           ");
	  	   PW.println();
	  	   PW.println("   Last name        First Name       City        state Zip Code      pop Type                       Quantity      Deposit AMT      total sales  ");
	  	   PW.println();
	  	   lineCounter = 1; //initialize to number of lines in heading
	  	   
	     }//end of Heading
		 
  static void headingErr(){
			 SimpleDateFormat ft = 
				      new SimpleDateFormat ("MM/dd/yyyy");
			 Date date = new Date();
		  	   pageNo ++; //Increment page counter
		  	   pw.println(ft.format(date)+"                                          Albia Soccer Club Fundraiser                                            page: "+pageNo);
		  	   pw.println("JAVAI05                                                 Khamiss    Division                                                        ");
		  	   pw.println("                                                            Error Report                                                           ");
		  	   pw.println("                                                                                                                                    ");
		  	   pw.println("ERROR RECORD                                                              ERROR DESCRIPTION                                           ");
		  	   pw.println("\r\n");
		  	   lineCounter = 5; //initialize to number of lines in heading
		  	   
		     }//end of HeadingErr
  static void grandTotal(){
			 heading();
			 PW.println("                                                                        ");
			 
			 PW.println("Grand Totals:");
			 PW.println();
			 PW.println();
			 PW.print("Coke                   "+cCoke+"    ");
			 PW.print("Diet Coke            "+cDCoke+"    ");
			 PW.println("Mello Yell          "+cMelloYell+"    ");
			 PW.println();
			 PW.print("Cherry Coke            "+cCCoke+"    ");
			 PW.print("Diet Cherry Coke       "+cDCCoke+"    ");
			 PW.println("Sprite          "+cSprite+"    ");
		 }
  static void TeamTotal(){
		    DecimalFormat df8 = new DecimalFormat("$.00");
	   	    String oTotalA = df8.format(cTotalA);
	    	DecimalFormat df9 = new DecimalFormat("$.00");
	    	String oTotalB = df9.format(cTotalB);
	   	    DecimalFormat df10 = new DecimalFormat("$.00");
	  	    String oTotalC = df10.format(cTotalC);
	        DecimalFormat df11 = new DecimalFormat("$.00");
	        String oTotalD = df11.format(cTotalD);
	        DecimalFormat df12 = new DecimalFormat("$.00");
	        String oTotalE = df12.format(cTotalE);
	         PW.println();
		     PW.println();
		     PW.println("Team Totals");
		     PW.println("   A           "+oTotalA+"   ");
		     PW.println();
			 PW.println("   B           "+oTotalB+"   ");
			 PW.println();
			 PW.println("   C           "+oTotalC+"   ");
			 PW.println();
			 PW.println("   D           "+oTotalD+"   ");
			 PW.println();
			 PW.println("   E           "+oTotalE+"   ");
			
		 }
  
public static void closing() {
			pw.println("Error Count :  "+ErrorCount);
	  		
			pw.close();
	  		PW.close();
	  		
	  	//end of closing
	}

}
