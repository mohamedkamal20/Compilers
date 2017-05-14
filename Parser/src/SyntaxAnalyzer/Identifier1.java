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
public class Identifier1 implements IdentifierFollowers
{
    Expression exp;

    public Identifier1(Expression exp) {
        this.exp = exp;
        
    }
    
    @Override
    public String getValue()//to do (st)*
    {
         return "="+exp.getValue()+";";//Iderntifier-Statements::=  "=" Expression ";" 
    }
    
}
