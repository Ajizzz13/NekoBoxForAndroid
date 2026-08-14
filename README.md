<div align="center">
  
<h1>NekoBox V2Ray & SSH Master Edition</h1>

<a href="https://github.com/Ajizzz13/NekoBoxForAndroid/releases"><img src="https://img.shields.io/github/v/release/Ajizzz13/NekoBoxForAndroid?style=for-the-badge&color=success" alt="Rilis Terbaru"/></a>
<a href="https://github.com/Ajizzz13/NekoBoxForAndroid/releases"><img src="https://img.shields.io/github/downloads/Ajizzz13/NekoBoxForAndroid/total?style=for-the-badge&color=blue" alt="Total Unduhan"/></a>
<a href="https://github.com/Ajizzz13/NekoBoxForAndroid/actions/workflows/release.yml"><img src="https://img.shields.io/github/actions/workflow/status/Ajizzz13/NekoBoxForAndroid/release.yml?style=for-the-badge&color=brightgreen" alt="Status Kompilasi"/></a>

<p>Sebuah klien proksi hibrida tingkat lanjut yang mengintegrasikan protokol V2Ray dan arsitektur Secure Shell secara langsung ke dalam inti Sing-box. Proyek ini dikembangkan untuk memberikan efisiensi tinggi serta stabilitas jaringan yang absolut dalam satu antarmuka terpadu.</p>

</div>

<hr/>

<h2>Ikhtisar Proyek</h2>

<p>Klien NekoBox berbasis Sing-box pada umumnya memerlukan konfigurasi pihak ketiga tambahan untuk dapat mengakomodasi protokol SSH secara optimal. Proyek modifikasi ini dirancang secara khusus guna memecahkan keterbatasan tersebut. Melalui pendekatan rekayasa perangkat lunak tingkat rendah, kami telah menanamkan modul injeksi SSH secara permanen ke dalam sistem inti, sehingga memangkas penggunaan memori perangkat keras secara drastis sembari mempertahankan kinerja transmisi data pada tingkat maksimal.</p>

<h2>Fitur Andalan Eksklusif</h2>

<h3>1. Antarmuka Pemisah Ruang Kerja Interaktif</h3>
<p>Sistem navigasi telah dirancang ulang untuk memisahkan profil V2Ray dan SSH secara fisik melalui mekanisme antarmuka usap. Pengguna hanya perlu menggeser layar ke kiri atau ke kanan untuk beralih antara ruang kerja proksi modern dan ruang kerja SSH. Desain ini menghilangkan ambiguitas operasional dan mempercepat manajemen peladen.</p>

<h3>2. Modul Injeksi SSH Internal Terintegrasi</h3>
<p>Implementasi SSH pada aplikasi ini dibangun dan dieksekusi secara mandiri tanpa bergantung pada paket aplikasi eksternal. Infrastruktur ini memastikan koneksi yang sangat stabil untuk aktivitas berselancar di dunia maya, menembus batasan dinding api jaringan, serta melakukan transmisi media beresolusi tinggi tanpa latensi yang berarti. <i>Catatan: Berkaitan dengan spesifikasi protokol, pengguna sangat disarankan untuk beralih ke ruang kerja V2Ray apabila membutuhkan transmisi berbasis UDP secara konstan, seperti pada aktivitas permainan daring berintensitas tinggi.</i></p>

<h3>3. Sistem Pengaturan Kilat Berbasis Teks</h3>
<p>Prosedur pengisian kredensial peladen yang panjang dan berulang telah dihilangkan. Pengguna kini difasilitasi dengan kolom penyiapan kilat. Cukup rekatkan teks dengan format <code>host:port@pengguna:sandi</code>, dan sistem akan memproses data tersebut untuk menghasilkan profil peladen secara otomatis. Penyesuaian proksi lanjutan maupun konfigurasi muatan data dapat dilakukan secara parsial setelah profil terbuat.</p>

<h2>Panduan Kompilasi Mandiri</h2>

<p>Bagi para pengembang yang berminat untuk menyusun aplikasi ini secara mandiri, seluruh proses telah terotomatisasi melalui layanan integrasi berkelanjutan.</p>

<ol>
  <li>Lakukan penyalinan repositori ini ke akun GitHub pribadi Anda.</li>
  <li>Buka menu <b>Actions</b> pada bilah navigasi repositori.</li>
  <li>Pilih skema alur kerja <b>Release Build</b>.</li>
  <li>Jalankan skema tersebut dan tentukan nama versi rilis pada kolom yang disediakan.</li>
  <li>Sistem GitHub Actions akan segera memproses kode sumber dan mengompilasi paket APK untuk seluruh arsitektur prosesor secara paralel.</li>
  <li>Unduh berkas instalasi dari halaman rilis setelah proses kompilasi dinyatakan selesai.</li>
</ol>

<h2>Pernyataan Lisensi dan Penafian</h2>

<p>Proyek ini dibangun bersandarkan pada fondasi sumber terbuka NekoBoxForAndroid beserta inti Sing-box. Hak cipta atas sistem dasar sepenuhnya merupakan milik para pengembang asli. Modifikasi struktural dan penambahan fitur SSH ini didedikasikan secara terbuka untuk komunitas jaringan global. Segala bentuk penyalahgunaan fungsi perangkat lunak ini merupakan tanggung jawab absolut masing-masing pengguna akhir.</p>
