package ui;

import estrutura.Fila;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {
    private final Fila fila = new Fila();
    private final JTextField campoNome = new JTextField(20);
    private final JTextArea areaFila = new JTextArea(10, 30);

    public TelaPrincipal() {
        super("Sistema de Atendimento - Clínica");

        // Layout
        setLayout(new BorderLayout());

        // Painel de Entrada
        JPanel painelEntrada = new JPanel();
        painelEntrada.add(new JLabel("Nome do paciente:"));
        painelEntrada.add(campoNome);

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnChamar = new JButton("Chamar");
        painelEntrada.add(btnAdicionar);
        painelEntrada.add(btnChamar);

        // Área de Exibição
        areaFila.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaFila);

        add(painelEntrada, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // Eventos
        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = campoNome.getText().trim();
                if (!nome.isEmpty()) {
                    fila.enfileirar(nome);
                    campoNome.setText("");
                    atualizarArea();
                }
            }
        });

        btnChamar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String chamado = fila.desenfileirar();
                if (chamado != null) {
                    JOptionPane.showMessageDialog(null, "Próximo paciente: " + chamado);
                } else {
                    JOptionPane.showMessageDialog(null, "Fila vazia!");
                }
                atualizarArea();
            }
        });

        // Configuração da Janela
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void atualizarArea() {
        areaFila.setText(fila.exibirFila());
    }
}
