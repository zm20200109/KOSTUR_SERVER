/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import operacije.Operacije;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Acer
 */
public class ObradaKlijentskihZahteva extends Thread{
    
    private Socket s; // socket sa kojeg je klijent poslao zahtev 

    public ObradaKlijentskihZahteva(Socket s) {
        this.s=s;
    }

    @Override
    public void run() {
    //    super.run(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        //kontroler-db broker - idi da vidis u bazi da li postoji ... sa tim podacima
        
        while(true){
        
        KlijentskiZahtev kz = primiZahtev(); //(operacija, param)
        ServerskiOdgovor so = new ServerskiOdgovor();
        switch(kz.getOperacija()){
            case Operacije.LOGIN:
                //zovemo kontrolera...
                break;
            case Operacije.DODAJ_KNJIGU:
                //zovemo kontrolera...
                break;
            case Operacije.OBRISI_KNJIGU:
                //zovemo kontrolera...
                break;
            default:
                System.out.println("GRESKA!");
        
        }
        so.setOdgovor("USPEH");
        posaljiOdgovor(so);
        }
    }

    private KlijentskiZahtev primiZahtev()  {
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            try {
                //klijent na svom soketu sa svog soketa salje neki objekat, mi taj objekat dobijamo kao serverska strana pomocu getInputSTream metode
                //pomocu ObjectInputStream konstruktora mi smo otvorili strim za citanje klijentskog zahteva sa klijentskog soketa.
                return (KlijentskiZahtev) ois.readObject(); // onaj klijentski zahtev procitamo i posto readObject vraca object
                //mi cemo da ga kastujemo u klijentski zahtev tip i vratimo serveru 
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream ous = new ObjectOutputStream(s.getOutputStream());
            ous.writeObject(so);
            ous.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
 
    
    
    
    
}
