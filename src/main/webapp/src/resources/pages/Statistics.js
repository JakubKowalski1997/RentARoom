import c3 from 'c3';
import React from "react";
import "../../../node_modules/c3/c3.css";

class Statistics extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            loading: true,
            priceList: ['priceList'],
            areaList: ['areaList'],
        };

        this.changeData = this.changeData.bind(this);
    }

    refreshTable() {
        fetch("/room/area-price-statistic")
            .then(response => {
                if (response.ok) {
                    return response;
                }
                throw Error(response.status);
            })
            .then(response => response.json())
            .then(data => {
                this.state.priceList = this.state.priceList.concat(data["priceList"]);
                this.state.areaList = this.state.areaList.concat(data["areaList"]);

            })
            .then(() => {
                this.renderChart();
                this.state.loading = false;
            })
            .catch(error => console.log("Following error occurred: " + error));
    }

    renderChart() {
        c3.generate({
            bindto: "#chart1",
            data: {
                xs: {
                    priceList: 'areaList',
                },
                columns: [this.state.areaList, this.state.priceList],
                type: 'scatter'
            },

        });
    }

    componentDidMount() {
        this.refreshTable();
        console.log(this.state.priceList);
        console.log(this.state.areaList);
        // this.renderChart();
    }

    componentDidUpdate() {
        this.renderChart();
    }

    render() {
        return (
            <div>
                <div id="chart1"/>
            </div>
        );
    }
}

export default Statistics;