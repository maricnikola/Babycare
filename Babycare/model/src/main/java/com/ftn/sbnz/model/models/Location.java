package com.ftn.sbnz.model.models;

import com.ftn.sbnz.model.models.enums.SymptomName;
import org.kie.api.definition.type.Position;

public class Location {
    @Position(0)
    private SymptomName item;

    @Position(1)
    private SymptomName location;

    public Location(SymptomName item, SymptomName location) {
        this.item = item;
        this.location = location;
    }

    public SymptomName getItem() {
        return item;
    }

    public void setItem(SymptomName item) {
        this.item = item;
    }

    public SymptomName getLocation() {
        return location;
    }

    public void setLocation(SymptomName location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        Location location1 = (Location) o;

        if (item != null ? !item.equals(location1.item) : location1.item != null) { return false; }
        if (location != null ? !location.equals(location1.location) : location1.location != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = item != null ? item.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

//    @Override
//    public SymptomName toSymptomName() {
//        return "Location{" +
//                "item='" + item.name() + '\'' +
//                ", location='" + location.name() + '\'' +
//                '}';
//    }
}
