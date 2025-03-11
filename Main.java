public class Main {
    public static void main(String[] args) {
        int x = 5, y = 3;
        double z = 3.1;

        System.out.println("A soma de "+ x + " + " + y + " = " +  (x + y));
        System.out.println("A subtracao de "+ x + " - " + z + " = " +(x - z));
        System.out.println("A soma de todas = "+ (x + z + y));
        System.out.println("A multiplicacao de "+ y + " * " + z + " a dividir por "+ x + " = " + ((y * z) / x));
        System.out.println("A soma de 3 com 3.1 a dividir por 5 = "+ ((y+z)/x));
    }
  }