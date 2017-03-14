package walkingdead;

import java.util.Arrays;

public class Juego {
    private boolean esNoche;//Estado del momento: dia o noche
    private int caminante;//Numero de caminantes por oleada
    private Persona companero[];//Vector de Personas
    private Pistola p;
    private Escopeta e;
    private Cuchillo c;
    
    //Constructor del juego
    public Juego (){
        companero = new Persona [10];
        for (int i=0; i<companero.length; i++){
            companero[i]=new Persona();
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
    
    //Recarga la pistola de una persona y a demas resta un cargador al total
    public void darCargadorPistola (Pistola p, int cargadoresPistola[]){
        for(int i=0; i<companero.length; i++){
            if (cargadoresPistola[0]>0 && companero[i].estadoMunicionPistola(p)==0){
                cargadoresPistola[0]=cargadoresPistola[0]-1;
                companero[i].recargarPistola(p);
            }else{
                System.out.println("Estamos fuera");
            }
        }
    }
    
    //Da cartuchos a una persona y a demas los resta del total
    public void darCartuchoEscopeta (Escopeta e, int numCartuchos,int cartuchos[]){
        for(int i=0; i<companero.length; i++){
            if (cartuchos[0]>=numCartuchos){
                cartuchos[0]=cartuchos[0]-numCartuchos;
                companero[i].recargarEscopeta(e, numCartuchos);
            }else{
                System.out.println("No nos quedan tantas, apañatelas como puedas");
                companero[i].recargarEscopeta(e, cartuchos[0]);
                cartuchos[0]=0;
            }
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
    
    //Según fallarDisparo, se encarga de matar caminantes con pistola y restarlos del total
    public boolean matarCaminante (Pistola p){
        int prob=0;
        boolean fallo=fallarDisparo (prob);
        if (esNoche){
            prob=40;
            if (fallo==false){
                if (p.getBala()!=0){
                    caminante=caminante-1;
                    System.out.println("He matado a 1");
                }
                System.out.println("Quedan "+caminante+" caminantes");
            }
            if (fallo){
                System.out.println("error");
            }
        }else{
            prob=20;
            if (fallo==false){
                if (p.getBala()!=0){
                    caminante=caminante-1;
                    System.out.println("He matado a 1");
                }
                System.out.println("Quedan "+caminante+" caminantes");
            }
            if (fallo){
                System.out.println("error");
            }
        }
        return !fallo;
    }
    
    //Segun el estado del dia y el fallarDisparo restarlos en total
    public boolean matarCaminante (Escopeta e){
        int prob=0;
        boolean fallo=fallarDisparo (prob);
        if (esNoche){
            prob=40;
            if (fallo==false){
                if (e.getBala()!=0){
                    caminante=caminante-2;
                    System.out.println("He matado a 2");
                }
                System.out.println("Quedan "+caminante+" caminantes");
            }
            if (fallo){
                System.out.println("error");
            }
        }else{
            prob=20;
            if (fallo==false){
                if (e.getBala()!=0){
                    caminante=caminante-3;
                    System.out.println("He matado a 3");
                }
                System.out.println("Quedan "+caminante+" caminantes");
            }
            if (fallo){
                System.out.println("error");
            }
        }
        return !fallo;
    }
    
    //Si se fallan los disparos se usa el cuchillo
    public boolean matarCaminante (Cuchillo c){
        int prob=0;
        boolean fallo=fallarDisparo (prob);
        if (esNoche){
            prob=40;
            if (fallo==false){
                if (c.getUso()!=0){
                    caminante=caminante-1;
                    System.out.println("He matado a 1");
                }
                System.out.println("Quedan "+caminante+" caminantes");
            }
            if (fallo){
                System.out.println("error");
            }
        }else{
            prob=20;
            if (fallo==false){
                if (c.getUso()!=0){
                    caminante=caminante-1;
                    System.out.println("He matado a 1");
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
                if (companero[i].estadoMunicionPistola(p)!=0){
                    System.out.println("Muere!!!!");
                    aciertoPistola=matarCaminante (p);
                    companero[i].dispararPistola(p);
                }
                if (companero[i].estadoMunicionEscopeta(e)!=0){
                    System.out.println("PUM!");
                    aciertoEscopeta=matarCaminante (e);
                    companero[i].dispararEscopeta(e);
                }
                if (aciertoPistola==false && aciertoEscopeta==false){
                    if (companero[i].estadoCuchillo(c)!=0){
                        System.out.println("Crashhh");
                        matarCaminante(c);
                        companero[i].matarCuchillo(c, esNoche);
                    }
                }
                if (companero[i].estadoCuchillo(c)<=0){
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
    public void municion (int[] cargadoresPistola, int[] cartuchos,Pistola p, Escopeta e, Cuchillo c){
        System.out.println("Tenemos "+cargadoresPistola[0]+ "cargadores de pistola y "+cartuchos[0]+" cartuchos de escopeta");
        for (Persona pe:companero){
            System.out.println(p);
            if (pe.isVivo()){
                if (pe.estadoMunicionPistola(p)==0){
                    darCargadorPistola(p, cargadoresPistola);
                }
                if (pe.estadoMunicionEscopeta(e)<5){
                    darCartuchoEscopeta (e, 5-e.getBala(), cartuchos);
                }
            }
        }
    }
}
