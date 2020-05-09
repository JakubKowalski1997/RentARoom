package com.edu.agh.fis.RentARoom.room.DTOs;

import java.math.BigDecimal;
import java.util.List;

public class AreaToPriceStatisticDTO {
    private List<BigDecimal> priceList;
    private List<Double> areaList;

    public AreaToPriceStatisticDTO(List<BigDecimal> priceList, List<Double> areaList) {
        this.priceList = priceList;
        this.areaList = areaList;
    }

    public List<BigDecimal> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<BigDecimal> priceList) {
        this.priceList = priceList;
    }

    public List<Double> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Double> areaList) {
        this.areaList = areaList;
    }
}
