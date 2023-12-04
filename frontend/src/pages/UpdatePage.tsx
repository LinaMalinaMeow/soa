import { Header } from "../components/Header"
import { Footer } from "../components/Footer"
import { useParams } from 'react-router-dom';
import { FuelType, IVehicle, VehicleType } from "../data/models";
import { products } from "../data/testData";
import { Link } from 'react-router-dom';
import { getVeh, updCurrVeh } from "../http/userAPI"
import { Button, Form, FormSelect } from "react-bootstrap"
import React, { useEffect, useState } from 'react'
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export function UpdatePage() {
    const { productId } = useParams();
    const curr = products.find(prod => { return prod.id == productId });

    const [veh, setVeh] = useState<IVehicle>()
    let stat = 0;

    const [name, setName] = useState('')
    const [coorx, setCoorx] = useState('')
    const [coory, setCoory] = useState('')
    const [enginePower, setEnginePower] = useState('')
    const [type1, setType1] = useState('HELICOPTER')
    const [type2, setType2] = useState('ELECTRICITY')

    useEffect(() => {
        getVeh(productId).then(data => {
            setVeh(data.data)
            console.log(data)
            setName(data.data.name)
            setCoorx(data.data.coordinates.x.toString())
            setCoory(data.data.coordinates.y.toString())
            setEnginePower(data.data.enginePower.toString())
            setType1(data.data.type)
            setType2(data.data.fuelType)
        })
    }, [stat])

    const updVeh = async (e: React.ChangeEvent<any>) => {
        e.preventDefault()
        const response = await updCurrVeh(parseInt(productId!), name, { x: parseFloat(coorx), y: parseFloat(coory) }, enginePower, type1, type2)
        if (response[0] == 1) {
            toast.error('Validation error !', {
                position: toast.POSITION.TOP_RIGHT
            });
        } else {
            toast.success('Updated !', {
                position: toast.POSITION.TOP_RIGHT
            });
        }
        stat++;
    }

    return (
        <div className="page">
            <Header />
            <ToastContainer></ToastContainer>
            <div className="page__wrap">
                <div className="container">
                    <h1>{veh?.name}</h1>
                    <Form onSubmit={updVeh}>

                        <Form.Group className="mb-3" controlId="formBasicText">
                            <Form.Control type="text" placeholder="Name (from 3 to 120 chars)"
                                value={name}
                                onChange={(e: React.ChangeEvent<HTMLInputElement>) => setName(e.target.value)}
                            />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicText">
                            <Form.Control type="text" placeholder="X" required
                                value={coorx}
                                onChange={(e: React.ChangeEvent<HTMLInputElement>) => setCoorx(e.target.value)}
                            />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicText">
                            <Form.Control type="text" placeholder="Y" required
                                value={coory}
                                onChange={(e: React.ChangeEvent<HTMLInputElement>) => setCoory(e.target.value)}
                            />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicText">
                            <Form.Control type="number" placeholder="Engine Power" required
                                value={enginePower}
                                onChange={(e: React.ChangeEvent<HTMLInputElement>) => setEnginePower(e.target.value)}
                            />
                        </Form.Group>
                        <Form.Select aria-label="Default select example" defaultValue="HELICOPTER" value={type1} onChange={(e: React.ChangeEvent<HTMLSelectElement>) => setType1(e.target.value)}>
                            {Object.keys(VehicleType).map(key =>
                                <option value={key}>{key}</option>
                            )}
                        </Form.Select>
                        <Form.Select aria-label="Default select example" defaultValue="ELECTRICITY" value={type2} onChange={(e: React.ChangeEvent<HTMLSelectElement>) => setType2(e.target.value)}>
                            {Object.keys(FuelType).map(key =>
                                <option value={key}>{key}</option>
                            )}
                        </Form.Select>
                        <Button variant="primary" type="submit">
                            Save
                        </Button>
                    </Form>
                    <div className="product__btns">
                        <Link className="product__del" to={{ pathname: "/product/" + productId }}>Return to Vehicle</Link>
                    </div>

                </div>
            </div>
            <Footer />
        </div>
    )
}