package walkingdead;

public class Cuchillo {
    private int uso;//Uso restante del cuchillo
    
    public Cuchillo(){
        uso = 100;
    }

    //Devuelve lo que nos queda de uso de cuchillo
    public int getUso() {
        return uso;
    }
    
    //Mata con el cuchillo restandole uso
    public void matarCuchillo(boolean esNoche){
        if(esNoche){
            uso = uso-10;
        }else{
            uso = uso-5;
        }
    }
}
