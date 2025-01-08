public class Login {
    public boolean validasiPembeli(String nama) {
        // Penerapan Abstraction, hanya memvalidasi apakah nama pembeli sudah ada
        return !nama.isEmpty();  // Pembeli dianggap valid jika nama tidak kosong
    }
} 







