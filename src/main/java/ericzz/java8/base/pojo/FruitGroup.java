package ericzz.java8.base.pojo;

import java.util.LinkedList;
import java.util.List;

public class FruitGroup {


    private List<Banana> bananaList = new LinkedList<>();




    public List<Banana> getBananaList() {
        return bananaList;
    }

    public void setBananaList(List<Banana> bananaList) {
        this.bananaList = bananaList;
    }
}
