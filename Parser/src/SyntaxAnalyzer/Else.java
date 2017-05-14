/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SyntaxAnalyzer ;

/**
 *
 * @author FCI-Laps
 */
public class Else 
{
    Stm st;

    public Else(Stm st) {
        this.st = st;
        
    }
    
    
    public String getValue()//to do (st)*
    {
         return "else"+st.getValue();//Else::= "else" Statement | £
    }
    
}
