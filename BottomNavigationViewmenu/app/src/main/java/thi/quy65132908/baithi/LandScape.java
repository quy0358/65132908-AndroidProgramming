package thi.quy65132908.baithi;

public class LandScape {
    private String landscapeName;
    private String landscapeImage;
    private String description;
    private String resourceType; // "mipmap" or "drawable"

    // Constructor for backward compatibility (mipmap images)
    public LandScape(String landscapeName, String landscapeImage) {
        this.landscapeName = landscapeName;
        this.landscapeImage = landscapeImage;
        this.description = "";
        this.resourceType = "mipmap";
    }

    // New constructor with description and resource type
    public LandScape(String landscapeName, String landscapeImage, String description, String resourceType) {
        this.landscapeName = landscapeName;
        this.landscapeImage = landscapeImage;
        this.description = description;
        this.resourceType = resourceType;
    }

    public String getLandscapeName() {
        return landscapeName;
    }

    public void setLandscapeName(String landscapeName) {
        this.landscapeName = landscapeName;
    }

    public String getLandscapeImage() {
        return landscapeImage;
    }

    public void setLandscapeImage(String landscapeImage) {
        this.landscapeImage = landscapeImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
}
