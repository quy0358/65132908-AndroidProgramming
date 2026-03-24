package ntquy.ntu.appmonan;

public class MonAn {
    private int hinhAnh;
    private String tenMon;
    private String gia;
    private String moTa;

    public MonAn(int hinhAnh, String tenMon, String gia, String moTa) {
        this.hinhAnh = hinhAnh;
        this.tenMon = tenMon;
        this.gia = gia;
        this.moTa = moTa;
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
}
