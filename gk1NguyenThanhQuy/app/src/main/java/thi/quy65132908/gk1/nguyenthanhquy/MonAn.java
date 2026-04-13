package thi.quy65132908.gk1.nguyenthanhquy;

public class MonAn {
    private int hinhAnh;
    private String tenMon;
    private String gia;
    private String moTa;
    private String nguyenLieu;

    public MonAn(int hinhAnh, String tenMon, String gia, String moTa, String nguyenLieu) {
        this.hinhAnh = hinhAnh;
        this.tenMon = tenMon;
        this.gia = gia;
        this.moTa = moTa;
        this.nguyenLieu = nguyenLieu;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }

    public String getTenMon() {
        return tenMon;
    }

    public String getGia() {
        return gia;
    }

    public String getMoTa() {
        return moTa;
    }

    public String getNguyenLieu() {
        return nguyenLieu;
    }
}
