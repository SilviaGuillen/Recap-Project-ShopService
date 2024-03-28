import lombok.With;



public enum OrderStatus {
    PROCESSING ("P"),
    IN_DELIVERY("I"),
    COMPLETED("C");

    private String abkürzung;
    OrderStatus(String a){
        abkürzung = a;
    }
    public String getAbkürzung(){
        return abkürzung;
    }
    public void setAbkürzung(String a){
        this.abkürzung = a;
    }


}
