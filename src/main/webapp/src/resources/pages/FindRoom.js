import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import ReactTable from "react-table";
import "react-table/react-table.css";

class FindRoom extends React.Component {
    constructor(props) {
        super(props);

        this.state = { data: [] };
    }

    refreshTable() {
        fetch("/room")
            .then(response => {
                if (response.ok) { return response; }
                throw Error(response.status);
            })
            .then(response => response.json())
            .then(data => { this.setState({ data: data, selected: {} }); })
            .catch(error => console.log("Following error occurred: " + error));
    }

    componentDidMount() {
        this.refreshTable();
    }

    renderTable = () => {
        const columns = [
            {
                Header: "Id",
                accessor: "id"
            },
            {
                Header: "title",
                accessor: "title"
            },
            // {
            //     Header: "address",
            //     accessor: "address"
            // },
            {
                Header: "price",
                accessor: "price"
            },
            {
                Header: "area",
                accessor: "area"
            },
            {
                Header: "description",
                accessor: "description"
            },
            {
                Header: "created",
                accessor: "created"
            }

        ];

        return (
            <ReactTable
                data={this.state.data}
                columns={columns}
                defaultSorted={[{ id: "id", desc: true }]}
            />
        );
    };

    render() {
        return this.renderTable();
    }
}

export default FindRoom;
