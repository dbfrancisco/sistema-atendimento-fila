package ui;

import estrutura.Fila;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;


public class TelaPrincipal extends JFrame {
    private final Fila fila = new Fila();
    private final JTextField campoNome = new JTextField(20);
    private final JTextArea areaFila = new JTextArea(10, 30);
    private final Color corFundo = new Color(245, 245, 250);
    private final Color corBotao = new Color(100, 149, 237); // Azul suave
    private final Color corBotaoChamar = new Color(60, 179, 113); // Verde suave
    private final Color corTexto = new Color(33, 33, 33);

    public TelaPrincipal() {
        super("Sistema de Atendimento - Clínica");

        getContentPane().setBackground(corFundo);
        areaFila.setEditable(false);
        areaFila.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaFila.setBackground(Color.WHITE);
        areaFila.setForeground(corTexto);
        JScrollPane scroll = new JScrollPane(areaFila);
        scroll.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Pacientes na Fila"));

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnChamar = new JButton("Chamar");

        estilizarBotao(btnAdicionar, corBotao);
        estilizarBotao(btnChamar, corBotaoChamar);

        JPanel painelEntrada = new JPanel();
        painelEntrada.setLayout(new BoxLayout(painelEntrada, BoxLayout.Y_AXIS));
        painelEntrada.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelEntrada.setBackground(corFundo);

        JLabel labelNome = new JLabel("Nome do paciente:");
        labelNome.setFont(new Font("Arial", Font.BOLD, 14));
        labelNome.setForeground(corTexto);
        campoNome.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        linha1.setBackground(corFundo);
        linha1.add(labelNome);
        linha1.add(campoNome);

        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        linha2.setBackground(corFundo);
        linha2.add(btnAdicionar);
        linha2.add(btnChamar);

        painelEntrada.add(linha1);
        painelEntrada.add(Box.createVerticalStrut(10));
        painelEntrada.add(linha2);

        setLayout(new BorderLayout(10, 10));
        add(painelEntrada, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);


        btnAdicionar.addActionListener(e -> {
            String nome = campoNome.getText().trim();
            if (!nome.isEmpty()) {
                fila.enfileirar(nome);
                campoNome.setText("");
                atualizarArea();
            }
        });

        btnChamar.addActionListener(e -> {
            String chamado = fila.desenfileirar();
            if (chamado != null) {
                // "Animação" simples: destaca a área por 500ms
                Color original = areaFila.getBackground();
                areaFila.setBackground(new Color(255, 250, 205)); // Amarelo claro
                Timer timer = new Timer(500, ev -> areaFila.setBackground(original));
                timer.setRepeats(false);
                timer.start();

                JOptionPane.showMessageDialog(null, "Próximo paciente: " + chamado);
            } else {
                JOptionPane.showMessageDialog(null, "Fila vazia!");
            }
            atualizarArea();
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void atualizarArea() {
        areaFila.setText(fila.exibirFila());
    }

    private void estilizarBotao(JButton botao, Color corFundo) {
        botao.setBackground(corFundo);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(corFundo.darker()),
                new EmptyBorder(5, 15, 5, 15)
        ));
    }
}
