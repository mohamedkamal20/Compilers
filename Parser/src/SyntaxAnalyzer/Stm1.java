/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SyntaxAnalyzer;

import java.util.ArrayList;

/**
 *
 * @author FCI-Laps
 */
public class Stm1 implements Stm
{
    ArrayList<Stm> st;

    public Stm1(ArrayList<Stm> st) {
        this.st = st;
        
    }
    
    public String printStatements()
    {
    	String result = new String();
		if(st == null)return result;
		for (Stm stm : this.st)
		{
			result += stm.getValue();
		}
		return result;
    }
    
    @Override
    public String getValue()//to do (st)*
    {
         return "{" + printStatements() +"}";//Statement::= "{" ( Statement )* "}" 
    }
    
}
