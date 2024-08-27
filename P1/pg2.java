public class Fraccion {
    // Atributos privados
    private int numerador;
    private int denominador;

    // Constructor sin parámetros (inicializa en 1/1)
    public Fraccion() {
        this.numerador = 1;
        this.denominador = 1;
    }

    // Constructor con un solo parámetro (inicializa como numerador/x y denominador=1)
    public Fraccion(int numerador) {
        this.numerador = numerador;
        this.denominador = 1;
    }

    // Constructor con dos parámetros (numerador y denominador)
    public Fraccion(int numerador, int denominador) {
        this.numerador = numerador;
        if (denominador == 0) {
            System.out.println("Error: El denominador no puede ser cero.");
            this.denominador = 1; // Se asigna un valor por defecto
        } else {
            this.denominador = denominador;
        }
    }

    // Método get para el numerador
    public int getNumerador() {
        return numerador;
    }

    // Método set para el numerador
    public void setNumerador(int numerador) {
        this.numerador = numerador;
    }

    // Método get para el denominador
    public int getDenominador() {
        return denominador;
    }

    // Método set para el denominador
    public void setDenominador(int denominador) {
        if (denominador == 0) {
            System.out.println("Error: El denominador no puede ser cero.");
            return;
        }
        this.denominador = denominador;
    }
}




        
        public class Main {
    public static void main(String[] args) {
        // Crear un objeto usando el constructor sin parámetros
        Fraccion fraccion1 = new Fraccion();
        System.out.println("Fracción 1: " + fraccion1.getNumerador() + "/" + fraccion1.getDenominador());

        // Crear un objeto usando el constructor con un parámetro (solo numerador)
        Fraccion fraccion2 = new Fraccion(5);
        System.out.println("Fracción 2: " + fraccion2.getNumerador() + "/" + fraccion2.getDenominador());

        // Crear un objeto usando el constructor con dos parámetros (numerador y denominador)
        Fraccion fraccion3 = new Fraccion(3, 4);
        System.out.println("Fracción 3: " + fraccion3.getNumerador() + "/" + fraccion3.getDenominador());

        // Crear un objeto usando el constructor con denominador 0 para ver el manejo de errores
        Fraccion fraccion4 = new Fraccion(7, 0);
        System.out.println("Fracción 4: " + fraccion4.getNumerador() + "/" + fraccion4.getDenominador());
    }
