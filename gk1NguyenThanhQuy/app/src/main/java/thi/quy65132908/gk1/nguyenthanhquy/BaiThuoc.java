package thi.quy65132908.gk1.nguyenthanhquy;

public class BaiThuoc {
    private String tenBaiThuoc;
    private String thoiGian;
    private String moTa;
    private String huongDan;
    private int hinhAnh;

    public BaiThuoc(String tenBaiThuoc, String thoiGian, String moTa, String huongDan, int hinhAnh) {
        this.tenBaiThuoc = tenBaiThuoc;
        this.thoiGian = thoiGian;
        this.moTa = moTa;
        this.huongDan = huongDan;
        this.hinhAnh = hinhAnh;
    }

    public String getTenBaiThuoc() {
        return tenBaiThuoc;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public String getMoTa() {
        return moTa;
    }

    public String getHuongDan() {
        return huongDan;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }
}
