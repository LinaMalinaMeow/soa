import { FuelType, IVehicle, VehicleType } from "./models";

let vech1: IVehicle = {
    id: 1,
    name: "Иранский дрон",
    coordinates: {
        x: 12,
        y: 12
    },
    creationDate: new Date(),
    enginePower: 2.2,
    type: VehicleType.DRONE,
    fuelType: FuelType.NUCLEAR
}
let vech2: IVehicle = {
    id: 2,
    name: "TRON MOTO",
    coordinates: {
        x: 2,
        y: 3
    },
    creationDate: new Date(),
    enginePower: 12.2,
    type: VehicleType.MOTORCYCLE,
    fuelType: FuelType.ELECTRICITY
}
let vech3: IVehicle = {
    id: 3,
    name: "Helicopter",
    coordinates: {
        x: -10,
        y: -10
    },
    creationDate: new Date(),
    enginePower: 12.2,
    type: VehicleType.HELICOPTER,
    fuelType: FuelType.ELECTRICITY
}
let vech4: IVehicle = {
    id: 4,
    name: "SUBWAY!!!",
    coordinates: {
        x: -8,
        y: 8
    },
    creationDate: new Date(),
    enginePower: 12.2,
    type: VehicleType.HOVERBOARD,
    fuelType: FuelType.ELECTRICITY
}
let vech5: IVehicle = {
    id: 5,
    name: "E6-ship",
    coordinates: {
        x: 22,
        y: -5
    },
    creationDate: new Date(),
    enginePower: 12.2,
    type: VehicleType.SHIP,
    fuelType: FuelType.ELECTRICITY
}
export let products: Array<IVehicle> = [vech1, vech2, vech3, vech4, vech5]