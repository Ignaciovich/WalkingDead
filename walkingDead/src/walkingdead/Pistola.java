package walkingdead;

public class Pistola {
    private int bala;
    
    public Pistola(){
        bala = 10;
    }
    
    public int getBala(){
        return bala;
    }
    public void recargar(){
        this.bala = bala+10;
    }
    public void disparo(){
        bala = bala-1;
    }
}
