package walkingdead;

import java.util.Arrays;

public class Juego {
    private boolean esNoche;
    private int caminante;
    private Persona companero[];
    
    //Constructor del juego
    public Juego (){
        companero = new Persona [10];
        for (int i=0; i<companero.length; i++){
            companero[i]=new Persona(i);
        }
        esNoche=false;
        caminante=0;
    }
    
    //Genera y devuelve el numero de caminantes por oleada
    public int ataqueCaminantes(){
        if (esNoche==false){
            caminante=(int)(Math.random()*20+1);
        }else{
            caminante=(int)(Math.random()*45+1);
        }
        return caminante;
    }
    
    //Devuelve si estamos en una oleada o no
    public int peligro(){
        int peligro = 0;
        if (caminante<=0){
            peligro=0;
        }else{
            peligro=1;
        }
        return peligro;
    }
    
    //Devuelve el estado de la municion de pistola de una persona
    public int estadoMunicionPistola (Persona p){
        return p.getPistola();
    }
    
    //Devuelve el estado de la municion de la escopeta de una persona
    public int estadoMunicionEscopeta (Persona p){
        return p.getEscopeta();
    }
    
    //Devuelve el uso restante del cuchillo de una persona
    public int estadoCuchillo (Persona p){
        return p.getCuchillo();
    }
    
    //Recarga la pistola de una persona y a demas resta un cargador al total
    public void darCargadorPistola (Persona p, int cargadoresPistola[]){
        if (cargadoresPistola[0]>0){
            cargadoresPistola[0]=cargadoresPistola[0]-1;
            p.recargarPistola();
        }else{
            System.out.println("Estamos fuera");
        }
    }
    
    //Da cartuchos a una persona y a demas los resta del total
    public void darCartuchoEscopeta (Persona p, int numCartuchos,int cartuchos[]){
        if (cartuchos[0]>=numCartuchos){
            cartuchos[0]=cartuchos[0]-numCartuchos;
            p.recargarEscopeta(numCartuchos);
        }else{
            System.out.println("No nos quedan tantas, apañatelas como puedas");
            p.recargarEscopeta(cartuchos[0]);
            cartuchos[0]=0;
        }
    }
    
    //Funcion que se encarga de las probabilidades de fallar un disparo segun el momento del dia
    public boolean fallarDisparo (int prob){
        boolean fallo;
        if (Math.random()*101<=prob){
            fallo=true;
        }else{
            fallo=false;
        }
        return fallo;
    }
    
    //Según fallarDisparo, se encarga de matar caminantes y restarlos del total
    public boolean matarCaminante (int arma){
        int prob=0;
        boolean fallo=fallarDisparo (prob);
        if (esNoche){
            prob=40;
            if (fallo==false){
                if (arma!=1){
                    caminante=caminante-1;
                    System.out.println("He matado a 1");
                }else{
                    caminante=caminante-2;
                    System.out.println("He matado a 2");
                }
                System.out.println("Quedan "+caminante+" caminantes");
            }
        }else{
            prob=20;
            if (fallo==false){
                if (arma!=1){
                    caminante=caminante-1;
                    System.out.println("He matado a 1");
                }else{
                    caminante=caminante-3;
                    System.out.println("He matado a 3!");
                }
                System.out.println("Quedan "+caminante+" caminantes");
            }
            if (fallo){
                System.out.println("error");
            }
        }
        return !fallo;
    }
    
    //Funcion que genera un aleatorio con la probabilidad de morir en cada momento
    public boolean caeMiembro (int prob){
        return Math.random()*101<=prob;
    }
    
    //Devuelve el momento de dia
    public boolean isDia (int cont){
        if (cont%2==0){
            esNoche=false;
        }else{
            esNoche=true;
        }
        return esNoche;
    }
    
    //Devuelve si quedan supervivientes en el grupo o han muerto todos
    public boolean supervivientes (){
        boolean superviviente=false;
        for (Persona p:companero){
            if (p.isVivo()==true){
                superviviente=true;
            }
        }
        return superviviente;
    }
    
    //Se encarga de recorrer el vector de personas para que cada uno realice sus disparos
    public void turno (){
        boolean aciertoPistola=false;
        boolean aciertoEscopeta=false;
        System.out.println("AAAAH"+peligro());
        for (int i=0; i<companero.length && peligro()!=0; i++){
            if (companero[i].isVivo()){
                if (companero[i].getPistola()!=0){
                    System.out.println("Muere!!!!");
                    aciertoPistola=matarCaminante (0);
                    companero[i].disparoPistola();
                }
                if (companero[i].getEscopeta()!=0){
                    System.out.println("PUM!");
                    aciertoEscopeta=matarCaminante (1);
                    companero[i].disparoEscopeta();
                }
                if (aciertoPistola==false && aciertoEscopeta==false){
                    if (companero[i].getCuchillo()!=0){
                        System.out.println("Crashhh");
                        matarCaminante(3);
                        companero[i].matarCuchillo(esNoche);
                    }
                }
                if (companero[i].getCuchillo()<=0){
                    if (esNoche==false){
                        if (caeMiembro(35)){
                            companero[i].matar();
                        }
                    }else{
                        if (caeMiembro (45)){
                            companero[i].matar();
                        }
                    }
                }
                if (esNoche==false){
                    if (caeMiembro(10)){
                        companero[i].matar();
                    }
                }else{
                    if (caeMiembro (35)){
                        companero[i].matar();
                    }
                }
            }
        }
    }
    
    //Se encarga de repartir municion viendo que personas necesitan municion
    public void municion (int[] cargadoresPistola, int[] cartuchos){
        System.out.println("Tenemos "+cargadoresPistola[0]+ "cargadores de pistola y "+cartuchos[0]+" cartuchos de escopeta");
        for (Persona p:companero){
            System.out.println(p);
            if (p.isVivo()){
                if (p.getPistola()==0){
                    darCargadorPistola(p, cargadoresPistola);
                }
                if (p.getEscopeta()<5){
                    darCartuchoEscopeta (p, 5-p.getEscopeta(), cartuchos);
                }
            }
        }
    }
}
