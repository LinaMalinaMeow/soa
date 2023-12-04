import { Header } from "../components/Header"
import { Footer } from "../components/Footer"
import { products } from "../data/testData"
import droneimg from "../img/drone.png"
import motoimg from "../img/moto.png"
import shipimg from "../img/ship.webp"
import heliimg from "../img/heli.png"
import hoverimg from "../img/hover.png"
import { FuelType, IVehicle, VehicleType } from "../data/models"
import { Link } from 'react-router-dom';
import { allVeh } from "../http/userAPI"
import { Button, Form, FormSelect } from "react-bootstrap"
import React, { useEffect, useState, useRef } from 'react'
import { ToastContainer, toast } from 'react-toastify';
import axios from "axios"
import { $host } from "../http"

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

export function CatalogPage() {

    const [veh, setVeh] = useState<IVehicle[]>([])
    let stat = 0;
    useEffect(() => {
        allVeh().then(data => {
            setVeh(data.data)
        }).catch((e) => {
            toast.error('Validation error !', {
                position: toast.POSITION.TOP_RIGHT
            });
        })
    }, [stat])

    const [page, setPage] = useState(1)

    const [coorxi, setCoorxi] = useState('')
    const [coorxa, setCoorxa] = useState('')

    const [cooryi, setCooryi] = useState('')
    const [coorya, setCoorya] = useState('')

    const [enginePoweri, setEnginePoweri] = useState('')
    const [enginePowera, setEnginePowera] = useState('')

    const [type1, setType1] = useState('ALL')
    const [type2, setType2] = useState('ALL')

    const [sort, setSort] = useState('asc')
    const [limit, setLimit] = useState('3')

    const myRefname = useRef<HTMLButtonElement>(null)

    const startFil = (e: React.ChangeEvent<any>) => {
        e.preventDefault()
        let query: String = '?' + 'sort=' + sort + '&limit=' + limit
        if (type1 != 'ALL') {
            query += '&type='
            query += type1
        }
        if (type2 != 'ALL') {
            query += '&fuelType='
            query += type2
        }
        if (page != 1) {
            query += '&offset='
            query += (parseInt(limit) * (page - 1)).toString()
        }
        if (coorxi != '') {
            query += '&minCoordinatesX='
            query += coorxi
        }
        if (coorxa != '') {
            query += '&maxCoordinatesX='
            query += coorxa
        }
        if (cooryi != '') {
            query += '&minCoordinatesY='
            query += cooryi
        }
        if (coorya != '') {
            query += '&maxCoordinatesY='
            query += coorya
        }
        if (enginePoweri != '') {
            query += '&minEnginePower='
            query += enginePoweri
        }
        if (enginePowera != '') {
            query += '&maxEnginePower='
            query += enginePowera
        }
        console.log(query)
        $host.get('/vehicles' + query).then((response) => {
            setVeh(response.data)
        }).catch((e) => {
            toast.error('Validation error !', {
                position: toast.POSITION.TOP_RIGHT
            });
        })
    }
    useEffect(() => {
        myRefname.current?.click()
    }, [page])
    const prevPage = () => {
        if (page > 1) {
            setPage(page - 1)
        }
    }
    const nextPage = () => {
        setPage(page + 1)
    }
    return (
        <div className="page">
            <Header />
            <ToastContainer></ToastContainer>
            <div className="page__wrap">
                <div className="container">
                    <h1 className="page__title">Catalog</h1>
                    <p className="page__descr">Let chooze!</p>
                    <div className="catalog__all">
                        <div className="catalog__aside">
                            <h3>Filters</h3>
                            <Form onSubmit={startFil}>
                                <div className="inputs__wrap">
                                    <Form.Group className="mb-3" controlId="formBasicText">
                                        <Form.Control type="text" placeholder="X - min"
                                            value={coorxi}
                                            onChange={(e: React.ChangeEvent<HTMLInputElement>) => setCoorxi(e.target.value)}
                                        />
                                    </Form.Group><Form.Group className="mb-3" controlId="formBasicText">
                                        <Form.Control type="text" placeholder="X - max"
                                            value={coorxa}
                                            onChange={(e: React.ChangeEvent<HTMLInputElement>) => setCoorxa(e.target.value)}
                                        />
                                    </Form.Group>
                                </div>
                                <div className="inputs__wrap">
                                    <Form.Group className="mb-3" controlId="formBasicText">
                                        <Form.Control type="text" placeholder="Y - min"
                                            value={cooryi}
                                            onChange={(e: React.ChangeEvent<HTMLInputElement>) => setCooryi(e.target.value)}
                                        />
                                    </Form.Group>
                                    <Form.Group className="mb-3" controlId="formBasicText">
                                        <Form.Control type="text" placeholder="Y - max"
                                            value={coorya}
                                            onChange={(e: React.ChangeEvent<HTMLInputElement>) => setCoorya(e.target.value)}
                                        />
                                    </Form.Group>
                                </div>
                                <div className="inputs__wrap">
                                    <Form.Group className="mb-3" controlId="formBasicText">
                                        <Form.Control type="number" placeholder="Min - Engine Power"
                                            value={enginePoweri}
                                            onChange={(e: React.ChangeEvent<HTMLInputElement>) => setEnginePoweri(e.target.value)}
                                        />
                                    </Form.Group><Form.Group className="mb-3" controlId="formBasicText">
                                        <Form.Control type="number" placeholder="Max - Engine Power"
                                            value={enginePowera}
                                            onChange={(e: React.ChangeEvent<HTMLInputElement>) => setEnginePowera(e.target.value)}
                                        />
                                    </Form.Group>
                                </div>

                                <Form.Select aria-label="Default select example" defaultValue="HELICOPTER" value={type1} onChange={(e: React.ChangeEvent<HTMLSelectElement>) => setType1(e.target.value)}>
                                    <option value="ALL">VehicleType</option>
                                    {Object.keys(VehicleType).map(key =>
                                        <option value={key}>{key}</option>
                                    )}
                                </Form.Select>
                                <Form.Select aria-label="Default select example" defaultValue="ELECTRICITY" value={type2} onChange={(e: React.ChangeEvent<HTMLSelectElement>) => setType2(e.target.value)}>
                                    <option value="ALL">FuelType</option>
                                    {Object.keys(FuelType).map(key =>
                                        <option value={key}>{key}</option>
                                    )}
                                </Form.Select>
                                <Form.Select aria-label="Default select example" value={sort} onChange={(e: React.ChangeEvent<HTMLSelectElement>) => setSort(e.target.value)}>
                                    <option value="asc">SORT: ASC</option>
                                    <option value="desc">SORT: DESC</option>
                                </Form.Select>

                                <Form.Select aria-label="Default select example" value={limit} onChange={(e: React.ChangeEvent<HTMLSelectElement>) => setLimit(e.target.value)}>
                                    <option value="3">Limit (default - 3)</option>
                                    <option value="6">6</option>
                                    <option value="9">9</option>
                                </Form.Select>
                                <Button variant="primary" ref={myRefname} type="submit">
                                    Filter
                                </Button>
                            </Form>
                        </div>
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
                            <div className="long">
                                Page <span>{page}</span> <br />
                                <button className="product__del" onClick={prevPage}>Prev</button>
                                <button className="product__del" onClick={nextPage}>Next</button>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <Footer />
        </div>
    )
}
