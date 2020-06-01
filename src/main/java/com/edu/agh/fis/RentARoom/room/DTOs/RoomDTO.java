package com.edu.agh.fis.RentARoom.room.DTOs;

/**
 * RoomDTO
 *
 *
 * @author  Jakub Kowalski
 * @version 1.0
 * @since   2020-05-30
 */
public class RoomDTO {

    private String title;

    private String address;

    private String price;

    private String area;

    private String description;

    public RoomDTO() {
    }

    public RoomDTO(String title, String address, String price, String area, String description) {
        this.title = title;
        this.address = address;
        this.price = price;
        this.area = area;
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
