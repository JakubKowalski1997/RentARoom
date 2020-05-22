import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import ReactTable from "react-table";
import "react-table/react-table.css";
import "../styles/FindRoom.css";
import Button from "react-bootstrap/Button";

class YourRooms extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            data: [],
            userName: ''
        };
        fetch("/username")
            .then(res => res.json())
            .then((result) => {
                this.setState({
                    userName: result.username
                });

            });
    }

    refreshTable() {
        fetch("/room/user")
            .then(response => {
                if (response.ok) {
                    return response;
                }
                throw Error(response.status);
            })
            .then(response => response.json())
            .then(data => {
                this.setState({data: data, selected: {}});
            })
            .catch(error => console.log("Following error occurred: " + error));
    }

    componentDidMount() {
        this.refreshTable();
    }

    redirectToLogin() {
        window.location.replace("/login");
    }

    renderTable = () => {
        const columns = [
            // {
            //     Header: "Id",
            //     accessor: "id"
            // },
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
                Header: "street",
                accessor: "address.street"
            },
            {
                Header: "house number",
                accessor: "address.houseNumber"
            },
            {
                Header: "city",
                accessor: "address.city"
            },
            // {
            //     Header: "description",
            //     accessor: "description"
            // },
            // {
            //     Header: "created",
            //     accessor: "created"
            // }

        ];

        return (
            <div>
                {
                    this.state.userName !== "anonymousUser" && this.state.userName !== ''
                        ? this.getYourRoomsTable(columns)
                        : <div className="row h-100 justify-content-center align-items-center">
                            <Button onClick={this.redirectToLogin}>LOGIN</Button>
                        </div>
                }
            </div>
        );
    };

    getYourRoomsTable(columns) {
        return <ReactTable
            data={this.state.data}
            columns={columns}
            defaultSorted={[{id: "id", desc: true}]}
            getTrProps={(state, rowInfo) => ({
                onClick: () => {
                    console.log(rowInfo);
                    var url = "/#/room/" + rowInfo.original.id;
                    window.location.replace(url);
                }
            })}
            className="-striped -highlight"
        />;
    }

    render() {
        return this.renderTable();
    }
}

export default YourRooms;
