public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}

class Auto{
    public Auto(String kleur, int snelheid) {
        this.kleur = kleur;
        this.snelheid = snelheid;
    }

    String kleur;
    int snelheid;


    void rijden(){
        System.out.println("De auto is aan het rijden");
    }
}

final bool aandrijving = true;