package no.ntnu.webshop.group12.webshop.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "images")
@Schema(description = "A collection of image", name = "WebpImage")
public class WebpImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TEXT")
    private String primaryImage;

    @Column(columnDefinition = "TEXT")
    private String imageWebp512;

    @Column(columnDefinition = "TEXT")
    private String imageWebp1024;

    @Column(columnDefinition = "TEXT")
    private String imageWebp2048;

    public WebpImage() {
    }

    private WebpImage(String primaryImage, String imageWebp512, String imageWebp1024, String imageWebp2048) {
        this.primaryImage = primaryImage;
        this.imageWebp512 = imageWebp512;
        this.imageWebp1024 = imageWebp1024;
        this.imageWebp2048 = imageWebp2048;
    }

    public String getPrimaryImage() {
        return primaryImage;
    }

    public void setImage(String image) {
        this.primaryImage = image;
    }

    public String getImageWebp512() {
        return imageWebp512;
    }

    public void setImageWebp512(String imageWebp512) {
        this.imageWebp512 = imageWebp512;
    }

    public String getImageWebp1024() {
        return imageWebp1024;
    }

    public void setImageWebp1024(String imageWebp1024) {
        this.imageWebp1024 = imageWebp1024;
    }

    public String getImageWebp2048() {
        return imageWebp2048;
    }

    public void setImageWebp2048(String imageWebp2048) {
        this.imageWebp2048 = imageWebp2048;
    }

    public static class Builder {

        private String primaryImage;

        private String imageWebp512;

        private String imageWebp1024;

        private String imageWebp2048;

        public Builder image(String primaryImage) {
            this.primaryImage = primaryImage;
            return this;
        }

        public Builder imageWebp512(String imageWebp512) {
            this.imageWebp512 = imageWebp512;
            return this;
        }

        public Builder imageWebp1024(String imageWebp1024) {
            this.imageWebp1024 = imageWebp1024;
            return this;
        }

        public Builder imageWebp2048(String imageWebp2048) {
            this.imageWebp2048 = imageWebp2048;
            return this;
        }

        public Builder imageAll(String path) {
            String filename = path.substring(path.lastIndexOf('/'), path.lastIndexOf('.'));
            String folderpath = path.substring(0, path.lastIndexOf('/'));
            this.primaryImage = String.format("%s", path);
            this.imageWebp512 = String.format("%s/512w%s-512w.webp", folderpath, filename);
            this.imageWebp1024 = String.format("%s/1024w%s-1024w.webp", folderpath, filename);
            this.imageWebp2048 = String.format("%s/2048w%s-2048w.webp", folderpath, filename);
            return this;
        }

        public WebpImage build() {
            return new WebpImage(primaryImage, imageWebp512, imageWebp1024, imageWebp2048);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((primaryImage == null) ? 0 : primaryImage.hashCode());
        result = prime * result + ((imageWebp512 == null) ? 0 : imageWebp512.hashCode());
        result = prime * result + ((imageWebp1024 == null) ? 0 : imageWebp1024.hashCode());
        result = prime * result + ((imageWebp2048 == null) ? 0 : imageWebp2048.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WebpImage other = (WebpImage) obj;
        if (id != other.id)
            return false;
        if (primaryImage == null) {
            if (other.primaryImage != null)
                return false;
        } else if (!primaryImage.equals(other.primaryImage))
            return false;
        if (imageWebp512 == null) {
            if (other.imageWebp512 != null)
                return false;
        } else if (!imageWebp512.equals(other.imageWebp512))
            return false;
        if (imageWebp1024 == null) {
            if (other.imageWebp1024 != null)
                return false;
        } else if (!imageWebp1024.equals(other.imageWebp1024))
            return false;
        if (imageWebp2048 == null) {
            if (other.imageWebp2048 != null)
                return false;
        } else if (!imageWebp2048.equals(other.imageWebp2048))
            return false;
        return true;
    }

}
