# NekoBox V2Ray & SSH Master Edition

Selamat datang di repositori resmi untuk NekoBox versi modifikasi tingkat lanjut. Proyek ini mendefinisikan ulang cara Anda berinteraksi dengan protokol proksi modern dengan menggabungkan keandalan V2Ray dan fleksibilitas SSH dalam satu aplikasi yang sangat ringan dan efisien.

## Latar Belakang Proyek

NekoBox pada dasarnya merupakan klien luar biasa untuk Sing-box. Namun, pengguna sering kali harus memasang beberapa aplikasi berbeda hanya untuk mengatur koneksi SSH standar. Modifikasi khusus ini menjebol batasan tersebut. Kami menyuntikkan mesin SSH langsung ke dalam urat nadi Sing-box, sehingga perangkat Anda tetap hemat memori tanpa perlu mengorbankan performa jaringan.

## Daftar Inovasi Utama

### 1. Desain Antarmuka Swipe Dinamis
Ucapkan selamat tinggal pada kerumitan menavigasi menu. Aplikasi ini menghadirkan dua panggung utama: satu sisi untuk V2Ray dan sisi lainnya khusus untuk SSH. Anda hanya perlu mengusap layar ke kiri atau ke kanan untuk berpindah ranah secara instan. 

### 2. Mesin Injeksi SSH Terintegrasi
Tidak seperti aplikasi proksi lain yang sekadar menumpang pada aplikasi pihak ketiga, kami membangun pembungkus SSH khusus yang berinteraksi langsung dengan inti sistem jaringan. Hasilnya adalah latensi yang sangat rendah dan stabilitas koneksi tingkat dewa, sangat cocok untuk kebutuhan bermain gim daring kelas berat.

### 3. Pengaturan Cepat Otomatis
Mengisi detail server satu per satu adalah cara kuno. Cukup tempelkan format server SSH Anda seperti "sg-melbi.toko-vpn.my.id:80@azzz:9201" pada bilah penyiapan kilat. Sistem akan membedah format tersebut dan menciptakan profil koneksi utuh dalam sekejap mata. Sisa pengaturan muatan dan proksi bisa Anda sesuaikan kemudian sesuai selera.

## Panduan Kompilasi Mandiri

Bagi para pengembang yang ingin meracik aplikasi ini dari kode sumber, prosesnya telah diotomatisasi sepenuhnya.

1. Lakukan *fork* atau duplikasi repositori ini ke akun GitHub Anda.
2. Navigasikan ke bagian **Actions**.
3. Pilih alur kerja **Release Build**.
4. Picu eksekusi alur kerja dan masukkan nama rilis yang Anda inginkan pada kolom yang tersedia.
5. GitHub Actions akan mengompilasi mesin inti dan aplikasi Android secara bersamaan untuk berbagai arsitektur perangkat keras.
6. Unduh APK dari halaman *Releases* setelah proses selesai.

## Penghargaan dan Lisensi

Proyek ini dibangun di atas fondasi kokoh NekoBoxForAndroid dan Sing-box. Semua hak cipta dari inti asli adalah milik pengembang asalnya. Modifikasi antarmuka dan mesin SSH ini didedikasikan untuk komunitas jaringan. Dilarang keras menggunakan aplikasi ini untuk tindakan yang melanggar hukum di yurisdiksi Anda. Penggunaan aplikasi sepenuhnya merupakan tanggung jawab pengguna.
