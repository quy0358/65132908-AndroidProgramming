package thi.quy65132908.gk1.myapplication;

/**
 * Model class đại diện cho một khoản Chi tiêu trong Firebase Realtime Database.
 * Firebase yêu cầu class phải có constructor rỗng và các getter/setter.
 */
public class ChiTieu {
    private String id;
    private String tenChiTieu;
    private double soTien;
    private String danhMuc;

    // Constructor rỗng (Firebase yêu cầu)
    public ChiTieu() {
    }

    // Constructor đầy đủ
    public ChiTieu(String id, String tenChiTieu, double soTien, String danhMuc) {
        this.id = id;
        this.tenChiTieu = tenChiTieu;
        this.soTien = soTien;
        this.danhMuc = danhMuc;
    }

    // Getter & Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenChiTieu() {
        return tenChiTieu;
    }

    public void setTenChiTieu(String tenChiTieu) {
        this.tenChiTieu = tenChiTieu;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    /**
     * Lấy emoji icon theo danh mục
     */
    public String getIconDanhMuc() {
        switch (danhMuc) {
            case "Ăn uống": return "🍔";
            case "Di chuyển": return "🚗";
            case "Mua sắm": return "🛒";
            case "Giải trí": return "🎮";
            case "Hóa đơn": return "📄";
            default: return "💸";
        }
    }

    @Override
    public String toString() {
        return getIconDanhMuc() + " " + tenChiTieu + " - " +
                String.format("%,.0f", soTien) + "đ (" + danhMuc + ")";
    }
}
