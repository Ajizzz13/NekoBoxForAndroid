<div align="center">
  <img src="logo.png" width="160" alt="ZeBox Logo">
  
  <h1>ZeBox Master Edition</h1>
  <em>Klien Proksi VPN dan Injektor Canggih Generasi Berikutnya</em>
  <br><br>
  
  <a href="https://github.com/Ajizzz13/NekoBoxForAndroid/releases/tag/v1.0.6"><img src="https://img.shields.io/github/v/tag/Ajizzz13/NekoBoxForAndroid?label=Rilis%20Terbaru&color=blue&style=flat-square" alt="Rilis Terbaru"></a>
  <a href="https://github.com/Ajizzz13/NekoBoxForAndroid/actions"><img src="https://img.shields.io/github/actions/workflow/status/Ajizzz13/NekoBoxForAndroid/preview.yml?branch=main&label=Status%20Kompilasi&style=flat-square" alt="Status Kompilasi"></a>
  <a href="https://github.com/Ajizzz13/NekoBoxForAndroid/releases/tag/v1.0.6"><img src="https://img.shields.io/github/downloads/Ajizzz13/NekoBoxForAndroid/v1.0.6/total?label=Total%20Unduhan&color=brightgreen&style=flat-square" alt="Total Unduhan"></a>

  <br><br>
  <a href="#-ikhtisar-proyek">Ikhtisar</a> • <a href="#-arsitektur-dan-fitur-utama">Fitur Utama</a> • <a href="#-panduan-mengunduh-apk">Panduan APK</a> • <a href="#-kompilasi-mandiri">Kompilasi</a>
</div>

<hr>

## 📖 Ikhtisar Proyek

**ZeBox** adalah modifikasi klien proksi mutakhir berbasis *Sing-box core* yang dirancang secara khusus untuk memecahkan berbagai keterbatasan konfigurasi jaringan konvensional. Diciptakan bagi para pengguna tingkat lanjut (*power user*), ZeBox mengintegrasikan kekuatan protokol proksi modern (V2Ray) dan keandalan klasik Secure Shell (SSH) ke dalam satu infrastruktur aplikasi yang sangat ringan.

Melalui pendekatan rekayasa tingkat rendah (penggunaan Go dan Kotlin murni), aplikasi ini memangkas konsumsi RAM dan daya baterai secara drastis sembari mempertahankan performa transmisi data yang agresif—memungkinkan Anda untuk berselancar, melakukan *streaming*, dan bermain *game online* tanpa hambatan *firewall* penyedia layanan internet.

> [!NOTE]  
> Versi ini dibangun secara eksklusif untuk memberikan keseimbangan absolut antara efisiensi sumber daya sistem (*zero-copy operation*) dan keandalan koneksi proksi kelas atas.

---

## ✨ Arsitektur dan Fitur Utama

ZeBox tidak sekadar menempelkan fitur baru; ia merombak cara aplikasi VPN beroperasi dari akarnya. Berikut adalah keunggulan utama dari ZeBox Master Edition:

### 1. Dual Workspace Interface (Antarmuka Ganda Dinamis)
**Cara Kerja:** Sistem antarmuka aplikasi tidak menggunakan *Fragment* penumpuk konvensional, melainkan memanfaatkan arsitektur `ViewPager2` terpisah yang mengisolasi memori visual. Profil **V2Ray (Kiri)** dan profil **SSH (Kanan)** memiliki tabel *database* SQLite tersendiri. Ketika Anda menggeser layar (*swipe*), *Adapter* Kotlin hanya memuat (*lazy load*) entitas yang relevan dari ruang kerja tersebut, menghemat beban RAM (*garbage collection*) secara drastis saat mengelola ratusan akun proksi sekaligus.

### 2. Native SSH HTTP Custom Injector
**Cara Kerja (Arsitektur Core Deception):** Berbeda dengan proksi lawas yang harus ditumpuk silang dengan aplikasi HTTP Injector, ZeBox mengeksekusi *splicing* jaringan secara asinkron murni menggunakan *Kotlin Coroutines* di latar belakang.
* **Header Stripping Otomatis:** Saat menghubungkan protokol SSH melalui WebSocket (Bug CDN), server CDN sering kali menyuntikkan respons teks HTTP tambahan (seperti `101 Switching Protocols`). Injektor ZeBox akan menyergap (*sniff*) trafik masuk ini menggunakan `BufferedInputStream`, "menelan" *header* HTTP yang mengotori alur, dan hanya meneruskan rentetan *handshake* bersih `SSH-2.0` langsung ke dalam *core Sing-box*. Hasilnya: *Bypass payload* bekerja 100% tanpa risiko kegagalan otentikasi SSH.

> [!WARNING]  
> Saat ini, infrastruktur SSH beroperasi sangat tangguh pada protokol TCP (seperti pengunduhan file berat dan *streaming* 4K). Namun, protokol SSH konvensional kerap gagal memproses protokol *UDP-over-TCP* (untuk *game* kompetitif/MCPE). Oleh sebab itu, sangat disarankan memanfaatkan ruang kerja **V2Ray** untuk aktivitas *gaming*.

### 3. Smart Import (Parser Kredensial Pintar)
**Cara Kerja:** ZeBox dilengkapi mesin ekspresi reguler (*Regex Engine*) terintegrasi yang terus memindai *clipboard* perangkat secara pasif saat Anda memfokuskan kursor ke dalam aplikasi. Ketika mendeteksi teks mentah berformat (contoh: `192.168.1.1:22@root:1234`), mesin pembelah string (*tokenizer*) akan langsung mengisolasi struktur *Host*, *Port*, *Username*, dan *Password*, kemudian melakukan injeksi SQL otomatis (*auto-commit*) ke *database* profil. Anda tidak perlu lagi menyalin dan merekatkan *field* satu per satu.

### 4. Advanced Anti-DPI & Loose SNI (Teknologi Tembus Batas)
**Cara Kerja:** Sistem penyedia layanan internet menggunakan DPI (Inspeksi Paket Mendalam) untuk membongkar dan memutus paket TLS/SSL dengan melacak parameter `server_name` (SNI). ZeBox mengatasi ini di level *socket*:
* **Fragmentasi Paket:** ZeBox memotong belah (*fragment*) jabat tangan TLS (*Client Hello*) menjadi dua hingga tiga serpihan mikro sebelum dikirim ke menara seluler (*BTS*).
* Akibat pemecahan *byte* TCP ini, mesin pelacak (*radar*) DPI ISP tidak mampu merangkai ulang *string* SNI yang sesungguhnya karena paket datang tidak utuh (*loose*). Akses proksi tetap mulus melewati tembok *firewall* terkuat.

### 5. Stabilisator Gim Otomatis (Graceful Wakelock)
**Cara Kerja:** Android modern sering mematikan paksa ("*Force Kill*") aplikasi latar belakang saat Anda mengunci layar untuk menghemat daya (*Doze Mode*). 
* ZeBox melingkari eksekusi *splicing* koneksi di dalam siklus hidup `VpnService` bawaan OS (status *Foreground* mutlak). 
* Mesin mengaktifkan parameter `PARTIAL_WAKE_LOCK` di level `PowerManager`, yang memaksa CPU (*Central Processing Unit*) untuk menolak tidur, sehingga transmisi milidetik (*ping*) paket tetap diproses tepat waktu walau layar ponsel gelap gulita (*Anti-Bengong*).

---

## 📦 Panduan Mengunduh APK

ZeBox didistribusikan dalam berbagai versi arsitektur demi memastikan performa terbaik di setiap tipe ponsel. Agar tidak bingung saat memilih di halaman rilis, ikuti panduan berikut:

1. **`arm64-v8a` (Sangat Direkomendasikan ⭐)**
   * **Untuk siapa:** Hampir 95% *smartphone* Android modern keluaran 5-7 tahun terakhir (berbasis 64-bit).
   * **Gunakan ini jika:** Anda menggunakan HP harian standar (seperti Samsung, Xiaomi, Oppo, Vivo, Realme tipe baru). Ini adalah opsi yang paling aman, paling ringan, dan performanya paling optimal.

2. **`armeabi-v7a`**
   * **Untuk siapa:** Ponsel Android lawas atau perangkat kelas bawah (*low-end*) yang masih menggunakan sistem 32-bit.
   * **Gunakan ini jika:** Proses instalasi versi `arm64-v8a` di HP Anda ditolak dengan peringatan "App not installed".

3. **`x86` / `x86_64`**
   * **Untuk siapa:** Emulator Android di PC/Laptop (misal: BlueStacks, Nox, LDPlayer, Windows Subsystem for Android) atau perangkat ChromeOS.

4. **`universal` (Semua Arsitektur)**
   * **Untuk siapa:** Pengguna yang tidak mengetahui secara pasti tipe prosesor perangkatnya.
   * **Gunakan ini jika:** Ingin "cari aman". Versi ini mencakup semua arsitektur di atas sehingga **pasti bisa dipasang di perangkat mana pun**. (Catatan: Ukuran berkas APK ini jauh lebih besar).

---

## ⚙️ Kompilasi Mandiri (Untuk Pengembang)

Bagi pengembang (*modder*) yang berminat untuk menyusun, memodifikasi, dan membangun aplikasi ini secara mandiri, seluruh proses telah terotomatisasi di awan (*cloud*) melalui GitHub Actions.

1. Lakukan *fork* (*Fork repository*) repositori ini ke akun GitHub pribadi Anda.
2. Navigasikan ke *tab* **Actions** pada bilah menu repositori.
3. Pilih skema alur kerja bernama **Release Build** di panel sebelah kiri.
4. Klik tombol **Run workflow** dan tentukan nama/tag versi rilis yang Anda inginkan (misal: `v2.0.0`).
5. Sistem GitHub Actions akan segera bekerja, menyusun kepingan *Go* dan *Kotlin*, serta mengompilasi paket APK untuk seluruh arsitektur secara paralel.
6. Anda dapat mengunduh berkas instalasi akhir di halaman **Releases** begitu seluruh proses tersebut selesai.

---
<div align="center">
  <sub>Dibangun dengan ketelitian teknis, dedikasi, dan semangat *Open Source*.</sub>
</div>
