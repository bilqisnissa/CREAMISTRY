import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {
    private static Pelanggan pelanggan = null;

    public static void main(String[] args) {
        Produk produk = new Produk();
        Transaksi transaksi = new Transaksi();
        Login login = new Login();

        JFrame frame = new JFrame("CREAMISTRY");
        frame.setSize(800, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new CardLayout());

        // Panel untuk memilih peran
        JPanel panelPilihan = new JPanel(new GridLayout(3, 1, 10, 10));

        // Label selamat datang
        JLabel labelSelamatDatang = new JLabel("Selamat Datang di CREAMISTRY:", JLabel.CENTER);
        labelSelamatDatang.setFont(new Font("Arial", Font.BOLD, 24)); // Mengatur font menjadi lebih besar, ukuran 24
        labelSelamatDatang.setForeground(Color.BLACK);
        panelPilihan.add(labelSelamatDatang);

        // Tombol Pembeli
        JButton buttonLoginPembeli = new JButton("Login");
        buttonLoginPembeli.setBackground(Color.PINK); // Ubah warna latar tombol menjadi pink
        buttonLoginPembeli.setForeground(Color.WHITE); // Warna teks tombol
        buttonLoginPembeli.setFocusPainted(false); // Hilangkan outline fokus pada tombol

        // Mengatur ukuran tombol secara manual
        buttonLoginPembeli.setPreferredSize(new Dimension(150, 50));

        // Panel pembungkus untuk mengatur ukuran tombol
        JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Atur posisi ke tengah
        buttonWrapper.add(buttonLoginPembeli);
        panelPilihan.add(buttonWrapper);


        // Panel untuk login pembeli
        JPanel panelLoginPembeli = new JPanel(null); // Menggunakan null layout untuk kontrol penuh terhadap posisi dan ukuran

        // Label
        JLabel labelNama = new JLabel("Masukkan Nama Anda:");
        labelNama.setBounds(300, 50, 250, 30); // Atur posisi dan ukuran label
        labelNama.setFont(new Font("Arial", Font.BOLD, 18));
        panelLoginPembeli.add(labelNama);

        // Text Name 
        JLabel labelUser = new JLabel("Username");
        labelUser.setBounds(100, 100, 250, 30); // Atur posisi dan ukuran label
        labelUser.setFont(new Font("Arial", Font.PLAIN, 14));
        panelLoginPembeli.add(labelUser);

        // Nama Pembeli
        JTextField fieldNamaPembeli = new JTextField();
        fieldNamaPembeli.setBounds(100, 130, 200, 40); // Atur posisi dan ukuran field
        fieldNamaPembeli.setFont(new Font("Arial", Font.PLAIN, 14)); // Atur font
        panelLoginPembeli.add(fieldNamaPembeli);

        // Button Masuk
        JButton buttonMasukPembeli = new JButton("Masuk");
        buttonMasukPembeli.setBounds(100, 200, 100, 40); // Atur posisi dan ukuran tombol
        panelLoginPembeli.add(buttonMasukPembeli);

    
        // Panel untuk pembeli
        JPanel panelPembeli = new JPanel(new BorderLayout());
        JPanel panelMenuPembeli = new JPanel();
        produk.tampilkanMenuToPanel(panelMenuPembeli, transaksi); // Kirim transaksi untuk integrasi tombol Add
        JScrollPane scrollMenuPembeli = new JScrollPane(panelMenuPembeli);

        JTextArea textTransaksiPembeli = new JTextArea();
        textTransaksiPembeli.setEditable(false);
        JScrollPane scrollTransaksiPembeli = new JScrollPane(textTransaksiPembeli);

        JPanel panelBayar = new JPanel(new GridLayout(3, 1, 5, 5));
        JLabel labelTotalBayar = new JLabel("Total: Rp0");
        JTextField fieldUangBayar = new JTextField();

        //Button Bayar
        JButton buttonBayar = new JButton("Bayar");       
        JButton buttonKembaliKeMenuAwalPembeli = new JButton("Kembali ke Menu Awal");

        panelBayar.add(buttonKembaliKeMenuAwalPembeli);
        panelBayar.add(labelTotalBayar);
        panelBayar.add(new JLabel("Jumlah Uang Yang Dibayar:"));
        panelBayar.add(fieldUangBayar);
        panelBayar.add(buttonBayar);

        panelPembeli.add(scrollMenuPembeli, BorderLayout.NORTH);
        panelPembeli.add(scrollTransaksiPembeli, BorderLayout.CENTER);
        panelPembeli.add(panelBayar, BorderLayout.SOUTH);

        frame.add(panelPilihan, "pilihan");
        frame.add(panelLoginPembeli, "loginPembeli");
        frame.add(panelPembeli, "pembeli");

        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();

        // Event handling
        buttonLoginPembeli.addActionListener(e -> cardLayout.show(frame.getContentPane(), "loginPembeli"));

        buttonMasukPembeli.addActionListener(e -> {
            String nama = fieldNamaPembeli.getText().trim();
            if (nama.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nama tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                pelanggan = new Pelanggan(nama);
                JOptionPane.showMessageDialog(frame, "Selamat datang, " + pelanggan.getNama());
                cardLayout.show(frame.getContentPane(), "pembeli");
            }
        });

        buttonBayar.addActionListener(e -> {
            try {
                if (pelanggan == null) {
                    JOptionPane.showMessageDialog(frame, "Harap login terlebih dahulu!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int uangDibayar = Integer.parseInt(fieldUangBayar.getText().trim());
                if (uangDibayar < transaksi.getTotal()) {
                    JOptionPane.showMessageDialog(frame, "Uang tidak cukup!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int kembalian = uangDibayar - transaksi.getTotal();
                    String detailTransaksi = textTransaksiPembeli.getText();

                    StringBuilder bill = new StringBuilder();
                    bill.append("============= CREAMISTRY =============\n");
                    bill.append("Tanggal: ").append(java.time.LocalDate.now()).append("\n");
                    bill.append("Pelanggan: ").append(pelanggan.getNama()).append("\n");
                    bill.append("--------------------------------------\n");
                    bill.append(detailTransaksi);
                    bill.append("--------------------------------------\n");
                    bill.append("Total: Rp").append(transaksi.getTotal()).append("\n");
                    bill.append("Jumlah Uang Yang Dibayar: Rp").append(uangDibayar).append("\n");
                    bill.append("Kembalian: Rp").append(kembalian).append("\n");
                    bill.append("========================================\n");
                    bill.append("Terima kasih telah berbelanja di CREAMISTRY!");

                    JOptionPane.showMessageDialog(frame, bill.toString(), "CREAMISTRY - Bill Pembayaran", JOptionPane.INFORMATION_MESSAGE);

                    transaksi.resetTotal();
                    labelTotalBayar.setText("Total: Rp0");
                    textTransaksiPembeli.setText("");
                    fieldUangBayar.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Masukkan jumlah uang yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        transaksi.setOnUpdate(() -> {
            labelTotalBayar.setText("Total: Rp" + transaksi.getTotal());
            textTransaksiPembeli.setText(transaksi.getDetail());
        });

        buttonKembaliKeMenuAwalPembeli.addActionListener(e -> cardLayout.show(frame.getContentPane(), "pilihan"));

        frame.setVisible(true);
    }
}

class Transaksi {
    private int total = 0;
    private StringBuilder detail = new StringBuilder();
    private Runnable onUpdate;

    public void tambahItem(String nama, int harga, int jumlah) {
        total += harga * jumlah;
        detail.append(jumlah).append(" x ").append(nama).append(" (Rp").append(harga * jumlah).append(")\n");
        if (onUpdate != null) onUpdate.run();
    }

    public int getTotal() {
        return total;
    }

    public String getDetail() {
        return detail.toString();
    }

    public void resetTotal() {
        total = 0;
        detail.setLength(0);
        if (onUpdate != null) onUpdate.run();
    }

    public void setOnUpdate(Runnable onUpdate) {
        this.onUpdate = onUpdate;
    }
}

class Pelanggan {
    private String nama;

    public Pelanggan(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }
}
