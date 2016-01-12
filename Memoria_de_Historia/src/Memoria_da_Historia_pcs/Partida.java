/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memoria_da_Historia_pcs;

import java.util.Random;
import java.util.Scanner;
import Memoria_da_Historia_pcs.Peca;


/**
 *
 * @author Alexandre
 */
public class Partida {

    //atributos
    public static int cronometro;
    public static int tempo;
    Tabuleiro Tabuleiro;
    public static int tempoF = 0;
    //construtor
    public Partida(int dificuldade) {
        this.Tabuleiro = new Tabuleiro(dificuldade);

    }

    public Tabuleiro getTabuleiro() {
        return Tabuleiro;
    }

  
    private boolean inicializarPartida() {

        inicializarCronometro(); //Inicializa o cronometro

        if (cronometro == 0) // caso o cronometro chegue a 0 a partida acaba(false)
        {
            return false;
        }

        return true;
    }

    public boolean inicializarCronometro()//inicializa o cronometro ( fazer depois)
    {

        cronometro = tempo;
        try {
            for (int i = cronometro; i > 0; i--) {
                System.out.println(i + " segundos");
                Thread.sleep(1000); // 1 segundo  
            }
            System.out.println("Seu tempo acabou!");
        } catch (InterruptedException e) {

            return true;
        }

        return true;

    }

    

    public boolean jogada(Carta carta1, Carta carta2)// como vou armazenar as pecas selecionadas em peca 1 e peca 2??
    {
        if(Tabuleiro.obterPar(carta1, carta2)==true){
            return true;
        }
        return false;
    }

    public static int verificaTempo()// verifica o tempo que o jogador levou para ganhar
    {
        
        tempoF = tempo - cronometro;
        System.out.println("Tempo gasto:" + tempoF);
        return tempoF;
    }

}
