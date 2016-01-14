/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package leitura_xml;
import Memoria_da_Historia_pcs.Partida;
import Memoria_da_Historia_pcs.Usuario;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import Memoria_da_Historia_pcs.Usuario;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carol
 */



public class GravaDadosXML {
    
   
    private static final String USERDATA_XML = "userdata.txt";
   
    public static BufferedWriter output = null;
    public static BufferedWriter output2 = null;
    public static File arquivo = new File(USERDATA_XML);
    
    public static void salvarJogo(Usuario usuario, long tempo) throws IOException {
        
    
        if (!arquivo.exists()) {
            try {
                arquivo = new File(USERDATA_XML);
                output = new BufferedWriter(new FileWriter(arquivo));
                output.write("Nome               Tempo");
                output.newLine();
                output.append(usuario.getNome()+"                "+tempo);
                output.close();
                
            } catch (IOException ex) {
                Logger.getLogger(GravaDadosXML.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }else{
            output.newLine();
            output.append("\n"+usuario.getNome()+"                "+tempo);
            output.close();
            
        }
    }
    
    /**
     *
     */
    public static void jogos(){        
        
            try {
                
                output = new BufferedWriter(new FileWriter(arquivo));            
                output.write("Nome                Tempo");
                
            } catch (IOException ex) {
                Logger.getLogger(GravaDadosXML.class.getName()).log(Level.SEVERE, null, ex);
            }
        
           
    }

      /* public static Usuario recuperarJogo() throws IOException {
        Usuario usuario = new Usuario();
        File arquivo = new File(USERDATA_XML);
        if (arquivo.exists()) {
            try (XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(USERDATA_XML))) {
                usuario = (Usuario) xmlDecoder.readObject();
            }
        }    
        return usuario;
    }*/
}

    
    

  