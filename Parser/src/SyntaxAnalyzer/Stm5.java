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
public class Stm5 implements Stm
{
   
    IdentifierFollowers ide;
    Identifier id;

    public Stm5( Identifier id , IdentifierFollowers ide) {
        this.ide = ide;
        this.id = id;
    }
    
    @Override
    public String getValue() 
    {
        return id.getValue() +" "+ide.getValue();//Statement::= Identifier Statement
    }
    
}
