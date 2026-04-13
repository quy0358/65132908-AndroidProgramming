package thi.quy65132908.gk1.nguyenthanhquy;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RssParser {

    public static List<RssItem> parse(String rssUrl) throws Exception {
        List<RssItem> items = new ArrayList<>();

        URL url = new URL(rssUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);

        InputStream inputStream = conn.getInputStream();

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(false);
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(inputStream, "UTF-8");

        boolean insideItem = false;
        RssItem currentItem = null;
        String tagName = "";

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    tagName = parser.getName();
                    if ("item".equalsIgnoreCase(tagName)) {
                        insideItem = true;
                        currentItem = new RssItem();
                    }
                    break;

                case XmlPullParser.TEXT:
                    if (insideItem && currentItem != null) {
                        String text = parser.getText();
                        if (text != null) {
                            text = text.trim();
                            if (!text.isEmpty()) {
                                switch (tagName) {
                                    case "title":
                                        currentItem.setTitle(text);
                                        break;
                                    case "description":
                                        // Trích xuất ảnh và text từ description
                                        String imgUrl = extractImageUrl(text);
                                        if (imgUrl != null && currentItem.getImageUrl() == null) {
                                            currentItem.setImageUrl(imgUrl);
                                        }
                                        currentItem.setDescription(stripHtml(text));
                                        break;
                                    case "pubDate":
                                        currentItem.setPubDate(text);
                                        break;
                                    case "link":
                                        currentItem.setLink(text);
                                        break;
                                }
                            }
                        }
                    }
                    break;

                case XmlPullParser.END_TAG:
                    if ("item".equalsIgnoreCase(parser.getName())) {
                        if (currentItem != null && currentItem.getTitle() != null) {
                            items.add(currentItem);
                        }
                        insideItem = false;
                        currentItem = null;
                    }
                    tagName = "";
                    break;
            }
            eventType = parser.next();
        }

        inputStream.close();
        conn.disconnect();
        return items;
    }

    // Trích xuất URL ảnh từ thẻ <img> trong description
    private static String extractImageUrl(String html) {
        if (html == null) return null;
        Pattern pattern = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    // Loại bỏ thẻ HTML và trả về text thuần
    private static String stripHtml(String html) {
        if (html == null) return "";
        String text = html.replaceAll("<[^>]+>", "").trim();
        // Loại bỏ ký tự thừa
        text = text.replaceAll("\\s+", " ").trim();
        return text;
    }
}
