# 📱 65132908 - Android Programming

> Repository môn học **Lập trình Android** — Tổng hợp các bài thực hành xây dựng ứng dụng Android sử dụng Java.

---

## 📋 Mục lục

- [Giới thiệu](#-giới-thiệu)
- [Công nghệ sử dụng](#-công-nghệ-sử-dụng)
- [Cấu trúc dự án](#-cấu-trúc-dự-án)
- [Danh sách ứng dụng](#-danh-sách-ứng-dụng)
  - [1. HelloAndroid](#1-helloandroid)
  - [2. AppCong — Ứng dụng Tính Tổng](#2-appcong--ứng-dụng-tính-tổng)
  - [3. BaiTH4_1 — LinearLayout & Button](#3-baith4_1--linearlayout--button)
  - [4. BaiTH4_2 — Máy Tính Bỏ Túi](#4-baith4_2--máy-tính-bỏ-túi)
  - [5. BaiTH5 — Xử Lý Sự Kiện (Máy Tính)](#5-baith5--xử-lý-sự-kiện-máy-tính)
  - [6. BaiTH6 — Xử Lý Sự Kiện Tính Tổng](#6-baith6--xử-lý-sự-kiện-tính-tổng)
  - [7. BaiTH7 — ListView (Món Ăn & Vật Liệu)](#7-baith7--listview-món-ăn--vật-liệu)
  - [8. BaiLamThem1 — ListView (Sinh Viên & Môn Học)](#8-bailamthem1--listview-sinh-viên--môn-học)
  - [9. BaiLamThem2 — ListView (Sản Phẩm & Nhà Cung Cấp)](#9-bailamthem2--listview-sản-phẩm--nhà-cung-cấp)
- [Yêu cầu hệ thống](#-yêu-cầu-hệ-thống)
- [Hướng dẫn cài đặt và chạy](#-hướng-dẫn-cài-đặt-và-chạy)
- [Tác giả](#-tác-giả)

---

## 🎯 Giới thiệu

Đây là repository chứa các bài thực hành môn **Lập trình Android** với mã sinh viên **65132908**. Mỗi thư mục con tương ứng với một ứng dụng Android riêng biệt, được xây dựng nhằm rèn luyện các kỹ năng phát triển ứng dụng di động trên nền tảng Android — từ giao diện cơ bản, xử lý sự kiện, đến làm việc với `ListView` và lưu trữ dữ liệu.

---

## 🛠 Công nghệ sử dụng

| Công nghệ | Chi tiết |
|---|---|
| **Ngôn ngữ** | Java 11 |
| **Build System** | Gradle (Kotlin DSL) |
| **Min SDK** | 24 (Android 7.0 Nougat) |
| **Target / Compile SDK** | 36 |
| **UI Framework** | ConstraintLayout, LinearLayout, Material Design |
| **IDE** | Android Studio |
| **Thư viện chính** | AndroidX AppCompat, Material Components, Activity, ConstraintLayout |

---

## 📁 Cấu trúc dự án

```
65132908-AndroidProgramming/
├── HelloAndroid/                        # Bài 1: Ứng dụng Hello World
├── AppCong/                             # Bài 2: Ứng dụng Tính Tổng
├── BaiTH4_1LinearLayOut_Tong2So/        # Bài TH4.1: LinearLayout & Button
├── BaiTH4_2LinearLayOut_Tong2So/        # Bài TH4.2: Máy Tính Bỏ Túi
├── BaiTH5_XuLySuKien1/                 # Bài TH5: Xử Lý Sự Kiện (Máy Tính)
├── BaiTH6_XuLySuKien_TinhTong/         # Bài TH6: Xử Lý Sự Kiện Tính Tổng
├── BaiTH7_ListView1/                   # Bài TH7: ListView (Món Ăn & Vật Liệu)
├── BaiLamThem1_ListView/               # Bài Làm Thêm 1: ListView (Sinh Viên & Môn Học)
├── BaiLamThem2_ListView/               # Bài Làm Thêm 2: ListView (Sản Phẩm & Nhà Cung Cấp)
└── README.md
```

Mỗi project có cấu trúc chuẩn Android:

```
<Tên Project>/
├── app/
│   └── src/main/
│       ├── java/<package>/       # Mã nguồn Java
│       └── res/layout/           # Giao diện XML
├── build.gradle.kts
└── settings.gradle.kts
```

---

## 📦 Danh sách ứng dụng

### 1. HelloAndroid

> 🟢 Ứng dụng đầu tiên — Làm quen với Android Studio và cấu trúc project Android.

- **Package:** `tiil.edu.helloandroid`
- **Mô tả:** Ứng dụng "Hello World" cơ bản, hiển thị dòng chữ **"Hello World!"** ở giữa màn hình.
- **Tính năng:**
  - Hiển thị văn bản trung tâm bằng `TextView` + `ConstraintLayout`
  - Hỗ trợ giao diện Edge-to-Edge (tràn viền)
  - Xử lý Window Insets cho System Bars
- **Kiến thức áp dụng:**
  - Tạo project Android mới
  - Cấu trúc thư mục `src/main/java` và `src/main/res`
  - Layout XML cơ bản với `ConstraintLayout`
  - Vòng đời Activity (`onCreate`)

---

### 2. AppCong — Ứng dụng Tính Tổng

> 🔵 Ứng dụng tính tổng hai số — Thực hành xử lý sự kiện và tương tác UI.

- **Package:** `tiil.edu.appcong`
- **Mô tả:** Ứng dụng cho phép người dùng nhập hai số **a** và **b**, sau đó tính và hiển thị tổng khi nhấn nút **"Tính Tổng"**.
- **Giao diện bao gồm:**
  - `EditText` **edtA** — Ô nhập số a
  - `EditText` **edtB** — Ô nhập số b
  - `Button` **btnCong** — Nút "Tính Tổng" (gọi phương thức `XuLyCong` qua thuộc tính `android:onClick`)
  - `EditText` **edtKQ** — Ô hiển thị kết quả
  - Các `TextView` label: "a:", "b:", "Kết Quả:"
- **Kiến thức áp dụng:**
  - Thiết kế giao diện với nhiều widget (`EditText`, `Button`, `TextView`)
  - Bố cục bằng `ConstraintLayout` với ràng buộc giữa các view
  - Xử lý sự kiện bằng thuộc tính `android:onClick` trong XML
  - Chuyển đổi kiểu dữ liệu (`String` → `int`) và xử lý kết quả

---

### 3. BaiTH4_1 — LinearLayout & Button

> 🟡 Thực hành bố cục tuyến tính — Làm quen với `LinearLayout` và tùy chỉnh giao diện `Button`.

- **Package:** `tiil.edu.vd1`
- **Thư mục:** `BaiTH4_1LinearLayOut_Tong2So/`
- **Mô tả:** Ứng dụng minh hoạ cách sử dụng `LinearLayout` theo chiều dọc (`vertical`) để sắp xếp ba nút bấm với giao diện tuỳ chỉnh.
- **Giao diện bao gồm:**
  - `Button` **nutSO1** — "NÚT SỐ 1"
  - `Button` **nutSO2** — "NÚT SỐ 2"
  - `Button` **nutSO3** — "NÚT SỐ 3"
  - Các nút có: chữ màu vàng (`#FFEB3B`), nền tím (`#9C27B0`), cỡ chữ 20dp
- **Kiến thức áp dụng:**
  - Sử dụng `LinearLayout` với `orientation="vertical"` và `gravity="center"`
  - Tuỳ chỉnh giao diện Button: `textColor`, `background`, `textSize`
  - Sử dụng `layout_marginTop` để tạo khoảng cách giữa các view
  - Hỗ trợ Edge-to-Edge và xử lý Window Insets

---

### 4. BaiTH4_2 — Máy Tính Bỏ Túi

> 🟣 Ứng dụng máy tính hoàn chỉnh — Thực hành xử lý nhiều phép tính và kiểm tra đầu vào.

- **Package:** `tiil.edu.vd2`
- **Thư mục:** `BaiTH4_2LinearLayOut_Tong2So/`
- **Mô tả:** Ứng dụng máy tính bỏ túi hỗ trợ **4 phép tính** cơ bản: Cộng (+), Trừ (−), Nhân (×), Chia (÷). Có xử lý ngoại lệ cho trường hợp chia cho 0 và nhập liệu không hợp lệ.
- **Giao diện bao gồm:**
  - `EditText` **edtSoThuNhat** — Ô nhập số thứ nhất
  - `EditText` **edtSoThuHai** — Ô nhập số thứ hai
  - `Button` **btnCong, btnTru, btnNhan, btnChia** — 4 nút phép tính (nền tím `#7B1FA2`)
  - `EditText` **edtKetQua** — Ô hiển thị kết quả (chỉ đọc)
  - `SeekBar` — Thanh kéo minh hoạ (progress mặc định: 50, max: 100)
- **Tính năng nổi bật:**
  - Hỗ trợ nhập số thập phân và số âm (`numberDecimal|numberSigned`)
  - Kiểm tra đầu vào: thông báo `Toast` khi chưa nhập đủ hoặc nhập sai định dạng
  - Xử lý chia cho 0 với cảnh báo `Toast`
  - Sử dụng kiểu `double` cho tính toán chính xác
- **Kiến thức áp dụng:**
  - Bố cục phức tạp với `LinearLayout` lồng nhau (horizontal + vertical)
  - Xử lý sự kiện bằng `setOnClickListener` với anonymous class
  - Sử dụng `Toast` để hiển thị thông báo
  - Try-catch xử lý `NumberFormatException`
  - Sử dụng `SeekBar` widget

---

### 5. BaiTH5 — Xử Lý Sự Kiện (Máy Tính)

> 🟠 Ứng dụng máy tính — Thực hành xử lý sự kiện với `setOnClickListener` và kiểu `float`.

- **Package:** `tiil.edu.baith5_xulysukien1`
- **Thư mục:** `BaiTH5_XuLySuKien1/`
- **Mô tả:** Ứng dụng máy tính cơ bản hỗ trợ 4 phép tính: Cộng, Trừ, Nhân, Chia. Mỗi phép tính được xử lý bởi một phương thức riêng biệt (`XULY_CONG`, `XULY_TRU`, `XULY_NHAN`, `XULY_CHIA`).
- **Giao diện bao gồm:**
  - `EditText` **editTextSo1** — Ô nhập số thứ nhất
  - `EditText` **editTextSo2** — Ô nhập số thứ hai
  - `Button` **nutCong, nutTru, nutNhan, nutChia** — 4 nút phép tính
  - `EditText` **editTextKQ** — Ô hiển thị kết quả
- **Tính năng nổi bật:**
  - Tính toán với kiểu `float`
  - Xử lý chia cho 0 hiển thị thông báo lỗi trực tiếp
  - Mỗi phép tính có phương thức xử lý riêng
- **Kiến thức áp dụng:**
  - Xử lý sự kiện bằng `setOnClickListener` với anonymous class
  - Chuyển đổi kiểu dữ liệu `String` → `float` với `Float.parseFloat()`
  - Tách logic xử lý thành các phương thức riêng biệt
  - Kiểm tra điều kiện chia cho 0

---

### 6. BaiTH6 — Xử Lý Sự Kiện Tính Tổng

> 🔵 Ứng dụng tính tổng — Thực hành xử lý sự kiện bằng thuộc tính `android:onClick` trong XML.

- **Package:** `tiil.edu.appcong`
- **Thư mục:** `BaiTH6_XuLySuKien_TinhTong/`
- **Mô tả:** Ứng dụng cho phép nhập hai số nguyên **a** và **b**, tính tổng và hiển thị kết quả bằng phương thức `XuLyCong` được gọi trực tiếp từ XML thông qua `android:onClick`.
- **Giao diện bao gồm:**
  - `EditText` **edtA** — Ô nhập số a
  - `EditText` **edtB** — Ô nhập số b
  - `Button` — Nút tính tổng (kết nối `XuLyCong` qua `android:onClick`)
  - `EditText` **edtKQ** — Ô hiển thị kết quả
- **Tính năng nổi bật:**
  - Sử dụng kiểu `int` cho tính toán số nguyên
  - Binding sự kiện trực tiếp từ XML layout (`android:onClick`)
- **Kiến thức áp dụng:**
  - Xử lý sự kiện bằng `android:onClick` trong XML layout
  - `findViewById()` để ánh xạ view
  - Chuyển đổi `String` → `int` với `Integer.parseInt()`
  - So sánh cách xử lý sự kiện: XML onClick vs `setOnClickListener`

---

### 7. BaiTH7 — ListView (Món Ăn & Vật Liệu)

> 🟤 Ứng dụng ListView đa màn hình — Thực hành `ListView`, `ArrayAdapter` và điều hướng Activity.

- **Package:** `tiil.edu.baith7_listview1`
- **Thư mục:** `BaiTH7_ListView1/`
- **Mô tả:** Ứng dụng hiển thị danh sách với hai màn hình riêng biệt: **Danh sách Món Ăn** và **Danh sách Vật Liệu**. Người dùng chọn từ màn hình chính để điều hướng đến từng danh sách.
- **Các Activity:**
  - `MainActivity` — Màn hình chính với 2 nút điều hướng
  - `MonAnActivity` — Hiển thị danh sách món ăn bằng `ListView`
  - `VatLieuActivity` — Hiển thị danh sách vật liệu bằng `ListView`
- **Tính năng nổi bật:**
  - Điều hướng giữa các Activity bằng `Intent`
  - Sử dụng `ArrayAdapter` với layout tùy chỉnh (`list_item_custom`)
  - Hiển thị dữ liệu dạng danh sách với `ListView`
- **Kiến thức áp dụng:**
  - Tạo và quản lý nhiều Activity
  - Sử dụng `Intent` để chuyển màn hình
  - `ArrayAdapter` và custom layout cho `ListView`
  - Đăng ký Activity trong `AndroidManifest.xml`

---

### 8. BaiLamThem1 — ListView (Sinh Viên & Môn Học)

> 🟢 Bài làm thêm 1 — Ứng dụng quản lý danh sách Sinh Viên và Môn Học.

- **Package:** `tiil.edu.bailamthem1_listview`
- **Thư mục:** `BaiLamThem1_ListView/`
- **Mô tả:** Ứng dụng hiển thị hai danh sách: **Sinh Viên** và **Môn Học**. Mỗi danh sách được hiển thị trên một Activity riêng, điều hướng từ màn hình chính.
- **Các Activity:**
  - `MainActivity` — Màn hình chính với 2 nút: "Sinh Viên" và "Môn Học"
  - `SinhVienActivity` — Hiển thị danh sách sinh viên (Nguyễn Văn A, Trần Thị B, ...)
  - `MonHocActivity` — Hiển thị danh sách môn học
- **Tính năng nổi bật:**
  - Điều hướng giữa các Activity bằng `Intent`
  - Sử dụng `ArrayAdapter` với layout tùy chỉnh
  - Dữ liệu mẫu danh sách sinh viên Việt Nam
- **Kiến thức áp dụng:**
  - Thực hành lại mô hình đa Activity với `Intent`
  - Hiển thị dữ liệu với `ListView` + `ArrayAdapter`
  - Tùy chỉnh item layout cho `ListView`

---

### 9. BaiLamThem2 — ListView (Sản Phẩm & Nhà Cung Cấp)

> 🔴 Bài làm thêm 2 — Ứng dụng quản lý Sản Phẩm & Nhà Cung Cấp với tìm kiếm, thêm mới và lưu trữ dữ liệu.

- **Package:** `tiil.edu.bailamthem2_listview`
- **Thư mục:** `BaiLamThem2_ListView/`
- **Mô tả:** Ứng dụng nâng cao với hai danh sách: **Sản Phẩm** và **Nhà Cung Cấp**. Hỗ trợ thêm mới, tìm kiếm/lọc và lưu trữ dữ liệu bền vững bằng `SharedPreferences`.
- **Các Activity:**
  - `MainActivity` — Màn hình chính với 2 nút điều hướng
  - `SanPhamActivity` — Quản lý danh sách sản phẩm (Laptop Dell, Điện thoại Samsung, ...)
  - `NhaCungCapActivity` — Quản lý danh sách nhà cung cấp
- **Tính năng nổi bật:**
  - 🔍 **Tìm kiếm/Lọc** danh sách theo thời gian thực với `TextWatcher`
  - ➕ **Thêm mới** sản phẩm/nhà cung cấp vào danh sách
  - 💾 **Lưu trữ bền vững** bằng `SharedPreferences` (dữ liệu không mất khi đóng app)
  - 👆 **Xem chi tiết** khi nhấn vào item (hiển thị `Toast`)
- **Kiến thức áp dụng:**
  - Lưu trữ dữ liệu với `SharedPreferences` và `StringSet`
  - Lọc danh sách với `TextWatcher` + `ArrayAdapter.getFilter()`
  - Thêm phần tử động vào `ArrayList` và cập nhật `Adapter`
  - Xử lý sự kiện `setOnItemClickListener` trên `ListView`
  - Quản lý vòng đời dữ liệu (load/save)

---

## 💻 Yêu cầu hệ thống

- **Android Studio** phiên bản Ladybug trở lên (khuyến nghị bản mới nhất)
- **JDK 11** trở lên
- **Android SDK** với API Level 36
- Thiết bị Android thật hoặc **Emulator** (min API 24)

---

## 🚀 Hướng dẫn cài đặt và chạy

1. **Clone repository:**
   ```bash
   git clone https://github.com/<username>/65132908-AndroidProgramming.git
   ```

2. **Mở project trong Android Studio:**
   - Mở Android Studio → `File` → `Open`
   - Chọn thư mục của ứng dụng cần chạy (ví dụ: `HelloAndroid/`, `BaiTH7_ListView1/`, ...)

3. **Sync Gradle:**
   - Android Studio sẽ tự động sync, nếu không hãy chọn `File` → `Sync Project with Gradle Files`

4. **Chạy ứng dụng:**
   - Chọn thiết bị/emulator từ thanh toolbar
   - Nhấn **▶ Run** hoặc `Shift + F10`

---

## 👨‍💻 Tác giả

- **MSSV:** 65132908
- **Môn học:** Lập trình Android

---

<p align="center">
  <i>⭐ Nếu repository này hữu ích, hãy cho mình một star nhé!</i>
</p>