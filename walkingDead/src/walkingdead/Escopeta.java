package walkingdead;

public class Escopeta {
    private int bala;//Numero de balas
    
    public Escopeta(){
        bala = 5;
    }
    
    //Nos dice las balas que quedan
    public int getBala(){
        return bala;
    }
    
    //Recarga la escopeta
    public void recargar(int numCartuchos){
        this.bala = bala+5;
    }
    
    //Dispara la escopeta descontando municion
    public void disparo(){
        bala = bala-1;
    }
}


