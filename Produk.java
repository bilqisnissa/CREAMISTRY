import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Produk {
    private HashMap<String, Integer> menu; // Harga produk
    private HashMap<String, Integer> stokProduk; // Stok produk
    private HashMap<String, String> gambarProduk; // Path gambar produk

    public Produk() {
        menu = new HashMap<>();
        stokProduk = new HashMap<>();
        gambarProduk = new HashMap<>();

        // Data produk, stok, dan gambar
        tambahProduk("Cream Puff", 15000, 50, "Images/cream puff.jpeg");
        tambahProduk("Apple Puff", 17000, 50, "Images/ApplePuff.jpg");
        tambahProduk("Butter Croissant", 25000, 50, "Images/Butter Croissant.jpeg");
        tambahProduk("Choco Eclairs", 16000, 50, "Images/choco eclairs.jpg");
        tambahProduk("Strawberry Cream Cookies", 25000, 50, "Images/Strawberry Cream Cookies.jpg");
        tambahProduk("Cromboloni", 15000, 50, "Images/Cromboloni.jpg");
        tambahProduk("Danish Pastry", 18000, 50, "Images/danish pastry.jpg");
        tambahProduk("Beef and Cheese Puff", 20000, 50, "Images/Beef and Cheese Puff Pastry.jpg");
        tambahProduk("Coffee Latte", 22000, 50, "Images/coffee latte.jpg");
        tambahProduk("Cookies n Cream Frappe", 17000, 50, "Images/cookies n cream frappe.jpg");
        tambahProduk("Creamy Chicken Pastry", 30000, 50, "Images/creamy chicken pastry.jpeg");
        tambahProduk("Croissant Sandwich", 35000, 50, "Images/croissant sandwich.jpg");
        tambahProduk("Ice Latte", 40000, 50, "Images/ice latte.jpeg");
        tambahProduk("Garlic Cheese Bread", 30000, 50, "Images/korean garlic cheese bread.jpg");
        tambahProduk("Milkshake Strawberry", 15000, 50, "Images/StrawberryMilkshake.jpg");
        tambahProduk("Tuna Puff Pastry", 28000, 50, "Images/tuna puff pastry.jpg");
        tambahProduk("Cinnamon Roll", 45000, 50, "Images/Cinnamon roll.jpeg");
        tambahProduk("Caramel Latte", 40000, 50, "Images/caramel latte.jpg");
    }

    private void tambahProduk(String nama, int harga, int stok, String gambarPath) {
        menu.put(nama, harga);
        stokProduk.put(nama, stok);
        gambarProduk.put(nama, gambarPath);
    }

    public void tampilkanMenuToPanel(JPanel panel, Transaksi transaksi) {
        panel.removeAll();
        panel.setLayout(new GridLayout(0, 6, 10, 10));

        for (String produk : menu.keySet()) {
            int stok = stokProduk.get(produk);
            int harga = menu.get(produk);

            JPanel card = new JPanel();
            card.setLayout(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

            ImageIcon icon = new ImageIcon(gambarProduk.get(produk));
            Image scaledImage = icon.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);
            JLabel gambarLabel = new JLabel(new ImageIcon(scaledImage));

            JLabel namaLabel = new JLabel(produk, JLabel.CENTER);
            JLabel stokLabel = new JLabel("Stok: " + stok, JLabel.CENTER);
            JLabel hargaLabel = new JLabel("Harga: Rp" + harga, JLabel.CENTER);

            JButton addButton = new JButton("Add");
            addButton.addActionListener(e -> {
                String jumlahStr = JOptionPane.showInputDialog(panel, "Masukkan jumlah untuk " + produk + ":");
                if (jumlahStr != null) {
                    try {
                        int jumlah = Integer.parseInt(jumlahStr);
                        if (stokProduk.get(produk) >= jumlah) {
                            stokProduk.put(produk, stokProduk.get(produk) - jumlah);
                            transaksi.tambahItem(produk, harga, jumlah);
                            stokLabel.setText("Stok: " + stokProduk.get(produk));
                        } else {
                            JOptionPane.showMessageDialog(panel, "Stok tidak mencukupi!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panel, "Masukkan jumlah yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            JPanel detailPanel = new JPanel();
            detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
            detailPanel.add(namaLabel);
            detailPanel.add(stokLabel);
            detailPanel.add(hargaLabel);
            detailPanel.add(addButton);

            card.add(gambarLabel, BorderLayout.CENTER);
            card.add(detailPanel, BorderLayout.SOUTH);

            panel.add(card);
        }

        panel.revalidate();
        panel.repaint();
    }
}
