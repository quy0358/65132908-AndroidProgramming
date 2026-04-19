package thi.quy65132908.gk1.thigk;

// Lớp mô hình dữ liệu cho món ăn
public class MonAn {

    private String tenMon;       // Tên món ăn
    private String moTa;         // Mô tả món ăn
    private String nguyenLieu;   // Nguyên liệu chính
    private String thoiGianNau;  // Thời gian nấu

    // Constructor mặc định
    public MonAn() {
    }

    // Constructor đầy đủ tham số
    public MonAn(String tenMon, String moTa, String nguyenLieu, String thoiGianNau) {
        this.tenMon = tenMon;
        this.moTa = moTa;
        this.nguyenLieu = nguyenLieu;
        this.thoiGianNau = thoiGianNau;
    }

    // Getter và Setter
    public String getTenMon() { return tenMon; }
    public void setTenMon(String tenMon) { this.tenMon = tenMon; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public String getNguyenLieu() { return nguyenLieu; }
    public void setNguyenLieu(String nguyenLieu) { this.nguyenLieu = nguyenLieu; }

    public String getThoiGianNau() { return thoiGianNau; }
    public void setThoiGianNau(String thoiGianNau) { this.thoiGianNau = thoiGianNau; }
}
