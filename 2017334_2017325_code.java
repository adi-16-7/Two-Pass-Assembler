package project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class project {
	//C:\Users\User\Documents\samplecode.txt

	public static void main(String[] args) throws IOException {
		//C:\Users\User\Documents\samplecode.txt
		// TODO Auto-generated method stub
		ArrayList<ArrayList<String>> symbol = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> literal = new ArrayList<ArrayList<String>>();
		ArrayList<String> errors = new ArrayList<String>();
		ArrayList<String> sym = new ArrayList<String>();
		ArrayList<String> lit = new ArrayList<String>();
		ArrayList<String> lab1 = new ArrayList<String>();
		ArrayList<String> lab2 = new ArrayList<String>();
		ArrayList<ArrayList<String>> machine = new ArrayList<ArrayList<String>>();
		Scanner scn= new Scanner(System.in);
		BufferedReader in = null;
		ArrayList<String> myList = new ArrayList<String>();
		try {   
			System.out.print("Enter File Location : ");
		    in = new BufferedReader(new FileReader(scn.nextLine()));
		    String str;
		    while ((str = in.readLine()) != null) {
		        myList.add(str);
		    }
		} catch (FileNotFoundException e) {
		    System.out.println("File not Found");
		} 
//		catch (IOException e) {
//		    e.printStackTrace();
//		} 
		finally {
		    if (in != null) {
		        in.close();
		    }
		}
		for(int i=0; i<myList.size(); i++) {
			System.out.println(myList.get(i));
		}
		
		//====================================================================================================================
		
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.printf("%30s%n","Converting File");  
		System.out.println("-----------------------------------------------------------------------------------");
		
		int flag=0;
		for(int i=0; i<myList.size() ; i++) {
			ArrayList<String> first = new ArrayList<String>();
			String str=myList.get(i);
			//System.out.println(str);
			String first1[] = str.split("\\s+");
			for(int j=0; j<first1.length; j++) {
				first.add(first1[j]);
			}
			System.out.print(i+"            ");
			for(int j=0; j<first.size(); j++) {
				System.out.print(first.get(j)+"            ");
			}
			System.out.println();
			
			//====================================================================================================================
			
			
			if(first.contains("CLA") && flag==0) {
				if(first.size()==1) {
					ArrayList<String> mno= new ArrayList<String>();
					String y=Integer.toString(machine.size(),2);
					mno.add(String.format("%8s", y).replace(' ', '0'));
					mno.add("0000");
					machine.add(mno);
				}
				
				else if((first.indexOf("CLA")==1) && (first.size()==2)) {
					if(lab1.contains(first.get(0))) {
						errors.add("In Line "+i+ " similar label is used before, change the label, ignoring this line.... ");
					}
					else if(lab2.contains(first.get(0))) {
						lab2.remove(first.get(0));
						lab1.add(first.get(0));
						for(int j=0; j<symbol.size(); j++) {
							if(symbol.get(j).contains(first.get(0))) {
								String x=Integer.toString(machine.size(),2);
								symbol.get(j).add( String.format("%8s",x).replace(' ', '0'));
							}
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0000");
							machine.add(mno);
						}
					}
					else {
						lab1.add(first.get(0));
						ArrayList<String> xyz= new ArrayList<String>();
						xyz.add(first.get(0));
						xyz.add("LABEL");
						xyz.add("null");
						String x=Integer.toString(machine.size(),2);
						xyz.add( String.format("%8s",x).replace(' ', '0'));
						symbol.add(xyz);
						ArrayList<String> mno=new ArrayList<String>();
						String y=Integer.toString(machine.size(),2);
						mno.add(String.format("%8s", y).replace(' ', '0'));
						mno.add("0000");
						machine.add(mno);
					}
																				
				}
				else if((first.indexOf("CLA")==0)&&(first.size()==2)  ) {
					errors.add("In Line "+i+ " no operand is needed, ignoring this line.... ");
				}
				
				else if((first.indexOf("CLA")==1) &&(first.size()==3) ) {
					errors.add("In Line "+i+ " no operand is needed, but two operands are given, ignoring this line.... ");
				}
				else {
					errors.add("In Line "+i+ " irrelevant values are given....");
				}
			}
			
			//====================================================================================================================
			
			else if(first.contains("LAC") && flag==0) {
				if (first.size()==1) {
					errors.add("In Line "+i+ " operand is missing....");
				}
				
				else if ((first.size()==2) && (first.indexOf("LAC")==1)) {
					errors.add("In Line "+i+ " label is given but the operand is missing....");
				}
				else if((first.size()==2) && (first.indexOf("LAC")==0)) {
					int count=0;
					for(char c : first.get(1).toCharArray()) {
					    if(Character.isDigit(c)) {
					        count=count+1;
					    }
					}
					if(count>0) {
						if(lit.contains(first.get(1))) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0001");
							mno.add(first.get(1));
							machine.add(mno);
						}
						else {
							lit.add(first.get(1));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(1));
							literal.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0001");
							mno.add(first.get(1));
							machine.add(mno);
						}
					}
					else if(count==0 && (lab1.contains(first.get(1)) || lab2.contains(first.get(1)))){
						errors.add("In Line "+i+ " variable or literal is required but the label is given....");
					}
					else {
						if (sym.contains(first.get(1))) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0001");
							mno.add(first.get(1));
							machine.add(mno);
						}
						else {
							sym.add(first.get(1));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(1));
							abc.add("Variable");
							symbol.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0001");
							mno.add(first.get(1));
							machine.add(mno);
						}
					}
				}
				
				else if((first.size()==3) && (first.indexOf("LAC")==1)) {
					if(sym.contains(first.get(0))) {
						errors.add("In Line "+i+ " label is required but variable is given....");
					}
					else if(lit.contains(first.get(0))) {
						errors.add("In Line "+i+ " label is required but literal is given....");
					}
					else if(lab1.contains(first.get(2)) || lab2.contains(first.get(2))) {
						errors.add("In Line "+i+ " variable is required but the label is given....");
					}
					else {
						if(lab1.contains(first.get(0))) {
							errors.add("In Line "+i+ " similar label is used before, change the label, ignoring this line.... ");
						}
						else if(lab2.contains(first.get(0))) {
							lab2.remove(first.get(0));
							lab1.add(first.get(0));
							for(int j=0; j<symbol.size(); j++) {
								if(symbol.get(j).contains(first.get(0))) {
									String x=Integer.toString(machine.size(),2);
									symbol.get(j).add( String.format("%8s",x).replace(' ', '0'));
								}
							}
						}
						else {
							lab1.add(first.get(0));
							ArrayList<String> xyz= new ArrayList<String>();
							xyz.add(first.get(0));
							xyz.add("LABEL");
							xyz.add("null");
							String x=Integer.toString(machine.size(),2);
							xyz.add( String.format("%8s",x).replace(' ', '0'));
							symbol.add(xyz);
						}
						
						int count=0;
						int gya=0;
						for(char c : first.get(2).toCharArray()) {
						    if(Character.isDigit(c)) {
						        count=count+1;
						    }
						}
						if(count>0) {
							if(lit.contains(first.get(2))) {
								gya=gya+1;
								ArrayList<String> mno=new ArrayList<String>();
								String y=Integer.toString(machine.size(),2);
								mno.add(String.format("%8s", y).replace(' ', '0'));
								mno.add("0001");
								mno.add(first.get(2));
								machine.add(mno);
							}
							else {
								gya = gya+1;
								lit.add(first.get(2));
								ArrayList<String> abc= new ArrayList<String>();
								abc.add(first.get(2));
								literal.add(abc);
								ArrayList<String> mno=new ArrayList<String>();
								String y=Integer.toString(machine.size(),2);
								mno.add(String.format("%8s", y).replace(' ', '0'));
								mno.add("0001");
								mno.add(first.get(2));
								machine.add(mno);
							}
						}
						if (sym.contains(first.get(2)) && gya==0) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0001");
							mno.add(first.get(2));
							machine.add(mno);
						}
						if(!sym.contains(first.get(2)) && gya ==0){
							sym.add(first.get(2));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(2));
							abc.add("Variable");
							symbol.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0001");
							mno.add(first.get(2));
							machine.add(mno);
						}
					}
				}
				else if((first.size()==3) && (first.indexOf("SAC")!=1)) {
					errors.add("In Line "+i+ " labels and operands are not correctly given.... ");
				}
				else {
					errors.add("In Line "+i+ " irrelevant values are passed....");
				}
			}
			
			//====================================================================================================================
			
			else if(first.contains("SAC") && flag==0) {
				if (first.size()==1) {
					errors.add("In Line "+i+ " operand is missing....");
				}
				
				else if ((first.size()==2) && (first.indexOf("SAC")==1)) {
					errors.add("In Line "+i+ " label is given but the operand is missing....");
				}
				else if((first.size()==2) && (first.indexOf("SAC")==0)) {
					int count=0;
					for(char c : first.get(1).toCharArray()) {
					    if(Character.isDigit(c)) {
					        count=count+1;
					    }
					}
					if(count>0) {
						if(lit.contains(first.get(1))) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0010");
							mno.add(first.get(1));
							machine.add(mno);
						}
						else {
							lit.add(first.get(1));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(1));
							literal.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0010");
							mno.add(first.get(1));
							machine.add(mno);
						}
					}
					else if(count==0 && (lab1.contains(first.get(1)) || lab2.contains(first.get(1)))){
						errors.add("In Line "+i+ " variable or literal is required but the label is given....");
					}
					else {
						if (sym.contains(first.get(1))) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0010");
							mno.add(first.get(1));
							machine.add(mno);
						}
						else {
							sym.add(first.get(1));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(1));
							abc.add("Variable");
							symbol.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0010");
							mno.add(first.get(1));
							machine.add(mno);
						}
					}
				}
				
				else if((first.size()==3) && (first.indexOf("SAC")==1)) {
					if(sym.contains(first.get(0))) {
						errors.add("In Line "+i+ " label is required but variable is given....");
					}
					else if(lit.contains(first.get(0))) {
						errors.add("In Line "+i+ " label is required but literal is given....");
					}
					else if(lab1.contains(first.get(2)) || lab2.contains(first.get(2))) {
						errors.add("In Line "+i+ " variable is required but the label is given....");
					}
					else {
						if(lab1.contains(first.get(0))) {
							errors.add("In Line "+i+ " similar label is used before, change the label, ignoring this line.... ");
						}
						else if(lab2.contains(first.get(0))) {
							lab2.remove(first.get(0));
							lab1.add(first.get(0));
							for(int j=0; j<symbol.size(); j++) {
								if(symbol.get(j).contains(first.get(0))) {
									String x=Integer.toString(machine.size(),2);
									symbol.get(j).add( String.format("%8s",x).replace(' ', '0'));
								}
							}
						}
						else {
							lab1.add(first.get(0));
							ArrayList<String> xyz= new ArrayList<String>();
							xyz.add(first.get(0));
							xyz.add("LABEL");
							xyz.add("null");
							String x=Integer.toString(machine.size(),2);
							xyz.add( String.format("%8s",x).replace(' ', '0'));
							symbol.add(xyz);
						}
						
						int count=0;
						int gya=0;
						for(char c : first.get(2).toCharArray()) {
						    if(Character.isDigit(c)) {
						        count=count+1;
						    }
						}
						if(count>0) {
							if(lit.contains(first.get(2))) {
								gya=gya+1;
								ArrayList<String> mno=new ArrayList<String>();
								String y=Integer.toString(machine.size(),2);
								mno.add(String.format("%8s", y).replace(' ', '0'));
								mno.add("0010");
								mno.add(first.get(2));
								machine.add(mno);
							}
							else {
								gya = gya+1;
								lit.add(first.get(2));
								ArrayList<String> abc= new ArrayList<String>();
								abc.add(first.get(2));
								literal.add(abc);
								ArrayList<String> mno=new ArrayList<String>();
								String y=Integer.toString(machine.size(),2);
								mno.add(String.format("%8s", y).replace(' ', '0'));
								mno.add("0010");
								mno.add(first.get(2));
								machine.add(mno);
							}
						}
						if (sym.contains(first.get(2))&& gya==0) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0010");
							mno.add(first.get(2));
							machine.add(mno);
						}
						if(!sym.contains(first.get(2)) && gya ==0){
							sym.add(first.get(2));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(2));
							abc.add("Variable");
							symbol.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0010");
							mno.add(first.get(2));
							machine.add(mno);
						}
					}
				}
				else if((first.size()==3) && (first.indexOf("SAC")!=1)) {
					errors.add("In Line "+i+ " labels and operands are not correctly given.... ");
				}
				else {
					errors.add("In Line "+i+ " irrelevant values are passed....");
				}
			}
			
			//====================================================================================================================
			
			else if(first.contains("ADD") && flag==0) {
				if (first.size()==1) {
					errors.add("In Line "+i+ " operand is missing....");
				}
				
				else if ((first.size()==2) && (first.indexOf("ADD")==1)) {
					errors.add("In Line "+i+ " label is given but the operand is missing....");
				}
				else if((first.size()==2) && (first.indexOf("ADD")==0)) {
					int count=0;
					for(char c : first.get(1).toCharArray()) {
					    if(Character.isDigit(c)) {
					        count=count+1;
					    }
					}
					if(count>0) {
						if(lit.contains(first.get(1))) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0011");
							mno.add(first.get(1));
							machine.add(mno);
						}
						else {
							lit.add(first.get(1));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(1));
							literal.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0011");
							mno.add(first.get(1));
							machine.add(mno);
						}
					}
					else if(count==0 && (lab1.contains(first.get(1)) || lab2.contains(first.get(1)))){
						errors.add("In Line "+i+ " variable or literal is required but the label is given....");
					}
					else {
						if (sym.contains(first.get(1))) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0011");
							mno.add(first.get(1));
							machine.add(mno);
						}
						else {
							sym.add(first.get(1));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(1));
							abc.add("Variable");
							symbol.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0011");
							mno.add(first.get(1));
							machine.add(mno);
						}
					}
				}
				
				else if((first.size()==3) && (first.indexOf("ADD")==1)) {
					if(sym.contains(first.get(0))) {
						errors.add("In Line "+i+ " label is required but variable is given....");
					}
					else if(lit.contains(first.get(0))) {
						errors.add("In Line "+i+ " label is required but literal is given....");
					}
					else if(lab1.contains(first.get(2)) || lab2.contains(first.get(2))) {
						errors.add("In Line "+i+ " variable is required but the label is given....");
					}
					else {
						if(lab1.contains(first.get(0))) {
							errors.add("In Line "+i+ " similar label is used before, change the label, ignoring this line.... ");
						}
						else if(lab2.contains(first.get(0))) {
							lab2.remove(first.get(0));
							lab1.add(first.get(0));
							for(int j=0; j<symbol.size(); j++) {
								if(symbol.get(j).contains(first.get(0))) {
									String x=Integer.toString(machine.size(),2);
									symbol.get(j).add( String.format("%8s",x).replace(' ', '0'));
								}
							}
						}
						else {
							lab1.add(first.get(0));
							ArrayList<String> xyz= new ArrayList<String>();
							xyz.add(first.get(0));
							xyz.add("LABEL");
							xyz.add("null");
							String x=Integer.toString(machine.size(),2);
							xyz.add( String.format("%8s",x).replace(' ', '0'));
							symbol.add(xyz);
						}
						
						int count=0; 
						int gya=0;
						for(char c : first.get(2).toCharArray()) {
						    if(Character.isDigit(c)) {
						        count=count+1;
						    }
						}
						if(count>0) {
							if(lit.contains(first.get(2))) {
								gya = gya+1;
								ArrayList<String> mno=new ArrayList<String>();
								String y=Integer.toString(machine.size(),2);
								mno.add(String.format("%8s", y).replace(' ', '0'));
								mno.add("0011");
								mno.add(first.get(2));
								machine.add(mno);
							}
							else { 
								 gya = gya +1;
								lit.add(first.get(2));
								ArrayList<String> abc= new ArrayList<String>();
								abc.add(first.get(2));
								literal.add(abc);
								ArrayList<String> mno=new ArrayList<String>();
								String y=Integer.toString(machine.size(),2);
								mno.add(String.format("%8s", y).replace(' ', '0'));
								mno.add("0011");
								mno.add(first.get(2));
								machine.add(mno);
							}
						}
						if (sym.contains(first.get(2)) && gya ==0) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0011");
							mno.add(first.get(2));
							machine.add(mno);
						}
						if(!sym.contains(first.get(2)) && gya==0){
							sym.add(first.get(2));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(2));
							abc.add("Variable");
							symbol.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0011");
							mno.add(first.get(2));
							machine.add(mno);
						}
					}
				}
				else if((first.size()==3) && (first.indexOf("ADD")!=1)) {
					errors.add("In Line "+i+ " labels and operands are not correctly given.... ");
				}
				else {
					errors.add("In Line "+i+ " irrelevant values are passed....");
				}
			}
			
			//====================================================================================================================
			
			else if(first.contains("SUB") && flag==0) {
				if (first.size()==1) {
					errors.add("In Line "+i+ " operand is missing....");
				}
				
				else if ((first.size()==2) && (first.indexOf("SUB")==1)) {
					errors.add("In Line "+i+ " label is given but the operand is missing....");
				}
				else if((first.size()==2) && (first.indexOf("SUB")==0)) {
					int count=0;
					for(char c : first.get(1).toCharArray()) {
					    if(Character.isDigit(c)) {
					        count=count+1;
					    }
					}
					if(count>0) {
						if(lit.contains(first.get(1))) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0100");
							mno.add(first.get(1));
							machine.add(mno);
						}
						else {
							lit.add(first.get(1));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(1));
							literal.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0100");
							mno.add(first.get(1));
							machine.add(mno);
						}
					}
					else if(count==0 && (lab1.contains(first.get(1)) || lab2.contains(first.get(1)))){
						errors.add("In Line "+i+ " variable or literal is required but the label is given....");
					}
					else {
						if (sym.contains(first.get(1))) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0100");
							mno.add(first.get(1));
							machine.add(mno);
						}
						else {
							sym.add(first.get(1));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(1));
							abc.add("Variable");
							symbol.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0100");
							mno.add(first.get(1));
							machine.add(mno);
						}
					}
				}
				
				else if((first.size()==3) && (first.indexOf("SUB")==1)) {
					if(sym.contains(first.get(0))) {
						errors.add("In Line "+i+ " label is required but variable is given....");
					}
					else if(lit.contains(first.get(0))) {
						errors.add("In Line "+i+ " label is required but literal is given....");
					}
					else if(lab1.contains(first.get(2)) || lab2.contains(first.get(2))) {
						errors.add("In Line "+i+ " variable is required but the label is given....");
					}
					else {
						if(lab1.contains(first.get(0))) {
							errors.add("In Line "+i+ " similar label is used before, change the label, ignoring this line.... ");
						}
						else if(lab2.contains(first.get(0))) {
							lab2.remove(first.get(0));
							lab1.add(first.get(0));
							for(int j=0; j<symbol.size(); j++) {
								if(symbol.get(j).contains(first.get(0))) {
									String x=Integer.toString(machine.size(),2);
									symbol.get(j).add( String.format("%8s",x).replace(' ', '0'));
								}
							}
						}
						else {
							lab1.add(first.get(0));
							ArrayList<String> xyz= new ArrayList<String>();
							xyz.add(first.get(0));
							xyz.add("LABEL");
							xyz.add("null");
							String x=Integer.toString(machine.size(),2);
							xyz.add( String.format("%8s",x).replace(' ', '0'));
							symbol.add(xyz);
						}
						
						int count=0; int gya=0;
						for(char c : first.get(2).toCharArray()) {
						    if(Character.isDigit(c)) {
						        count=count+1;
						    }
						}
						if(count>0) {
							if(lit.contains(first.get(2))) {
								gya= gya+1;
								ArrayList<String> mno=new ArrayList<String>();
								String y=Integer.toString(machine.size(),2);
								mno.add(String.format("%8s", y).replace(' ', '0'));
								mno.add("0100");
								mno.add(first.get(2));
								machine.add(mno);
							}
							else {
								gya = gya+1;
								lit.add(first.get(2));
								ArrayList<String> abc= new ArrayList<String>();
								abc.add(first.get(2));
								literal.add(abc);
								ArrayList<String> mno=new ArrayList<String>();
								String y=Integer.toString(machine.size(),2);
								mno.add(String.format("%8s", y).replace(' ', '0'));
								mno.add("0100");
								mno.add(first.get(2));
								machine.add(mno);
							}
						}
						if (sym.contains(first.get(2))&& gya==0) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0100");
							mno.add(first.get(2));
							machine.add(mno);
						}
						if(!sym.contains(first.get(2)) && gya==0){
							sym.add(first.get(2));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(2));
							abc.add("Variable");
							symbol.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0100");
							mno.add(first.get(2));
							machine.add(mno);
						}
					}
				}
				else if((first.size()==3) && (first.indexOf("SUB")!=1)) {
					errors.add("In Line "+i+ " labels and operands are not correctly given.... ");
				}
				else {
					errors.add("In Line "+i+ " irrelevant values are passed....");
				}
			}
			
			//====================================================================================================================
			
			else if(first.contains("BRZ") && flag==0) {
				if (first.size()==1) {
					errors.add("In Line "+i+ " label is missing....");
				}
				
				else if ((first.size()==2) && (first.indexOf("BRZ")==1)) {
					errors.add("In Line "+i+ " label is given but the operand is missing....");
				}
				else if((first.size()==2) && (first.indexOf("BRZ")==0)) {
					if(sym.contains(first.get(1))) {
						errors.add("In Line "+i+ " label is required but the variable is given....");
					}
					else if(lit.contains(first.get(1))) {
						errors.add("In Line "+i+ " label is required but the literal is given....");
					}
					else if(lab2.contains(first.get(1))) {
						ArrayList<String> mno=new ArrayList<String>();
						String y=Integer.toString(machine.size(),2);
						mno.add(String.format("%8s", y).replace(' ', '0'));
						mno.add("0101");
						mno.add(first.get(1));
						machine.add(mno);
					}
					else if(lab1.contains(first.get(1))) {
						ArrayList<String> mno=new ArrayList<String>();
						String y=Integer.toString(machine.size(),2);
						mno.add(String.format("%8s", y).replace(' ', '0'));
						mno.add("0101");
						mno.add(first.get(1));
						machine.add(mno);
					}
					else {
						lab2.add(first.get(1));
						ArrayList<String> xyz= new ArrayList<String>();
						xyz.add(first.get(1));
						xyz.add("LABEL");
						xyz.add("null");
						symbol.add(xyz);
						ArrayList<String> mno=new ArrayList<String>();
						String y=Integer.toString(machine.size(),2);
						mno.add(String.format("%8s", y).replace(' ', '0'));
						mno.add("0101");
						mno.add(first.get(1));
						machine.add(mno);
					}
				}
				
				else if((first.size()==3) && (first.indexOf("BRZ")==1)) {
					if((sym.contains(first.get(0))) || (lit.contains(first.get(0)))) {
						errors.add("In Line "+i+ " label is required but symbol is given....");
					}
					else if(sym.contains(first.get(2)) || lit.contains(first.get(2))) {
						errors.add("In Line "+i+ " label is required but symbol is given....");
					}
					else {
						int count=0;
						if(lab1.contains(first.get(0))) {
							count= count+1;
							errors.add("In Line "+i+ " similar label is used before, change the label, ignoring this line.... ");
						}
						else if(lab2.contains(first.get(0))) {
							lab2.remove(first.get(0));
							lab1.add(first.get(0));
							for(int j=0; j<symbol.size(); j++) {
								if(symbol.get(j).contains(first.get(0))) {
									String x=Integer.toString(machine.size(),2);
									symbol.get(j).add( String.format("%8s",x).replace(' ', '0'));
								}
							}
						}
						else {
							lab1.add(first.get(0));
							ArrayList<String> xyz= new ArrayList<String>();
							xyz.add(first.get(0));
							xyz.add("LABEL");
							xyz.add("null");
							String x=Integer.toString(machine.size(),2);
							xyz.add( String.format("%8s",x).replace(' ', '0'));
							symbol.add(xyz);
						}
						
						if (lab2.contains(first.get(2)) && count ==0) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0101");
							mno.add(first.get(2));
							machine.add(mno);
						}
						if(lab1.contains(first.get(2)) && count==0){
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0101");
							mno.add(first.get(2));
							machine.add(mno);
						}
						if((!lab1.contains(first.get(2)) || !lab2.contains(first.get(2))) && count==0) {
							lab2.add(first.get(2));
							ArrayList<String> xyz= new ArrayList<String>();
							xyz.add(first.get(2));
							xyz.add("LABEL");
							xyz.add("null");
							symbol.add(xyz);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0101");
							mno.add(first.get(2));
							machine.add(mno);
						}
					}
				}
				else if((first.size()==3) && (first.indexOf("BRZ")!=1)) {
					errors.add("In Line "+i+ " labels are not correctly given.... ");
				}
				else {
					errors.add("In Line "+i+ " irrelevant values are passed....");
				}
			}
			
			//====================================================================================================================
			
			else if(first.contains("BRN") && flag==0) {
				if (first.size()==1) {
					errors.add("In Line "+i+ " label is missing....");
				}
				
				else if ((first.size()==2) && (first.indexOf("BRN")==1)) {
					errors.add("In Line "+i+ " label is given but the operand is missing....");
				}
				else if((first.size()==2) && (first.indexOf("BRN")==0)) {
					if(sym.contains(first.get(1)) || lit.contains(first.get(1))) {
						errors.add("In Line "+i+ " label is required but the symbol is given....");
					}
					else if(lab2.contains(first.get(1))) {
						ArrayList<String> mno=new ArrayList<String>();
						String y=Integer.toString(machine.size(),2);
						mno.add(String.format("%8s", y).replace(' ', '0'));
						mno.add("0110");
						mno.add(first.get(1));
						machine.add(mno);
					}
					else if(lab1.contains(first.get(1))) {
						ArrayList<String> mno=new ArrayList<String>();
						String y=Integer.toString(machine.size(),2);
						mno.add(String.format("%8s", y).replace(' ', '0'));
						mno.add("0110");
						mno.add(first.get(1));
						machine.add(mno);
					}
					else {
						lab2.add(first.get(1));
						ArrayList<String> xyz= new ArrayList<String>();
						xyz.add(first.get(1));
						xyz.add("LABEL");
						xyz.add("null");
						symbol.add(xyz);
						ArrayList<String> mno=new ArrayList<String>();
						String y=Integer.toString(machine.size(),2);
						mno.add(String.format("%8s", y).replace(' ', '0'));
						mno.add("0110");
						mno.add(first.get(1));
						machine.add(mno);
					}
				}
				
				else if((first.size()==3) && (first.indexOf("BRN")==1)) {
					if((sym.contains(first.get(0))) || (lit.contains(first.get(0)))) {
						errors.add("In Line "+i+ " label is required but symbol is given....");
					}
					else if(sym.contains(first.get(2)) || lit.contains(first.get(2))) {
						errors.add("In Line "+i+ " label is required but symbol is given....");
					}
					else {
						int count=0;
						if(lab1.contains(first.get(0))) {
							count=count+1;
							errors.add("In Line "+i+ " similar label is used before, change the label, ignoring this line.... ");
						}
						else if(lab2.contains(first.get(0))) {
							lab2.remove(first.get(0));
							lab1.add(first.get(0));
							for(int j=0; j<symbol.size(); j++) {
								if(symbol.get(j).contains(first.get(0))) {
									String x=Integer.toString(machine.size(),2);
									symbol.get(j).add( String.format("%8s",x).replace(' ', '0'));
								}
							}
						}
						else {
							lab1.add(first.get(0));
							ArrayList<String> xyz= new ArrayList<String>();
							xyz.add(first.get(0));
							xyz.add("LABEL");
							xyz.add("null");
							String x=Integer.toString(machine.size(),2);
							xyz.add( String.format("%8s",x).replace(' ', '0'));
							symbol.add(xyz);
						}
						
						if (lab2.contains(first.get(2)) && count==0) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0110");
							mno.add(first.get(2));
							machine.add(mno);
						}
						if(lab1.contains(first.get(2)) && count==0){
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0110");
							mno.add(first.get(2));
							machine.add(mno);
						}
						if((!lab1.contains(first.get(2)) || !lab2.contains(first.get(2))) && count==0) {
							lab2.add(first.get(2));
							ArrayList<String> xyz= new ArrayList<String>();
							xyz.add(first.get(2));
							xyz.add("LABEL");
							xyz.add("null");
							symbol.add(xyz);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0110");
							mno.add(first.get(2));
							machine.add(mno);
						}
					}
				}
				else if((first.size()==3) && (first.indexOf("BRN")!=1)) {
					errors.add("In Line "+i+ " labels are not correctly given.... ");
				}
				else {
					errors.add("In Line "+i+ " irrelevant values are passed....");
				}
			}
			
			//====================================================================================================================
			
			else if(first.contains("BRP") && flag==0) {
				if (first.size()==1) {
					errors.add("In Line "+i+ " label is missing....");
				}
				
				else if ((first.size()==2) && (first.indexOf("BRP")==1)) {
					errors.add("In Line "+i+ " label is given but the operand is missing....");
				}
				else if((first.size()==2) && (first.indexOf("BRP")==0)) {
					if(sym.contains(first.get(1)) || lit.contains(first.get(1))) {
						errors.add("In Line "+i+ " label is required but the symbol is given....");
					}
					else if(lab2.contains(first.get(1))) {
						ArrayList<String> mno=new ArrayList<String>();
						String y=Integer.toString(machine.size(),2);
						mno.add(String.format("%8s", y).replace(' ', '0'));
						mno.add("0111");
						mno.add(first.get(1));
						machine.add(mno);
					}
					else if(lab1.contains(first.get(1))) {
						ArrayList<String> mno=new ArrayList<String>();
						String y=Integer.toString(machine.size(),2);
						mno.add(String.format("%8s", y).replace(' ', '0'));
						mno.add("0111");
						mno.add(first.get(1));
						machine.add(mno);
					}
					else {
						lab2.add(first.get(1));
						ArrayList<String> xyz= new ArrayList<String>();
						xyz.add(first.get(1));
						xyz.add("LABEL");
						xyz.add("null");
						symbol.add(xyz);
						ArrayList<String> mno=new ArrayList<String>();
						String y=Integer.toString(machine.size(),2);
						mno.add(String.format("%8s", y).replace(' ', '0'));
						mno.add("0111");
						mno.add(first.get(1));
						machine.add(mno);
					}
				}
				
				else if((first.size()==3) && (first.indexOf("BRP")==1)) {
					if((sym.contains(first.get(0))) || (lit.contains(first.get(0)))) {
						errors.add("In Line "+i+ " label is required but symbol is given....");
					}
					else if(sym.contains(first.get(2)) || lit.contains(first.get(2))) {
						errors.add("In Line "+i+ " label is required but symbol is given....");
					}
					else {
						int count=0;
						if(lab1.contains(first.get(0))) {
							count=count+1;
							errors.add("In Line "+i+ " similar label is used before, change the label, ignoring this line.... ");
						}
						else if(lab2.contains(first.get(0))) {
							lab2.remove(first.get(0));
							lab1.add(first.get(0));
							for(int j=0; j<symbol.size(); j++) {
								if(symbol.get(j).contains(first.get(0))) {
									String x=Integer.toString(machine.size(),2);
									symbol.get(j).add( String.format("%8s",x).replace(' ', '0'));
								}
							}
						}
						else {
							lab1.add(first.get(0));
							ArrayList<String> xyz= new ArrayList<String>();
							xyz.add(first.get(0));
							xyz.add("LABEL");
							xyz.add("null");
							String x=Integer.toString(machine.size(),2);
							xyz.add( String.format("%8s",x).replace(' ', '0'));
							symbol.add(xyz);
						}
						
						if (lab2.contains(first.get(2)) && count==0) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0111");
							mno.add(first.get(2));
							machine.add(mno);
						}
						if(lab1.contains(first.get(2)) && count==0){
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0111");
							mno.add(first.get(2));
							machine.add(mno);
						}
						if((!lab1.contains(first.get(2)) || !lab2.contains(first.get(2))) && count==0) {
							lab2.add(first.get(2));
							ArrayList<String> xyz= new ArrayList<String>();
							xyz.add(first.get(2));
							xyz.add("LABEL");
							xyz.add("null");
							symbol.add(xyz);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("0111");
							mno.add(first.get(2));
							machine.add(mno);
						}
					}
				}
				else if((first.size()==3) && (first.indexOf("BRP")!=1)) {
					errors.add("In Line "+i+ " labels are not correctly given.... ");
				}
				else {
					errors.add("In Line "+i+ " irrelevant values are passed....");
				}
			}
			
			//====================================================================================================================
			
			else if(first.contains("INP") && flag==0) {
				if (first.size()==1) {
					errors.add("In Line "+i+ " operand is missing....");
				}
				
				else if ((first.size()==2) && (first.indexOf("INP")==1)) {
					errors.add("In Line "+i+ " label is given but the operand is missing....");
				}
				else if((first.size()==2) && (first.indexOf("INP")==0)) {
					int count=0;
					for(char c : first.get(1).toCharArray()) {
					    if(Character.isDigit(c)) {
					        count=count+1;
					    }
					}
					if(count>0) {
						if(lit.contains(first.get(1))) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1000");
							mno.add(first.get(1));
							machine.add(mno);
						}
						else {
							lit.add(first.get(1));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(1));
							literal.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1000");
							mno.add(first.get(1));
							machine.add(mno);
						}
					}
					else if(count==0 && (lab1.contains(first.get(1)) || lab2.contains(first.get(1)))){
						errors.add("In Line "+i+ " variable or literal is required but the label is given....");
					}
					else {
						if (sym.contains(first.get(1))) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1000");
							mno.add(first.get(1));
							machine.add(mno);
						}
						else {
							sym.add(first.get(1));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(1));
							abc.add("Variable");
							symbol.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1000");
							mno.add(first.get(1));
							machine.add(mno);
						}
					}
				}
				
				else if((first.size()==3) && (first.indexOf("INP")==1)) {
					if(sym.contains(first.get(0))) {
						errors.add("In Line "+i+ " label is required but variable is given....");
					}
					else if(lit.contains(first.get(0))) {
						errors.add("In Line "+i+ " label is required but literal is given....");
					}
					else if(lab1.contains(first.get(2)) || lab2.contains(first.get(2))) {
						errors.add("In Line "+i+ " variable is required but the label is given....");
					}
					else {
						if(lab1.contains(first.get(0))) {
							errors.add("In Line "+i+ " similar label is used before, change the label, ignoring this line.... ");
						}
						else if(lab2.contains(first.get(0))) {
							lab2.remove(first.get(0));
							lab1.add(first.get(0));
							for(int j=0; j<symbol.size(); j++) {
								if(symbol.get(j).contains(first.get(0))) {
									String x=Integer.toString(machine.size(),2);
									symbol.get(j).add( String.format("%8s",x).replace(' ', '0'));
								}
							}
						}
						else {
							lab1.add(first.get(0));
							ArrayList<String> xyz= new ArrayList<String>();
							xyz.add(first.get(0));
							xyz.add("LABEL");
							xyz.add("null");
							String x=Integer.toString(machine.size(),2);
							xyz.add( String.format("%8s",x).replace(' ', '0'));
							symbol.add(xyz);
						}
						
						int count=0; 
						int gya=0;
						for(char c : first.get(2).toCharArray()) {
						    if(Character.isDigit(c)) {
						        count=count+1;
						    }
						}
						if(count>0) {
							if(lit.contains(first.get(2))) {
								gya=gya+1;
								ArrayList<String> mno=new ArrayList<String>();
								String y=Integer.toString(machine.size(),2);
								mno.add(String.format("%8s", y).replace(' ', '0'));
								mno.add("1000");
								mno.add(first.get(2));
								machine.add(mno);
							}
							else {
								gya = gya+1;
								lit.add(first.get(2));
								ArrayList<String> abc= new ArrayList<String>();
								abc.add(first.get(2));
								literal.add(abc);
								ArrayList<String> mno=new ArrayList<String>();
								String y=Integer.toString(machine.size(),2);
								mno.add(String.format("%8s", y).replace(' ', '0'));
								mno.add("1000");
								mno.add(first.get(2));
								machine.add(mno);
							}
						}
						if (sym.contains(first.get(2)) && gya==0) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1000");
							mno.add(first.get(2));
							machine.add(mno);
						}
						if(!sym.contains(first.get(2)) && gya==0){
							sym.add(first.get(2));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(2));
							abc.add("Variable");
							symbol.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1000");
							mno.add(first.get(2));
							machine.add(mno);
						}
					}
				}
				else if((first.size()==3) && (first.indexOf("INP")!=1)) {
					errors.add("In Line "+i+ " labels and operands are not correctly given.... ");
				}
				else {
					errors.add("In Line "+i+ " irrelevant values are passed....");
				}
			}
			
			//====================================================================================================================
			
			else if(first.contains("DSP") && flag==0) {
				if (first.size()==1) {
					errors.add("In Line "+i+ " operand is missing....");
				}
				
				else if ((first.size()==2) && (first.indexOf("DSP")==1)) {
					errors.add("In Line "+i+ " label is given but the operand is missing....");
				}
				else if((first.size()==2) && (first.indexOf("DSP")==0)) {
					int count=0;
					for(char c : first.get(1).toCharArray()) {
					    if(Character.isDigit(c)) {
					        count=count+1;
					    }
					}
					if(count>0) {
						if(lit.contains(first.get(1))) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1001");
							mno.add(first.get(1));
							machine.add(mno);
						}
						else {
							lit.add(first.get(1));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(1));
							literal.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1001");
							mno.add(first.get(1));
							machine.add(mno);
						}
					}
					else if(count==0 && (lab1.contains(first.get(1)) || lab2.contains(first.get(1)))){
						errors.add("In Line "+i+ " variable or literal is required but the label is given....");
					}
					else {
						if (sym.contains(first.get(1))) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1001");
							mno.add(first.get(1));
							machine.add(mno);
						}
						else {
							sym.add(first.get(1));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(1));
							abc.add("Variable");
							symbol.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1001");
							mno.add(first.get(1));
							machine.add(mno);
						}
					}
				}
				
				else if((first.size()==3) && (first.indexOf("DSP")==1)) {
					if(sym.contains(first.get(0))) {
						errors.add("In Line "+i+ " label is required but variable is given....");
					}
					else if(lit.contains(first.get(0))) {
						errors.add("In Line "+i+ " label is required but literal is given....");
					}
					else if(lab1.contains(first.get(2)) || lab2.contains(first.get(2))) {
						errors.add("In Line "+i+ " variable is required but the label is given....");
					}
					else {
						if(lab1.contains(first.get(0))) {
							errors.add("In Line "+i+ " similar label is used before, change the label, ignoring this line.... ");
						}
						else if(lab2.contains(first.get(0))) {
							lab2.remove(first.get(0));
							lab1.add(first.get(0));
							for(int j=0; j<symbol.size(); j++) {
								if(symbol.get(j).contains(first.get(0))) {
									String x=Integer.toString(machine.size(),2);
									symbol.get(j).add( String.format("%8s",x).replace(' ', '0'));
								}
							}
						}
						else {
							lab1.add(first.get(0));
							ArrayList<String> xyz= new ArrayList<String>();
							xyz.add(first.get(0));
							xyz.add("LABEL");
							xyz.add("null");
							String x=Integer.toString(machine.size(),2);
							xyz.add( String.format("%8s",x).replace(' ', '0'));
							symbol.add(xyz);
						}
						
						int count=0;
						int gya=0;
						for(char c : first.get(2).toCharArray()) {
						    if(Character.isDigit(c)) {
						        count=count+1;
						    }
						}
						if(count>0) {
							if(lit.contains(first.get(2))) {
								gya= gya +1;
								ArrayList<String> mno=new ArrayList<String>();
								String y=Integer.toString(machine.size(),2);
								mno.add(String.format("%8s", y).replace(' ', '0'));
								mno.add("1001");
								mno.add(first.get(2));
								machine.add(mno);
							}
							else {
								gya= gya+1;
								lit.add(first.get(2));
								ArrayList<String> abc= new ArrayList<String>();
								abc.add(first.get(2));
								literal.add(abc);
								ArrayList<String> mno=new ArrayList<String>();
								String y=Integer.toString(machine.size(),2);
								mno.add(String.format("%8s", y).replace(' ', '0'));
								mno.add("1001");
								mno.add(first.get(2));
								machine.add(mno);
							}
						}
						if (sym.contains(first.get(2)) && gya==0) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1001");
							mno.add(first.get(2));
							machine.add(mno);
						}
						if(!sym.contains(first.get(2)) && gya==0){
							sym.add(first.get(2));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(2));
							abc.add("Variable");
							symbol.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1001");
							mno.add(first.get(2));
							machine.add(mno);
						}
					}
				}
				else if((first.size()==3) && (first.indexOf("DSP")!=1)) {
					errors.add("In Line "+i+ " labels and operands are not correctly given.... ");
				}
				else {
					errors.add("In Line "+i+ " irrelevant values are passed....");
				}
			}
			
			//====================================================================================================================
			
			else if(first.contains("MUL") && flag==0) {
				if (first.size()==1) {
					errors.add("In Line "+i+ " operand is missing....");
				}
				
				else if ((first.size()==2) && (first.indexOf("MUL")==1)) {
					errors.add("In Line "+i+ " label is given but the operand is missing....");
				}
				else if((first.size()==2) && (first.indexOf("MUL")==0)) {
					int count=0;
					for(char c : first.get(1).toCharArray()) {
					    if(Character.isDigit(c)) {
					        count=count+1;
					    }
					}
					if(count>0) {
						if(lit.contains(first.get(1))) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1010");
							mno.add(first.get(1));
							machine.add(mno);
						}
						else {
							lit.add(first.get(1));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(1));
							literal.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1010");
							mno.add(first.get(1));
							machine.add(mno);
						}
					}
					else if(count==0 && (lab1.contains(first.get(1)) || lab2.contains(first.get(1)))){
						errors.add("In Line "+i+ " variable or literal is required but the label is given....");
					}
					else {
						if (sym.contains(first.get(1))) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1010");
							mno.add(first.get(1));
							machine.add(mno);
						}
						else {
							sym.add(first.get(1));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(1));
							abc.add("Variable");
							symbol.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1010");
							mno.add(first.get(1));
							machine.add(mno);
						}
					}
				}
				
				else if((first.size()==3) && (first.indexOf("MUL")==1)) {
					if(sym.contains(first.get(0))) {
						errors.add("In Line "+i+ " label is required but variable is given....");
					}
					else if(lit.contains(first.get(0))) {
						errors.add("In Line "+i+ " label is required but literal is given....");
					}
					else if(lab1.contains(first.get(2)) || lab2.contains(first.get(2))) {
						errors.add("In Line "+i+ " variable is required but the label is given....");
					}
					else {
						if(lab1.contains(first.get(0))) {
							errors.add("In Line "+i+ " similar label is used before, change the label, ignoring this line.... ");
						}
						else if(lab2.contains(first.get(0))) {
							lab2.remove(first.get(0));
							lab1.add(first.get(0));
							for(int j=0; j<symbol.size(); j++) {
								if(symbol.get(j).contains(first.get(0))) {
									String x=Integer.toString(machine.size(),2);
									symbol.get(j).add( String.format("%8s",x).replace(' ', '0'));
								}
							}
						}
						else {
							lab1.add(first.get(0));
							ArrayList<String> xyz= new ArrayList<String>();
							xyz.add(first.get(0));
							xyz.add("LABEL");
							xyz.add("null");
							String x=Integer.toString(machine.size(),2);
							xyz.add( String.format("%8s",x).replace(' ', '0'));
							symbol.add(xyz);
						}
						
						int count=0;
						int gya=0;
						for(char c : first.get(2).toCharArray()) {
						    if(Character.isDigit(c)) {
						        count=count+1;
						    }
						}
						if(count>0) {
							if(lit.contains(first.get(2))) {
								gya=gya+1;
								ArrayList<String> mno=new ArrayList<String>();
								String y=Integer.toString(machine.size(),2);
								mno.add(String.format("%8s", y).replace(' ', '0'));
								mno.add("1010");
								mno.add(first.get(2));
								machine.add(mno);
							}
							else {
								gya=gya+1;
								lit.add(first.get(2));
								ArrayList<String> abc= new ArrayList<String>();
								abc.add(first.get(2));
								literal.add(abc);
								ArrayList<String> mno=new ArrayList<String>();
								String y=Integer.toString(machine.size(),2);
								mno.add(String.format("%8s", y).replace(' ', '0'));
								mno.add("1010");
								mno.add(first.get(2));
								machine.add(mno);
							}
						}
						if (sym.contains(first.get(2)) && gya==0) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1010");
							mno.add(first.get(2));
							machine.add(mno);
						}
						if(!sym.contains(first.get(2)) && gya==0){
							sym.add(first.get(2));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(2));
							abc.add("Variable");
							symbol.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1010");
							mno.add(first.get(2));
							machine.add(mno);
						}
					}
				}
				else if((first.size()==3) && (first.indexOf("MUL")!=1)) {
					errors.add("In Line "+i+ " labels and operands are not correctly given.... ");
				}
				else {
					errors.add("In Line "+i+ " irrelevant values are passed....");
				}
			}
			
			//====================================================================================================================
			
			else if(first.contains("DIV") && flag==0) {
				if (first.size()==1) {
					errors.add("In Line "+i+ " operand is missing....");
				}
				
				else if ((first.size()==2) && (first.indexOf("MUL")==1)) {
					errors.add("In Line "+i+ " label is given but the operand is missing....");
				}
				else if((first.size()==2) && (first.indexOf("DIV")==0)) {
					int count=0;
					for(char c : first.get(1).toCharArray()) {
					    if(Character.isDigit(c)) {
					        count=count+1;
					    }
					}
					if(count>0) {
						if(lit.contains(first.get(1))) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1011");
							mno.add(first.get(1));
							machine.add(mno);
						}
						else {
							lit.add(first.get(1));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(1));
							literal.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1011");
							mno.add(first.get(1));
							machine.add(mno);
						}
					}
					else if(count==0 && (lab1.contains(first.get(1)) || lab2.contains(first.get(1)))){
						errors.add("In Line "+i+ " variable or literal is required but the label is given....");
					}
					else {
						if (sym.contains(first.get(1))) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1011");
							mno.add(first.get(1));
							machine.add(mno);
						}
						else {
							sym.add(first.get(1));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(1));
							abc.add("Variable");
							symbol.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1011");
							mno.add(first.get(1));
							machine.add(mno);
						}
					}
				}
				
				else if((first.size()==3) && (first.indexOf("DIV")==1)) {
					if(sym.contains(first.get(0)) || (lit.contains(first.get(0)))) {
						errors.add("In Line "+i+ " label is required but variable is given....");
					}
					else if(lab1.contains(first.get(2)) || lab2.contains(first.get(2))) {
						errors.add("In Line "+i+ " variable is required but the label is given....");
					}
					else {
						if(lab1.contains(first.get(0))) {
							errors.add("In Line "+i+ " similar label is used before, change the label, ignoring this line.... ");
						}
						else if(lab2.contains(first.get(0))) {
							lab2.remove(first.get(0));
							lab1.add(first.get(0));
							for(int j=0; j<symbol.size(); j++) {
								if(symbol.get(j).contains(first.get(0))) {
									String x=Integer.toString(machine.size(),2);
									symbol.get(j).add( String.format("%8s",x).replace(' ', '0'));
								}
							}
						}
						else {
							lab1.add(first.get(0));
							ArrayList<String> xyz= new ArrayList<String>();
							xyz.add(first.get(0));
							xyz.add("LABEL");
							xyz.add("null");
							String x=Integer.toString(machine.size(),2);
							xyz.add( String.format("%8s",x).replace(' ', '0'));
							symbol.add(xyz);
						}
						
						int count=0; 
						int gya=0;
						for(char c : first.get(2).toCharArray()) {
						    if(Character.isDigit(c)) {
						        count=count+1;
						    }
						}
						if(count>0) {
							if(lit.contains(first.get(2))) {
								gya=gya+1;
								ArrayList<String> mno=new ArrayList<String>();
								String y=Integer.toString(machine.size(),2);
								mno.add(String.format("%8s", y).replace(' ', '0'));
								mno.add("1011");
								mno.add(first.get(2));
								machine.add(mno);
							}
							else {
								gya=gya+1;
								lit.add(first.get(2));
								ArrayList<String> abc= new ArrayList<String>();
								abc.add(first.get(2));
								literal.add(abc);
								ArrayList<String> mno=new ArrayList<String>();
								String y=Integer.toString(machine.size(),2);
								mno.add(String.format("%8s", y).replace(' ', '0'));
								mno.add("1011");
								mno.add(first.get(2));
								machine.add(mno);
							}
						}
						if (sym.contains(first.get(2)) && gya==0) {
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1011");
							mno.add(first.get(2));
							machine.add(mno);
						}
						if(!sym.contains(first.get(2)) && gya==0){
							sym.add(first.get(2));
							ArrayList<String> abc= new ArrayList<String>();
							abc.add(first.get(2));
							abc.add("Variable");
							symbol.add(abc);
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1011");
							mno.add(first.get(2));
							machine.add(mno);
						}
					}
				}
				else if((first.size()==3) && (first.indexOf("DIV")!=1)) {
					errors.add("In Line "+i+ " labels and operands are not correctly given.... ");
				}
				else {
					errors.add("In Line "+i+ " irrelevant values are passed....");
				}
			}
			
			//====================================================================================================================
			
			else if((first.contains("STP") || first.contains("END")) && flag==0) {
				flag=flag+1;
				if(first.size()==1) {
					ArrayList<String> mno=new ArrayList<String>();
					String y=Integer.toString(machine.size(),2);
					mno.add(String.format("%8s", y).replace(' ', '0'));
					mno.add("1100");
					machine.add(mno);
				}
				else if(((first.indexOf("STP")==1) || first.indexOf("END")==1) && (first.size()==2)) {
					if(lab1.contains(first.get(0))) {
						errors.add("In Line "+i+ " similar label is used before, change the label, ignoring this line.... ");
					}
					else if(lab2.contains(first.get(0))) {
						lab2.remove(first.get(0));
						lab1.add(first.get(0));
						for(int j=0; j<symbol.size(); j++) {
							if(symbol.get(j).contains(first.get(0))) {
								String x=Integer.toString(machine.size(),2);
								symbol.get(j).add( String.format("%8s",x).replace(' ', '0'));
							}
							ArrayList<String> mno=new ArrayList<String>();
							String y=Integer.toString(machine.size(),2);
							mno.add(String.format("%8s", y).replace(' ', '0'));
							mno.add("1100");
							machine.add(mno);
						}
					}
					else {
						lab1.add(first.get(0));
						ArrayList<String> xyz= new ArrayList<String>();
						xyz.add(first.get(0));
						xyz.add("LABEL");
						xyz.add("null");
						String x=Integer.toString(machine.size(),2);
						xyz.add( String.format("%8s",x).replace(' ', '0'));
						symbol.add(xyz);
						ArrayList<String> mno=new ArrayList<String>();
						String y=Integer.toString(machine.size(),2);
						mno.add(String.format("%8s", y).replace(' ', '0'));
						mno.add("1100");
						machine.add(mno);
					}
																				
				}
				else if(((first.indexOf("STP")==0) || first.indexOf("END")==0) && (first.size()==2)  ) {
					errors.add("In Line "+i+ " no operand is needed, ignoring this line.... ");
				}
				
				else if(((first.indexOf("STP")==1) || first.indexOf("END")==1) &&(first.size()==3) ) {
					errors.add("In Line "+i+ " no operand is needed, ignoring this line.... ");
				}
				else {
					errors.add("In Line "+i+ " irrelevant values are given....");
				}
			}
				
			//====================================================================================================================
			
			else if(first.contains("DS") || first.contains("DC") || first.contains("DW")) {
				if (first.size()==1) {
					errors.add("In Line "+i+ " symbols and values are missing....");
				}
				else if ((first.size()==2) && ((first.indexOf("DC")==1) || (first.indexOf("DS")==1) || (first.indexOf("DW")==1))) {
					errors.add("In Line "+i+ " symbol is given but the value is missing....");
				}
				else if((first.size()==2) && ((first.indexOf("DC")==0) || (first.indexOf("DS")==0) || (first.indexOf("DW")==0))) {
					errors.add("In Line "+i+ " value is given but the symbol is missing....");
				}
				else if((first.size()==3) && ((first.indexOf("DC")==1) || (first.indexOf("DS")==1) || (first.indexOf("DW")==1))) {
					int gya=0;
					if(first.get(2).matches("^[0-9]*$")){	
						gya=gya+1;
					}
					if(gya==1 && sym.contains(first.get(0))) {
						for(int j=0; j<symbol.size(); j++) {
							if(symbol.get(j).contains(first.get(0))) {
//								String x=Integer.toString(machine.size(),2);
								symbol.get(j).add( first.get(2));
							}
						}
					}
					else if(gya==0 && sym.contains(first.get(0)) ) {
						for(int j=0; j<symbol.size(); j++) {
							if(symbol.get(j).contains(first.get(0))) {
//								String x=Integer.toString(machine.size(),2);
								symbol.get(j).add("XXXX");
							}
						}
						errors.add("In Line "+i+ " non-int value is declared for a variable....");
					}
					else if(lit.contains(first.get(0))) {
						errors.add("In Line "+i+ " literal is passed in place of a variable....");
					}
					else if(lab1.contains(first.get(0)) || lab1.contains(first.get(0))) {
						errors.add("In Line "+i+ " label is passed in place of a variablee....");
					}
					else {
						errors.add("In Line "+i+ " irrelevant values are given....");
					}	
				}
				}	
		}
			
//		System.out.print("Machine Language Code : ");
//		System.out.println(machine);
		
		int kitna=machine.size();
		for(int i=0; i<symbol.size(); i++) {
			if(symbol.get(i).size()==2) {
				symbol.get(i).add("Not declared");
			}
			if(symbol.get(i).size()==3) {
				String y=Integer.toString(kitna,2);
				symbol.get(i).add(String.format("%8s", y).replace(' ', '0'));
				kitna=kitna+1;
			}
		}
		for(int i=0; i<literal.size(); i++) {
			String y=Integer.toString(kitna,2);
			literal.get(i).add(String.format("%8s", y).replace(' ', '0'));
			kitna=kitna+1;
		}
		for(int i=0; i<machine.size(); i++) {
			if(machine.get(i).size()>2) {
				String val=machine.get(i).get(2);
				for(int j=0; j<symbol.size(); j++) {
					if(symbol.get(j).contains(val)) {
						String add=symbol.get(j).get(3);
						machine.get(i).set(2, add);
						break;
					}
				}
				for(int j=0; j<literal.size(); j++) {
					if(literal.get(j).contains(val)) {
						String add=literal.get(j).get(1);
						machine.get(i).set(2, add);
						break;
					}
				}
			}
			else {
				machine.get(i).add(" ");
			}
		}
			
//	System.out.println("-----------------------------------------------------------------------------------");
//	System.out.print("Literal : ");
//	System.out.println(literal);
//	System.out.print("Symbol : ");
//	System.out.println(symbol);
//	System.out.print("Errors : ");
//	System.out.println(errors);
//	System.out.print("All Symbols : ");
//	System.out.println(sym);
//	System.out.print("All Literals : ");
//	System.out.println(lit);
//	System.out.print("Machine Language Code : ");
//	System.out.println(machine);
	
	System.out.println("-----------------------------------------------------------------------------------");
	System.out.println();
	System.out.printf("%30s%n","Symbol Table");  
	System.out.println();
	System.out.println("-----------------------------------------------------------------------------------");	
	System.out.printf("%-16s%-16s%-18s%-20s%n", "Symbol", "|  "+"Type", "|  "+"Value", "|  "+"Address");
	System.out.println("-----------------------------------------------------------------------------------");
	for(int i=0; i<symbol.size(); i++) {
		System.out.printf("%-16s%-16s%-18s%-20s%n", symbol.get(i).get(0), "|  "+symbol.get(i).get(1), "|  "+symbol.get(i).get(2), "|  "+symbol.get(i).get(3));
		if(symbol.get(i).get(2).equals("Not declared")) {
			errors.add("The Value of "+symbol.get(i).get(2)+" is not declared within the assembly code....");
		}
		else if(symbol.get(i).get(2).equals("XXXX")) {
			errors.add("The Value of "+symbol.get(i).get(2)+" is not declared in the form of an interger within the assembly code....");
		}
		//System.out.println();
	}
	System.out.println("-----------------------------------------------------------------------------------");	
	
	
	System.out.println();
	
	
	System.out.println("-----------------------------------------------------------------------------------");
	System.out.println();
	System.out.printf("%30s%n","Literal Table");  
	System.out.println();
	System.out.println("-----------------------------------------------------------------------------------");	
	System.out.printf("%-18s%-20s%n", "Literal", "|  "+"Address");
	System.out.println("-----------------------------------------------------------------------------------");
	for(int i=0; i<literal.size(); i++) {
		System.out.printf("%-18s%-20s%n", literal.get(i).get(0), "|  "+literal.get(i).get(1));
		//System.out.println();
	}
	System.out.println("-----------------------------------------------------------------------------------");	
	
	
	System.out.println();
	
	
	System.out.println("-----------------------------------------------------------------------------------");
	System.out.println();
	System.out.printf("%30s%n","Errors"); 
	System.out.println();
	System.out.println("-----------------------------------------------------------------------------------");	
	for(int i=0; i<errors.size();i++) {
		System.out.println(errors.get(i));
	}
	System.out.println("-----------------------------------------------------------------------------------");
	
	
	System.out.println("-----------------------------------------------------------------------------------");
	System.out.println();
	System.out.printf("%30s%n","Machine Language Code");  
	System.out.println();
	System.out.println("-----------------------------------------------------------------------------------");	
	System.out.printf("%-20s%-20s%-20s%n", "Address", "|  "+"Opcode", "| "+ "Operand");
	System.out.println("-----------------------------------------------------------------------------------");
	for(int i=0; i<machine.size(); i++) {
		if(machine.get(i).size()==3) {
			System.out.printf("%-20s%-20s%-20s%n", machine.get(i).get(0), "|  "+machine.get(i).get(1), "| "+machine.get(i).get(2));
			//System.out.println();
		}
	}
	System.out.println("-----------------------------------------------------------------------------------");	
	}

}
