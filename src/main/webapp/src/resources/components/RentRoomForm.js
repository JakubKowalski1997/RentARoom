import React from "react"
import "../styles/RentRoom.css"
import Button from "react-bootstrap/Button"
import Form from 'react-bootstrap/Form'
import Col from 'react-bootstrap/Col'
import Row from 'react-bootstrap/Row'
import 'bootstrap/dist/css/bootstrap.min.css'

class RentRoomForm extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            title: '',
            address: '',
            price: '',
            area: '',
            description: '',
            street: '',
            houseNumber: '',
            city: '',
            zipcode: '',
            country: '',
            userName: ''
        };
        fetch("/username")
            .then(res => res.json())
            .then((result) => {
                this.setState({
                    userName: result.username
                });

            });
        console.log(this.state.userName)
    }

    handleSubmit = e => {
        e.preventDefault();
        let room = {
            title: this.state.title,
            address: {
                street: this.state.street,
                houseNumber: this.state.houseNumber,
                city: this.state.city,
                zipcode: this.state.zipcode,
                country: this.state.country,
            },
            price: this.state.price,
            area: this.state.area,
            description: this.state.description
        };
        fetch('/room', {
            method: 'POST',
            body: JSON.stringify(room),
            headers: {'Content-Type': 'application/json'}
        }).then((response) => {
            console.log(response);
            if (response.url.indexOf("login") !== -1) {
                this.redirectToLogin();
            } else {
                this.redirectToHome();
            }
        });
        this.state = {
            title: '',
            address: '',
            price: '',
            area: '',
            description: '',

        };

    };

    redirectToLogin() {
        window.location.replace("/login");
    }

    redirectToHome() {
        window.location.replace("/");

    }

    handleChange = e => {
        this.setState({[e.target.name]: e.target.value});
    };


    render() {
        return (
            <div>
                {
                    this.state.userName !== "anonymousUser" && this.state.userName !== ''
                        ? this.getRentRoomForm()
                        : <div className="row h-100 justify-content-center align-items-center">
                            <Button onClick={this.redirectToLogin}>LOGIN</Button>
                        </div>
                }
            </div>

        )
    }

    getRentRoomForm() {
        return (
            <div className="row h-100 justify-content-center align-items-center">
            <Form style={{width: '50%', display: 'flex', justifyContent: 'center', flexDirection: 'row',}}
                  onSubmit={e => this.handleSubmit(e)}>
                <Form.Group controlId="formGridName">
                    <Form.Label>Title</Form.Label>
                    <Form.Control type="text" name="title" value={this.state.title} onChange={this.handleChange}/>
                </Form.Group>
                <Row>
                    <Col>
                        <Form.Group controlId="formGridName">
                            <Form.Label>street</Form.Label>
                            <Form.Control type="text" name="street" value={this.state.street}
                                          onChange={this.handleChange}/>
                        </Form.Group>
                    </Col>

                    <Col>
                        <Form.Group controlId="formGridName">
                            <Form.Label>House number</Form.Label>
                            <Form.Control type="number" name="houseNumber" value={this.state.houseNumber}
                                          onChange={this.handleChange}/>
                        </Form.Group>
                    </Col>

                    <Col>
                        <Form.Group controlId="formGridName">
                            <Form.Label>city</Form.Label>
                            <Form.Control type="text" name="city" value={this.state.city}
                                          onChange={this.handleChange}/>
                        </Form.Group>
                    </Col>

                    <Col>
                        <Form.Group controlId="formGridName">
                            <Form.Label>zipcode</Form.Label>
                            <Form.Control type="text" name="zipcode" value={this.state.zipcode}
                                          onChange={this.handleChange}/>
                        </Form.Group>
                    </Col>

                    <Col>
                        <Form.Group controlId="formGridName">
                            <Form.Label>country</Form.Label>
                            <Form.Control type="text" name="country" value={this.state.country}
                                          onChange={this.handleChange}/>
                        </Form.Group>
                    </Col>
                </Row>
                <Form.Group controlId="formGridName">
                    <Form.Label>Price</Form.Label>
                    <Form.Control type="number" name="price" value={this.state.price} onChange={this.handleChange}/>
                </Form.Group>
                <Form.Group controlId="formGridName">
                    <Form.Label>Area</Form.Label>
                    <Form.Control type="number" name="area" value={this.state.area} onChange={this.handleChange}/>
                </Form.Group>
                <Form.Group controlId="formGridName">
                    <Form.Label>Description</Form.Label>
                    <Form.Control as="textarea" rows="3" type="text" name="description"
                                  value={this.state.description}
                                  onChange={this.handleChange}/>
                </Form.Group>
                <Button variant="primary" type="submit"> Submit </Button>
            </Form>
        </div>
        )
    }
}

export default RentRoomForm;
