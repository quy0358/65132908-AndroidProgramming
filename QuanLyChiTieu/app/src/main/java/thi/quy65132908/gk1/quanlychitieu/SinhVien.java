package thi.quy65132908.gk1.quanlychitieu;

/**
 * Model class đại diện cho một Sinh viên trong Firebase Realtime Database.
 * Firebase yêu cầu class phải có constructor rỗng và các getter/setter.
 */
public class SinhVien {
    private String maSV;
    private String hoTen;
    private double diemTB;

    // Constructor rỗng (Firebase yêu cầu)
    public SinhVien() {
    }

    // Constructor đầy đủ
    public SinhVien(String maSV, String hoTen, double diemTB) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.diemTB = diemTB;
    }

    // Getter & Setter
    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public double getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(double diemTB) {
        this.diemTB = diemTB;
    }

    @Override
    public String toString() {
        return maSV + " - " + hoTen + " - ĐTB: " + diemTB;
    }
}
