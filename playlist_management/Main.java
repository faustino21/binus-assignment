import java.util.Scanner;

// Kelas untuk merepresentasikan sebuah lagu
class Lagu {
    private String judul;
    private String artis;
    private double durasi;

    // Constructor
    Lagu(String judul, String artis, double durasi) {
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
    }

    // Getter untuk judul
    public String getJudul() {
        return judul;
    }

    // Getter untuk durasi
    public double getDurasi() {
        return durasi;
    }

    // Menampilkan informasi lagu dalam format ringkas
    void tampilkanInfo() {
        System.out.println(judul + " - " + artis + " (" + durasi + " menit)");
    }
}

// Kelas untuk mengelola playlist lagu menggunakan array statis
class PlaylistArray {
    private Lagu[] playlist;
    private int jumlahLagu;
    private static final int KAPASITAS = 10;

    // Constructor - inisialisasi playlist kosong
    PlaylistArray() {
        playlist = new Lagu[KAPASITAS];
        jumlahLagu = 0;
    }

    // Menampilkan semua lagu dalam playlist (Traversal - O(n))
    void tampilkanSemuaLagu() {
        if (jumlahLagu == 0) {
            System.out.println("Playlist kosong.");
            return;
        }
        System.out.println("\nDaftar lagu saat ini:");
        for (int i = 0; i < jumlahLagu; i++) {
            System.out.print((i + 1) + ". ");
            playlist[i].tampilkanInfo();
        }
    }

    // Menambahkan lagu langsung tanpa input (untuk testing)
    void tambahLagu(String judul, String artis, double durasi) {
        if (jumlahLagu >= KAPASITAS) return;
        playlist[jumlahLagu] = new Lagu(judul, artis, durasi);
        jumlahLagu++;
    }

    // Menambahkan lagu baru ke playlist (Insertion - O(1))
    void tambahLagu(Scanner sc) {
        // Memeriksa apakah playlist sudah penuh
        if (jumlahLagu >= KAPASITAS) {
            System.out.println("Playlist penuh! Tidak dapat menambahkan lagu baru.");
            return;
        }
        System.out.print("Masukkan judul lagu : ");
        String judul = sc.nextLine();
        System.out.print("Masukkan artis      : ");
        String artis = sc.nextLine();
        System.out.print("Masukkan durasi (menit): ");
        double durasi = sc.nextDouble();
        sc.nextLine(); // membersihkan buffer

        playlist[jumlahLagu] = new Lagu(judul, artis, durasi);
        jumlahLagu++;
        System.out.println("Lagu berhasil ditambahkan!");
        tampilkanSemuaLagu();
    }

    // Menghapus lagu dari playlist berdasarkan judul (Deletion - O(n))
    void hapusLagu(Scanner sc) {
        System.out.print("Masukkan judul lagu yang akan dihapus: ");
        String cari = sc.nextLine();
        int indeksHapus = -1;

        // Mencari indeks lagu yang akan dihapus
        for (int i = 0; i < jumlahLagu; i++) {
            if (playlist[i].getJudul().equalsIgnoreCase(cari)) {
                indeksHapus = i;
                break;
            }
        }

        if (indeksHapus != -1) {
            // Menggeser elemen array agar data tetap rapat
            for (int i = indeksHapus; i < jumlahLagu - 1; i++) {
                playlist[i] = playlist[i + 1];
            }
            playlist[jumlahLagu - 1] = null; // Hapus referensi terakhir
            jumlahLagu--;
            System.out.println("Lagu '" + cari + "' berhasil dihapus.");
        } else {
            System.out.println("Lagu '" + cari + "' tidak ditemukan.");
        }
    }

    // Mencari lagu berdasarkan judul menggunakan Linear Search (O(n))
    void cariLagu(Scanner sc) {
        System.out.print("Masukkan judul lagu yang dicari: ");
        String cari = sc.nextLine();
        boolean ditemukan = false;

        for (int i = 0; i < jumlahLagu; i++) {
            if (playlist[i].getJudul().equalsIgnoreCase(cari)) {
                System.out.println("Lagu ditemukan pada urutan ke-" + (i + 1) + ":");
                playlist[i].tampilkanInfo();
                ditemukan = true;
                break;
            }
        }

        if (!ditemukan) {
            System.out.println("Lagu '" + cari + "' tidak ditemukan.");
        }
    }

    // Mengurutkan lagu berdasarkan durasi menggunakan Bubble Sort (O(n²))
    void urutkanBubbleSort() {
        if (jumlahLagu == 0) {
            System.out.println("Playlist kosong.");
            return;
        }

        System.out.println("\nSebelum diurutkan:");
        for (int i = 0; i < jumlahLagu; i++) {
            playlist[i].tampilkanInfo();
        }

        // Bubble Sort berdasarkan durasi secara ascending
        for (int i = 0; i < jumlahLagu - 1; i++) {
            for (int j = 0; j < jumlahLagu - 1 - i; j++) {
                if (playlist[j].getDurasi() > playlist[j + 1].getDurasi()) {
                    Lagu temp = playlist[j];
                    playlist[j] = playlist[j + 1];
                    playlist[j + 1] = temp;
                }
            }
        }

        System.out.println("\nSetelah diurutkan berdasarkan durasi (ascending):");
        for (int i = 0; i < jumlahLagu; i++) {
            playlist[i].tampilkanInfo();
        }
    }

    // Mengurutkan lagu berdasarkan durasi menggunakan Selection Sort (O(n²))
    void urutkanSelectionSort() {
        if (jumlahLagu == 0) {
            System.out.println("Playlist kosong.");
            return;
        }

        System.out.println("\nSebelum diurutkan:");
        for (int i = 0; i < jumlahLagu; i++) {
            playlist[i].tampilkanInfo();
        }

        // Selection Sort berdasarkan durasi secara ascending
        for (int i = 0; i < jumlahLagu - 1; i++) {
            int indeksMin = i;
            for (int j = i + 1; j < jumlahLagu; j++) {
                if (playlist[j].getDurasi() < playlist[indeksMin].getDurasi()) {
                    indeksMin = j;
                }
            }
            // Tukar elemen minimum dengan elemen posisi i
            Lagu temp = playlist[indeksMin];
            playlist[indeksMin] = playlist[i];
            playlist[i] = temp;
        }

        System.out.println("\nSetelah diurutkan berdasarkan durasi (ascending):");
        for (int i = 0; i < jumlahLagu; i++) {
            playlist[i].tampilkanInfo();
        }
    }
}

// Kelas utama - menjalankan program manajemen playlist musik
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlaylistArray playlist = new PlaylistArray();

        // Data lagu untuk testing
        playlist.tambahLagu("Yellow", "Coldplay", 4.10);
        playlist.tambahLagu("Perfect", "Ed Sheeran", 4.23);
        playlist.tambahLagu("Shivers", "Ed Sheeran", 3.50);
        playlist.tambahLagu("Blinding Lights", "The Weeknd", 3.22);
        playlist.tambahLagu("Shape of You", "Ed Sheeran", 3.54);
        playlist.tambahLagu("Levitating", "Dua Lipa", 3.23);
        playlist.tambahLagu("Stay", "The Kid LAROI", 2.21);
        playlist.tambahLagu("As It Was", "Harry Styles", 2.37);
        playlist.tambahLagu("Anti-Hero", "Taylor Swift", 3.21);

        int memilih = 0;

        while (memilih != 7) {
            System.out.println("\n=== MENU PLAYLIST MUSIK ===");
            System.out.println("1. Tampilkan semua lagu");
            System.out.println("2. Tambah lagu baru");
            System.out.println("3. Hapus lagu berdasarkan judul");
            System.out.println("4. Cari lagu berdasarkan judul");
            System.out.println("5. Urutkan lagu berdasarkan durasi (Bubble Sort)");
            System.out.println("6. Urutkan lagu berdasarkan durasi (Selection Sort)");
            System.out.println("7. Keluar");
            System.out.print("Pilih salah satu angka dalam menu: ");
            memilih = scanner.nextInt();
            scanner.nextLine(); // membersihkan buffer

            switch (memilih) {
                case 1: playlist.tampilkanSemuaLagu(); break;
                case 2: playlist.tambahLagu(scanner); break;
                case 3: playlist.hapusLagu(scanner); break;
                case 4: playlist.cariLagu(scanner); break;
                case 5: playlist.urutkanBubbleSort(); break;
                case 6: playlist.urutkanSelectionSort(); break;
                case 7: System.out.println("Memproses keluar dari program..."); break;
                default: System.out.println("Pilih angka menu dari 1-7.");
            }
        }

        scanner.close();
    }
}
