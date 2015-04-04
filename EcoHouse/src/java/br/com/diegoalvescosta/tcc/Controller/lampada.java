package br.com.diegoalvescosta.tcc.Controller;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import static java.awt.image.ImageObserver.ERROR;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author diego-dell
 */
@ManagedBean
@RequestScoped
public class lampada {

   
    public lampada() {
        
        
    }
    
    public void Ligar() throws InterruptedException {
        iniciarConexao();
        Thread.sleep(1000);
        enviarDados(verdeON);
        Thread.sleep(1000);
        enviarDados(amareloON);
        Thread.sleep(1000);
        enviarDados(vermelhoON);
        Thread.sleep(1000);
        
        
    }
    
    public void Desligar() throws InterruptedException {
        
        serialPort.close();
       
    }
    
    
    //Envio de dados para arduino
    private static final String verdeOFF = "0";
    private static final String verdeON = "1";
    
    private static final String amareloOFF = "2";
    private static final String amareloON = "3";
    
    private static final String vermelhoOFF = "4";
    private static final String vermelhoON = "5";
    
    //Variaveis de Conexao
    private OutputStream output = null;
    SerialPort serialPort;
    private final String porta="/dev/ttyS81";
    private static final int timeOut = 2000; //2 segundos
    private static final int dataRate = 9600;
  
    
    
    public void iniciarConexao(){
        CommPortIdentifier portaId = null;
        Enumeration portaEnum = CommPortIdentifier.getPortIdentifiers();
        
        while(portaEnum.hasMoreElements()){
            CommPortIdentifier atualPortaId =(CommPortIdentifier) portaEnum.nextElement();
            if(porta.equals(atualPortaId.getName())){
                portaId=atualPortaId;
                break;
            }
        }
        if(portaId == null){
            //mostrarErro("Não se pode conectar a porta");
            System.exit(ERROR);
        }
        
        try{
            serialPort = (SerialPort) portaId.open(this.getClass().getName(), timeOut);
            //parametros da porta serial
            
            serialPort.setSerialPortParams(dataRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            output = serialPort.getOutputStream();
        }catch(Exception e){
            //mostrarErro(e.getMessage());
            System.exit(ERROR);
        }
    }
    
    
    private void enviarDados(String dados){
        
        try {            
            output.write(dados.getBytes());
        } catch (IOException ex) {
            
        }
        
    }
    
}
