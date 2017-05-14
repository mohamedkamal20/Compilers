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
public class Identifier2 implements IdentifierFollowers
{
    Expression exp;
    Expression exp2;

    public Identifier2(Expression exp,Expression exp2) {
        this.exp = exp;
        this.exp2 = exp2;
    }
    
    @Override
    public String getValue()//to do (st)*
    {
         return "["+exp.getValue()+"] ="+exp2.getValue()+";";//Iderntifier-Statements::= "[" Expression "]" "=" Expression ";"
    }
    
}
