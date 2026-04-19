package thi.quy65132908.gk1.thigk;

// Lớp mô hình dữ liệu cho tin tức RSS
public class TinRss {

    private String tieuDe;     // Tiêu đề tin
    private String moTa;       // Mô tả tin
    private String ngayDang;   // Ngày đăng tin
    private String duongDan;   // Đường dẫn bài viết
    private String anhUrl;     // URL ảnh minh họa

    // Constructor mặc định
    public TinRss() {
    }

    // Constructor đầy đủ tham số
    public TinRss(String tieuDe, String moTa, String ngayDang, String duongDan, String anhUrl) {
        this.tieuDe = tieuDe;
        this.moTa = moTa;
        this.ngayDang = ngayDang;
        this.duongDan = duongDan;
        this.anhUrl = anhUrl;
    }

    // Getter và Setter
    public String getTieuDe() { return tieuDe; }
    public void setTieuDe(String tieuDe) { this.tieuDe = tieuDe; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public String getNgayDang() { return ngayDang; }
    public void setNgayDang(String ngayDang) { this.ngayDang = ngayDang; }

    public String getDuongDan() { return duongDan; }
    public void setDuongDan(String duongDan) { this.duongDan = duongDan; }

    public String getAnhUrl() { return anhUrl; }
    public void setAnhUrl(String anhUrl) { this.anhUrl = anhUrl; }
}
