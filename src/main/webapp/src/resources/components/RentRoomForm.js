import React from "react"
import "../styles/RentRoom.css"
import Button from "react-bootstrap/Button"
import Form from 'react-bootstrap/Form'
import Col from 'react-bootstrap/Col'
import Row from 'react-bootstrap/Row'
import 'bootstrap/dist/css/bootstrap.min.css'

class RentRoomForm extends React.Component{

    constructor(props) {
        super(props);

        this.state= {
            title : '',
            address: '',
            price: '',
            area : '',
            description: '',
        };

    }

    handleSubmit = e => {
        console.log("dupa");
        console.log(this.state.title);
        e.preventDefault();
        let room = {
            title: this.state.title,
            address: this.state.address,
            price: this.state.price,
            area: this.state.area,
            description: this.state.description
        };


        fetch('/rent-room', {
            method: 'post',
            body: JSON.stringify(room),
            headers: {'Content-Type': 'application/json'}
        }).then( (response) => response.json())
        this.state= {
            title : '',
            address: '',
            price: '',
            area : '',
            description: '',

        };

    };

    handleChange = e => {
        this.setState({[e.target.name]: e.target.value});
    };

    render() {
        return (
            <Form style = {{display: 'flex', justifyContent: 'center', flexDirection: 'row',}} onSubmit={e => this.handleSubmit(e)}>
                <Col>
                    <Row style = {{display: 'flex', justifyContent: 'center', flexDirection: 'row',}} >
                        <Form.Group controlId = "formGridName">
                            <Form.Label>Title</Form.Label>
                            <Form.Control type="text" name = "title" value = {this.state.title} onChange = {this.handleChange}/>
                        </Form.Group>
                    </Row>
                    <Row style = {{display: 'flex', justifyContent: 'center', flexDirection: 'row',}} >
                        <Form.Group controlId = "formGridName">
                            <Form.Label>Title</Form.Label>
                            <Form.Control type="text" name = "address" value = {this.state.address} onChange = {this.handleChange}/>
                        </Form.Group>
                    </Row>
                    <Row style = {{display: 'flex', justifyContent: 'center', flexDirection: 'row',}} >
                        <Form.Group controlId = "formGridName">
                            <Form.Label>Title</Form.Label>
                            <Form.Control type="text" name = "price" value = {this.state.price} onChange = {this.handleChange}/>
                        </Form.Group>
                    </Row>
                    <Row style = {{display: 'flex', justifyContent: 'center', flexDirection: 'row',}} >
                        <Form.Group controlId = "formGridName">
                            <Form.Label>Title</Form.Label>
                            <Form.Control type="text" name = "area" value = {this.state.area} onChange = {this.handleChange}/>
                        </Form.Group>
                    </Row>
                    <Row style = {{display: 'flex', justifyContent: 'center', flexDirection: 'row',}} >
                        <Form.Group controlId = "formGridName">
                            <Form.Label>Title</Form.Label>
                            <Form.Control type="text" name = "description" value = {this.state.description} onChange = {this.handleChange}/>
                        </Form.Group>
                    </Row>

                </Col>
                <Button variant="primary" type="submit"> Submit </Button>
            </Form>
        )
    }

}

export default RentRoomForm;
