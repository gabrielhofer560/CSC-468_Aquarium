package sample;

public class Goldfish implements Fish {
    int health=6, hunger=0;
    public boolean newDay(){
        if(hunger>=90) health-=2;
        hunger+=40;
        health-=1;
        if(health<=0) return false;
        return true;
    }
    public void feedFish(int amount) {
        hunger -= amount;
        if(hunger<0) hunger=0;
        if(hunger>100) hunger=100;
    }
    public int getHealth(){ return health; }
    public int getHunger(){ return hunger; }
}
