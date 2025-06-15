package main;/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class main
	
{
	public static void main (String[] args) throws java.lang.Exception
	{
		System.out.println("Система расчёта штрафов в Германии");
		
		int carSpeed = 78;
		boolean isTown=false;
		
		int fineFor1to10 =30;
		int fineFor11to15 = 50;
		int fineFor16to20 = 70;
		int fineFor21to25 = 115;
		int fineFor26to30 =180;
		int fineFor31to40 = 260;
		int fineFor41to50 = 400;
		int fineFor51to60 = 560;
		int fineFor61to70 = 700;
		int fineFor71andMore = 800;
		
		int townSpeed = 50;
		int countrySpeed=90;

		String currency=" евро";

		int overSpeed;

		if(isTown==true){
		overSpeed = carSpeed - townSpeed;
		}else{
			overSpeed=carSpeed-countrySpeed;
		}
		
		if(overSpeed < 1) {
			System.out.println("Скорость не превышена или превышена незначительно");
		}
		else if(overSpeed >= 1 && overSpeed <= 10) {
			System.out.println("Штраф: " + fineFor1to10 + currency);
		}
		else if(overSpeed >= 11 && overSpeed <= 15) {
			System.out.println("Штраф: " + fineFor11to15 + currency);
		}
		else if(overSpeed >= 16 && overSpeed <= 20) {
			System.out.println("Штраф: " + fineFor16to20 + currency);
		}
		else if(overSpeed >= 21 && overSpeed <= 25) {
			System.out.println("Штраф: " + fineFor21to25 + currency);
		}
		else if(overSpeed >= 26 && overSpeed <= 30) {
			System.out.println("Штраф: " + fineFor26to30 + currency
					+" и изъятие прав на 1 месяц");
		}
		else if(overSpeed >= 31 && overSpeed <= 40) {
			System.out.println("Штраф: " + fineFor31to40 + currency
					+" и изъятие прав на 1 месяц");
		}
		else if(overSpeed >= 41 && overSpeed <= 50) {
			System.out.println("Штраф: " + fineFor41to50 + currency
					+" и изъятие прав на 1 месяц");
		}
		else if(overSpeed >= 51 && overSpeed <= 60) {
			System.out.println("Штраф: " + fineFor51to60 + currency
					+" и изъятие прав на 2 месяц");
		}
		else if(overSpeed >= 61 && overSpeed <= 70) {
			System.out.println("Штраф: " + fineFor61to70 + currency
					+" и изъятие прав на 3 месяц");
		}
		else if(overSpeed >= 71) {
			System.out.println("Штраф: " + fineFor71andMore + currency
					+" и изъятие прав на 3 месяц");
		}

	}
}