/**
 *  Bestpay.com.cn Inc.
 *  Copyright (c) 2011-2019 All Rights Reserved.
 */
package ericzz.reflect;

import java.util.UUID;

/**
 *  
 *  * @author zz_huns  
 *  @version Id: Apple.java, v 0.1 2019/2/7 12:57 PM zz_huns Exp $$
 */
public class Apple implements Comparable<Apple> {

    private Integer color;

    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public int compareTo(Apple o) {
        return this.getColor().compareTo(o.getColor());
    }

}
