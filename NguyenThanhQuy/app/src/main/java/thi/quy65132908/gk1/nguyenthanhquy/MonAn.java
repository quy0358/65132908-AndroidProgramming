package thi.quy65132908.gk1.nguyenthanhquy;

public class MonAn {
    private String ten;
    private String moTa;
    private String nguyenLieu;
    private String cachLam;
    private String thoiGian;

    public MonAn() {
    }

    public MonAn(String ten, String moTa, String nguyenLieu, String cachLam, String thoiGian) {
        this.ten = ten;
        this.moTa = moTa;
        this.nguyenLieu = nguyenLieu;
        this.cachLam = cachLam;
        this.thoiGian = thoiGian;
    }

    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public String getNguyenLieu() { return nguyenLieu; }
    public void setNguyenLieu(String nguyenLieu) { this.nguyenLieu = nguyenLieu; }

    public String getCachLam() { return cachLam; }
    public void setCachLam(String cachLam) { this.cachLam = cachLam; }

    public String getThoiGian() { return thoiGian; }
    public void setThoiGian(String thoiGian) { this.thoiGian = thoiGian; }
}
