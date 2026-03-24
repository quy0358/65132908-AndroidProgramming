package ntquy.ntu.bailamthem3_recyclerview;

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
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);

        InputStream inputStream = connection.getInputStream();

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
                    if (tagName.equalsIgnoreCase("item")) {
                        insideItem = true;
                        currentItem = new RssItem();
                    } else if (insideItem && tagName.equalsIgnoreCase("enclosure")) {
                        String enclosureUrl = parser.getAttributeValue(null, "url");
                        if (enclosureUrl != null && currentItem != null) {
                            currentItem.setImageUrl(enclosureUrl);
                        }
                    }
                    break;

                case XmlPullParser.TEXT:
                    if (insideItem && currentItem != null) {
                        String text = parser.getText().trim();
                        if (!text.isEmpty()) {
                            if (tagName.equalsIgnoreCase("title")) {
                                currentItem.setTitle(text);
                            } else if (tagName.equalsIgnoreCase("description")) {
                                // Trích xuất mô tả văn bản từ CDATA
                                String desc = extractDescription(text);
                                currentItem.setDescription(desc);
                                // Nếu chưa có ảnh, thử lấy từ description
                                if (currentItem.getImageUrl() == null || currentItem.getImageUrl().isEmpty()) {
                                    String imgUrl = extractImageUrl(text);
                                    if (imgUrl != null) {
                                        currentItem.setImageUrl(imgUrl);
                                    }
                                }
                            } else if (tagName.equalsIgnoreCase("pubDate")) {
                                currentItem.setPubDate(text);
                            } else if (tagName.equalsIgnoreCase("link")) {
                                currentItem.setLink(text);
                            }
                        }
                    }
                    break;

                case XmlPullParser.END_TAG:
                    if (parser.getName().equalsIgnoreCase("item")) {
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
        connection.disconnect();

        return items;
    }

    // Trích xuất URL ảnh từ thẻ <img> trong CDATA
    private static String extractImageUrl(String html) {
        Pattern pattern = Pattern.compile("<img[^>]+src=[\"']([^\"']+)[\"']");
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    // Trích xuất mô tả văn bản (bỏ thẻ HTML)
    private static String extractDescription(String html) {
        // Loại bỏ thẻ <a> và <img>
        String text = html.replaceAll("<[^>]+>", "").trim();
        // Loại bỏ ký tự thừa
        text = text.replaceAll("\\s+", " ").trim();
        return text;
    }
}
