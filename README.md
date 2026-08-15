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
Ucapkan selamat tinggal pada daftar profil peladen yang berantakan. Sistem navigasi ZeBox dirancang ulang dengan struktur **ViewPager2**, memisahkan profil **V2Ray (Kiri)** dan profil **SSH (Kanan)** secara fisik. Mekanisme usap layar (*swipe*) ini menghilangkan ambiguitas operasional dan mempercepat manajemen peladen secara drastis.

### 2. Native SSH HTTP Custom Injector
Implementasi injeksi SSH di dalam ZeBox dieksekusi secara mandiri (*native*) tanpa membutuhkan aplikasi pihak ketiga seperti *HTTP Injector*.
* Menggunakan arsitektur *Core Deception*, Injektor beroperasi murni melalui manipulasi soket internal, memungkinkan injeksi *header* kompleks (seperti *WebSocket Payload*) langsung menuju *core Sing-box*.
* **Otomatisasi Header Stripping:** ZeBox secara cerdas membuang respons HTTP dari peladen Bug/CDN untuk mencegah kegagalan *handshake* pada SSH, membuat koneksi instan dan mulus.

> [!WARNING]  
> Untuk saat ini, infrastruktur SSH difokuskan untuk aktivitas *browsing* dan pengunduhan berat. Jika Anda membutuhkan transmisi berbasis UDP secara konstan (khususnya untuk panggilan video atau *game online* kompetitif kelas berat), sangat disarankan menggunakan ruang kerja **V2Ray**.

### 3. Smart Import (Pengaturan Kilat)
Prosedur pengisian kredensial peladen secara manual yang membosankan kini telah ditinggalkan. Pengguna cukup menyalin dan merekatkan teks mentah dengan format `host:port@pengguna:sandi`, dan sistem *parser* internal ZeBox akan mengekstrak informasi tersebut lalu menciptakan profil peladen SSH yang siap digunakan dalam sekejap mata.

### 4. Advanced Anti-DPI & Loose SNI (Teknologi Tembus Batas)
Penyedia layanan internet moderen menggunakan Inspeksi Paket Mendalam (DPI) untuk memblokir VPN. ZeBox dipersenjatai dengan fitur **Loose DPI**. Fitur ini memanipulasi rentetan paket TCP awal dan memecah (*fragment*) muatan TLS, membutakan radar sistem operator sehingga peladen VPN Anda yang sebenarnya tidak dapat dideteksi maupun diblokir.

### 5. Stabilisator Gim Otomatis
Mengusung mekanisme "Anti-Bengong" tingkat lanjut, OS tidak akan diizinkan membunuh koneksi Anda. Fitur ini memprioritaskan paket lalu lintas data krusial, menjaga stabilitas latensi, serta mencegah *timeout* secara tiba-tiba di tengah sesi permainan yang intens melalui manajemen *wakelock* yang presisi di latar belakang.

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
