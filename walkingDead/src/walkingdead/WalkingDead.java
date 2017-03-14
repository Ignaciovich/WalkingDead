package walkingdead;

public class WalkingDead {
   
    public static void main(String[] args) {
        int cargadoresPistola[] = {50};
        int cartuchos[]= {250};
        int cont=0;
        Pistola p=new Pistola();
        Escopeta e=new Escopeta();
        Cuchillo c=new Cuchillo();
        Juego walking = new Juego ();
        do{
            walking.isDia(cont);
            walking.ataqueCaminantes();
            System.out.println("Empiezan los disparos");
            do{
                System.out.println("Comienza el turno");
                walking.turno();
            }while (walking.peligro()!=0 && walking.supervivientes());
            System.out.println("A recargar");
            walking.municion(cargadoresPistola, cartuchos, p, e, c);
            cont++;
        }while (walking.supervivientes());
        System.out.println("Hemos muerto en: "+cont+" oleadas");
    }
}
