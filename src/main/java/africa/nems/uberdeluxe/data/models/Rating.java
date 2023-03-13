package africa.nems.uberdeluxe.data.models;

public enum Rating {
    BAD(1), GOOD(3), SATISFACTORY(2),EXCELLENT(4);


    private int rating;

    public  int getRating(){
        return rating;
    }
    Rating(int rating){
        this.rating = rating;
    }
}
