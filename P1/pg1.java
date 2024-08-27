public class Fraccion {
    //aplicamos encapsulamiento a los atributos numerador  y denominador
    private int numerador;
    private int denominador;
    
    
    //agregamos un constructor paraq inicializar todos los metodos.
     // Constructor para inicializar la fracción
    public Fraccion(int numerador, int denominador) {
        this.numerador = numerador;
        if (denominador == 0) {
            throw new IllegalArgumentException("El denominador no puede ser cero.");
        }
        this.denominador = denominador;
    }
    
    
    
    // colocamos un metodo get para retornar el numerador
    public int getNumerador() {
        return numerador;
    }

    // y con este metodo set eatablecemos el numerador
    public void setNumerador(int numerador) {
        this.numerador = numerador;
    }

    // y con este metodo obtenemos el denominador un metodo get
    public int getDenominador() {
        return denominador;
    }
    
     // restrincion para el denominador que no sea 0
    public void setDenominador(int denominador) {
        if (denominador == 0) {
            throw new IllegalArgumentException("El denominador no puede ser cero.");

        }
        this.denominador = denominador;
    }
     // Método para mostrar la fracción como una cadena
    public String mostrarFraccion() {
        return numerador + "/" + denominador;
    }
}
