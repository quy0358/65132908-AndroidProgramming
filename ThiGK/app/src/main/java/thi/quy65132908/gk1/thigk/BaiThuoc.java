package thi.quy65132908.gk1.thigk;

// Lớp mô hình dữ liệu cho bài thuốc
public class BaiThuoc {

    private String tenBaiThuoc;   // Tên bài thuốc
    private String thoiGian;      // Thời gian thực hiện
    private String moTa;          // Mô tả chi tiết
    private String thanhPhan;     // Thành phần thuốc
    private String cachDung;      // Cách sử dụng

    // Constructor mặc định
    public BaiThuoc() {
    }

    // Constructor đầy đủ tham số
    public BaiThuoc(String tenBaiThuoc, String thoiGian, String moTa, String thanhPhan, String cachDung) {
        this.tenBaiThuoc = tenBaiThuoc;
        this.thoiGian = thoiGian;
        this.moTa = moTa;
        this.thanhPhan = thanhPhan;
        this.cachDung = cachDung;
    }

    // Getter và Setter
    public String getTenBaiThuoc() { return tenBaiThuoc; }
    public void setTenBaiThuoc(String tenBaiThuoc) { this.tenBaiThuoc = tenBaiThuoc; }

    public String getThoiGian() { return thoiGian; }
    public void setThoiGian(String thoiGian) { this.thoiGian = thoiGian; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public String getThanhPhan() { return thanhPhan; }
    public void setThanhPhan(String thanhPhan) { this.thanhPhan = thanhPhan; }

    public String getCachDung() { return cachDung; }
    public void setCachDung(String cachDung) { this.cachDung = cachDung; }
}
