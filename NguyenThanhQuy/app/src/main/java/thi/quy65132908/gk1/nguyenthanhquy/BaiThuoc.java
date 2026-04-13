package thi.quy65132908.gk1.nguyenthanhquy;

public class BaiThuoc {
    private String ten;
    private String moTa;
    private String thanhPhan;
    private String cachDung;
    private String thoiGian;

    public BaiThuoc() {
    }

    public BaiThuoc(String ten, String moTa, String thanhPhan, String cachDung, String thoiGian) {
        this.ten = ten;
        this.moTa = moTa;
        this.thanhPhan = thanhPhan;
        this.cachDung = cachDung;
        this.thoiGian = thoiGian;
    }

    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public String getThanhPhan() { return thanhPhan; }
    public void setThanhPhan(String thanhPhan) { this.thanhPhan = thanhPhan; }

    public String getCachDung() { return cachDung; }
    public void setCachDung(String cachDung) { this.cachDung = cachDung; }

    public String getThoiGian() { return thoiGian; }
    public void setThoiGian(String thoiGian) { this.thoiGian = thoiGian; }
}
