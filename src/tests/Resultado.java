package tests;

public class Resultado {

    private static int passou = 0;
    private static int falhou = 0;

    // Registra e mostra o resultado de um teste
    public static void registrar(String teste, boolean ok) {
        if (ok) {
            passou++;
            System.out.println(teste + ": Passou");
        } else {
            falhou++;
            System.err.println(teste + ": Falhou");
        }
    }

    public static int getPassou() {
        return passou;
    }

    public static int getFalhou() {
        return falhou;
    }
}
