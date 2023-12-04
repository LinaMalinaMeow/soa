import React, { useEffect, useState } from 'react'
import { Header } from "../components/Header"
import { Footer } from "../components/Footer"
import './Page.sass'
import { Button, Form, FormSelect } from "react-bootstrap"
import { addVeh } from '../http/userAPI'
import { FuelType, VehicleType } from '../data/models'
import { useSnackbar } from "notistack"
import axios from "axios"
import { is } from 'typescript-is'
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export function FormPage() {
    const [job, setJob] = useState('')
    const [name, setName] = useState('')
    const [coorx, setCoorx] = useState('')
    const [coory, setCoory] = useState('')
    const [enginePower, setEnginePower] = useState('')
    const [type1, setType1] = useState('HELICOPTER')
    const [type2, setType2] = useState('ELECTRICITY')
    const [emps, setEmps] = useState([])


    let stat = 0
    const newVeh = async (e: React.ChangeEvent<any>) => {
        e.preventDefault()
        const response = await addVeh(name, { x: parseFloat(coorx), y: parseFloat(coory) }, enginePower, type1, type2)
        console.log(response)
        if (response[0] == 1) {
            toast.error('Validation error !', {
                position: toast.POSITION.TOP_RIGHT
            });
        } else {
            toast.success('Created !', {
                position: toast.POSITION.TOP_RIGHT
            });
            setName('')
            setCoorx('')
            setCoory('')
            setEnginePower('')
        }
        stat++;
    }

    return (
        <div className="page">

            <Header />
            <ToastContainer></ToastContainer>
            <div className="page__wrap">
                <div className="container">
                    <h1 className="page__title">Add new Vehicle</h1>
                    <p className="page__descr">Enter the vehicle details in the form below to add it to the site</p>
                    <Form onSubmit={newVeh}>

                        <Form.Group className="mb-3" controlId="formBasicText">
                            <Form.Control type="text" placeholder="Name (from 3 to 120 chars)" required
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
                                <option key={key} value={key}>{key}</option>
                            )}
                        </Form.Select>
                        <Form.Select aria-label="Default select example" defaultValue="ELECTRICITY" value={type2} onChange={(e: React.ChangeEvent<HTMLSelectElement>) => setType2(e.target.value)}>
                            {Object.keys(FuelType).map(key =>
                                <option value={key}>{key}</option>
                            )}
                        </Form.Select>
                        <Button variant="primary" type="submit">
                            Add
                        </Button>
                    </Form>
                </div>
            </div>
            <Footer />
        </div>
    )
}