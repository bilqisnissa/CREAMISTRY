import java.util.HashMap;

public class Transaksi {
    private int totalHarga;
    private HashMap<String, Integer> pesanan; // Menyimpan nama produk dan jumlahnya

    public Transaksi() {
        this.totalHarga = 0;
        this.pesanan = new HashMap<>();
    }

    public void tambahKePesanan(String namaProduk, int harga, int jumlah) {
        // Tambahkan jumlah ke pesanan jika produk sudah ada
        pesanan.put(namaProduk, pesanan.getOrDefault(namaProduk, 0) + jumlah);
        totalHarga += harga * jumlah; // Tambah total harga
    }

    public String getRincianPesanan() {
        StringBuilder rincian = new StringBuilder();
        for (String namaProduk : pesanan.keySet()) {
            int jumlah = pesanan.get(namaProduk);
            rincian.append(jumlah).append(" x ").append(namaProduk).append("\n");
        }
        return rincian.toString();
    }

    public int getTotal() {
        return totalHarga;
    }

    public void resetTotal() {
        totalHarga = 0;
        pesanan.clear();
    }
}