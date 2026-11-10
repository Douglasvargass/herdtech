package views;

import models.Cliente;
import utils.Utilidade;
import javax.swing.*;

public class ClienteView {
    public int telaInicial() {
        String[] menuCliente = {"Lista de Cliente", "Cadastro de Cliente", "Alterar Cliente", "Excluir Cliente"};
        int opcao = JOptionPane.showOptionDialog(
                null,
                "MENU DE CLIENTE",
                "HerdTech",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                menuCliente,
                menuCliente[0]
        );
        return opcao;
    }

    public void exibir (String titulo, String texto) {
        JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.PLAIN_MESSAGE);
    }

    public Cliente informarDados(String titulo, Cliente cliente) {
        String nome = ler(titulo, "Nome:", cliente.getNome());
        if (nome == null) return null;

        String cpf = ler(titulo, "CPF:", cliente.getCpf());
        if (cpf == null) return null;

        String dataNasc = ler(titulo, "Data de Nascimento:", cliente.getDataNasc());
        if (dataNasc == null) return null;

        String nomeFazenda = ler(titulo, "Nome da Fazenda:", cliente.getNomeFazenda());
        if (nomeFazenda == null) return null;

        String endereco = ler(titulo, "Endereço:", cliente.getEndereco());
        if (endereco == null) return null;

        cliente.setNome(nome);
        cliente.setCpf(Utilidade.converterLong(cpf));
        cliente.setDataNasc(dataNasc);
        cliente.setNomeFazenda(nomeFazenda);
        cliente.setEndereco(endereco);

        return cliente;
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

    public Integer informarId (String titulo, String lista) {
        String inoutId = ler(titulo, lista + "\nId do Cliente:", null);
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
