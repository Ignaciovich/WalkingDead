package walkingdead;

public class Persona {
    private String nombre;
    private Pistola pistola;
    private Escopeta escopeta;
    private Cuchillo cuchillo;
    private boolean vivo;
    

    //Constructor
    public Persona() {
        pistola=new Pistola();
        escopeta=new Escopeta();
        cuchillo=new Cuchillo();
        vivo=true;
        switch ((int)(Math.random() * 10)) {
            case 0: 
                nombre="Rampblast"; 
                break;
            case 1: 
                nombre="Bartolo"; 
                break;
            case 2:
                nombre="Caracachondo";
                break;
            case 3:
                nombre="Tiopocho";
                break;
            case 4:
                nombre="Tropikus";
                break;
            case 5:
                nombre="Overcraft";
                break;
            case 6:
                nombre="Rick";
                break;
            case 7:
                nombre="Morty";
                break;
            case 8:
                nombre="Gazorpazorfield";
                break;
            case 9:
                nombre="Plumbus";
                break;
        }
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
    
    //Metodo de persona que devuelve la municion de la pistola
    public int estadoMunicionPistola (Pistola p){
        return p.getBala();
    }
    
    //Metodo de persona que devuelve el estado de la municion de la escopeta de una persona
    public int estadoMunicionEscopeta (Escopeta e){
        return e.getBala();
    }
    
    //Metodo de persona que devuelve el uso restante del cuchillo de una persona
    public int estadoCuchillo (Cuchillo c){
        return c.getUso();
    }
    
    //Metodo de persona que recarga la pistola
    public void recargarPistola (Pistola p){
        p.recargar();
    }
    
    //Metodo de persona que recarga la escopeta
    public void recargarEscopeta (Escopeta e, int cartuchos){
        e.recargar(cartuchos);
    }
    
    //Metodo de persona que dispara la pistola
    public void dispararPistola (Pistola p){
        p.disparo();
    }
    
    //Metodo de persona que dispara la escopeta
    public void dispararEscopeta (Escopeta e){
        e.disparo();
    }
    
    //Metodo de persona que usa el cuchillo
    public void matarCuchillo (Cuchillo c, boolean esNoche){
        c.matarCuchillo(esNoche);
    }
    @Override
    //Es una funcion chivatillo que nos indica que persona tiene tanta municion y si esta vivo
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", pistola=" + pistola + ", escopeta=" + escopeta + ", cuchillo=" + cuchillo + ", vivo=" + vivo + '}';
    }
    
}