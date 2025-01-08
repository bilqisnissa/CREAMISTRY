public class Pelanggan {
    private String nama;  // Penerapan Encapsulation, atribut 'nama' disembunyikan agar hanya bisa diakses dengan metode

    public Pelanggan(String nama) {
        this.nama = nama;  // Menyembunyikan data pelanggan dengan menggunakan setter
    }

    public String getNama() {
        return nama;  // Memberikan akses yang terbatas ke data nama pelanggan
    }
}