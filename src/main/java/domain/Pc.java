package domain;

public class Pc {
    private Long id;
    private String speed;
    private String ram;
    private String hd;
    private String cd;
    private String price;
    private String count;

    private Product product;

    public Pc(Long id, Product product, String speed, String ram, String hd, String cd, String price, String count) {
        this.id = id;
        this.product = product;
        this.speed = speed;
        this.ram = ram;
        this.hd = hd;
        this.cd = cd;
        this.price = price;
        this.count = count;
    }

    public Long getId() {
        return id;
    }
    public String getModel() {
        return product.getModel();
    }

    public String getSpeed() {
        return speed;
    }

    public String getRam() {
        return ram;
    }

    public String getHd() {
        return hd;
    }

    public String getCd() {
        return cd;
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

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public void setHd(String hd) {
        this.hd = hd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Pc {"
                + "Id = " + id
                + ", model = " + getModel()
                + ", speed = " + speed
                + ", ram = " + ram
                + ", hd = " + hd
                + ", cd = " + cd
                + ", price = " + price
                + ", count = " + count
                + "}";
    }
}
