package utt.cntt.food;

public class Food  {
    private int img;
    private String name;

    public Food(){

    }
    public Food(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Food{" +
                "img=" + img +
                ", name='" + name + '\'' +
                '}';
    }
}
