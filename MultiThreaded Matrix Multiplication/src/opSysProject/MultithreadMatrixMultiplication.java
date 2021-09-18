package opSysProject;

import java.util.Random;
import java.util.Scanner;

import opSysProject.MatrisCarp;

public class MultithreadMatrixMultiplication {
	
	public static void main(String[] args) {
		
		int rA, rB, cA, cB;
		int [][] matrisA;
		int [][] matrisB;
		int [][] sonuc;
		int threadSayisi;
		
		//klavyeden girdi almak için 
		Scanner scan = new Scanner(System.in);
		
		//Matrislerin satýr ve sütun sayýlarýný klavyeden girdi olarak al.
		System.out.print("A Matrisinin Satýr Sayýsý: ");     
        rA = scan.nextInt();
        System.out.print("A Matrisinin Sütun Sayýsý: ");
        cA = scan.nextInt();
        System.out.print("B Matrisinin Satýr Sayýsý: ");     
        rB = scan.nextInt();
        System.out.print("B Matrisinin Sütun Sayýsý: ");
        cB = scan.nextInt();
        System.out.println();

        /*
         * Matris çarpýmýnýn yapýlabilmesi için A matrisinin sütun sayýsý ile 
         * B matrisinin satýr sayýsý birbirine eþit olmalý.
         */
        
        if(cA != rB)
        {
             System.out.println("Bu iki matris çarpýlamaz!");
             System.exit(-1);
        }
        
        System.out.print("Thread sayýsý giriniz: ");
        threadSayisi = scan.nextInt();
        System.out.println();
        
        //matrisler
        matrisA = new int[rA][cA];
        matrisB = new int[rB][cB];
        sonuc = new int[rA][cB];
        
        //A matrisinin random olarak üretilmesi
        for(int i = 0; i < rA; i++) {
	    	for(int j = 0; j < cA; j++ ) {
	    		Random r = new Random();
	    		matrisA[i][j] = r.nextInt(17);
	    	}
	    }
        
        //A matrisini yazdýrmak için
	    System.out.println("A Matrisi: ");
	    System.out.println();
	    MatrisYazdirma(matrisA);
	    System.out.println();
	    
	    //B matrisinin random olarak üretilmesi
        for(int i = 0; i < rB; i++) {
	    	for(int j = 0; j < cB; j++ ) {
	    		Random r = new Random();
	    		matrisB[i][j] = r.nextInt(17);
	    	}
	    }
        
        //B matrisini yazdýrmak için
	    System.out.println("B Matrisi: ");
	    System.out.println();
	    MatrisYazdirma(matrisB);
	    System.out.println();
	    
	    //baþlangýç zamaný
	    long startTime , endTime;
	    
	    //Matrisin satýrlarýný girilen thread sayýsýna bölmek için
	    int step = rA / threadSayisi;
	    int startIndex = 0;
	    int endIndex = step;
	    
	    
	    //klavyeden girdi olarak alýnan thread sayýsý kadar thread üretmek için
	    for(int i = 0; i < threadSayisi; i++) {
	    	startTime = System.currentTimeMillis();
	    	MatrisCarp carp = new MatrisCarp(matrisA, matrisB, sonuc, startIndex, endIndex, i);
	    	Thread thread = new Thread(carp);
            thread.start();
           
            startIndex = endIndex;
            
            if(i == threadSayisi-2) {
            	endIndex = rA;
            }
            else {
            	endIndex += step;
            }
            
            try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            System.out.println("Matris Sonucu: ");
            System.out.println();
            MatrisYazdirma(sonuc);
            
            //sona erme zamaný
            endTime = System.currentTimeMillis();
    	     
    	    //Hesaplamanýn tamamlanma süresi
    	    System.out.println();
    	    System.out.println("Çalýþma süresi :  " + (endTime - startTime) + " milisaniye");
	    }      
	}

	//matrisleri ekrana yazdýrmak için
	private static void MatrisYazdirma(int[][] matris) {
		// TODO Auto-generated method stub
		int satirSayisi = matris.length;
		for(int row = 0; row < satirSayisi; row++) {
			int sutunSayisi = matris[row].length;
			for(int column = 0; column < sutunSayisi; column++) {
				System.out.print(matris[row][column] + "   ");
			}
			System.out.println();
		}
	}

}
