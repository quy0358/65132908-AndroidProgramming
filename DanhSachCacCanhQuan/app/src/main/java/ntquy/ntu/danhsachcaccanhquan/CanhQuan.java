package ntquy.ntu.danhsachcaccanhquan;

public class CanhQuan {
    private String tenCanhQuan;
    private int hinhAnh;

    public CanhQuan(String tenCanhQuan, int hinhAnh) {
        this.tenCanhQuan = tenCanhQuan;
        this.hinhAnh = hinhAnh;
    }

    public String getTenCanhQuan() {
        return tenCanhQuan;
    }

    public void setTenCanhQuan(String tenCanhQuan) {
        this.tenCanhQuan = tenCanhQuan;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
