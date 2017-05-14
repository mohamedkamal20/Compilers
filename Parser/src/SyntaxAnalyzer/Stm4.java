/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SyntaxAnalyzer;

/**
 *
 * @author FCI-Laps
 */
public class Stm4 implements Stm
{
   
    Expression exp;
    

    public Stm4(Expression exp) {
        this.exp = exp;
    }
    
    @Override
    public String getValue() 
    {
        return "System.out.println ("+exp.getValue()+") ;"  ;//Statement::= "System.out.println" "(" Expression ")" ";"  
    }
    
}
