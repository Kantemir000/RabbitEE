package domain;

public class Product {
    private String model;
    private String maker;
    private String type;

    public Product(String model, String maker, String type) {
        this.model = model;
        this.maker = maker;
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public String getMaker() {
        return maker;
    }

    public String getType() {
        return type;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Product {"
                + ", model = " + model
                + ", maker = " + maker
                + ", type = " + type
                + "}";
    }

}
