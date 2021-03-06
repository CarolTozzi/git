/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memoria_da_Historia_pcs;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import Memoria_da_Historia_pcs.Carta;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.GridLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

/**
 *
 * @author Debora
 */
public class Tabuleiro {

    //atributos

    public static final int COLUNAFACIL = 4;
    public static final int LINHAFACIL = 5 ;
    
    public static final int COLUNADIF = 6;
    public static final int LINHADIF = 7; 
            
    public static int LINHA;
    public static int COLUNA;
    public Carta[][] tabuleiro;
    Map<Integer, Carta> pares;
    public Frame frame; 
  
   
    
    //vetor com todas as cartas do jogo
	ArrayList<Carta> cartas = new ArrayList();
        public Carta carta [] = new Carta [42];

	//Última carta virada (sem par). Exerce papel auxiliar
	private Carta ultimaCartaVirada;	
	//conta quantas vezes o jogador errou um par
	private int erros;
	//conta quantos pares foram formados. Exerce papel auxiliar
	private int paresFormados;	
	//quando 'true' impede que as sejam viradas ("bloqueia" o método acao)
	private boolean lock;

public Tabuleiro(int dificuldade){ //recebe parametros?
    super();

    if (dificuldade == 1){
        this.LINHA = LINHAFACIL;
        this.COLUNA = COLUNAFACIL;
        tabuleiro = new Carta[LINHA][COLUNA];// cria matriz de pecas 
        pares = new HashMap<>();
    }else{ 
        this.LINHA = LINHADIF;
        this.COLUNA = COLUNADIF;
        tabuleiro = new Carta[LINHA][COLUNA];// cria matriz de pecas 
        pares = new HashMap<>();
    }
        

		
		//****CONFIGURAÇÕES DO TABULEIRO****		
		//cria um vetor que irá conter as instâncias de "Carta" 
			
		//cria os objetos do tipo carta usando imagens do diretório do jogo e armazena no vetor "cartas"
		carta[0]= new Carta (0,this,"Descobrimento do Brasil" );
		carta[1]= new Carta (0,this,"22 de Abril de 1500" );
		carta[2]= new Carta (1,this,"Execução de Tiradentes" );
		carta[3]= new Carta (1,this,"21 de Abril de 1792" );
		carta[4]= new Carta (2,this,"Dia do Fico" );
		carta[5]= new Carta (2,this,"9 de Janeiro de  1822" );
		carta[6]= new Carta (3,this,"Independência do Brasil" );
		carta[7]= new Carta (3,this,"7 de Setembro de 1822" );
		carta[8]= new Carta (4,this,"Lei Áurea" );
		carta[9]= new Carta (4,this,"13 de Maio de 1888" );
		carta[10]= new Carta (5,this,"Proclamação da República" );
		carta[11]= new Carta (5,this,"15 de Novembro de 1889" );
		carta[12]= new Carta (6,this,"Dia da Bandeira Nacional" );
		carta[13]= new Carta (6,this,"19 de Novembro de 1889" );
		carta[14]= new Carta (7,this,"Pacto de Varsovia" );
		carta[15]= new Carta (7,this,"Após 2ª Guerra Mundial" );
		carta[16]= new Carta (8,this,"Final da Era Vargas" );
		carta[17]= new Carta (8,this,"1945" );
		carta[18]= new Carta (9,this,"Tríplice Aliança" );
		carta[19]= new Carta (9,this,"Brasil, Argentina e Uruguai" );
		carta[20]= new Carta (10,this,"Guerra do Vietnam" );
		carta[21]= new Carta (10,this,"Sudeste asiático" );
		carta[22]= new Carta (11,this,"Nazismo" );
		carta[23]= new Carta (11,this,"Extrema direita" );
		carta[24]= new Carta (12,this,"Início 2ª guerra" );
		carta[25]= new Carta (12,this,"Invasao da Polonia" );
		carta[26]= new Carta (13,this,"Pioneiro Revolucao industrial" );
		carta[27]= new Carta (13,this,"Inglaterra" );
		carta[28]= new Carta (14,this,"Lider da Guerra dos Farrapos" );
		carta[29]= new Carta (14,this,"Bento Gonçalves" );
		carta[30]= new Carta (15,this,"Guerra do Paraguai" );
		carta[31]= new Carta (15,this,"Paraguai x Triplice Alianca" );
		carta[32]= new Carta (16,this,"Triplices Entente e Aliança" );
		carta[33]= new Carta (16,this,"1ª guerra mundial" );
		carta[34]= new Carta (17,this,"Imperatriz do Brasil" );
		carta[35]= new Carta (17,this,"Carlota Joaquina" );
		carta[36]= new Carta (18,this,"Principe Regente do Brasil" );
		carta[37]= new Carta (18,this,"Pedro I" );
		carta[38]= new Carta (19,this,"Feudalismo" );
		carta[39]= new Carta (19,this,"Escambo" );
		carta[40]= new Carta (20,this,"Montesquieu e Voltaire" );
		carta[41]= new Carta (20,this,"Iluministas" );

                if(dificuldade==1){
                    for(int i =0; i<= ((carta.length/2)-2);i++){
                        cartas.add(carta[i]);
                        pares.put(i, carta[i]);
                    }
                }else{
                    for(int i =0; i< carta.length;i++){
                        cartas.add(carta[i]);
                        pares.put(i, carta[i]);
                    }
                }
               
                
		            
		//coloca as variáveis auxiliares em seus estados inicias para que o jogo possa iniciar
		ultimaCartaVirada = null;
		erros = 0;
		paresFormados = 0;
		lock = false;
                this.distribuirPecas(cartas,dificuldade); //toda vez que uma novo tabuleiro for criado as pecas sao distribuidas
    }

      /* public void reset(){
		//este "for" coloca todas as cartas viradas para baixo
		for(int i = 0; i < carta.length; i++){
			if(carta[i].isViradaParaCima()){
				carta[i].mudarPosicao();
			}			
		}
		
		//readiciona as cartas ao tabuleiro na nova ordem
		for(int i = 0; i < carta.length; i++){
			cartas.add(carta[i]);
		}
		//coloca as variáveis auxiliares em seus estados inicias
		ultimaCartaVirada = null;		
		erros = 0;
		paresFormados = 0;
		lock = false;
		//esses dois métodos atualizam a interface gráfica e as novas posições das cartas.
		//revalidate();
		//repaint();
                
	} */       

    private void distribuirPecas(List<Carta> Cartas, int dificuldade) //embaralha as pecas no tabuleiro ( jogar para o tabuleiro)
    {
        int cont = 0;
        //embaralha o vetor de cartas
	Collections.shuffle(cartas);
        if (dificuldade == 1){      
            for (int i = 0; i < LINHAFACIL; i++) {
                for (int j = 0; j < COLUNAFACIL; j++) {
                    tabuleiro[i][j] = Cartas.get(cont);//armazena a peca na posicÃ£o do tabuleiro
                    cont++;
                }
            }
        }else if (dificuldade == 2){      
            for (int i = 0; i < LINHADIF; i++) {
                for (int j = 0; j < COLUNADIF; j++) {
                    tabuleiro[i][j] = Cartas.get(cont);//armazena a peca na posicÃ£o do tabuleiro
                    cont++;
                }
            }
        }
    }
    
    public boolean obterPar(Carta carta1, Carta carta2) //Roda o tabuleiro a procura da segunda peca virada( diferente da usada como parametro)   
    {
        if(carta1.id == carta2.id){
            return true;
        }else{
            return false;
        }
        
    }

    public Carta getCarta(int i, int j) {
        return tabuleiro[i][j];
    }
    
    
   /* public void acao(final Carta peca){
		//se a carta estiver virada para baixo e o método "acao" não estiver bloqueado
		if(!peca.isViradaParaCima() && !lock){//inicio do if
			//Se "carta" for a primeira escolhida para tentar formar um par... 
			if(ultimaCartaVirada == null){//inicio do if
				//...vira-a para cima e armazena a sua referência em "ultimaCartaVirada"
				peca.mudarPosicao();				
				ultimaCartaVirada = peca;
			}else{//inicio do else
				//...senão, então a primeira carta já foi escolhida e "carta" é a segunda. 
				//Se a segunda carta for igual a primeira....  
				if(peca.equals(ultimaCartaVirada)){//inicio do if
					//ambas formam um par e são mantidas viradas para cima,
					//"ultimaCartaVirada" para a ser "null" (assim uma nova tentativa de par pode ser feita)
					ultimaCartaVirada = null;
					//vira a carta para cima				
					peca.mudarPosicao();	
					//incrementa "paresFormados"
					paresFormados++;
					//Se "paresFormados" for igual a 6, então todos os pares ja foram formados... 
					if(paresFormados == 6){//inicio do if
						//fim do jogo, exibe mensagem com os erros
//						JOptionPane.showMessageDialog( 
//								"O jogo acabou! Quantidade de erros: " + erros,
//								"Fim de jogo",
//								JOptionPane.INFORMATION_MESSAGE);
                                                JOptionPane.showMessageDialog(frame,"O jogo acabou!");
						reset();//reinicia o jogo
					}//fim do if
				}else{//inicio do else
					//...senão a primeira e a segunda carta não formam um par, já que são diferentes
					//Tarefa "t1": virar a carta para cima para que o jogador veja e "bloquear" o método "acao"
					TimerTask t1 = new TimerTask(){
						@Override
						public void run() {
							peca.mudarPosicao();
							lock = true;
						}						
					};
					//Tarefa "t2": colocar ambas as cartas viradas para baixo, desbloquear "acao", incrementar "erros"...
					//...e alterar o valor de "ultimaCartaVirada" para "null".
					TimerTask t2 = new TimerTask(){
						@Override
						public void run() {
							ultimaCartaVirada.mudarPosicao();
							peca.mudarPosicao();
							ultimaCartaVirada = null;
							erros++;
							lock = false;
						}						
					};
					//Timer é um "agendador" de tarefas
					Timer timer = new Timer();
					//Agenda as tarefas "t1" e "t2":
					//"t1" é executado 1 milissegundo após o agendamento
					timer.schedule(t1, 1);
					//"t2" é executado 1 segundo após o agendamento
					timer.schedule(t2, 1000);
				}//fim do if...else
			}//fim do if...else
		}//fim do if
	}//fim do método "acao"*/

}



