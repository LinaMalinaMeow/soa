import React, { useEffect, useState } from 'react'
import { Header } from "../components/Header"
import { Footer } from "../components/Footer"
import './Page.sass'
import droneimg from "../img/drone.png"
import motoimg from "../img/moto.png"
import shipimg from "../img/ship.webp"
import heliimg from "../img/heli.png"
import hoverimg from "../img/hover.png"
import { Button, Form, FormSelect } from "react-bootstrap"
import { searchEng } from '../http/userAPI'
import { FuelType, IVehicle, VehicleType } from '../data/models'
import { Link } from 'react-router-dom';

function choozeProductImage(type: VehicleType) {
    if (type == VehicleType.DRONE) {
        return droneimg
    }
    if (type == VehicleType.MOTORCYCLE) {
        return motoimg
    }
    if (type == VehicleType.SHIP) {
        return shipimg
    }
    if (type == VehicleType.HELICOPTER) {
        return heliimg
    }
    if (type == VehicleType.HOVERBOARD) {
        return hoverimg
    }
}

export function SecondPage() {

    const [veh, setVeh] = useState<IVehicle[]>([])

    const [enginePoweri, setEnginePoweri] = useState('')
    const [enginePowera, setEnginePowera] = useState('')

    const startFil = async (e: React.ChangeEvent<any>) => {
        e.preventDefault()
        const response = await searchEng(enginePoweri, enginePowera)
        setVeh(response.data)
        console.log(response)
    }
    return (
        <div className="page">
            <Header />
            <div className="page__wrap">
                <div className="container">
                    <h1 className="page__title">Second service</h1>

                    <Form onSubmit={startFil}>
                        <div className="inputs__wrap">
                            <Form.Group className="mb-3" controlId="formBasicText">
                                <Form.Control type="number" placeholder="Min - Engine Power" required
                                    value={enginePoweri}
                                    onChange={(e: React.ChangeEvent<HTMLInputElement>) => setEnginePoweri(e.target.value)}
                                />
                            </Form.Group><Form.Group className="mb-3" controlId="formBasicText">
                                <Form.Control type="number" placeholder="Max - Engine Power" required
                                    value={enginePowera}
                                    onChange={(e: React.ChangeEvent<HTMLInputElement>) => setEnginePowera(e.target.value)}
                                />
                            </Form.Group>
                        </div>

                        <Button variant="primary" type="submit">
                            Show
                        </Button>
                    </Form>
                    <div className="catalog__wrap">
                        {veh.map(product =>
                            <Link className="product__block" key={product.id} to={{ pathname: "/product/" + product.id }}>
                                <div className="product__img">
                                    <img src={choozeProductImage(product.type)} alt="" />
                                </div>

                                <span className="product__name">{product.name}</span>
                                <span>
                                    &#123;{product.coordinates.x}; {product.coordinates.y}&#125;
                                </span>
                                <span className="product__more">Learn more...</span>
                            </Link>
                        )}
                    </div>
                </div>
            </div>
            <Footer />
        </div>
    )
}