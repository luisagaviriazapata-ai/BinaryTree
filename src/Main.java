import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    static class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
        }
    }

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
            if (value < node.value) return searchRec(node.left, value);
            return searchRec(node.right, value);
        }
    }

    static class TreePanel extends JPanel {
        private Node root;

        public void setRoot(Node root) {
            this.root = root;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (root != null) {
                drawTree(g, root, getWidth() / 2, 50, getWidth() / 4);
            }
        }

        private void drawTree(Graphics g, Node node, int x, int y, int offset) {
            if (node == null) return;

            g.setColor(Color.CYAN);
            g.fillOval(x - 15, y - 15, 30, 30);
            g.setColor(Color.BLACK);
            g.drawOval(x - 15, y - 15, 30, 30);
            g.drawString(String.valueOf(node.value), x - 5, y + 5);

            if (node.left != null) {
                g.drawLine(x, y, x - offset, y + 50);
                drawTree(g, node.left, x - offset, y + 50, offset / 2);
            }
            if (node.right != null) {
                g.drawLine(x, y, x + offset, y + 50);
                drawTree(g, node.right, x + offset, y + 50, offset / 2);
            }
        }
    }

    private BinaryTree tree = new BinaryTree();
    private TreePanel treePanel = new TreePanel();
    private JTextField inputField = new JTextField(10);

    public  Main() {
        setTitle("Árbol Binario con Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        JButton insertButton = new JButton("Insertar");
        JButton searchButton = new JButton("Buscar");

        controlPanel.add(new JLabel("Número:"));
        controlPanel.add(inputField);
        controlPanel.add(insertButton);
        controlPanel.add(searchButton);

        add(controlPanel, BorderLayout.NORTH);
        add(treePanel, BorderLayout.CENTER);

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int num = Integer.parseInt(inputField.getText());
                    tree.insert(num);
                    treePanel.setRoot(tree.root);
                    inputField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int num = Integer.parseInt(inputField.getText());
                    boolean found = tree.search(num);
                    if (found)
                        JOptionPane.showMessageDialog(null, "✅ El número " + num + " está en el árbol.");
                    else
                        JOptionPane.showMessageDialog(null, "❌ El número " + num + " NO está en el árbol.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}