import React, { useEffect, useState } from 'react'
import { Header } from "../components/Header"
import { Footer } from "../components/Footer"
import './Page.sass'
import { Button, Form, FormSelect } from "react-bootstrap"
import { sumPow, avPow } from '../http/userAPI'
import { FuelType, VehicleType } from '../data/models'

export function StatsPage() {
    const [job, setJob] = useState('')
    const [summ, setSumm] = useState('')
    const [aver, setAver] = useState('')
    const [coory, setCoory] = useState('')
    const [enginePower, setEnginePower] = useState('')
    const [type1, setType1] = useState('HELICOPTER')
    const [type2, setType2] = useState('ELECTRICITY')
    const [emps, setEmps] = useState([])

    let stat = 0
    const sumPower = async () => {
        const response = await sumPow()
        setSumm(response.data)
    }
    const avPower = async () => {
        const response = await avPow()
        setAver(response.data)
    }
    
    useEffect(() => {
        sumPower();
        avPower();
      }, []);
    return (
        <div className="page">
            <Header />
            <div className="page__wrap">
                <div className="container">
                    <h1 className="page__title">Stats</h1>
                    <p className="page__descr">Engine Power</p>
                    <table>
                        <tr>
                            <th>Criteria</th>
                            <th>Info</th>
                        </tr>
                        <tr>
                            <td>Summ</td>
                            <td>{summ}</td>
                        </tr>
                        <tr>
                            <td>Average</td>
                            <td>{aver}</td>
                        </tr>
                    </table>

                </div>
            </div>
            <Footer />
        </div>
    )
}