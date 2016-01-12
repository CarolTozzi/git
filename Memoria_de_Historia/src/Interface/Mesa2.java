/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import static Interface.Mesa1.totalTime;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonModel;
import javax.swing.DefaultButtonModel;
import javax.swing.JButton;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import Memoria_da_Historia_pcs.Partida;
import Memoria_da_Historia_pcs.Carta;
import Memoria_da_Historia_pcs.Tabuleiro;
import Memoria_da_Historia_pcs.Usuario;
import java.awt.Frame;
import java.io.IOException;
import javax.swing.JOptionPane;
import leitura_xml.GravaDadosXML;

/**
 *
 * @author Carol
 */
class PecaButtonModel2 extends DefaultButtonModel {

    private Carta carta;

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public PecaButtonModel2(Carta carta) {
        this.carta = carta;
    }
}

public class Mesa2 extends javax.swing.JFrame {

    private Partida Partida1;
    private ArrayList<JButton> botoes;
    private ArrayList<JButton> botoesSelecionados;
    javax.swing.Timer timer;
    int paresCertos = 0;
    Frame frame;
    //faltou o timer para quando  a 2 carta for clicada, não consegui fazer e acho que um loop pra todo esse codigo ia
    //ajudar, mas estava dando erros
    public Mesa2(final int modulo) {
        Partida1 = new Partida(modulo);
        initComponents();

        botoesSelecionados = new ArrayList();
        
        //array list de botoes
        botoes = new ArrayList();
        botoes.add(BotaoP1);//0
        botoes.add(BotaoP2);//1
        botoes.add(BotaoP3);//2
        botoes.add(BotaoP4);
        botoes.add(BotaoP5);
        botoes.add(BotaoP6);
        botoes.add(BotaoP7);
        botoes.add(BotaoP8);
        botoes.add(BotaoP9);
        botoes.add(BotaoP10);
        botoes.add(BotaoP11);
        botoes.add(BotaoP12);
        botoes.add(BotaoP13);
        botoes.add(BotaoP14);
        botoes.add(BotaoP15);
        botoes.add(BotaoP16);
        botoes.add(BotaoP17);
        botoes.add(BotaoP18);
        botoes.add(BotaoP19);
        botoes.add(BotaoP20);
         botoes.add(BotaoP21);
            botoes.add(BotaoP22);
            botoes.add(BotaoP23);
            botoes.add(BotaoP24);
            botoes.add(BotaoP25);
            botoes.add(BotaoP26);
            botoes.add(BotaoP27);
            botoes.add(BotaoP28);
            botoes.add(BotaoP29);
            botoes.add(BotaoP30);
            botoes.add(BotaoP31);
            botoes.add(BotaoP32);
            botoes.add(BotaoP33);
            botoes.add(BotaoP34);
            botoes.add(BotaoP35);
            botoes.add(BotaoP36);
            botoes.add(BotaoP37);
            botoes.add(BotaoP38);
            botoes.add(BotaoP39);
            botoes.add(BotaoP40);
            botoes.add(BotaoP41);
            botoes.add(BotaoP42);
            
        
            

        for (int i = 0; i < botoes.size(); i++) {
            botoes.get(i).setName(String.valueOf(i));

            // AS DUAS PROXIMAS LINHAS PODEM SER DESCOMENTADAS PARA TESTAR MELHOR O PROGRAMA
            //Peca carta = obterCartaAssociadaAoBotao(botoes.get(i));
            //exibirTextoOperacao(carta, botoes.get(i));
            botoes.get(i).addActionListener(new ActionListener() {//pega o primeiro botao da lista
                public void actionPerformed(ActionEvent evt) {
                    JButton botao = (JButton) evt.getSource();
                    Carta cartaSelecionadaAgora = obterCartaAssociadaAoBotao(botao);
                    exibirfato (cartaSelecionadaAgora, botao);
                   // cartaSelecionadaAgora.desvirar();// desvira a peça 0
                    botao.setEnabled(false);//desabilita o botao para que nao possa ser clicado de novo ate que outra peça seja escolhida
                    botoesSelecionados.add(botao);

                    if (botoesSelecionados.size() == 2) {
                        JButton primeiroBotaoSelecionado = botoesSelecionados.get(0);
                        Carta primeiraCartaSelecionada = obterCartaAssociadaAoBotao(primeiroBotaoSelecionado);
                        if (Partida1.jogada(primeiraCartaSelecionada, cartaSelecionadaAgora) == true) {
                            botao.setEnabled(false);
                            primeiroBotaoSelecionado.setEnabled(false);
                            botoesSelecionados.clear();
                            paresCertos++;
                            if(paresCertos == 21){
                                 try {
                                    dialogFinal();
                                } catch (IOException ex) {
                                    Logger.getLogger(Mesa1.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        } else {
                            timer.start();
                        }
                    }
                }
            });

            timer = new javax.swing.Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    for (int i = 0; i < 2; i++) {
                        botoesSelecionados.get(i).setEnabled(true);
                        botoesSelecionados.get(i).setText("");
                    }
                    botoesSelecionados.clear();
                    timer.stop();
                }
            });

        }
       
    }

    private Carta obterCartaAssociadaAoBotao(JButton botao) {
        String nome = botao.getName();
        int lin = Integer.parseInt(nome) / Tabuleiro.COLUNA;
        int col = Integer.parseInt(nome) % Tabuleiro.COLUNA;
        return Partida1.getTabuleiro().getCarta(lin, col);
    }

private void exibirfato (Carta carta, JButton botao) {
        botao.setText(carta.nometexto);
    }

private void dialogFinal() throws IOException{
    long endTime   = System.currentTimeMillis();
    totalTime = endTime - Menu.startTime;
    JOptionPane.showMessageDialog(frame, "Final do Jogo!");     
    GravaDadosXML.salvarJogo(TelaLogin.user, totalTime);
    
}
	

    private Mesa2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BotaoP1 = new javax.swing.JButton();
        BotaoP2 = new javax.swing.JButton();
        BotaoP3 = new javax.swing.JButton();
        BotaoP4 = new javax.swing.JButton();
        BotaoP5 = new javax.swing.JButton();
        BotaoP6 = new javax.swing.JButton();
        BotaoP7 = new javax.swing.JButton();
        BotaoP8 = new javax.swing.JButton();
        BotaoP9 = new javax.swing.JButton();
        BotaoP10 = new javax.swing.JButton();
        BotaoP11 = new javax.swing.JButton();
        BotaoP12 = new javax.swing.JButton();
        BotaoP13 = new javax.swing.JButton();
        BotaoP14 = new javax.swing.JButton();
        BotaoP15 = new javax.swing.JButton();
        BotaoP16 = new javax.swing.JButton();
        BotaoP17 = new javax.swing.JButton();
        BotaoP18 = new javax.swing.JButton();
        BotaoP19 = new javax.swing.JButton();
        BotaoP20 = new javax.swing.JButton();
        BotaoP22 = new javax.swing.JButton();
        BotaoP23 = new javax.swing.JButton();
        BotaoP24 = new javax.swing.JButton();
        BotaoP25 = new javax.swing.JButton();
        BotaoP26 = new javax.swing.JButton();
        BotaoP27 = new javax.swing.JButton();
        BotaoP28 = new javax.swing.JButton();
        BotaoP29 = new javax.swing.JButton();
        BotaoP30 = new javax.swing.JButton();
        BotaoP31 = new javax.swing.JButton();
        BotaoP32 = new javax.swing.JButton();
        BotaoP33 = new javax.swing.JButton();
        BotaoP34 = new javax.swing.JButton();
        BotaoP35 = new javax.swing.JButton();
        BotaoP36 = new javax.swing.JButton();
        BotaoP37 = new javax.swing.JButton();
        BotaoP38 = new javax.swing.JButton();
        BotaoP39 = new javax.swing.JButton();
        BotaoP40 = new javax.swing.JButton();
        BotaoP41 = new javax.swing.JButton();
        BotaoP42 = new javax.swing.JButton();
        BotaoP21 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Memória de História");
        setBackground(new java.awt.Color(255, 51, 51));

        jPanel1.setBackground(new java.awt.Color(204, 102, 0));

        BotaoP1.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP1.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP2.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP2.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP3.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP3.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP4.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP4.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP5.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP5.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP6.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP6.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP7.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP7.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP8.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP8.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP9.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP9.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP10.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP10.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP11.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP11.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP12.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP12.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N
        BotaoP12.setBorderPainted(false);
        BotaoP12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        BotaoP13.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP13.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP14.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP14.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP15.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP15.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP16.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP16.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP17.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP17.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP18.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP18.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP19.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP19.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP20.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP20.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP22.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP22.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP23.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP23.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP24.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP24.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP25.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP25.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP26.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP26.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP27.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP27.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP28.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP28.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP29.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP29.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP30.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP30.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP31.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP31.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP32.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP32.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP33.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP33.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP34.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP34.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP35.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP35.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP36.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP36.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP37.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP37.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP38.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP38.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP39.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP39.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP40.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP40.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP41.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP41.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP42.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP42.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        BotaoP21.setBackground(new java.awt.Color(255, 102, 51));
        BotaoP21.setFont(new java.awt.Font("Vani", 1, 17)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BotaoP17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(BotaoP13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(BotaoP26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BotaoP27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(BotaoP14, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(BotaoP18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BotaoP29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BotaoP24, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BotaoP15, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(BotaoP16, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(BotaoP19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(BotaoP28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(BotaoP23, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BotaoP22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BotaoP20, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                    .addComponent(BotaoP25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BotaoP9, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(BotaoP1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(BotaoP5, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(BotaoP2, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(BotaoP3, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(BotaoP7, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(BotaoP10, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(BotaoP6, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                    .addComponent(BotaoP11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BotaoP8, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                            .addComponent(BotaoP4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BotaoP12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BotaoP30, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoP33, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BotaoP31, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoP32, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BotaoP34, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoP37, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BotaoP36, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoP35, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BotaoP38, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoP41, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BotaoP40, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoP39, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BotaoP42, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BotaoP21, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(BotaoP4, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                        .addComponent(BotaoP3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotaoP2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotaoP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(BotaoP42, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoP21, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BotaoP8, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoP7, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoP6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoP5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotaoP41, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BotaoP12, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BotaoP11, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BotaoP10, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BotaoP9, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BotaoP39, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(BotaoP16, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                        .addComponent(BotaoP15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(BotaoP13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(BotaoP14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(BotaoP36, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BotaoP37, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(BotaoP20, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(BotaoP19, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(BotaoP18, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(BotaoP17, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(BotaoP35, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(BotaoP27, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(BotaoP24, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(26, 26, 26)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(BotaoP26, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(BotaoP29, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(BotaoP28, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(BotaoP22, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(BotaoP30, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(BotaoP31, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap())
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                            .addComponent(BotaoP23, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(170, 170, 170))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(BotaoP33, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(BotaoP32, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGap(164, 164, 164)))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(BotaoP25, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(121, 121, 121))))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(BotaoP34, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BotaoP38, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BotaoP40, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mesa2().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoP1;
    private javax.swing.JButton BotaoP10;
    private javax.swing.JButton BotaoP11;
    private javax.swing.JButton BotaoP12;
    private javax.swing.JButton BotaoP13;
    private javax.swing.JButton BotaoP14;
    private javax.swing.JButton BotaoP15;
    private javax.swing.JButton BotaoP16;
    private javax.swing.JButton BotaoP17;
    private javax.swing.JButton BotaoP18;
    private javax.swing.JButton BotaoP19;
    private javax.swing.JButton BotaoP2;
    private javax.swing.JButton BotaoP20;
    private javax.swing.JButton BotaoP21;
    private javax.swing.JButton BotaoP22;
    private javax.swing.JButton BotaoP23;
    private javax.swing.JButton BotaoP24;
    private javax.swing.JButton BotaoP25;
    private javax.swing.JButton BotaoP26;
    private javax.swing.JButton BotaoP27;
    private javax.swing.JButton BotaoP28;
    private javax.swing.JButton BotaoP29;
    private javax.swing.JButton BotaoP3;
    private javax.swing.JButton BotaoP30;
    private javax.swing.JButton BotaoP31;
    private javax.swing.JButton BotaoP32;
    private javax.swing.JButton BotaoP33;
    private javax.swing.JButton BotaoP34;
    private javax.swing.JButton BotaoP35;
    private javax.swing.JButton BotaoP36;
    private javax.swing.JButton BotaoP37;
    private javax.swing.JButton BotaoP38;
    private javax.swing.JButton BotaoP39;
    private javax.swing.JButton BotaoP4;
    private javax.swing.JButton BotaoP40;
    private javax.swing.JButton BotaoP41;
    private javax.swing.JButton BotaoP42;
    private javax.swing.JButton BotaoP5;
    private javax.swing.JButton BotaoP6;
    private javax.swing.JButton BotaoP7;
    private javax.swing.JButton BotaoP8;
    private javax.swing.JButton BotaoP9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
