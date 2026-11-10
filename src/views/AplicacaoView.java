package views;

import models.Aplicacao;
import utils.Utilidade;
import javax.swing.*;

public class AplicacaoView {
    public int telaInicial() {
        String[] menu = {"Histórico de Vacinas", "Cadastro da Aplicação", "Alterar Aplicação", "Excluir Aplicação"};

        int opcao = JOptionPane.showOptionDialog(
                null,
                "MENU DE APLICAÇÕES:",
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

    public Aplicacao informarDados(String titulo, Aplicacao aplicacao) {
        String vacina = ler(titulo, "Nome da Vacina:", aplicacao.getVacina());
        if (vacina == null) return null;

        String dataAplicacao = ler(titulo, "Data da Aplicação:", aplicacao.getDataAplicacao());
        if (dataAplicacao == null) return null;

        String proximaAplicacao = ler(titulo, "Próxima vacina (data):", aplicacao.getProximaVacina());
        if (proximaAplicacao == null) return null;

        String quemAplicou = ler(titulo, "Quem aplicou? (Veterinário ou Dono):", aplicacao.getQuemAplicou());
        if (quemAplicou == null) return null;

        aplicacao.setVacina(vacina);
        aplicacao.setDataAplicacao(dataAplicacao);
        aplicacao.setProximaVacina(proximaAplicacao);
        aplicacao.setQuemAplicou(quemAplicou);

        return aplicacao;
    }

    public void alertaSucesso (String titulo, String msg) {
        JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public void alertaErro (String titulo, String msg) {
        JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public Integer informarId (String titulo, String lista) {
        String inoutId = ler(titulo, lista + "\nId da Aplicação:", null);
        if (inoutId == null) return null;

        Integer id = Utilidade.converterInteger(inoutId);
        if (id == null) alertaErro(titulo, "Ops! Id inválido");

        return id;
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
