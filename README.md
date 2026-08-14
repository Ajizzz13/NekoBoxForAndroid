# NekoBox Custom SSH Injection

NekoBox versi modifikasi khusus ini merupakan aplikasi VPN serbaguna yang menggabungkan kemampuan V2Ray tangguh dengan fitur injeksi SSH kustom dalam satu antarmuka yang mulus.

## Fitur Utama

* **Sistem Navigasi Swipe Baru**: Beralih antara profil V2Ray dan SSH dengan sangat mudah hanya dengan menggeser layar ke kanan atau ke kiri. Tidak ada lagi menu yang membingungkan.
* **Injeksi SSH Mandiri**: Berbeda dengan aplikasi lain yang harus menginstal mesin inti tambahan, versi ini menyematkan modul proksi SSH kustom ke dalam inti Sing-box bawaan. Ini membuat aplikasi berjalan sangat ringan dan menghemat RAM ponsel Anda.
* **Fitur Quick Setup SSH**: Tidak perlu lagi mengisi formulir panjang. Cukup tempelkan kredensial SSH dengan format "host:port@user:password" pada halaman SSH dan aplikasi akan otomatis membuatkan profil untuk Anda.
* **Pemecahan Kebuntuan Koneksi**: Algoritma injektor telah diperbarui untuk mencegah koneksi macet saat melakukan streaming berat atau bermain gim daring. 

## Cara Membangun Aplikasi

Aplikasi ini menggunakan sistem otomatis Github Actions. 

1. Lakukan *fork* atau *push* kode terbaru ke repositori utama.
2. Buka tab Actions di Github.
3. Pilih alur kerja bernama "Release Build".
4. Klik tombol "Run workflow" dan ketikkan nama rilis contoh: v1.0.0.
5. Github akan otomatis membangun file APK lengkap untuk semua arsitektur ponsel pintar dan mengunggahnya ke halaman Releases.

## Penafian

Proyek ini dibangun berdasarkan NekoBoxForAndroid sumber terbuka. Modifikasi ini dibuat untuk keperluan kustomisasi antarmuka dan kemudahan injeksi SSH. Segala bentuk penyalahgunaan aplikasi merupakan tanggung jawab masing-masing pengguna.
