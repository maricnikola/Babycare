package com.ftn.sbnz.model.models;

//import com.ftn.sbnz.model.models.enums.String;
import org.kie.api.definition.type.Position;

public class Fact {
    @Position(0)
    private String item;

    @Position(1)
    private String location;

    public Fact(String item, String location) {
        this.item = item;
        this.location = location;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        Fact fact1 = (Fact) o;

        if (item != null ? !item.equals(fact1.item) : fact1.item != null) { return false; }
        if (location != null ? !location.equals(fact1.location) : fact1.location != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = item != null ? item.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

//    @Override
//    public String toString() {
//        return "Location{" +
//                "item='" + item.name() + '\'' +
//                ", location='" + location.name() + '\'' +
//                '}';
//    }
}
