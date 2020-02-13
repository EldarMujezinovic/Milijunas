package com.omareldar.milijunas;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.Scanner;

public class Pitanja {
	

	private static int ind;
	

	static ArrayList<String> pitanja = new ArrayList<>();
	static ArrayList<String> postavljenaPitanja = new ArrayList<>();
	static Random rand = new Random();

    public static void loading() throws FileNotFoundException {
    	
		File file = new File ("Pitanja.txt");
		Scanner read = new Scanner(file);

		while (read.hasNextLine()) 
			pitanja.add(read.nextLine());
			
		

		read.close();

	}
    
    public static void ucitajPitanje() {
        
    	int rb =rand.nextInt(pitanja.size());
    		if(!postavljenaPitanja.contains(pitanja.get(rb))){
    			
		    	postavljenaPitanja.add(pitanja.get(rb));
		    	setIndex(rb);
		    	
		    	System.out.println(pitanja.get(rb));
		    	}else ucitajPitanje();
    }
    
   
    
    
    
    public static void setIndex(int x) {
    	ind = x;
    }
    public static int getIndex() {
    	return ind;
    }
}

