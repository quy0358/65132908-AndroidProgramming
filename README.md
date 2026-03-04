# 📱 65132908 - Android Programming

> Repository môn học **Lập trình Android** — Tổng hợp các bài thực hành xây dựng ứng dụng Android sử dụng Java.

---

## 📋 Mục lục

- [Giới thiệu](#-giới-thiệu)
- [Công nghệ sử dụng](#-công-nghệ-sử-dụng)
- [Cấu trúc dự án](#-cấu-trúc-dự-án)
- [Danh sách ứng dụng](#-danh-sách-ứng-dụng)
  - [1. HelloAndroid](#1-helloandroid)
  - [2. AppCong](#2-appcong---ứng-dụng-tính-tổng)
- [Yêu cầu hệ thống](#-yêu-cầu-hệ-thống)
- [Hướng dẫn cài đặt và chạy](#-hướng-dẫn-cài-đặt-và-chạy)
- [Tác giả](#-tác-giả)

---

## 🎯 Giới thiệu

Đây là repository chứa các bài thực hành môn **Lập trình Android** với mã sinh viên **65132908**. Mỗi thư mục con tương ứng với một ứng dụng Android riêng biệt, được xây dựng nhằm rèn luyện các kỹ năng phát triển ứng dụng di động trên nền tảng Android.

---

## 🛠 Công nghệ sử dụng

| Công nghệ | Chi tiết |
|---|---|
| **Ngôn ngữ** | Java 11 |
| **Build System** | Gradle (Kotlin DSL) |
| **Min SDK** | 24 (Android 7.0 Nougat) |
| **Target / Compile SDK** | 36 |
| **UI Framework** | ConstraintLayout, Material Design |
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

### 2. AppCong - Ứng dụng Tính Tổng

> 🔵 Ứng dụng tính tổng hai số — Thực hành xử lý sự kiện và tương tác UI.

- **Package:** `tiil.edu.appcong`
- **Mô tả:** Ứng dụng cho phép người dùng nhập hai số **a** và **b**, sau đó tính và hiển thị tổng khi nhấn nút **"Tính Tổng"**.
- **Giao diện bao gồm:**
  - `EditText` **edtA** — Ô nhập số a
  - `EditText` **edtB** — Ô nhập số b
  - `Button` **btnCong** — Nút "Tính Tổng"
  - `EditText` **edtKQ** — Ô hiển thị kết quả
  - Các `TextView` label: "a:", "b:", "Kết Quả:"
- **Kiến thức áp dụng:**
  - Thiết kế giao diện với nhiều widget (`EditText`, `Button`, `TextView`)
  - Bố cục bằng `ConstraintLayout` với ràng buộc giữa các view
  - Xử lý sự kiện người dùng (Button click)
  - Chuyển đổi kiểu dữ liệu (String → Number)

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
   - Chọn thư mục của ứng dụng cần chạy (ví dụ: `HelloAndroid/` hoặc `AppCong/`)

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