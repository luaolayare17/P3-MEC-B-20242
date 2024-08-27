public class Fraccion {
    private int numerador;
    private int denominador;

    // Constructor
    public Fraccion(int numerador, int denominador) {
        if (denominador == 0) {
            throw new IllegalArgumentException("El denominador no puede ser cero.");
        }
        this.numerador = numerador;
        this.denominador = denominador;
    }

    // Método para sumar dos fracciones
    public Fraccion sumar(Fraccion otra) {
        int nuevoNumerador = this.numerador * otra.denominador + otra.numerador * this.denominador;
        int nuevoDenominador = this.denominador * otra.denominador;
        return new Fraccion(nuevoNumerador, nuevoDenominador);
    }

    // Método para mostrar la fracción en formato string
    @Override
    public String toString() {
        return numerador + "/" + denominador;
    }

    // Método principal para probar la clase
    public static void main(String[] args) {
        Fraccion f1 = new Fraccion(1, 2); // Representa 1/2
        Fraccion f2 = new Fraccion(1, 3); // Representa 1/3
        Fraccion resultado = f1.sumar(f2);
        System.out.println("Resultado de la suma: " + resultado); // Debería imprimir 5/6
    }
}
