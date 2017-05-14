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
public class Stm3 implements Stm
{
   
    Expression exp;
    Stm st;

    public Stm3(Expression exp,Stm st) {
        this.exp = exp;
        this.st = st;
    }
    
    @Override
    public String getValue() 
    {
        return "while ("+exp.getValue()+")" + st.getValue();//Statement::= "while" "(" Expression ")" Statement 
    }
    
}
