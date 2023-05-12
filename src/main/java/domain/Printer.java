package domain;

public class Printer {
    private Long id;
    private String color;
    private String type;
    private String price;
    private String count;
    private Product product;

    public Printer(Long id, Product product, String color, String type, String price, String count) {
        this.id = id;
        this.product = product;
        this.color = color;
        this.type = type;
        this.price = price;
        this.count = count;
    }

    public Long getId() {
        return id;
    }
    public String getModel() {
        return product.getModel();
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public String getPrice() {
        return price;
    }

    public String getCount() {
        return count;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setModel(String model) {
        product.setModel(model);
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Printer {"
                + "Id = " + id
                + ", model = " + getModel()
                + ", color = " + color
                + ", type = " + type
                + ", price = " + price
                + "}";
    }


}
