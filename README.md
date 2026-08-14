<div align="center">
  <img src="logo.png" width="128" alt="ZeBox Logo">
  
  <h1>ZeBox Master Edition</h1>
  <em>Klien proksi VPN canggih dengan antarmuka yang mudah digunakan</em>
  <br><br>
  
  <a href="https://github.com/Ajizzz13/NekoBoxForAndroid/releases/tag/v1.0.5"><img src="https://img.shields.io/github/v/tag/Ajizzz13/NekoBoxForAndroid?label=Rilis%20Terbaru&color=blue&style=flat-square" alt="Rilis Terbaru"></a>
  <a href="https://github.com/Ajizzz13/NekoBoxForAndroid/actions"><img src="https://img.shields.io/github/actions/workflow/status/Ajizzz13/NekoBoxForAndroid/preview.yml?branch=main&label=Status%20Kompilasi&style=flat-square" alt="Status Kompilasi"></a>
  <a href="https://github.com/Ajizzz13/NekoBoxForAndroid/releases/tag/v1.0.5"><img src="https://img.shields.io/github/downloads/Ajizzz13/NekoBoxForAndroid/v1.0.5/total?label=Total%20Unduhan&color=brightgreen&style=flat-square" alt="Total Unduhan"></a>

  <br><br>
  <a href="#ikhtisar">Ikhtisar</a> • <a href="#fitur-andalan">Fitur Andalan</a> • <a href="#kompilasi-mandiri">Kompilasi Mandiri</a>
</div>

<hr>

## Ikhtisar

ZeBox merupakan modifikasi klien proksi mutakhir berbasis Sing-box yang dirancang khusus untuk memecahkan keterbatasan konfigurasi jaringan konvensional. Proyek ini mengintegrasikan protokol V2Ray dan arsitektur Secure Shell secara langsung ke dalam satu sistem inti. Melalui pendekatan rekayasa tingkat rendah, aplikasi ini memangkas konsumsi memori secara drastis sembari mempertahankan kinerja transmisi data pada tingkat maksimal.

> [!NOTE]  
> Versi ini dibangun secara eksklusif untuk memberikan keseimbangan antara efisiensi sumber daya dan keandalan koneksi proksi kelas atas.

## Fitur Andalan

### 1. Antarmuka Ruang Kerja Interaktif
Sistem navigasi dirancang ulang untuk memisahkan profil V2Ray dan SSH secara fisik melalui mekanisme usap layar. Desain ini menghilangkan ambiguitas operasional dan mempercepat manajemen peladen secara drastis.

### 2. Modul Injeksi SSH Internal
Implementasi SSH dieksekusi secara mandiri tanpa bergantung pada aplikasi eksternal. Infrastruktur ini memastikan koneksi yang sangat stabil untuk aktivitas jaringan berat. 

> [!WARNING]  
> Pengguna disarankan untuk beralih ke ruang kerja V2Ray apabila membutuhkan transmisi berbasis UDP secara konstan, khususnya pada permainan daring kompetitif.

### 3. Sistem Pengaturan Kilat
Prosedur pengisian kredensial peladen yang berulang telah dihilangkan. Pengguna dapat merekatkan teks berformat `host:port@pengguna:sandi`, dan sistem akan menciptakan profil peladen secara otomatis dalam sekejap mata.

### 4. Teknologi Loose DPI
Fitur khusus pada pengaturan ini memanipulasi paket komunikasi awal dengan memecah muatan TLS. Hal ini membuat sistem Inspeksi Paket Mendalam dari penyedia layanan internet tidak mampu menganalisis indikator peladen yang sebenarnya.

### 5. Stabilisator Gim Otomatis
Mekanisme Anti-Jeda tingkat lanjut yang mengelola proksi secara pintar dengan memprioritaskan lalu lintas data krusial, menjaga stabilitas latensi, dan mencegah lonjakan secara tiba-tiba di tengah sesi permainan.

## Kompilasi Mandiri

Bagi pengembang yang berminat untuk menyusun aplikasi ini secara mandiri, seluruh proses telah terotomatisasi melalui layanan integrasi berkelanjutan.

1. Lakukan penyalinan repositori ini ke akun GitHub pribadi Anda.
2. Buka menu **Actions** pada bilah navigasi repositori.
3. Pilih skema alur kerja **Release Build**.
4. Jalankan skema tersebut dan tentukan nama versi rilis.
5. Sistem akan segera mengompilasi paket APK untuk seluruh arsitektur prosesor.
6. Unduh berkas instalasi dari halaman rilis setelah proses kompilasi selesai.
