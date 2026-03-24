package ntquy.ntu.bailamthem3_recyclerview;

public class RssItem {
    private String title;
    private String description;
    private String pubDate;
    private String link;
    private String imageUrl;
    private boolean bookmarked;

    public RssItem() {
    }

    public RssItem(String title, String description, String pubDate, String link, String imageUrl) {
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.link = link;
        this.imageUrl = imageUrl;
        this.bookmarked = false;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPubDate() { return pubDate; }
    public void setPubDate(String pubDate) { this.pubDate = pubDate; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public boolean isBookmarked() { return bookmarked; }
    public void setBookmarked(boolean bookmarked) { this.bookmarked = bookmarked; }
}
