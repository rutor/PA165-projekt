package cz.muni.fi.pa165.dto;

import java.util.Objects;

/**
 *
 * @author xtrnkal
 */
public class HallDTO {

    private Long id;
    private String name;
    private Long capacity;
    private String address;

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, capacity, address);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HallDTO)) {
            return false;
        }
        HallDTO other = (HallDTO) obj;
        return Objects.equals(name, other.getName()) && Objects.equals(capacity, other.getCapacity()) && Objects.equals(address, other.getAddress());
    }
}
