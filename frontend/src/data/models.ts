export interface IVehicle {
    readonly id?: number
    name: string
    coordinates: ICoordinates
    creationDate: Date
    enginePower: number
    type: VehicleType
    fuelType: FuelType
}
export interface ICoordinates {
    x: number
    y: number
}
export enum VehicleType {
    HELICOPTER = "HELICOPTER",
    DRONE = "DRONE",
    SHIP = "SHIP",
    MOTORCYCLE = "MOTORCYCLE",
    HOVERBOARD = "HOVERBOARD"
}
export enum FuelType {
    ELECTRICITY = "ELECTRICITY",
    DIESEL = "DIESEL",
    NUCLEAR = "NUCLEAR"
}