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
public class Stm2 implements Stm
{
    Expression exp;
    Stm st;
    Else els;
    public Stm2(Expression exp,Stm st,Else els) {
        this.exp = exp;
        this.st = st;
        this.els = els;
    }
    
    @Override
    public String getValue() {
        return "if ("+exp.getValue()+")"+st.getValue()+els.getValue();//Statement::= "if" "(" Expression ")" Statement Else
    }
    
}
