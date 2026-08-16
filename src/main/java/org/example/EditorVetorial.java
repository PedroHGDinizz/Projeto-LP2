package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EditorVetorial extends JFrame {

    public EditorVetorial() {
        setTitle("Editor Gráfico Vetorial");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        CanvasPanel canvas = new CanvasPanel();
        add(canvas);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditorVetorial().setVisible(true));
    }
}

class CanvasPanel extends JPanel {
    private List<Figura> figuras = new ArrayList<>();
    private Figura figuraEmFoco = null;

    private Point lastMousePoint;
    private boolean isResizing = false;


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Figura fig : figuras) {
            fig.desenhar(g2d, fig == figuraEmFoco);
        }
    }}
