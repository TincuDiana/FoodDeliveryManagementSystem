package bussinessLayer;

public class BaseProduct extends MenuItem{
    private float rating;
    private int calorii;
    private int proteine;
    private int grasimi;
    private int sodium;
    private int id;

    //constructori
   
    public BaseProduct(int id, String name, int price,float rating,int calories,int protein,int fat,int sodium) {
        super(id, name, price);
        this.rating=rating;
        this.calorii=calories;
        this.proteine=protein;
        this.grasimi=fat;
        this.sodium=sodium;
    }

    public BaseProduct(String name, int price) {
        super(name, price);
    }
    
    public BaseProduct(String name, int price,float rating,int calories,int protein,int fat,int sodium) {
        super(name, price);
        this.rating=rating;
        this.calorii=calories;
        this.proteine=protein;
        this.grasimi=fat;
        this.sodium=sodium;
    }
    
    //setteri si getteri
    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getCalorii() {
        return this.calorii;
    }

    public void setCalorii(int calories) {
        this.calorii = calories;
    }

    public int getProteine() {
        return this.proteine;
    }

    public void setProteine(int protein) {
        this.proteine = protein;
    }

    public int getGrasimi() {
        return grasimi;
    }

    public void setGrasimi(int fat) {
        this.grasimi = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }
    
    

}
