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
  - [3. VD1 — Ví dụ LinearLayout & Button](#3-vd1--ví-dụ-linearlayout--button)
  - [4. VD2 — Máy Tính Bỏ Túi](#4-vd2--máy-tính-bỏ-túi)
- [Yêu cầu hệ thống](#-yêu-cầu-hệ-thống)
- [Hướng dẫn cài đặt và chạy](#-hướng-dẫn-cài-đặt-và-chạy)
- [Tác giả](#-tác-giả)

---

## 🎯 Giới thiệu

Đây là repository chứa các bài thực hành môn **Lập trình Android** với mã sinh viên **65132908**. Mỗi thư mục con tương ứng với một ứng dụng Android riêng biệt, được xây dựng nhằm rèn luyện các kỹ năng phát triển ứng dụng di động trên nền tảng Android — từ giao diện cơ bản đến xử lý sự kiện và tương tác người dùng.

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
├── HelloAndroid/          # Bài 1: Ứng dụng Hello World
│   ├── app/
│   │   └── src/main/
│   │       ├── java/tiil/edu/helloandroid/
│   │       │   └── MainActivity.java
│   │       └── res/layout/
│   │           └── activity_main.xml
│   ├── build.gradle.kts
│   └── settings.gradle.kts
│
├── AppCong/               # Bài 2: Ứng dụng Tính Tổng
│   ├── app/
│   │   └── src/main/
│   │       ├── java/tiil/edu/appcong/
│   │       │   └── MainActivity.java
│   │       └── res/layout/
│   │           └── activity_main.xml
│   ├── build.gradle.kts
│   └── settings.gradle.kts
│
├── VD1/                   # Bài 3: Ví dụ LinearLayout & Button
│   ├── app/
│   │   └── src/main/
│   │       ├── java/tiil/edu/vd1/
│   │       │   └── MainActivity.java
│   │       └── res/layout/
│   │           └── activity_main.xml
│   ├── build.gradle.kts
│   └── settings.gradle.kts
│
├── VD2/                   # Bài 4: Máy Tính Bỏ Túi
│   ├── app/
│   │   └── src/main/
│   │       ├── java/tiil/edu/vd2/
│   │       │   └── MainActivity.java
│   │       └── res/layout/
│   │           └── activity_main.xml
│   ├── build.gradle.kts
│   └── settings.gradle.kts
│
└── README.md
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

### 3. VD1 — Ví dụ LinearLayout & Button

> 🟡 Thực hành bố cục tuyến tính — Làm quen với `LinearLayout` và tùy chỉnh giao diện `Button`.

- **Package:** `tiil.edu.vd1`
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

### 4. VD2 — Máy Tính Bỏ Túi

> 🟣 Ứng dụng máy tính hoàn chỉnh — Thực hành xử lý nhiều phép tính và kiểm tra đầu vào.

- **Package:** `tiil.edu.vd2`
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
   - Chọn thư mục của ứng dụng cần chạy (ví dụ: `HelloAndroid/`, `AppCong/`, `VD1/` hoặc `VD2/`)

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