package views;

import javax.swing.*;

public class HeedTechView {
    public int telaInicial() {
        String[] home = {"Cliente", "Gado", "Agendamento da Vacina"};
        int opcao = JOptionPane.showOptionDialog(
                null,
                "HOME:",
                "HerdTech",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                home,
                home[0]
        );
        return opcao;
    }
}
