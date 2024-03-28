



public enum OrderStatus {
    PROCESSING ("P"),
    IN_DELIVERY("I"),
    COMPLETED("C");

    private String abkürzung;
    OrderStatus(String a){
        abkürzung = a;
    }



}
