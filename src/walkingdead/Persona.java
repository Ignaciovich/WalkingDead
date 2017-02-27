package walkingdead;

public class Persona {
    private int nombre;
    private int pistola;
    private int escopeta;
    private int cuchillo;
    private boolean vivo;

    //Constructor
    public Persona(int nombre) {
        this.nombre=nombre;
        pistola = 10;
        escopeta = 5;
        cuchillo = 100;
        vivo=true;
    }
    
    //Devuelve la municion de pistola de una persona
    public int getPistola (){
        return pistola;
    }
    
    //Devuelve los cartuchos de escopeta de una persona
    public int getEscopeta (){
        return escopeta;
    }
    
    //Devuelve el uso restante del cuchillo de una persona
    public int getCuchillo (){
        return cuchillo;
    }
    
    //Devuelve si la persona esta viva o muerta
    public boolean isVivo (){
        return vivo;
    }
    
    //Funcion para matar a una persona
    public void matar (){
        vivo=false;
        System.out.println("Me muero");
    }
    
    //Funcion que recarga la pistola de una persona si es necesario
    public void recargarPistola (){
        this.pistola=pistola+10;
    }
    
    //Funcion que recarga la escopeta de una persona si es necesario
    public void recargarEscopeta (int numCartuchos){
        this.escopeta=escopeta+numCartuchos;
    }
    
    //Funcion para la accion dispararcon pistola
    public void disparoPistola (){
        pistola=pistola-1;
    }
    
    //Funcion para la accion disparar con escopeta
    public void disparoEscopeta (){
        escopeta=escopeta-1;
    }
    
    //Funcion para la accion de matar con cuchillo
    public void matarCuchillo (boolean esNoche){
        if (esNoche){
            cuchillo=cuchillo-10;
        }else{
            cuchillo=cuchillo-5;
        }
    }

    @Override
    //Es una funcion chivatillo que nos indica que persona tiene tanta municion y si esta vivo
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", pistola=" + pistola + ", escopeta=" + escopeta + ", cuchillo=" + cuchillo + ", vivo=" + vivo + '}';
    }
    
}