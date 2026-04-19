package thi.quy65132908.gk1.thigk;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Lớp xử lý phân tích dữ liệu RSS từ VN Express
public class BoXuLyRss {

    // Phương thức phân tích dữ liệu RSS từ URL
    public static List<TinRss> phanTich(String rssUrl) throws Exception {
        List<TinRss> danhSachTin = new ArrayList<>();

        URL url = new URL(rssUrl);
        HttpURLConnection ketNoi = (HttpURLConnection) url.openConnection();
        ketNoi.setRequestMethod("GET");
        ketNoi.setConnectTimeout(10000);
        ketNoi.setReadTimeout(10000);

        InputStream luongNhap = ketNoi.getInputStream();

        XmlPullParserFactory nhaMay = XmlPullParserFactory.newInstance();
        nhaMay.setNamespaceAware(false);
        XmlPullParser boXuLy = nhaMay.newPullParser();
        boXuLy.setInput(luongNhap, "UTF-8");

        boolean dangTrongItem = false;
        TinRss tinHienTai = null;
        String tenThe = "";

        int loaiSuKien = boXuLy.getEventType();
        while (loaiSuKien != XmlPullParser.END_DOCUMENT) {
            switch (loaiSuKien) {
                case XmlPullParser.START_TAG:
                    tenThe = boXuLy.getName();
                    if (tenThe.equalsIgnoreCase("item")) {
                        dangTrongItem = true;
                        tinHienTai = new TinRss();
                    } else if (dangTrongItem && tenThe.equalsIgnoreCase("enclosure")) {
                        String urlAnh = boXuLy.getAttributeValue(null, "url");
                        if (urlAnh != null && tinHienTai != null) {
                            tinHienTai.setAnhUrl(urlAnh);
                        }
                    }
                    break;

                case XmlPullParser.TEXT:
                    if (dangTrongItem && tinHienTai != null) {
                        String noiDung = boXuLy.getText().trim();
                        if (!noiDung.isEmpty()) {
                            if (tenThe.equalsIgnoreCase("title")) {
                                tinHienTai.setTieuDe(noiDung);
                            } else if (tenThe.equalsIgnoreCase("description")) {
                                // Trích xuất mô tả văn bản từ CDATA
                                String moTa = trichXuatMoTa(noiDung);
                                tinHienTai.setMoTa(moTa);
                                // Nếu chưa có ảnh, thử lấy từ description
                                if (tinHienTai.getAnhUrl() == null || tinHienTai.getAnhUrl().isEmpty()) {
                                    String urlAnh = trichXuatUrlAnh(noiDung);
                                    if (urlAnh != null) {
                                        tinHienTai.setAnhUrl(urlAnh);
                                    }
                                }
                            } else if (tenThe.equalsIgnoreCase("pubDate")) {
                                tinHienTai.setNgayDang(noiDung);
                            } else if (tenThe.equalsIgnoreCase("link")) {
                                tinHienTai.setDuongDan(noiDung);
                            }
                        }
                    }
                    break;

                case XmlPullParser.END_TAG:
                    if (boXuLy.getName().equalsIgnoreCase("item")) {
                        if (tinHienTai != null && tinHienTai.getTieuDe() != null) {
                            danhSachTin.add(tinHienTai);
                        }
                        dangTrongItem = false;
                        tinHienTai = null;
                    }
                    tenThe = "";
                    break;
            }
            loaiSuKien = boXuLy.next();
        }

        luongNhap.close();
        ketNoi.disconnect();

        return danhSachTin;
    }

    // Trích xuất URL ảnh từ thẻ <img> trong CDATA
    private static String trichXuatUrlAnh(String html) {
        Pattern mauTim = Pattern.compile("<img[^>]+src=[\"']([^\"']+)[\"']");
        Matcher boSoKhop = mauTim.matcher(html);
        if (boSoKhop.find()) {
            return boSoKhop.group(1);
        }
        return null;
    }

    // Trích xuất mô tả văn bản (bỏ thẻ HTML)
    private static String trichXuatMoTa(String html) {
        // Loại bỏ tất cả thẻ HTML
        String vanBan = html.replaceAll("<[^>]+>", "").trim();
        // Loại bỏ ký tự thừa
        vanBan = vanBan.replaceAll("\\s+", " ").trim();
        return vanBan;
    }
}
