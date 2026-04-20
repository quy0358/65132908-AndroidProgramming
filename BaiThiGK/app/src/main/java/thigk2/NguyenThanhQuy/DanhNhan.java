package thigk2.NguyenThanhQuy;

// Lớp mô hình dữ liệu cho Danh nhân lịch sử
public class DanhNhan {

    private int hinhAnh;       // ID ảnh drawable
    private String hoTen;      // Họ và tên danh nhân
    private String queQuan;    // Quê quán

    // Hàm khởi tạo
    public DanhNhan(int hinhAnh, String hoTen, String queQuan) {
        this.hinhAnh = hinhAnh;
        this.hoTen = hoTen;
        this.queQuan = queQuan;
    }

    // Các phương thức getter
    public int getHinhAnh() {
        return hinhAnh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getQueQuan() {
        return queQuan;
    }
}
