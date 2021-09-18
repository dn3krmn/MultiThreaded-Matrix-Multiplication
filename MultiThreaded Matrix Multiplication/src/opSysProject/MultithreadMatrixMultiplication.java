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
		
		//klavyeden girdi almak i�in 
		Scanner scan = new Scanner(System.in);
		
		//Matrislerin sat�r ve s�tun say�lar�n� klavyeden girdi olarak al.
		System.out.print("A Matrisinin Sat�r Say�s�: ");     
        rA = scan.nextInt();
        System.out.print("A Matrisinin S�tun Say�s�: ");
        cA = scan.nextInt();
        System.out.print("B Matrisinin Sat�r Say�s�: ");     
        rB = scan.nextInt();
        System.out.print("B Matrisinin S�tun Say�s�: ");
        cB = scan.nextInt();
        System.out.println();

        /*
         * Matris �arp�m�n�n yap�labilmesi i�in A matrisinin s�tun say�s� ile 
         * B matrisinin sat�r say�s� birbirine e�it olmal�.
         */
        
        if(cA != rB)
        {
             System.out.println("Bu iki matris �arp�lamaz!");
             System.exit(-1);
        }
        
        System.out.print("Thread say�s� giriniz: ");
        threadSayisi = scan.nextInt();
        System.out.println();
        
        //matrisler
        matrisA = new int[rA][cA];
        matrisB = new int[rB][cB];
        sonuc = new int[rA][cB];
        
        //A matrisinin random olarak �retilmesi
        for(int i = 0; i < rA; i++) {
	    	for(int j = 0; j < cA; j++ ) {
	    		Random r = new Random();
	    		matrisA[i][j] = r.nextInt(17);
	    	}
	    }
        
        //A matrisini yazd�rmak i�in
	    System.out.println("A Matrisi: ");
	    System.out.println();
	    MatrisYazdirma(matrisA);
	    System.out.println();
	    
	    //B matrisinin random olarak �retilmesi
        for(int i = 0; i < rB; i++) {
	    	for(int j = 0; j < cB; j++ ) {
	    		Random r = new Random();
	    		matrisB[i][j] = r.nextInt(17);
	    	}
	    }
        
        //B matrisini yazd�rmak i�in
	    System.out.println("B Matrisi: ");
	    System.out.println();
	    MatrisYazdirma(matrisB);
	    System.out.println();
	    
	    //ba�lang�� zaman�
	    long startTime , endTime;
	    
	    //Matrisin sat�rlar�n� girilen thread say�s�na b�lmek i�in
	    int step = rA / threadSayisi;
	    int startIndex = 0;
	    int endIndex = step;
	    
	    
	    //klavyeden girdi olarak al�nan thread say�s� kadar thread �retmek i�in
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
            
            //sona erme zaman�
            endTime = System.currentTimeMillis();
    	     
    	    //Hesaplaman�n tamamlanma s�resi
    	    System.out.println();
    	    System.out.println("�al��ma s�resi :  " + (endTime - startTime) + " milisaniye");
	    }      
	}

	//matrisleri ekrana yazd�rmak i�in
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
