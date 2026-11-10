package views;

import models.Gado;
import utils.Utilidade;
import javax.swing.*;

public class GadoView {
    public int telaInicial() {
        String[] menu = {"Lista de Gado", "Cadastro de Gado", "Alterar Gado", "Excluir Gado"};

        int opcao = JOptionPane.showOptionDialog(
                null,
                "MENU DE GADOS",
                "HerdTech",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                menu,
                menu[0]);
        return opcao;
    }

    public void exibir (String titulo, String texto) {
        JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.PLAIN_MESSAGE);
    }

    public Integer informarId (String titulo, String lista) {
        String inoutId = ler(titulo, lista + "\nId do Animal:", null);
        if (inoutId == null) return null;

        Integer id = Utilidade.converterInteger(inoutId);
        if (id == null) alertaErro(titulo, "Ops! Id inválido");

        return id;
    }

    public  String ler (String titulo, String texto, Object campo) {
        return (String) JOptionPane.showInputDialog(
                null,
                texto,
                titulo,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                campo
        );
    }

    public void alertaSucesso (String titulo, String msg) {
        JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public void alertaErro (String titulo, String msg) {
        JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public Gado informarDados(String titulo, Gado gado) {
        String nome = ler(titulo, "Apelido do Animal:", gado.getNome());
        if (nome == null) return null;

        String raca = ler(titulo, "Raça do Animal:", gado.getRaca());
        if (raca == null) return null;

        String genero = ler(titulo, "Genero:", gado.getGenero());
        if (genero == null) return null;

        String dataNasc = ler(titulo, "Data de Nascimento do Animal:", gado.getDataNasc());
        if (dataNasc == null) return null;

        gado.setNome(nome);
        gado.setRaca(raca);
        gado.setGenero(genero);
        gado.setDataNasc(dataNasc);

        return gado;
    }

    public int alertaConfirmacao (String titulo, String msg) {
        return JOptionPane.showConfirmDialog(
                null,
                msg,
                titulo,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
    }
}
