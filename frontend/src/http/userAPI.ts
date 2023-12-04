import { IVehicle, ICoordinates, FuelType, VehicleType } from "../data/models";
import { $host, $host2 } from "./index"

export const addVeh = async (name: String, coordinates: ICoordinates, enginePower: String, type1: String, type2: String) => {
    console.log(type1)
    const newVeh: IVehicle = {
        name: name.toString(),
        coordinates: coordinates,
        creationDate: new Date(),
        enginePower: parseFloat(enginePower.toString()),
        type: type1 as VehicleType,
        fuelType: type2 as FuelType
    }
    console.log(newVeh)
    let error = null
    const response = await $host.post('vehicles', newVeh)
        .catch((err) => {
            error = err.response.data
        })
    console.log(response)
    console.log(error)
    if (error) {

        return [1, error]
    }
    return [0, response]
}
export const updCurrVeh = async (ids: number | undefined, name: String, coordinates: ICoordinates, enginePower: String, type1: String, type2: String) => {
    console.log(type1)
    const newVeh2: IVehicle = {
        id: ids,
        name: name.toString(),
        coordinates: coordinates,
        creationDate: new Date(),
        enginePower: parseFloat(enginePower.toString()),
        type: type1 as VehicleType,
        fuelType: type2 as FuelType
    }
    console.log(newVeh2)
    let error = null
    const response = await $host.put('vehicles', newVeh2)
        .catch((err) => {
            error = err.response.data
        })
    if (error) {

        return [1, error]
    }
    return [0, response]
}


export const allVeh = async () => {
    const response = await $host.get<IVehicle[]>('vehicles');
    return response
}
export const getVeh = async (id: string | undefined) => {
    const response = await $host.get<IVehicle>("vehicles/" + id);
    return response
}
export const delVeh = async (id: string | undefined) => {
    const response = await $host.delete<IVehicle>("vehicles/" + id);
    return response
}
export const delAllVeh = async (type: string | undefined) => {
    const response = await $host.delete<IVehicle>("vehicles/type/" + type);
    return response
}

export const sumPow = async () => {
    const response = await $host.get("vehicles/engine-power/summ");
    return response
}
export const avPow = async () => {
    const response = await $host.get("vehicles/engine-power/average");
    return response
}


export const searchEng = async (from: string | undefined, to: string | undefined) => {
    const response = await $host2.get<IVehicle[]>('shop/search/by-engine-power/' + from + '/' + to);
    return response
}
export const addWheels = async (id: string | undefined, count: string | undefined) => {
    const response = await $host2.get("shop/add-wheels/" + id + '/' + count);
    return response
} 