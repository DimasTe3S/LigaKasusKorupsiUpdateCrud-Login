activity_main.xml
![activitymain](https://github.com/user-attachments/assets/7f877d6b-714e-40a7-a395-a9b52159a28b)

androidmanifest.xml
![image](https://github.com/user-attachments/assets/88c71ba7-daaf-4f00-85a2-d26c2f837b84)
![image](https://github.com/user-attachments/assets/53fe163c-73bf-40c3-9a7c-eca814a34053)


activity_list.xml
![image](https://github.com/user-attachments/assets/c9dd8018-4ec0-4591-ab8e-dbfc91b22bc2)
![image](https://github.com/user-attachments/assets/2eba8d2b-17f3-44ee-8fa7-fb1b0014dd59)

activity_detail.xml
![image](https://github.com/user-attachments/assets/346956fa-df89-4673-a407-bb1fb317f435)
![image](https://github.com/user-attachments/assets/7c7febe3-689c-4a11-93e8-1c955be44c6c)
![image](https://github.com/user-attachments/assets/cf361f6e-adf6-4a8a-9cab-aa54d31b29e1)
![image](https://github.com/user-attachments/assets/9c9d6b5a-2044-4c7b-9ebd-f5e885d95527)
![image](https://github.com/user-attachments/assets/4fbce210-4d0f-4f00-abfe-1c9b82a35474)

list_item.xml
![image](https://github.com/user-attachments/assets/6979c108-6c48-490d-9148-8f77b68b8084)

mainactiviy.java
![image](https://github.com/user-attachments/assets/1a3bed74-79b0-4ab3-a9cc-c644cb3f3975)

listactiviy.java
![image](https://github.com/user-attachments/assets/f6fc2215-6e93-4611-8c24-c37094911bdf)

detailactivity.java
![image](https://github.com/user-attachments/assets/5df9606b-28ab-42a7-a543-a870afba5d24)
![image](https://github.com/user-attachments/assets/076db26e-854f-4bed-967a-2dea0a9a299d)
![image](https://github.com/user-attachments/assets/ebbe6433-8c9f-4e75-97a1-9609b272477f)
![image](https://github.com/user-attachments/assets/18085a7c-1dc0-475d-91ec-40f3b62a6580)
![image](https://github.com/user-attachments/assets/11d03c65-e4a6-41da-b868-ba113acd4857)
![image](https://github.com/user-attachments/assets/b3a57f09-3455-4bcb-9a2c-2d1178fac53e)

kasusadapter.java
![image](https://github.com/user-attachments/assets/d3dc335d-a1dc-42b3-aef6-7cd0f1f5a852)
![image](https://github.com/user-attachments/assets/eedeb82d-5efd-4857-93d4-f5e03348b046)
![image](https://github.com/user-attachments/assets/c473b3de-7aa7-4324-bb92-cb237eed29d7)

Fitur Aplikasi
1. Halaman Utama (MainActivity): Menampilkan ikon aplikasi dan tombol masuk menuju daftar kasus.
2. Halaman Daftar Kasus (ListActivity): Berisi daftar kasus korupsi dalam tampilan grid yang dapat diklik untuk melihat detail.
3. Halaman Detail Kasus (DetailActivity): Menampilkan deskripsi lengkap kasus, gambar utama, dan gambar tambahan jika tersedia.
4. Navigasi Antar-Kasus: Terdapat tombol untuk berpindah ke kasus sebelumnya dan selanjutnya.

Struktur Navigasi
1. MainActivity → ListActivity
2. ListActivity → DetailActivity
3. DetailActivity → Next/Previous Case atau Kembali ke ListActivity

app/
├── java/com/example/ligakasuskorupsi/
│   ├── MainActivity.java           # Halaman utama dengan tombol masuk
│   ├── ListActivity.java           # Halaman daftar kasus
│   ├── DetailActivity.java         # Halaman detail kasus
│   └── KasusAdapter.java           # Adapter untuk menghubungkan data ke GridView
│
├── res/
│   ├── layout/
│   │   ├── activity_main.xml       # Layout halaman utama
│   │   ├── activity_list.xml       # Layout halaman daftar kasus
│   │   ├── activity_detail.xml     # Layout halaman detail kasus
│   │   └── list_item.xml           # Layout untuk setiap item di grid
│   │
│   ├── mipmap/                     # Folder ikon aplikasi dan gambar kasus
│   └── values/                     # File nilai seperti strings.xml, colors.xml, dll.
│
└── AndroidManifest.xml             # Deklarasi aktivitas dan konfigurasi aplikasi

AppCompat - Untuk kompatibilitas tampilan pada berbagai versi Android.
GridView - Menampilkan daftar kasus dalam bentuk grid.
Intent - Navigasi antar-activity.
LinearLayout - Struktur layout vertikal dan horizontal.
ImageView - Menampilkan gambar kasus.
TextView - Menampilkan judul dan deskripsi kasus.

Seluruhnya menggunakan ujicoba melalui handphone dengan cara usb debug > install aplikasi
![Gambar WhatsApp 2025-05-17 pukul 12 09 08_5e13e5d1](https://github.com/user-attachments/assets/7c833b6c-c1ab-4135-89a1-35286114943f)
![Gambar WhatsApp 2025-05-17 pukul 12 09 08_9f4366f4](https://github.com/user-attachments/assets/d5fb1695-d4d3-4a7c-bcd6-9728578da55b)
![Gambar WhatsApp 2025-05-17 pukul 12 09 08_4ddc2933](https://github.com/user-attachments/assets/5044368a-17bd-4da6-89d6-17a98575c9c0)
![Gambar WhatsApp 2025-05-17 pukul 12 09 09_e4aba871](https://github.com/user-attachments/assets/a75babda-8858-45e8-b7d5-94976243a094)
![Gambar WhatsApp 2025-05-17 pukul 12 09 09_f00776c9](https://github.com/user-attachments/assets/d5d4ad79-a7bc-4f93-90a4-2c043c1cb180)
![Gambar WhatsApp 2025-05-17 pukul 12 09 09_e62a42e1](https://github.com/user-attachments/assets/435b0a0f-b8a2-48be-af5b-f34b6fb67995)
![Gambar WhatsApp 2025-05-17 pukul 12 09 11_3c8775a5](https://github.com/user-attachments/assets/33f74b2d-7f27-43a5-93ae-3bfb98061718)
![Gambar WhatsApp 2025-05-17 pukul 12 09 11_48248c1f](https://github.com/user-attachments/assets/8872c4de-0c9f-4bc0-b880-42cbd3c21955)
![Gambar WhatsApp 2025-05-17 pukul 12 09 11_0a290ff7](https://github.com/user-attachments/assets/a4c9d05e-3ddb-418c-b50a-871ca75f2aa0)
![Gambar WhatsApp 2025-05-17 pukul 12 09 12_eb4d81ff](https://github.com/user-attachments/assets/40897f08-fd7b-4483-b906-c2a9ae47ce41)
![Gambar WhatsApp 2025-05-17 pukul 12 09 12_c83635e9](https://github.com/user-attachments/assets/4bb36d90-053d-441e-94ca-2d70b8d80ee4)
![Gambar WhatsApp 2025-05-17 pukul 12 09 12_9c196a2f](https://github.com/user-attachments/assets/5a89814d-fcad-4e5e-a101-4e6d674b2124)
