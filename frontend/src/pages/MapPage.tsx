import { Header } from "../components/Header"
import { Footer } from "../components/Footer"
import mappng from '../img/map.png'
export function MapPage() {
    return (
        <div className="page">
            <Header />
            <div className="map">
                <img src={mappng} alt="" />
            </div>
            <Footer />
        </div>
    )
}