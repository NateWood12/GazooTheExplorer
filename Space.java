public class Space {
    LivingThing occupant;

    private Treasure cache;

    public Space(){
        occupant = null;
        cache = null;
    }
    public Space(LivingThing occupant){
        setOccupant(occupant);
    }

    public Space(Treasure occupant){
        setCache(occupant);
    }

    public void setOccupant(LivingThing occupant){
        this.occupant = occupant;
    }

    public LivingThing getOccupant(){
        return occupant;
    }
    public String getConsoleStr(boolean reveal){
        if (reveal == true) {
            if (cache != null){
                return cache.getConsoleStr();

            } else if (occupant != null) {
                return occupant.getConsoleStr();

            } else {
                return "-";

            }

        } else{
            if (occupant != null && occupant instanceof Explorer){
                return occupant.getConsoleStr();

            } else {
                return "-";

            }

        }

    }


    public Treasure getCache() {
        return cache;
    }

    public void setCache(Treasure cache) {
        this.cache = cache;
    }
}

