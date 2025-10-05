import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    //  Nodo del árbol
    static class Node {
        int value; //nos guarda el valor del nodo
        Node left, right; // nos referencia los hijos del nodo izquierda y derecha
        Node(int value) { this.value = value; } //inicia el nodo con un valor que le demos
    }

    // 🌲 Clase árbol binario
    static class BinaryTree {
        Node root;

        public void insert(int value) {
            root = insertRec(root, value);
        }

        private Node insertRec(Node node, int value) {
            if (node == null) return new Node(value);
            if (value < node.value) node.left = insertRec(node.left, value);
            else if (value > node.value) node.right = insertRec(node.right, value);
            return node;
        }

        public boolean search(int value) {
            return searchRec(root, value);
        }

        private boolean searchRec(Node node, int value) {
            if (node == null) return false;
            if (node.value == value) return true;
            return value < node.value ? searchRec(node.left, value) : searchRec(node.right, value);
        }
    }

    // 🎨 Panel que dibuja el árbol
    static class TreePanel extends JPanel {
        private Node root;

        public TreePanel() {
            setBackground(new Color(240, 248, 255)); // Fondo claro
        }

        public void setRoot(Node root) {
            this.root = root;
            repaint(); // Redibuja el árbol
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (root != null) {
                drawTree(g, root, getWidth() / 2, 70, getWidth() / 4);
            }
        }

        // Método recursivo para dibujar el árbol
        private void drawTree(Graphics g, Node node, int x, int y, int offset) {
            if (node == null) return;

            g.setColor(new Color(100, 100, 100));
            if (node.left != null) {
                g.drawLine(x, y, x - offset, y + 80);
            }
            if (node.right != null) {
                g.drawLine(x, y, x + offset, y + 80);
            }

            // 🌟 Nodo redondo con color y texto centrado
            g.setColor(new Color(135, 206, 250));
            g.fillOval(x - 25, y - 25, 50, 50);
            g.setColor(Color.BLACK);
            g.drawOval(x - 25, y - 25, 50, 50);

            g.setFont(new Font("Segoe UI", Font.BOLD, 16));
            String valueStr = String.valueOf(node.value);
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(valueStr);
            g.drawString(valueStr, x - textWidth / 2, y + 5);

            // Llamadas recursivas
            if (node.left != null) drawTree(g, node.left, x - offset, y + 80, offset / 2);
            if (node.right != null) drawTree(g, node.right, x + offset, y + 80, offset / 2);
        }
    }

    // 🧩 Componentes principales
    private BinaryTree tree = new BinaryTree();
    private TreePanel treePanel = new TreePanel();
    private JTextField inputField = new JTextField(10);

    // 🖼️ Constructor con la interfaz
    public Main() {
        setTitle("🌳 Visualizador de Árbol Binario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 🔝 Panel superior (título + controles)
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        controlPanel.setBackground(new Color(220, 240, 255));

        JLabel titleLabel = new JLabel("Gestor Visual de Árbol Binario");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(new Color(20, 60, 100));

        JLabel label = new JLabel("Número:");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        JButton insertButton = new JButton("➕ Insertar");
        JButton searchButton = new JButton("🔍 Buscar");

        // 🎨 Estilo de botones
        Font btnFont = new Font("Segoe UI", Font.BOLD, 15);
        insertButton.setFont(btnFont);
        searchButton.setFont(btnFont);
        insertButton.setBackground(new Color(144, 238, 144));
        searchButton.setBackground(new Color(255, 218, 185));

        // ➕ Añadir componentes al panel
        controlPanel.add(titleLabel);
        controlPanel.add(label);
        controlPanel.add(inputField);
        controlPanel.add(insertButton);
        controlPanel.add(searchButton);

        add(controlPanel, BorderLayout.NORTH);

        // 📜 Panel con scroll para el árbol
        JScrollPane scrollPane = new JScrollPane(treePanel);
        scrollPane.setPreferredSize(new Dimension(900, 550));
        add(scrollPane, BorderLayout.CENTER);

        // 📌 Eventos de botones
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int num = Integer.parseInt(inputField.getText());
                    tree.insert(num);
                    treePanel.setRoot(tree.root);
                    inputField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "⚠️ Ingrese un número válido.");
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int num = Integer.parseInt(inputField.getText());
                    boolean found = tree.search(num);
                    JOptionPane.showMessageDialog(null,
                            found ? "✅ El número " + num + " está en el árbol." :
                                    "❌ El número " + num + " NO está en el árbol.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "⚠️ Ingrese un número válido.");
                }
            }
        });
    }

    // ▶️ Método principal
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}
