package ua.my.model;

import javax.persistence.*;

@Entity
@Table(name="Conference")
public class Conference {
    @Id
    @GeneratedValue
    private long id;
    
    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;

    private String name;
    @Column(length = 5000)
    private String description;
    private int price;
    private String date;
    private String image;

    private String email;
    public Conference() {}

    public Conference(Group group, String name, int price, String date, String image, String email, String description) {
        this.group = group;
        this.name = name;
        this.description = description;
        this.price = price;
        this.date = date;
        this.image = image;
        this.email = email;
    }


    public String getShotDescription(){
        return  description.substring(0, 200) + "...";
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Conference{" +
                "id=" + id +
                ", group=" + group +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
