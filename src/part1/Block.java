/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part1;

public class Block {

    boolean Allocated = false;
    int Start_Index=0;
    int End_Index=0;
    int start_of_the_next_extend=0;
    Block(){
        
     Allocated = false;
     Start_Index=0;
     End_Index=0;
     start_of_the_next_extend=0;
    
    }

}
