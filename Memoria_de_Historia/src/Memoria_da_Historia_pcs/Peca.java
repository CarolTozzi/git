/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memoria_da_Historia_pcs;

import java.util.Random;
import javax.swing.Icon;

/**
 *
 * 
 */
public class Peca {

    //Atributos
    private int fato1;
    private int fato2;
    private int resultado;//resultado da operacao
    private Icon imagemFrontalDaCarta;
    private boolean virada;// peca esta virada ou nao

    //Construtor
    public Peca (int resultado,Icon imagemFrontalDaCarta) {   /*algoritimo de criacao dos fato em cima do valor do resultado*/
        this.virada = false;
        this.resultado = resultado;
        this.imagemFrontalDaCarta = imagemFrontalDaCarta;
      

        Random rand = new Random();
        this.fato1 = rand.nextInt(resultado) + 1;

          }

    public void desvirar() {
        virada = ! virada;
    }

    public int getOperando1() {
        return fato1;
    }

    public int getOperando2() {
        return fato2;
    }

    public int getResultado() {
        return resultado;
    }
    

    
    public boolean isVirada() {
        return virada;
    }
}
